package hudelement;

import gameobject.ObjectInfo;
import gameobject.component.type.ComponentType;
import view.gameplay.hud.hudgameplayelement.BarGameplayElement;
import view.gameplay.hud.hudgameplayelement.HUDGameplayElement;

/**
 * represents a BarElement in the HUDConfiguration
 * @author gabrielchen
 *
 */
public class BarElement extends HUDElement{
	private ComponentType myComponent;
	private String myBarColor;
	private String myTextColor;

	public BarElement(double xPos, double yPos, double h, double w, 
			String type, ComponentType c, String barColor, String textColor) {
		super(xPos, yPos, h, w, type);
		myComponent = c;
		myBarColor = barColor;
		myTextColor = textColor;
	}
	
	/**
	 * set the bar color upon user selection
	 * @param color
	 */
	public void setBarColor(String color){
		myBarColor = color;
	}
	
	/**
	 * set the text overlay color on user selection
	 * @param color
	 */
	public void setTextColor(String color){
		myTextColor = color;
	}

	@Override
	public HUDGameplayElement createGameplayInstance(ObjectInfo hero) {
		return new BarGameplayElement(hero, myComponent, this.getXPos(), 
				this.getYPos(), this.getHeight(), getWidth(), this.getType(), myBarColor, myTextColor);
	}

}
