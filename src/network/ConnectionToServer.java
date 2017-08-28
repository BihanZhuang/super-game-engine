package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;

import model.ChampionPool;

/**
 * This class works in conjunction with Client in order to establish
 * connection with the Server.
 * @author Bihan Zhuang
 *
 */
class ConnectionToServer {

    private int clientID;
    private Socket socket;
    private Client client;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private SendThread sendThread;
    private ReceiveThread receiveThread;
    private LinkedBlockingDeque<Object> outgoingMessages;
    private volatile boolean closed;

    public ConnectionToServer(Client client, String host, int port) throws IOException {
        this.client = client;
        outgoingMessages = new LinkedBlockingDeque<Object>();
        socket = new Socket(host, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject("Hello Server");
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());
        try {
            // Step2: get championpool from server
        	ChampionPool pool = (ChampionPool) in.readObject();
            client.setPool(pool);
            out.flush();
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("Illegal response from server.");
        }
        // Step5: get id and objectinfo from server
        this.client.extraHandshake(in, out);
        sendThread = new SendThread();
        receiveThread = new ReceiveThread();
        sendThread.start();
        receiveThread.start();
    }

    /**
     * This method is called to close the connection. 
     */
    public void close() {
        closed = true;
        sendThread.interrupt();
        receiveThread.interrupt();
        try {
            socket.close();
        } catch (IOException e) {
        }
    }

    /**
     * This method is called to transmit a message to the Server.
     * 
     * @param message must be Serializable.
     */
    public void send(Object message) {
        outgoingMessages.add(message);
    }

    /**
     * This method is called by the threads that do input and output on the
     * connection when an IOException occurs.
     */
    synchronized void closedByError(String message) {
        if (!closed) {
            client.connectionClosedByError(message);
            close();
        }
    }

    public int getClientID() {
        return clientID;
    }

    public boolean getClosed() {
        return closed;
    }

    /**
     * This class defines a thread that sends messages to the server.
     */
    private class SendThread extends Thread {
        public void run() {
            System.out.println("Client send thread started.");
            try {
                while (!closed) {
                    Object message = outgoingMessages.take();
                    if (message instanceof ResetSignal) {
                        out.reset();
                    } else {
                        if (client.getAutoreset()) {
                            out.reset();
                        }
                        out.writeObject(message);
                        out.flush();
                        if (message instanceof DisconnectMessage) {
                            close();
                        }
                    }
                }
            } catch (IOException e) {
                if (!closed) {
                    closedByError("IO error occurred while trying to send message.");
                    System.out.println("Client send thread terminated by IOException: " + e);
                    e.printStackTrace();
                }
            } catch (Exception e) {
                if (!closed) {
                    closedByError("Unexpected internal error in send thread: " + e);
                    System.out.println("\nUnexpected error shuts down client send thread:");
                    e.printStackTrace();
                }
            } finally {
                System.out.println("Client send thread terminated.");
            }
        }
    }

    /**
     * This class defines a thread that reads messages from the Server.
     */
    private class ReceiveThread extends Thread {
        public void run() {
            System.out.println("Client receive thread started.");
            try {
                while (!closed) {
                    Object obj = in.readObject();
                    if (obj instanceof DisconnectMessage) {
                        close();
                        client.serverShutdown(((DisconnectMessage) obj).message);
                    } else {
                        client.messageReceived(obj);
                    }
                }
            } catch (IOException e) {
                if (!closed) {
                    closedByError("IO error occurred while waiting to receive  message.");
                    System.out.println("Client receive thread terminated by IOException: " + e);
                    e.printStackTrace();
                }
            } catch (Exception e) {
                if (!closed) {
                    closedByError("Unexpected internal error in receive thread: " + e);
                    System.out.println("\nUnexpected error shuts down client receive thread:");
                    e.printStackTrace();
                }
            } finally {
                System.out.println("Client receive thread terminated.");
            }
        }
    }

}
