package gameobject.basics;

import engine.physicsStrategies.CharacterStrategy;
import gameobject.Basics;
import gameobject.component.Animation;
import gameobject.component.Belongings;
import gameobject.component.Collider;
import gameobject.component.Vector2D;
import gameobject.component.type.ComponentTypes;

@SuppressWarnings("serial")
public class Character extends Basics {

	public Character(String name, String type, String imagePath) {
	    super(name, type, imagePath, "");
		addComponents(
				new Vector2D(ComponentTypes.VELOCITY),
				new Vector2D(ComponentTypes.ACCELERATION),
				new Belongings(),
				new Animation(), 
				new Collider()
				);	
		this.getComponent(ComponentTypes.PHYSICS_BEHAVIOR).setStrategy(new CharacterStrategy(true, true, true));
	}

}
