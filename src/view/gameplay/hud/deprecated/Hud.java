package view.gameplay.hud.deprecated;

import java.util.ArrayList;

import gameobject.ObjectInfo;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Hud {
    
	private Pane hudLayerPane;
	private ArrayList<InfoDisplay> myInfoDisplays;
	/*
	 * This class manages a Pane that will be a layer above the game.
	 * The purpose of this Pane layer is to manage all of the info displays.
	 */
	ObjectInfo myMainCharacter;

	public Hud (ObjectInfo mainCharacter){
		myInfoDisplays = new ArrayList<InfoDisplay>();
		myMainCharacter = mainCharacter;
		hudLayerPane = new Pane();
		createInfoDisplay("hi", 20, 20);
	}
	
	public Pane getHudPane(){
		return hudLayerPane;
	}
	
	/*
	 * TODO:
	 * using reflection to make infoViews based on the types specified by the user.
	 */
	public void createInfoDisplay(String infoViewType, double xPos, double yPos){
//		HealthInfoDisplay h = new HealthInfoDisplay(140, Color.RED, Color.WHITE);

//		hudLayerPane.getChildren().add(h.getPane());
		HealthInfoDisplay h = new HealthInfoDisplay(200, myMainCharacter, Color.RED, Color.TRANSPARENT);
		myInfoDisplays.add(h);
		h.getPane().setLayoutX(xPos);
		h.getPane().setLayoutY(yPos);
		hudLayerPane.getChildren().add(h.getPane());
	}
	
	/*
	 * iterate through all the info displays and update their values
	 */
	public void updateAllInfoViews(){
		for (InfoDisplay i : myInfoDisplays){
			i.updateValues();
		}
	}
	
}
