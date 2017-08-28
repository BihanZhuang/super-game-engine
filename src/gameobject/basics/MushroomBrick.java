package gameobject.basics;

import gameobject.IGameObject;
import gameobject.component.type.ComponentTypes;

@SuppressWarnings("serial")
public class MushroomBrick extends PowerupBrick {

	public MushroomBrick(String imagePath) {
		super(imagePath);
		// hardcoded for nows
		IGameObject obj = new Mushroom("Mushroom.png").newInstance(0);
		obj.getComponent(ComponentTypes.AI).setLeft(200);
		obj.getComponent(ComponentTypes.AI).setRight(200);
		this.getComponent(ComponentTypes.GENERATOR).setTemplate(obj);
	}
	
	

}
