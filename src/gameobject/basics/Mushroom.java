package gameobject.basics;

import gameobject.Basics;
import gameobject.basics.CollisionStrategy.ItemStrategy;
import gameobject.component.AI;
import gameobject.component.BonusScore;
import gameobject.component.Collider;
import gameobject.component.Vector2D;
import gameobject.component.type.ComponentTypes;

@SuppressWarnings("serial")
public class Mushroom extends Basics {
    
	public Mushroom(String imagePath) {
		super("Mushroom", "Powerup", imagePath, "");
		this.addComponents(
				new Collider(), 
				new BonusScore(), 
				new Vector2D(ComponentTypes.VELOCITY),
				new AI(ComponentTypes.AI)
				);
		this.getComponent(ComponentTypes.VELOCITY).setX(100);
		Collider c = this.getComponent(ComponentTypes.COLLIDE);
		c.add(new ItemStrategy("levimove.gif", 0));
//		this.getComponent(ComponentTypes.PHYSICS_BEHAVIOR).setStrategy(new CharacterStrategy(true, false));

	}

}
