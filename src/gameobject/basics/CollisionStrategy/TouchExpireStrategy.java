package gameobject.basics.CollisionStrategy;

import gameobject.IGameObject;
import gameobject.component.type.ComponentTypes;
import util.Direction;
import util.SerializablePredicate;
import util.TriConsumer;

@SuppressWarnings("serial")
public class TouchExpireStrategy extends CollisionStrategy {
	
	public TouchExpireStrategy() {
		super();

		SerializablePredicate<IGameObject> cond = e -> {
			return e.has(ComponentTypes.TEAM);
		};
		TriConsumer<IGameObject, IGameObject, Direction> func = (I, other, direction) -> {
			if (I.getComponent(ComponentTypes.TEAM).getTeamnumber()!=other.getComponent(ComponentTypes.TEAM).getTeamnumber()) {
				I.setExpired();
			}
		};	
		this.addCondition(cond);
		this.addFunction(func);
	}
}
