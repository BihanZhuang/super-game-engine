package gameobject.basics;

import engine.physicsStrategies.NullVelocityStrategy;
import gameobject.Basics;
import gameobject.basics.CollisionStrategy.ItemStrategy;
import gameobject.component.Animation;
import gameobject.component.BonusScore;
import gameobject.component.Collider;
import gameobject.component.type.ComponentTypes;

@SuppressWarnings("serial")
public class Fireflower extends Basics {
	public Fireflower(String imagePath) {
		super("Fireflower","",imagePath, "");
		this.getComponent(ComponentTypes.PHYSICS_BEHAVIOR).setStrategy(new NullVelocityStrategy(true));
		this.addComponents(new Animation(), new Collider(), new BonusScore());
		Collider c = this.getComponent(ComponentTypes.COLLIDE);
		c.add(new ItemStrategy("Mario3.png",0));
	}
}
