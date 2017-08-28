package gameobject.basics.CollisionStrategy;

import gameobject.IGameObject;
import gameobject.component.type.ComponentTypes;
import util.Direction;
import util.SerializablePredicate;
import util.TriConsumer;

@SuppressWarnings("serial")
public class InvulnerableStrategy extends CollisionStrategy {
	
	public InvulnerableStrategy() {
		super();

		SerializablePredicate<IGameObject> cond = e-> e.has(ComponentTypes.CHAMPION);
		TriConsumer<IGameObject, IGameObject, Direction> func = (I, other, direction) -> {
			if (direction == Direction.UP) {
				//I.setExpired();
			}
		};
		this.addFunction(func);
		this.addCondition(cond);
	}
}
