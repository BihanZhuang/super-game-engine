package gameobject.basics;

import engine.physicsStrategies.BrickStrategy;
import gameobject.Basics;
import gameobject.IGameObject;
import gameobject.component.BonusScore;
import gameobject.component.Collider;
import gameobject.component.Vector2D;
import gameobject.component.type.ComponentTypes;
import util.Direction;
import util.SerializablePredicate;
import util.TriConsumer;

@SuppressWarnings("serial")
public class Brick extends Basics {

	public Brick(String imagePath) {
		super("Brick", "Brick", imagePath, "");
		this.addComponents(new Vector2D(ComponentTypes.VELOCITY), new Collider(), new BonusScore(100));

		this.getComponent(ComponentTypes.HEALTH).setMaxHealth(Double.MAX_VALUE);
		this.getComponent(ComponentTypes.HEALTH).setCurrentHealth(Double.MAX_VALUE);
		
		SerializablePredicate<IGameObject> cond = e -> e.has(ComponentTypes.CHAMPION);
		TriConsumer<IGameObject, IGameObject, Direction> func = (I, other, direction) -> {
			if (direction == Direction.UP) {
				//I.setExpired();
				I.getComponent(ComponentTypes.HEALTH).setCurrentHealth(-1);
			}
		};
		this.getComponent(ComponentTypes.COLLIDE).add(cond, func);
		
		this.getComponent(ComponentTypes.PHYSICS_BEHAVIOR).setStrategy(new BrickStrategy());
	}
	
}
