package gameobject.basics;

import gameobject.component.type.ComponentTypes;

@SuppressWarnings("serial")
public class FireflowerBrick extends PowerupBrick {
	public FireflowerBrick(String imagePath) {
		super(imagePath);
		this.getComponent(ComponentTypes.GENERATOR).setTemplate(new Fireflower("FireFlower.png").newInstance(0));
	}
}
