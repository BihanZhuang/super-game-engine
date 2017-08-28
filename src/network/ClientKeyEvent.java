package network;

import java.io.Serializable;

import javafx.scene.input.KeyCode;

@SuppressWarnings("serial")
public class ClientKeyEvent implements Serializable {
    
    public enum Type {
        PRESS,
        RELEASE
    }
	
	private KeyCode myCode;
	private int myID;
	private Type myType;

	public ClientKeyEvent(KeyCode code, int id, Type type) {
		myCode = code;
		myID = id;
		myType = type;
	}

	public KeyCode getCode() {
		return myCode;
	}

	public int getID() {
		return myID;
	}
	
	public Type getType() {
	    return myType;
	}
	
}
