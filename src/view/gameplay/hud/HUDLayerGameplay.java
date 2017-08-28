package view.gameplay.hud;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import gameobject.ObjectInfo;
import gameobject.component.type.ComponentTypes;
import goalstrategies.IGoal;
import hudelement.BarElement;
import hudelement.HUDElement;
import hudelement.StaticDecorativeImageElement;
import hudelement.TextElement;
import javafx.scene.layout.Pane;
import model.GameInfo;
import model.GameStats;
import model.HUDConfiguration;
import util.Observer;
import view.gameplay.hud.hudgameplayelement.HUDGameplayElement;

/**
 * layer that holds the visual hud elements that are used in the gameview
 * @author gabrielchen
 *
 */
public class HUDLayerGameplay implements Observer<HUDElement>{
	private Pane myPane;
	private ObjectInfo myHero;
	private Map<HUDElement, HUDGameplayElement> myHUDElements;
	private HUDConfiguration myHUDConfig;

	
	public HUDLayerGameplay(ObjectInfo hero, HUDConfiguration hudConfig){
		myHero = hero;
		myPane = new Pane();
		myHUDConfig = hudConfig;
		myHUDElements = new HashMap<>();
		initializeHUD();
//		intializeHUD2();
	}
	
	private void intializeHUD2(){
		if(myHUDConfig != null){
			Iterator<HUDElement> i = myHUDConfig.iterator();
			while(i.hasNext()){
				HUDElement curr = i.next();
				myHUDElements.put(curr, curr.createGameplayInstance(myHero));
			}
		}
	}
	
	private void initializeHUD(){
		//HEALTH BAR
		BarElement bar = new BarElement(40, 40, 30, 100, "Health", ComponentTypes.HEALTH,
				"#FF0000", "#FFFFFF");
		HUDGameplayElement b = bar.createGameplayInstance(myHero);
		myPane.getChildren().add(b.getPane());
		myHUDElements.put(bar, b);
		
		//SCORE
		TextElement score = new TextElement(170, 20, 100, 100, "Score", "#351184", 16);
		HUDGameplayElement s = score.createGameplayInstance(myHero);
		myPane.getChildren().add(s.getPane());
		myHUDElements.put(score, s);
		
		//LEVEL
		TextElement level = new TextElement(170, 40, 100, 100, "Level", "#351184", 16);
		HUDGameplayElement l = level.createGameplayInstance(myHero);
		myPane.getChildren().add(l.getPane());
		myHUDElements.put(level, l);
		
		//GOALS
		TextElement goal = new TextElement(150, 60, 60, 100, "Goal", "#351184", 12);
		HUDGameplayElement g = goal.createGameplayInstance(myHero);
		myPane.getChildren().add(g.getPane());
		myHUDElements.put(goal, g);

	}
	
	private void testHUD(){
		HUDElement image = new StaticDecorativeImageElement(200, 200, 300, 500, "Hurt.png", "Image");
		HUDGameplayElement i = image.createGameplayInstance(null);
		myPane.getChildren().add(i.getPane());
	}

	/*
	 * updates when new HUDElements are added to the hud manager
	 */
	@Override
	public void update(HUDElement arg) {
		if(arg.expired()){
			myPane.getChildren().remove(myHUDElements.get(arg).getPane());
		} else {
			addHUDElement(arg);
		}
			// may or may not need this
//		if(!myHUDElements.containsKey(arg)){
//			addHUDElement(arg);
//		}
	}
	
	/**
	 * call this to keep the HUD up to date with the Gameplay
	 */
	public void updateWithGame(GameInfo info, GameStats stats, IGoal goal){
		for(HUDGameplayElement h : myHUDElements.values()){
			h.update(info, stats, goal);
		}
	}
	
	public Pane getPane(){
		return myPane;
	}
	
	private void addHUDElement(HUDElement elem){
		HUDGameplayElement hudPiece = elem.createGameplayInstance(myHero);
		myHUDElements.put(elem, hudPiece);
		myPane.getChildren().add(hudPiece.getPane());
	}
	
}
