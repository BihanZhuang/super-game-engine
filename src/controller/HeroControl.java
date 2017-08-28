package controller;

import javafx.scene.input.KeyCode;

public interface HeroControl {
	
	void onKeyPressed(KeyCode code);
    
    void onKeyReleased(KeyCode code);

}
