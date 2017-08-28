package gameobject.basics;

import engine.physicsStrategies.NullVelocityStrategy;
import gameobject.Basics;
import gameobject.component.Animation;
import gameobject.component.Attack;
import gameobject.component.Generator;
import gameobject.component.type.ComponentTypes;

@SuppressWarnings("serial")
public class Weapon extends Basics {
    
	public Weapon(String imagePath) {
		super("Weapon", "Weapon", imagePath, "");
		this.addComponents(new Animation(), new Generator(), new Attack(0.0,0.0));
		Generator g = this.getComponent(ComponentTypes.GENERATOR);
		//g.set
		g.setTemplate(new Bullet("Bullet.png").newInstance(0));
		g.setGenerateDiffX(50.0);
		this.getComponent(ComponentTypes.PHYSICS_BEHAVIOR).setStrategy(new NullVelocityStrategy(false));
	
		//Here needs an Image Path
	}

}
