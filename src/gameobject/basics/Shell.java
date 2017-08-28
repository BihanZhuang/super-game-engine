package gameobject.basics;

import engine.physicsStrategies.CharacterStrategy;
import gameobject.IGameObject;
import gameobject.basics.CollisionStrategy.InvulnerableStrategy;
import gameobject.basics.GeneratorStrategy.OnetimeStrategy;
import gameobject.component.Collider;
import gameobject.component.Generator;
import gameobject.component.Vector2D;
import gameobject.component.type.ComponentTypes;

@SuppressWarnings("serial")
public class Shell extends Enemy {
    
	public Shell(String imagePath) {
		super("Shell", imagePath);
		this.addComponents(
					new Generator(),
					new Vector2D(ComponentTypes.VELOCITY)
				);				
		//this.removeComponent(ComponentTypes.VELOCITY);
		Generator g = this.getComponent(ComponentTypes.GENERATOR);
		g.changeRebornCheck(new OnetimeStrategy(5));
		g.setGenerateCheck(true);
		g.setGenerate(false);
		// hardcoded
		IGameObject obj = new Enemy("RebornTurtle","TurtleReborn.png").newInstance(0);
		obj.getComponent(ComponentTypes.AI).setLeft(200);
		obj.getComponent(ComponentTypes.AI).setRight(200);
		g.setTemplate(obj);
		
		this.removeComponent(ComponentTypes.COLLIDE);
		this.addComponent(new Collider());
		obj.getComponent(ComponentTypes.COLLIDE).add(new InvulnerableStrategy());
		this.getComponent(ComponentTypes.PHYSICS_BEHAVIOR).setStrategy(new CharacterStrategy(true,true, true));
	}
}
