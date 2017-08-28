package engine.physicsStrategies;

import java.util.Map;
import java.util.Set;

import engine.physicsUtil.PhysicsToolKit;
import gameobject.IGameObject;
import gameobject.ObjectInfo;
import gameobject.component.Vector2D;
import gameobject.component.type.ComponentTypes;
import model.Environment.Constant;
import util.Direction;

@SuppressWarnings("serial")
public class CharacterStrategy extends DefaultStrategy {

	private boolean frictionOn;
	
	public CharacterStrategy(boolean gravityOn, boolean collisionOn, boolean frictionOn) {
		super(gravityOn, collisionOn, frictionOn);
		this.frictionOn=frictionOn;
	}
	
	@Override
	public void updatePosition(IGameObject obj, PhysicsToolKit kit, Map<Direction, Set<IGameObject>> overlapers) {
		Vector2D velocity = obj.getComponent(ComponentTypes.VELOCITY);
		Vector2D position = obj.getPosition();
		double G = kit.getConstantsGetter().apply(Constant.G), frameTime = kit.getFrameTime();
		boolean willFreeFall = super.getFreeFallCondition(overlapers, kit.getCollisionAlgo());
		if (willFreeFall) {
			velocity.setY(velocity.getY() + G * frameTime);
		}  else {
			if(velocity.getY() > 0){
//				velocity.setY(0);
				ObjectInfo aBrick=getABrickBelow(overlapers, kit.getCollisionAlgo());
				velocity.setY(0);
				obj.getPosition().setY(aBrick.getPosition().getY() - obj.getDimension().getY() / 2 - aBrick.getDimension().getY() / 2);
			}
			if(velocity.getX()!=0 && frictionOn){
				double FRIC_COEFF=kit.getConstantsGetter().apply(Constant.CHAR_FRICTION_COEFF);
				double frictionAcceleration=velocity.getX()>0? -FRIC_COEFF*kit.getConstantsGetter().apply(Constant.G): FRIC_COEFF*kit.getConstantsGetter().apply(Constant.G);
				velocity.setX(velocity.getX()+frictionAcceleration*kit.getFrameTime());
			}
		} 

		position.setX(position.getX() + velocity.getX() * frameTime);
		position.setY(position.getY() + velocity.getY() * frameTime);
	}
	
	

}
