package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;

import controller.ServerControl;
import gameobject.ObjectInfo;
import gameobject.Template;
import model.ChampionPool;
import model.IGame;
import network.ClientKeyEvent.Type;

/**
 * Server class that creates the game service by possessing part of the backend
 * model. For full description of the network classes, please refer to the top
 * of Client.java. 
 * @author Bihan Zhuang
 *
 */
public class Server {

	/**
     *  A map that associates player IDs with the connections to each player.
     */
	private Map<Integer, ConnectionToClient> playerConnections;
	
	/**
     * A queue of messages received from clients.  
     */
    private LinkedBlockingDeque<Object> incomingMessages;
    private volatile boolean autoreset;
    private volatile boolean shutdown; 
    private ServerSocket serverSocket;  
    private Thread serverThread;    
    private ChampionPool myPool;
    private IGame myGame;
    private ServerControl myController;

	public Server(int port, IGame game, ServerControl controller) throws IOException {
	    myGame = game;
	    myController = controller;
		playerConnections = new HashMap<>();
		incomingMessages = new LinkedBlockingDeque<>();
		serverSocket = new ServerSocket(port);
        System.out.println("Listening for client connections on port " + port);
        serverThread = new ServerThread();
        serverThread.start();
        Thread readerThread = new Thread(){
            public void run() {
               while (true) {
                   try {
                       incomingMessages.take();
                   }
                   catch (Exception e) {
                       System.out.println("Exception while handling received message:");
                       e.printStackTrace();
                   }
               }
           }
        };
        readerThread.setDaemon(true);
        readerThread.start();
	}
	
	public void getPoolFromGame(ChampionPool pool) {
		this.myPool = pool;
	}
	
	public ChampionPool getPool() {
		return myPool;
	}
	
	/**
     * This method is called when a message is received from one of the 
     * connected players.
     * @param playerID  The ID number of the player who sent the message.
     * @param message The message that was received from the player.
     */
    protected void messageReceived(int clientID, Object message) {
//        sendToAll(new Message(clientID, message));
    }
    
    /**
     * This method is called just after a player has connected.
     * @param playerID the ID number of the new player.
     */
    protected void playerConnected(int clientID) {
    }
    
    /**
     * This method is called just after a player has disconnected.
     * @param playerID the ID number of the new player.
     */
    protected void playerDisconnected(int clientID) {
    }
    
    /**
     * Gets a list of ID numbers of currently connected clients.
     * The array is newly created each time this method is called.
     */
    synchronized public int[] getClientIDList() {
        int[] players = new int[playerConnections.size()];
        int i = 0;
        for (int p : playerConnections.keySet())
            players[i++] = p;
        return players;
    }
    
    /**
     * Stops listening, without disconnecting any currently connected clients.
     */
    public void shutdownServerSocket() {
        if (serverThread == null)
            return;
        incomingMessages.clear();
        shutdown = true;
        try {
            serverSocket.close();
        }
        catch (IOException e) {
        }
        serverThread = null;
        serverSocket = null;
    }
    
    /**
     * Restarts listening and accepting new clients.  This would only be used if
     * the shutDownServer() method has been called previously.
     * @param port the port on which the server should listen.
     * @throws IOException if it is impossible to create a listening socket on the specified port.
     */
    public void restartServer(int port) throws IOException {
        if (serverThread != null && serverThread.isAlive())
            throw new IllegalStateException("Server is already listening for connections.");
        shutdown = false;
        serverSocket = new ServerSocket(port);
        serverThread = new ServerThread();
        serverThread.start();
    }
    
    /**
     *  Disconnects all currently connected clients and stops accepting new client
     *  requests.
     */
    public void shutDownServer() {
        shutdownServerSocket();
        sendToAll(new DisconnectMessage("*shutdown*"));
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
        }
        for (ConnectionToClient pc : playerConnections.values())
            pc.close();
    }
    
    public boolean autoreset() {
    	return autoreset = true;
    }
    
    /**
     * Sends a specified non-null Object as a message to ALL connected client.
     * @param message must be Serializable.
     */
    synchronized public void sendToAll(Object message) {
        if (message == null) {
            throw new IllegalArgumentException("Null cannot be sent as a message.");
        }
        if ( ! (message instanceof Serializable) ) {
            throw new IllegalArgumentException("Messages must implement the Serializable interface.");
        }
        for (ConnectionToClient pc : playerConnections.values()) {
            pc.send(message);
        }
    }
    
    /**
     * Sends a specified non-null Object as a message to one connected client.
     * @param recipientID The ID number of the player to whom the message is
     * to be sent.  If there is no such player, then the method returns the 
     * value false.
     * @param message must be Serializable.
     */
    synchronized public boolean sendToOne(int recipientID, Object message) {
        if (message == null)
            throw new IllegalArgumentException("Null cannot be sent as a message.");
        if ( ! (message instanceof Serializable) )
            throw new IllegalArgumentException("Messages must implement the Serializable interface.");
        ConnectionToClient pc = playerConnections.get(recipientID);
        if (pc == null)
            return false;
        else {
            pc.send(message);
            return true;
        }
    }
    
    protected void extraHandshake(ConnectionToClient connection, ObjectInputStream in, 
            ObjectOutputStream out) throws IOException {
    	try {
			Object response = in.readObject();
			int clientID = myGame.connect((Template)response);
			// put clientid/connection mapping into the MAP.
			acceptConnection(connection, clientID);
			out.writeObject(clientID);
			out.writeObject(myGame.getChampion(clientID));
			out.writeObject(myGame.getWorld().getBoundary());
			out.flush();
			update(out);
		} catch (ClassNotFoundException e) {
			throw new IOException("Illegal response from this client.");
		}
    }
    
    /**
     * Obtain updated game object information from the host backend and send to clients.
     * @param out
     * @throws IOException
     */
    synchronized protected void update(ObjectOutputStream out) throws IOException {
    	for(ObjectInfo obj: myGame.getWorld()) {
			out.writeObject(obj);
			out.flush();
		}
    }
    
	synchronized protected void messageReceived(Object message) {
		if(message instanceof ClientKeyEvent) {
			ClientKeyEvent event = (ClientKeyEvent)message;
			if(event.getType() == Type.PRESS) {
			    myController.handleClientKeyPressed(event.getID(), event.getCode());
			}
			else if(event.getType() == Type.RELEASE) {
			    myController.handleClientKeyReleased(event.getID(), event.getCode());
			}
		}
	}

	synchronized void acceptConnection(ConnectionToClient newConnection, int clientID) {
        playerConnections.put(clientID, newConnection);
        StatusMessage sm = new StatusMessage(clientID, true, getClientIDList());
        sendToAll(sm);
        playerConnected(clientID);
        System.out.println("Connection accepted from client number " + clientID);
    }
	
	synchronized void clientDisconnected(int clientID) {
        if (playerConnections.containsKey(clientID)) {
            playerConnections.remove(clientID);
            StatusMessage sm = new StatusMessage(clientID, false, getClientIDList());
            sendToAll(sm);
            playerDisconnected(clientID);
            System.out.println("Connection with client number " + clientID + " closed by DisconnectMessage from client.");
        }
    }
	
	synchronized void connectionToClientClosedWithError( ConnectionToClient playerConnection, String message ) {
        int ID = playerConnection.getClientID();
        if (playerConnections.remove(ID) != null) {
            StatusMessage sm = new StatusMessage(ID, false, getClientIDList());
            sendToAll(sm);
        }
    }
	
	// Listens for connection requests from clients.
	private class ServerThread extends Thread {  
        public void run() {
            try {
                while (!shutdown) {
                    Socket connection = serverSocket.accept();
                    if (shutdown) {
                        System.out.println("Listener socket has shut down.");
                        break;
                    }
                    new ConnectionToClient(incomingMessages, connection, Server.this);
                }
            }
            catch (Exception e) {
                if (shutdown) {
                    System.out.println("Listener socket has shut down.");
                } else {
                    System.out.println("Listener socket has been shut down by error: " + e);
                }
            }
        }
	}
}
