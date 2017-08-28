package view.gameplay.hud.deprecated;

import gameobject.ObjectInfo;
import gameobject.component.Health;
import gameobject.component.type.ComponentTypes;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import view.gameplay.hud.hudgameplayelement.pieces.Bar;

public class HealthInfoDisplay extends InfoDisplay{

	private ObjectInfo myUser;
	private double myMaxHealth, myCurrHealth, myBarLength, myXPos, myYPos;
	private Color myBarColor, myTextColor;

	
	private Bar myHealthBar;
	private Health myHealthData;
	
	private Pane healthInfoDisplayPane;
	
	/*
	 * TODO Create a display for the health with a few things
	 * 1. a number display to display Curr/Max
	 * 2. a visual bar display for displaying the actual health
	 * 
	 * Need a ActorType Component to
	 */

	public HealthInfoDisplay(double barMaxLength, ObjectInfo user, Color barColor, Color textColor) {

		super();
		myUser = user;
		myHealthData = myUser.getComponent(ComponentTypes.HEALTH);
		myMaxHealth = myHealthData.getMaxHealth();
		myCurrHealth = myHealthData.getCurrentHealth();
		
		myBarLength = barMaxLength;
		myBarColor = barColor;
		myTextColor = textColor;
		
		healthInfoDisplayPane = getPane();
		
		setupHealthBar();
	}
	
	/*
	 * sets up the health bar and adds it to the HealthInfoDisplay pane
	 */
	private void setupHealthBar(){
//		myHealthBar = new Bar(myBarLength, 100, 100, myBarColor, myTextColor);

//		/myHealthBar = new Bar(myBarLength, myMaxHealth, myCurrHealth, myBarColor, myTextColor);
		healthInfoDisplayPane.getChildren().add(myHealthBar.getBarPane());
	}

	
	/*
	 * Update the values by checking the values in the Health Component of the IGameObject
	 */
	@Override
	public void updateValues(){
		myMaxHealth = myHealthData.getMaxHealth();
		myCurrHealth = myHealthData.getCurrentHealth();
		
		myHealthBar.updateBarMax(myMaxHealth);
		myHealthBar.updateCurrBarValue(myCurrHealth);
	}

}
