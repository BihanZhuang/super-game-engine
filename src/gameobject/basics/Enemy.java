package gameobject.basics;

import engine.physicsStrategies.CharacterStrategy;
import gameobject.basics.CollisionStrategy.EnemyStrategy;
import gameobject.component.AI;
import gameobject.component.Attack;
import gameobject.component.BonusScore;
import gameobject.component.Collider;
import gameobject.component.Team;
import gameobject.component.type.ComponentTypes;

@SuppressWarnings("serial")
public class Enemy extends Character {
	
	public static final double DEFAULT_X = 20;
	public static final double DEFAULT_Y = 20;

	public Enemy(String name, String imagePath) {
		super(name, "Character", imagePath);
		this.addComponents(
				new Attack(0, 10),
				new AI(ComponentTypes.AI),
				new Team(2)
		);
		this.getComponent(ComponentTypes.VELOCITY).setX(DEFAULT_X);
		this.addComponents(new BonusScore(100));
		
		this.getComponent(ComponentTypes.PHYSICS_BEHAVIOR).setStrategy(new CharacterStrategy(true, true, true));
		Collider c = this.getComponent(ComponentTypes.COLLIDE);
		c.add(new EnemyStrategy());
	}

}
