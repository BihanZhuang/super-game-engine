package engine.physicsStrategies;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import engine.physicsUtil.ObjectPair;
import engine.physicsUtil.PhysicsToolKit;
import gameobject.IGameObject;
import util.Direction;

@SuppressWarnings("serial")
public class NullVelocityStrategy extends DefaultStrategy {

	/**
	 * 
	 * @param collisionOn
	 */
	public NullVelocityStrategy(boolean collisionOn) {
		super(false, collisionOn, false);
	}
	
	
	
	@Override
	public void updatePosition(IGameObject obj, PhysicsToolKit kit, Map<Direction, Set<IGameObject>> overlapers) {}
	
	
	
	@Override
	public void handleCollision(IGameObject protagonist, IGameObject target, PhysicsToolKit kit,HashSet<ObjectPair> handeledPairs) {}

}
