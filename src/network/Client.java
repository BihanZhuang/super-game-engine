// This entire file is part of my masterpiece.
// Bihan Zhuang
package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import gameobject.Template;
import javafx.scene.input.KeyCode;
import model.ChampionPool;

/**
 * Defines the general framework for clients in our project. In a concrete 
 * implementation, the abstract methods here could be defined based on needs.
 * 
 * The most important challenge of building the network was establishing the 
 * initial connection and configure on both host and client side. 
 * 
 * The steps are as follows, and some of them appear in other classes within 
 * the package:
 * 
 * A Server is constantly listening for Client connection on a port. Once a 
 * connection message is sent from a Client to connect, the Server first sends
 * all the choice for champions to the Client. The Client side GameView will 
 * appear, letting the user choose a template he/she likes. This Template is 
 * then sent back to the Server. The Server uses this Template and asks the 
 * backend model to match Client up with an ID. This is the same ID used to do
 * champion key input controls. Then, the Server sends the ID and initial info
 * about all the game objects to the Client. Here after, the connection is 
 * successfully made.
 * 
 * In these network classes, multi-threading was frequently used as such in 
 * ConnectionToServer and ConnectionToClient to allow for sending and receiving
 * to happen at the same time. Concurrent modification of the same object was
 * battled by using the synchronized and volatile key word.  
 * @author Bihan Zhuang
 *
 */
public abstract class Client {
	
	/**
     * A list of the ID numbers of all clients who are currently connected
     * to the hub.  This list is set each time this client is notified that
     * a client has connected to or disconnected from the hub.
     */
	protected int[] connectedPlayerIDs = new int[0];
	
	/**
     * If the autoreset property is set to true, then the ObjectOutputStream
     * that is used for transmitting messages is reset before each object is
     * sent.
     */
	private volatile boolean autoreset;
	private ConnectionToServer myConnectionToServer;
	private ChampionPool myPool;

	public Client(String host, int port) throws IOException {
		myConnectionToServer = new ConnectionToServer(this, host, port);
	}
	
	public ChampionPool getPool() {
		return myPool;
	}
	
	public void setPool(ChampionPool pool) {
		myPool = pool;
	}
	
	/**
     * This method can be called to disconnect cleanly from the server.
     * If the connection is already closed, this method has no effect.
     */
    public void disconnect() {
        if (!myConnectionToServer.getClosed())
        	myConnectionToServer.send(new DisconnectMessage("Goodbye Server"));
    }
    
    /**
     * This method is called to send a message to the Server. This method simply
     * drops the message into a queue of outgoing messages, and it
     * never blocks.
     * @param message A non-null Serializable object representing the message. 
     * @throws IllegalArgumentException if message is null or is not Serializable.
     * @throws IllegalStateException if the connection has already been closed,
     *    either by the disconnect() method, because the Hub has shut down, or
     *    because of a network error.
     */
    public void send(Object message) {
        if (message == null)
            throw new IllegalArgumentException("Null cannot be sent as a message.");
        if (! (message instanceof Serializable))
            throw new IllegalArgumentException("Messages must implement the Serializable interface.");
        if (myConnectionToServer.getClosed())
            throw new IllegalStateException("Message cannot be sent because the connection is closed.");
        myConnectionToServer.send(message);
    }
    
    /**
     * Returns the ID number of this client, which is assigned by the hub when
     * the connection to the hub is created.
     */
    public int getID() {
        return myConnectionToServer.getClientID();
    }
    
    /**
     * Resets the output stream, after any messages currently in the output queue
     * have been sent.
     */
    public void resetOutput() {
    	myConnectionToServer.send(new ResetSignal()); 
    }
    
    /**
     * If the autoreset property is set to true, then the output stream will be reset
     * before every object transmission.
     */
    public void setAutoreset(boolean auto) {
        autoreset = auto;
    }
    
    /**
     * Returns the value of the autoreset property.
     */
    public boolean getAutoreset() {
        return autoreset;
    }
	
	/**
     *  This method is called when a message is received from the Server.
     */
	protected abstract void messageReceived(Object obj);
    
    /**
     * This method is called when the connection to the Server is closed down
     * because of some error.
     */
    protected abstract void connectionClosedByError(String message);
    
    protected void extraHandshake(ObjectInputStream in, ObjectOutputStream out) 
            throws IOException {}
    
    /**
     * This method is called when the connection to the Server is closed down
     * because the server is shutting down normally.
     */
    protected abstract void serverShutdown(String message);
    
    protected abstract void handleKeyPressed(KeyCode code) throws IOException;
    
    protected abstract void handleKeyReleased(KeyCode code) throws IOException;
    
    /**
     * This method is called when user on client side chooses a champion.
     * @param pool
     * @return A Template representing the champion the user chose.
     */
    protected abstract Template chooseChampion(ChampionPool pool);

}
