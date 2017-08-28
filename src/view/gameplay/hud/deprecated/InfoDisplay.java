package view.gameplay.hud.deprecated;

import javafx.scene.layout.Pane;

public abstract class InfoDisplay {
	/*
	 * Displays game info such as
	 * 		- health bar
	 * 		- score bar
	 * 		- stats
	 * 		- etc
	 */
	
	Pane myPane;
	
	public InfoDisplay(){
		myPane = new Pane();
	}
		
//	public abstract void displayInfo();
//
//	public abstract void setValue(double value);
	
	public abstract void updateValues();
	
	/*
	 * set the position of the info display based on values given by the user.
	 */
	public void setPos(double x, double y){
		myPane.setLayoutX(x);
		myPane.setLayoutY(y);
	};
	
	public Pane getPane(){
		return myPane;
	}
}
