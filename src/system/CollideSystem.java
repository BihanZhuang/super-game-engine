package system;

import engine.PhysicsEngine;
import gameobject.IGameObject;
import gameobject.component.type.ComponentTypes;
import util.Direction;

public class CollideSystem extends AbstractSystem {

    public CollideSystem(ISystemInfo info, PhysicsEngine engine) {
        super(info, engine, ComponentTypes.COLLIDE);
    }

	@Override
	protected void singleStep(IGameObject obj, double elapsedTime) {
		Iterable<IGameObject> collisionObject = getEngine().getColliders(obj);
		obj.getComponent(ComponentTypes.COLLIDE).setColliders(collisionObject);
		for (IGameObject other : collisionObject) {
			Direction direction = this.getEngine().getCollisionSurface(obj, other);			
			obj.getComponent(ComponentTypes.COLLIDE).test(other)
					.forEach(action -> action.accept(obj, other, direction));
		}
	}
}
