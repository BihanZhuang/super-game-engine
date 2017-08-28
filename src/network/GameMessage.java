package network;

import java.io.Serializable;

public class GameMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Object myContent;
	private ConnectionToClient myGameClient;

	public GameMessage(Object content, ConnectionToClient gc) {
		myContent = content;
		myGameClient = gc;
	}
	
	public ConnectionToClient getClient() {
		return myGameClient;
	}
	
	public Object getContent() {
		return myContent;
	}

}
