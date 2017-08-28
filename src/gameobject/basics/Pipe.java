package gameobject.basics;

import engine.physicsStrategies.BrickStrategy;
import gameobject.Basics;
import gameobject.IGameObject;
import gameobject.basics.GeneratorStrategy.OnetimeStrategy;
import gameobject.component.Generator;
import gameobject.component.Vector2D;
import gameobject.component.type.ComponentTypes;

@SuppressWarnings("serial")
public class Pipe extends Basics {

	public Pipe(String imagePath) {
		super("Pipe", "Obstacle", imagePath, "");
		
		this.addComponents(new Generator(), new Vector2D(ComponentTypes.VELOCITY));
		this.getComponent(ComponentTypes.GENERATOR).changeRebornCheck(new OnetimeStrategy());
		// hardcoded for now
		IGameObject obj = new Mushroom("Mushroom.png").newInstance(0);
		obj.getComponent(ComponentTypes.AI).setLeft(0);
		obj.getComponent(ComponentTypes.AI).setRight(1000);
		this.getComponent(ComponentTypes.GENERATOR).setTemplate(obj);
		this.getComponent(ComponentTypes.GENERATOR).setGenerateDiffY(-50);
		this.getComponent(ComponentTypes.GENERATOR).setGenerateCheck(true);
		this.getComponent(ComponentTypes.GENERATOR).setGenerateCnt(Integer.MAX_VALUE);
		this.getComponent(ComponentTypes.MASS).setValue(Double.POSITIVE_INFINITY);
		this.getComponent(ComponentTypes.PHYSICS_BEHAVIOR).setStrategy(new BrickStrategy());
	}
	

}
