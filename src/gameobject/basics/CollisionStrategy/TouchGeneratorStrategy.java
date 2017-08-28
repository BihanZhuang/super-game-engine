package gameobject.basics.CollisionStrategy;

import gameobject.IGameObject;
import gameobject.component.type.ComponentTypes;
import util.Direction;
import util.SerializablePredicate;
import util.TriConsumer;

@SuppressWarnings("serial")
public class TouchGeneratorStrategy extends CollisionStrategy {
	private String imagePath = "PowerupBlockNoPower.png";
	private Direction dire = Direction.UP;

	public TouchGeneratorStrategy() {
		super();
		instantize();
	}

	public TouchGeneratorStrategy(String imagePath) {
		super();
		this.imagePath = imagePath;
		instantize();
	}

	public TouchGeneratorStrategy(String imagePath, Direction touchdirection) {
		super();
		this.imagePath = imagePath;
		this.dire = touchdirection;
	}

	private void instantize() {

		SerializablePredicate<IGameObject> cond = e -> e.has(ComponentTypes.CHAMPION);
		TriConsumer<IGameObject, IGameObject, Direction> func = (I, other, direction) -> {
			if (direction == dire) {
				// I.setExpired();
				I.getComponent(ComponentTypes.GENERATOR).setGenerateCheck(true);
				// obj.getComponent(ComponentTypes.GENERATOR).setGenerate(true);
				I.getComponent(ComponentTypes.GENERATOR).setGeneratePosX(I.getPosition().getX());
				I.getComponent(ComponentTypes.GENERATOR).setGeneratePosY(I.getPosition().getY());
				I.setImagePath(imagePath);
			}
		};
		this.addFunction(func);
		this.addCondition(cond);
	}

}
