package hudelement;

import gameobject.ObjectInfo;
import view.gameplay.hud.hudgameplayelement.HUDGameplayElement;
import view.gameplay.hud.hudgameplayelement.StaticDecorativeImageGameplayElement;

public class StaticDecorativeImageElement extends HUDElement{
	private String myImagePath;

	public StaticDecorativeImageElement(double xPos, double yPos, double h, double w, 
			String imagePath, String type) {
		super(xPos, yPos, h, w, type);
		myImagePath = imagePath;
	}
	
	public void setImage(String imagePath){
		myImagePath = imagePath;
	}


	@Override
	public HUDGameplayElement createGameplayInstance(ObjectInfo hero) {
		return new StaticDecorativeImageGameplayElement(myImagePath, getXPos(), 
				getYPos(), getHeight(), getWidth(), "");
	}
	
}
