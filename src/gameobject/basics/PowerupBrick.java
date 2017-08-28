package gameobject.basics;

import engine.physicsStrategies.BrickStrategy;
import gameobject.Basics;
import gameobject.basics.CollisionStrategy.TouchGeneratorStrategy;
import gameobject.basics.GeneratorStrategy.OnetimeStrategy;
import gameobject.component.BonusScore;
import gameobject.component.Collider;
import gameobject.component.Generator;
import gameobject.component.Vector2D;
import gameobject.component.type.ComponentTypes;

@SuppressWarnings("serial")
public class PowerupBrick extends Basics {
	public PowerupBrick(String imagePath) {
		super("PowerupBrick", "PowerupBrick", imagePath, "");
		this.addComponents(new BonusScore(100), new Collider(), new Generator(), new Vector2D(ComponentTypes.VELOCITY));
		
		this.getComponent(ComponentTypes.PHYSICS_BEHAVIOR).setStrategy(new BrickStrategy());
		this.getComponent(ComponentTypes.MASS).setValue(Double.POSITIVE_INFINITY);

		this.getComponent(ComponentTypes.GENERATOR).changeRebornCheck(new OnetimeStrategy(0.5));
		this.getComponent(ComponentTypes.COLLIDE).add(new TouchGeneratorStrategy());
		
		this.getComponent(ComponentTypes.GENERATOR).setTemplate(new Fireflower("FireFlower.png").newInstance(0));
		//this.getComponent(ComponentTypes.GENERATOR).setGeneratePosY(this.getComponent(ComponentTypes.POSITION).getY()-this.getComponent(ComponentTypes.DIMENSION).getY());
		this.getComponent(ComponentTypes.GENERATOR).setGenerateDiffY(-50);
		this.getComponent(ComponentTypes.GENERATOR).setGenerateCnt(1);
	}

}
