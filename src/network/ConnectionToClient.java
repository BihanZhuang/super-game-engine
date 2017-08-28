package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import model.ChampionPool;

/**
 * This class works in conjunction with Server to create its connection
 * to the clients.
 * @author Bihan Zhuang
 *
 */
class ConnectionToClient {
	
	public static final int CAPACITY = 2000;

    private int myClientID;
    private BlockingDeque<Object> incomingMessages;
    private LinkedBlockingDeque<Object> outgoingMessages;
    private Socket connection;
    private Server server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private volatile boolean closed;
    private Thread sendThread;
    private volatile Thread receiveThread; 
    private ChampionPool myPool;

    public ConnectionToClient(BlockingDeque<Object> receivedMessageDeque, Socket connection, Server server) {
        this.connection = connection;
        incomingMessages = receivedMessageDeque;
        outgoingMessages = new LinkedBlockingDeque<>();
        this.server = server;
        sendThread = new SendThread();
        sendThread.start();
    }

    public int getClientID() {
        return myClientID;
    }

    public void setClientID(int id) {
        myClientID = id;
    }

    public void close() {
        closed = true;
        sendThread.interrupt();
        if (receiveThread != null)
            receiveThread.interrupt();
        try {
            connection.close();
        } catch (IOException e) {
        }
    }

    public void send(Object obj) {
        if (obj instanceof DisconnectMessage) {
            outgoingMessages.clear();
        }
        if(outgoingMessages.size() > CAPACITY) {
            outgoingMessages.clear();
        }
        outgoingMessages.add(obj);
    }

    private void closedWithError(String message) {
        server.connectionToClientClosedWithError(this, message);
        close();
    }

    /**
     * Handles the "handshake" that occurs before the connection is opened. Once
     * that's done, it creates a thread for receiving incoming messages, and
     * goes into an infinite loop in which it transmits outgoing messages.
     */
    private class SendThread extends Thread {
        
        @Override
        public void run() {
            try {
                out = new ObjectOutputStream(connection.getOutputStream());
                in = new ObjectInputStream(connection.getInputStream());
                String handle = (String) in.readObject(); // first input must be
                                                          // "Hello Server"
                if (!"Hello Server".equals(handle))
                    throw new Exception("Incorrect hello string received from client.");
                synchronized (server) {
                    myPool = server.getPool();
                }
                // Step 1: send pool to the client.
                out.writeObject(myPool);
                out.flush();
                // Step4: send assigned id to client, send corresponding
                // ObjectInfo of champion to client
                server.extraHandshake(ConnectionToClient.this, in, out);
                receiveThread = new ReceiveThread();
                receiveThread.start();
            } catch (Exception e) {
                try {
                    closed = true;
                    connection.close();
                } catch (Exception e1) {
                }
                System.out.println("\nError while setting up connection: " + e);
                e.printStackTrace();
                return;
            }
            try {
                while (!closed) {
                    try {
                        Object message = outgoingMessages.take();
                        if (message instanceof ResetSignal)
                            out.reset();
                        else {
                            if (server.autoreset()) {
                                out.reset();
                            }
                            out.writeObject(message);
                            out.flush();
                            if (message instanceof DisconnectMessage) {
                                close();
                            }
                        }
                    } catch (InterruptedException e) {}
                }
            } catch (IOException e) {
                if (!closed) {
                    closedWithError("Error while sending data to client.");
                    System.out.println("Server send thread terminated by IOException: " + e);
                }
            } catch (Exception e) {
                if (!closed) {
                    closedWithError("Internal Error: Unexpected exception in output thread: " + e);
                    System.out.println("\nUnexpected error shuts down Server's send thread:");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * The ReceiveThread reads messages transmitted from the client. If a DisconnectMessage
     * is received, however, it is a signal from the client that the client is disconnecting.
     */
    private class ReceiveThread extends Thread {
        public void run() {
            try {
                while (!closed) {
                    Object message = in.readObject();
					if (!(message instanceof DisconnectMessage)) {
						server.messageReceived(message);
					} else {
					    closed = true;
					    outgoingMessages.clear();
					    out.writeObject("*goodbye*");
					    out.flush();
					    server.clientDisconnected(myClientID);
					    close();
					}
                }
            } catch (IOException e) {
                if (!closed) {
                    closedWithError("Error while reading data from client.");
                    System.out.println("Server receive thread terminated by IOException: " + e);
                }
            } catch (Exception e) {
                if (!closed) {
                    closedWithError("Internal Error: Unexpected exception in input thread: " + e);
                    System.out.println("\nUnexpected error shuts down Server's receive thread:");
                    e.printStackTrace();
                }
            }
        }
    }

}
