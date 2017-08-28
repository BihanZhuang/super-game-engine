package hudelement;

import gameobject.ObjectInfo;
import view.gameplay.hud.hudgameplayelement.HUDGameplayElement;

/**
 * Text element
 * @author Yanbo Fang
 *
 */
public class TextElement extends HUDElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String HUD_GAMEPLAY_PACKAGE = "view.gameplay.hud.hudgameplayelement.";

	private String myTextColor;
	private int myTextSize;

	public TextElement(double xPos, double yPos, double h, double w, String type, String textColor, int textSize) {
		super(xPos, yPos, h, w, type);
		myTextColor = textColor;
		myTextSize = textSize;
	}

	@Override
	public HUDGameplayElement createGameplayInstance(ObjectInfo hero) {
		try {
			Class<?> clazz = Class.forName(HUD_GAMEPLAY_PACKAGE + this.getType() + "GameplayElement");
			return (HUDGameplayElement) clazz.getDeclaredConstructor(gameobject.ObjectInfo.class, double.class,
					double.class, double.class, double.class, java.lang.String.class, java.lang.String.class, int.class)
					.newInstance(hero, this.getXPos(), this.getYPos(), this.getHeight(), this.getWidth(),
							this.getType(), myTextColor, myTextSize);
		} catch (Exception e) {
			return null;
		}
	}

}
