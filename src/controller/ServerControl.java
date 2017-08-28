package controller;

import javafx.scene.input.KeyCode;

public interface ServerControl {
	
	void handleClientKeyPressed(int id, KeyCode code);
	
	void handleClientKeyReleased(int id, KeyCode code);
	
}
