package engine.physicsStrategies;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import engine.physicsUtil.ObjectPair;
import engine.physicsUtil.PhysicsToolKit;
import gameobject.IGameObject;
import util.Direction;

public interface IPhysicsStrategy extends Serializable {

	/**
	 * Updates the position of a <code>IGameObject</code>.
	 * @param obj
	 * @param kit
	 * @param overlapers
	 */
	public void updatePosition(IGameObject obj, PhysicsToolKit kit, Map<Direction, Set<IGameObject>> overlapers );
	
	/**
	 * Handels the collision between one <code>IGameObject</code> and another <code>IGameObject</code>.
	 * @param thisObj
	 * @param target
	 * @param kit
	 */
	public void handleCollision(IGameObject thisObj, IGameObject target, PhysicsToolKit kit, HashSet<ObjectPair> handeledPairs);
	
	
	/**
	 * 
	 * @return true if this strategy has collision handling on
	 */
	public boolean isCollisionOn();
	
	
	/**
	 * Checks if collision is already handeled between two objects
	 * @param currentPair
	 * @param handeledPairs
	 * @return true if this pair is alreadu handeled
	 */
	public default boolean isHandeledPair(ObjectPair currentPair, HashSet<ObjectPair> handeledPairs){
		for(ObjectPair pair: handeledPairs){
			if(pair.equals(currentPair)){
				return true;
			}
		}
		return false;
	}
}
