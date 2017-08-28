package engine.physicsStrategies;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import engine.physicsUtil.CollisionDetector;
import engine.physicsUtil.ObjectPair;
import engine.physicsUtil.PhysicsToolKit;
import gameobject.IGameObject;
import gameobject.ObjectInfo;
import gameobject.component.Scalar;
import gameobject.component.Vector2D;
import gameobject.component.type.ComponentTypes;
import model.Environment.Constant;
import util.Axes;
import util.Direction;
import util.ResourceParser;

/**
 * Deafault strategy, if not specified, applies all physics rules
 * 
 *
 */
@SuppressWarnings("serial")
public class DefaultStrategy implements IPhysicsStrategy {

	private final String RESOURCES_PATH = "resources/physics_setup";
	private final String COEFF_OF_RESTITUTION_KEY = "CHARACTER_RESTITUTION_CONSTANT";
	private double COEFF_OF_RESTITUTION;
	private boolean gravityOn, collisionOn, frictionOn;

	/**
	 * 
	 * @param gravityOn
	 * @param collisionOn
	 * @param frictionOn
	 */
	public DefaultStrategy(boolean gravityOn, boolean collisionOn, boolean frictionOn) {
		this.collisionOn = collisionOn;
		this.gravityOn = gravityOn;
		this.frictionOn = frictionOn;
		ResourceParser rscParser = new ResourceParser(RESOURCES_PATH);
		COEFF_OF_RESTITUTION = Double.parseDouble(rscParser.getString(COEFF_OF_RESTITUTION_KEY));
	}

	@Override
	public void updatePosition(IGameObject obj, PhysicsToolKit kit, Map<Direction, Set<IGameObject>> overlapers) {
		Vector2D velocity = obj.getComponent(ComponentTypes.VELOCITY);
		Vector2D position = obj.getPosition();
		double G = kit.getConstantsGetter().apply(Constant.G), frameTime = kit.getFrameTime();
		boolean willFreeFall = getFreeFallCondition(overlapers, kit.getCollisionAlgo());
		if (willFreeFall && gravityOn) {
			velocity.setY(velocity.getY() + G * frameTime);
		} 
		else if (velocity.getX() != 0 && frictionOn && !willFreeFall) {
			double FRIC_COEFF = kit.getConstantsGetter().apply(Constant.CHAR_FRICTION_COEFF);
			double frictionAcceleration = velocity.getX() > 0 ? -FRIC_COEFF * kit.getConstantsGetter().apply(Constant.G)
					: FRIC_COEFF * kit.getConstantsGetter().apply(Constant.G);
			velocity.setX(velocity.getX() + frictionAcceleration * kit.getFrameTime());
		}
		else if(!willFreeFall) {
//			ObjectInfo aBrick=getABrickBelow(overlapers, kit.getCollisionAlgo());
			velocity.setY(0);
//			obj.getPosition().setY(aBrick.getPosition().getY() - obj.getDimension().getY() / 2 - aBrick.getDimension().getY() / 2);
			
		}
		
		
		position.setX(position.getX() + velocity.getX() * frameTime);
		position.setY(position.getY() + velocity.getY() * frameTime);
	}

	protected boolean getFreeFallCondition(Map<Direction, Set<IGameObject>> overlapers,
			CollisionDetector collisionAlgo) {
		if (!overlapers.containsKey(Direction.DOWN)) {
			return true;
		} else {
			if(getABrickBelow(overlapers, collisionAlgo)!=null){
				return false;
			}
			return true;
		}

	}

	protected ObjectInfo getABrickBelow(Map<Direction, Set<IGameObject>> overlapers, CollisionDetector collisionAlgo) {
		for (IGameObject overlapper : overlapers.get(Direction.DOWN)) {
			if (collisionAlgo.isBrick(overlapper)) {
				return overlapper;
			}
		}
		return null;
	}

	@Override
	public void handleCollision(IGameObject protagonist, IGameObject target, PhysicsToolKit kit,
			HashSet<ObjectPair> handeledPairs) {
		ObjectPair currentPair = new ObjectPair(protagonist, target);

		if (isHandeledPair(currentPair, handeledPairs)) {
			return;
		}
		Vector2D pV = protagonist.getComponent(ComponentTypes.VELOCITY),
				targetV = target.getComponent(ComponentTypes.VELOCITY);

		Vector2D pPos = protagonist.getPosition(), tPos = target.getPosition();

		Vector2D pSize = protagonist.getDimension(), tSize = target.getDimension();

		if (collisionOn && targetV != null
				&& target.getComponent(ComponentTypes.PHYSICS_BEHAVIOR).getStrategy().isCollisionOn()) {
			Direction targetTouchSurf = kit.getCollisionAlgo().getTouchingSurface(protagonist, target);

			if ((targetTouchSurf == Direction.LEFT || targetTouchSurf == Direction.RIGHT)
					&& kit.getCollisionAlgo().haveCollision(protagonist, target, Axes.X)) {

				if (targetTouchSurf == Direction.LEFT) {
					pPos.setX(tPos.getX() - pSize.getX() / 2 - tSize.getX() / 2);
				} else {
					pPos.setX(tPos.getX() + pSize.getX() / 2 + tSize.getX() / 2);
				}

				setCollisionVelocity1D(protagonist, pV.getX(), target, targetV.getX(), kit, Axes.X);
			} else if ((targetTouchSurf == Direction.UP || targetTouchSurf == Direction.DOWN)
					&& kit.getCollisionAlgo().haveCollision(protagonist, target, Axes.Y)) {
				if (targetTouchSurf == Direction.UP) {
					pPos.setY(tPos.getY() - pSize.getY() / 2 - tSize.getY() / 2);
				} else {
					pPos.setY(tPos.getY() + pSize.getY() / 2 + tSize.getY() / 2);
				}
				setCollisionVelocity1D(protagonist, pV.getY(), target, targetV.getY(), kit, Axes.Y);
			}
		}
		handeledPairs.add(currentPair);
	}

	/**
	 * calculates the velocity of objMass after collision Uses conservation of
	 * momentum
	 * 
	 * @param objMass
	 * @param objV
	 * @param targetMass
	 * @param targetV
	 * @return objV after collision
	 */
	protected void setCollisionVelocity1D(ObjectInfo obj, double objV, ObjectInfo target, double targetV,
			PhysicsToolKit kit, Axes axis) {
		if ((objV == 0 && targetV == 0)) {
			return;
		}
		Scalar objMass = obj.getComponent(ComponentTypes.MASS), targetMass = target.getComponent(ComponentTypes.MASS);
		if (kit.getCollisionAlgo().isBrick(target)) {
			obj.getComponent(ComponentTypes.VELOCITY).set(axis, -1 * objV * COEFF_OF_RESTITUTION);
		} else {
			double objMassDouble = objMass.getValue(), targetMassDouble = targetMass.getValue();
			double objVFinal = (objMassDouble * objV + targetMassDouble * targetV
					+ COEFF_OF_RESTITUTION * targetMassDouble * (targetV - objV)) / (objMassDouble + targetMassDouble);
			double targetVFinal = (objMassDouble * objV + targetMassDouble * targetV
					+ COEFF_OF_RESTITUTION * objMassDouble * (objV - targetV)) / (objMassDouble + targetMassDouble);
			obj.getComponent(ComponentTypes.VELOCITY).set(axis, objVFinal);
			target.getComponent(ComponentTypes.VELOCITY).set(axis, targetVFinal);
		}
	}

	@Override
	public boolean isCollisionOn() {
		return collisionOn;
	}

}
