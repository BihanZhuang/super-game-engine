package view.gameplay.hud.hudgameplayelement;

import gameobject.ObjectInfo;
import goalstrategies.IGoal;
import javafx.scene.layout.Pane;
import model.GameInfo;
import model.GameStats;

/**
 * Represents a HUDGameplay Element, displays game info such as
 * 		- health bar
 * 		- score bar
 * 		- stats
 * 		- etc
 * When in Player Mode
 */
public abstract class HUDGameplayElement {
	private Pane myPane;
	private ObjectInfo myHero;
	private String myType;
	
	public HUDGameplayElement(ObjectInfo hero, String type){
		myPane = new Pane();
		myHero = hero;
		myType = type;
	}
	
	public abstract void update(GameInfo info, GameStats stats, IGoal goal);
	
	/*
	 * set the position of the info display based on values given by the user.
	 */
	protected void setPos(double x, double y){
		myPane.setLayoutX(x);
		myPane.setLayoutY(y);
	}
	
	/**
	 * returns the hero that this hud element belongs to
	 * @return
	 */
	protected ObjectInfo getHero(){
		return myHero;
	}
	
	
	public Pane getPane(){
		return myPane;
	}
	
	protected String getType(){
		return myType;
	}
}
