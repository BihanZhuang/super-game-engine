package system;

import java.util.Collection;

import engine.PhysicsEngine;
import gameobject.IGameObject;
import gameobject.component.Vector2D;
import gameobject.component.type.ComponentTypes;
import gameobject.component.Animation;

public class MoveSystem extends AbstractSystem {

	public MoveSystem(ISystemInfo info, PhysicsEngine engine) {
		super(info, engine, ComponentTypes.VELOCITY, ComponentTypes.ACCELERATION);
	}

	@Override
	public void step(double elapsedTime) {
		getEngine().updateVelocities(elapsedTime);
		super.step(elapsedTime);
	}

	@Override
	protected void singleStep(IGameObject obj, double elapsedTime) {

		if (obj.has(ComponentTypes.ANIMATION)) {
			obj.getComponent(ComponentTypes.ANIMATION).setState(Animation.State.MOVE);
//			obj.setImagePath(obj.getComponent(ComponentTypes.ANIMATION).getImagePath());
		}

		Vector2D pos = obj.getPosition();
		Vector2D vel = obj.getComponent(ComponentTypes.VELOCITY);
		Vector2D accel = obj.getComponent(ComponentTypes.ACCELERATION);
		vel.setX(vel.getX() + accel.getX() * elapsedTime);
		vel.setY(vel.getY() + accel.getY() * elapsedTime);
		pos.setX(pos.getX() + vel.getX() * elapsedTime);
		pos.setY(pos.getY() + vel.getY() * elapsedTime);

		Collection<IGameObject> backpack = obj.getComponent(ComponentTypes.BELONGINGS).getBelongings();
		for (IGameObject item : backpack) {
			Vector2D itempos = item.getPosition();
			itempos.setX(pos.getX() + vel.getX() * elapsedTime);
			itempos.setY(pos.getY() + vel.getY() * elapsedTime);
		}
		if(!getSystemInfo().getWorld().getBoundary().in(obj.getPosition().getX(), obj.getPosition().getY()) && obj.has(ComponentTypes.HEALTH)) {
		    obj.getComponent(ComponentTypes.HEALTH).setCurrentHealth(0);
		}
	}

}
