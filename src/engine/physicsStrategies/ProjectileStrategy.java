package engine.physicsStrategies;


/**
 * Physics rules for projectiles, e.g. bullets
 * 
 * @author Yuxiang He
 *
 */
@SuppressWarnings("serial")
public class ProjectileStrategy extends DefaultStrategy implements IPhysicsStrategy{



	public ProjectileStrategy(boolean gravityOn, boolean collisionOn, boolean frictionOn) {
		super(gravityOn, collisionOn, frictionOn);
	}

}
