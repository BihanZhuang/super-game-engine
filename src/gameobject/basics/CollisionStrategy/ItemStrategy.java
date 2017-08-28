package gameobject.basics.CollisionStrategy;

import gameobject.IGameObject;
import gameobject.component.type.ComponentTypes;
import util.Direction;
import util.SerializablePredicate;
import util.TriConsumer;

@SuppressWarnings("serial")
public class ItemStrategy extends CollisionStrategy {
	private double HeroHealthIncrease=0;
	private boolean HeroStateChange=false;
	private String imagePath="Mario2.png";
	
	public ItemStrategy() {
		super();
		instantize();
	}
	
	public ItemStrategy(String imagePath, double HeroHealthIncrease) {
		this.HeroStateChange=true;
		this.imagePath=imagePath;
		this.HeroHealthIncrease=HeroHealthIncrease;
		instantize();
	}
	
	private void instantize() {
		SerializablePredicate<IGameObject> cond = e -> e.has(ComponentTypes.CHAMPION);
		TriConsumer<IGameObject, IGameObject, Direction> func = (I, other, direction) -> {
			I.setExpired();
			if (HeroStateChange) other.setImagePath(this.imagePath);
			//System.out.println("Checking");
			other.getComponent(ComponentTypes.HEALTH).changeCurrentHealth(HeroHealthIncrease);
		};
		this.addFunction(func);
		this.addCondition(cond);
	}

}
