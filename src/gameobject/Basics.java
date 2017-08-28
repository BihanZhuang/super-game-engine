package gameobject;

import engine.physicsStrategies.DefaultStrategy;
import gameobject.component.Health;
import gameobject.component.Mass;
import gameobject.component.PhysicsBehavior;
import gameobject.component.type.ComponentTypes;

@SuppressWarnings("serial")
public class Basics extends Template {
	public Basics(String name, String type, String imagePath, String description) {
		super(name, type, imagePath, description);
		this.addComponents(new Mass(10.0), new PhysicsBehavior(new DefaultStrategy(true, true, true)), new Health());
		this.getComponent(ComponentTypes.HEALTH).setMaxHealth(100);
		this.getComponent(ComponentTypes.HEALTH).setCurrentHealth(100);
	}

}
