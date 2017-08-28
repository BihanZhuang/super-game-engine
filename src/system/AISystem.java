package system;

import java.util.Collection;
import java.util.HashSet;

import engine.PhysicsEngine;
import gameobject.IGameObject;
import gameobject.ObjectInfo;
import gameobject.component.AI;
import gameobject.component.Actor.Facing;
import gameobject.component.Vector2D;
import gameobject.component.type.ComponentTypes;

public class AISystem extends AbstractSystem {
	
	/**
	 * Check if the IGameObject has one of the following components.
	 * If so, then this system can operate on it.
	 * @param obj
	 * @return
	 */
	public static boolean isAI(ObjectInfo obj) {
		return obj.has(ComponentTypes.AI);
	}
	
	private Collection<IGameObject> myChampions;

	public AISystem(ISystemInfo info, PhysicsEngine engine) {
		super(info, engine);
		myChampions = new HashSet<>();
	}

	/**
	 * In one step the AI first moves, then operates (for example,
	 * attacks the champion if the champion is in range) depending
	 * on the level of the AI.
	 */
	@Override
	protected void singleStep(IGameObject obj, double elapsedTime) {
		if(obj.has(ComponentTypes.AI)) {
			move(obj);
		}
	}
	
	/**
	 * Whenever there is a change to the IGameObject, the system will be notified. 
	 * Checks if a champion is added to the IGameObject.
	 */
	@Override
	public void update(IGameObject obj) {
		super.update(obj);
		if(obj.has(ComponentTypes.CHAMPION)) {
			myChampions.add(obj);
		}
		if(obj.has(ComponentTypes.AI)) {
	        AI ai = obj.getComponent(ComponentTypes.AI);
	        ai.setInitX(obj.getPosition().getX());
	        ai.setInitY(obj.getPosition().getY());
		}
	}

	/**
	 * By default all the AI moves back and forth horizontally in the creator-defined territory。
	 * @param obj
	 */
	protected void move(IGameObject obj) {
		System.out.println(obj.getImagePath());
		Vector2D velocity = obj.getComponent(ComponentTypes.VELOCITY);
		Vector2D position = obj.getPosition();
		AI ai = obj.getComponent(ComponentTypes.AI);
		
		if (position.getX() > ai.getInitX() + ai.getRight()) {
			ai.setFacing(Facing.LEFT);
		} else if(position.getX() < ai.getInitX() - ai.getLeft()) {
			ai.setFacing(Facing.RIGHT);
		}
		
		if (ai.getFacing() == Facing.LEFT) {
			velocity.setX(-ai.getHSpeed());
		} else {
			velocity.setX(ai.getHSpeed());
		}
	}
}
