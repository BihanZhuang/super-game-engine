package engine.physicsStrategies;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import engine.physicsUtil.ObjectPair;
import engine.physicsUtil.PhysicsToolKit;
import gameobject.IGameObject;
import util.Direction;
import util.TriConsumer;

@SuppressWarnings("serial")
public class AdHocStrategy implements IPhysicsStrategy{

	private TriConsumer<IGameObject, PhysicsToolKit, Map<Direction, Set<IGameObject>>> myPositionUpdater;
	private TriConsumer<IGameObject, IGameObject, PhysicsToolKit> myCollisionHandler;
	private boolean collisionOn;
	
	public AdHocStrategy(boolean collisionOn, TriConsumer<IGameObject, PhysicsToolKit, Map<Direction, Set<IGameObject>>> positionUpdater, TriConsumer<IGameObject, IGameObject, PhysicsToolKit> collisionHandler) {
		myPositionUpdater=positionUpdater;
		myCollisionHandler=collisionHandler;
		this.collisionOn=collisionOn;
	}

	@Override
	public void updatePosition(IGameObject obj, PhysicsToolKit kit, Map<Direction, Set<IGameObject>> overlapers ) {
		myPositionUpdater.accept(obj, kit,overlapers);
	}

	@Override
	public void handleCollision(IGameObject thisObj, IGameObject target, PhysicsToolKit kit,HashSet<ObjectPair> handeledPairs) {
		myCollisionHandler.accept(thisObj, target, kit);	
	}

	@Override
	public boolean isCollisionOn() {
		return collisionOn;
	}

}
