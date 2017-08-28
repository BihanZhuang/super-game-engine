package gameobject.basics;

import engine.physicsStrategies.BrickStrategy;
import gameobject.Basics;
import gameobject.basics.CollisionStrategy.InvulnerableStrategy;
import gameobject.component.BonusScore;
import gameobject.component.Collider;
import gameobject.component.Vector2D;
import gameobject.component.type.ComponentTypes;

@SuppressWarnings("serial")
public class Stone extends Basics {
	public Stone(String imagePath) {
		super("Stone", "Obstacle", imagePath, "");
		this.addComponents(
				new Vector2D(ComponentTypes.VELOCITY), 
				new Collider(),

				new BonusScore(1000));
		
		this.getComponent(ComponentTypes.MASS).setValue(Double.POSITIVE_INFINITY);
		this.getComponent(ComponentTypes.COLLIDE).add(new InvulnerableStrategy());
		this.getComponent(ComponentTypes.PHYSICS_BEHAVIOR).setStrategy(new BrickStrategy());
	}
}
