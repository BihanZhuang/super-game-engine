package view.gameplay.hud.hudgameplayelement;

import gameobject.ObjectInfo;
import gameobject.component.MaxAndCurrComponent;
import gameobject.component.type.ComponentType;
import goalstrategies.IGoal;
import javafx.scene.layout.Pane;
import model.GameInfo;
import model.GameStats;
import view.gameplay.hud.hudgameplayelement.pieces.Bar;

/**
 * Hud element of type bar. Bar displays information based on the selected component
 * @author gabrielchen
 *
 */
public class BarGameplayElement extends HUDGameplayElement{
	private Pane myPane;
	private Bar myBar;
	private ObjectInfo myHero;
//	private ComponentType myComponentType;
	private MaxAndCurrComponent myComponent;
	

	public BarGameplayElement(ObjectInfo hero, ComponentType comp, double xPos, double yPos, double height, double width, String type, String barColor, String textColor){
		super(hero, type);
		myPane = getPane();
		myHero = getHero();		
		myComponent = (MaxAndCurrComponent) myHero.getComponent(comp);
		
		//TODO Change these hard coded values to actual values;
//		setUpBar(width, height, 100, 90, barColor, textColor);
		setUpBar(width, height, myComponent.getMaxValue(), myComponent.getCurrValue(), barColor, textColor);
		setPos(xPos, yPos);
		
	}
	
	/**
	 * sets up the bar
	 */
	private void setUpBar(double maxWidth, double height, double barMaxValue, double barCurrValue, String barColor, String textColor){
		myBar = new Bar(maxWidth, height, barMaxValue, barCurrValue, barColor, textColor);
		
		//add bar to BarGamePlayElement Pane
		myPane.getChildren().add(myBar.getBarPane());
	}
	
	/**
	 * update the values and display of the bar
	 * ... the max and the curr of the bar's values
	 */
	@Override
	public void update(GameInfo info, GameStats stats, IGoal goal) {
		myBar.updateBarMax(myComponent.getMaxValue());
		myBar.updateCurrBarValue(myComponent.getCurrValue());		
	}

}
