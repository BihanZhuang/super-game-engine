package gameobject.basics;

import engine.physicsStrategies.ProjectileStrategy;
import gameobject.Basics;
import gameobject.basics.CollisionStrategy.TouchExpireStrategy;
import gameobject.component.Animation;
import gameobject.component.Attack;
import gameobject.component.Collider;
import gameobject.component.Team;
import gameobject.component.Vector2D;
import gameobject.component.type.ComponentTypes;

@SuppressWarnings("serial")
public class Bullet extends Basics {
	public Bullet(String imagePath) {
		super("Bullet","", imagePath, "");
		this.addComponents(
				new Attack(0, 60), 
				new Animation(),
				new Vector2D(ComponentTypes.VELOCITY), 
				new Collider(),
				new Team(1));
		Collider c = this.getComponent(ComponentTypes.COLLIDE);
		c.add(new TouchExpireStrategy());

		this.getDimension().setX(20);
		this.getDimension().setY(20);
		this.getComponent(ComponentTypes.VELOCITY).setX(400);
		this.getComponent(ComponentTypes.PHYSICS_BEHAVIOR).setStrategy(new ProjectileStrategy(false, true, true));
	}
}
