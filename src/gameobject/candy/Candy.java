package gameobject.candy;

import engine.physicsStrategies.BrickStrategy;
import gameobject.Basics;
import gameobject.basics.CollisionStrategy.ItemStrategy;
import gameobject.basics.GeneratorStrategy.OnetimeStrategy;
import gameobject.component.BonusScore;
import gameobject.component.Collider;
import gameobject.component.Generator;
import gameobject.component.Vector2D;
import gameobject.component.type.ComponentTypes;

@SuppressWarnings("serial")
public class Candy extends Basics {

	public Candy(String imagePath) {
		super("CandyPipe", "Obstacle", imagePath, "");
		
		this.addComponents(new Generator(), 
						   new Vector2D(ComponentTypes.VELOCITY),
						   new Collider(),
						   new BonusScore(100));
		this.getComponent(ComponentTypes.GENERATOR).changeRebornCheck(new OnetimeStrategy());
		// hardcoded for now
		this.getComponent(ComponentTypes.GENERATOR).setGenerateDiffY(-50);
		this.getComponent(ComponentTypes.GENERATOR).setGenerateCheck(true);
		this.getComponent(ComponentTypes.GENERATOR).setGenerateCnt(Integer.MAX_VALUE);
		this.getComponent(ComponentTypes.MASS).setValue(Double.POSITIVE_INFINITY);
		this.getComponent(ComponentTypes.PHYSICS_BEHAVIOR).setStrategy(new BrickStrategy());
	}
}

