package gameobject.basics.CollisionStrategy;

import gameobject.IGameObject;
import gameobject.component.type.ComponentTypes;
import util.Direction;
import util.SerializablePredicate;
import util.TriConsumer;

@SuppressWarnings("serial")
public class EnemyStrategy extends CollisionStrategy {
	
	public EnemyStrategy() {
		super();

		SerializablePredicate<IGameObject> cond = e -> {
			//System.out.println("Hey");
			return e.has(ComponentTypes.CHAMPION);
		};
		TriConsumer<IGameObject, IGameObject, Direction> func = (I, other, direction) -> {
			if (direction==Direction.DOWN) {
				if(I.has(ComponentTypes.HEALTH)) {
					I.getComponent(ComponentTypes.HEALTH).setCurrentHealth(-1);
				}
        		if (I.has(ComponentTypes.GENERATOR)) {
        			I.getComponent(ComponentTypes.GENERATOR).setGeneratePosX(I.getPosition().getX());
        			I.getComponent(ComponentTypes.GENERATOR).setGeneratePosY(I.getPosition().getY());
        			I.getComponent(ComponentTypes.GENERATOR).setGenerateCheck(true);
        		}
        	} else {
			}
		};
		this.addFunction(func);
		this.addCondition(cond);
		cond = e-> {
			return e.has(ComponentTypes.TEAM) && e.has(ComponentTypes.ATTACK);
		};
		func = (I, other, direction) -> {
			if (I.getComponent(ComponentTypes.TEAM).getTeamnumber()!=other.getComponent(ComponentTypes.TEAM).getTeamnumber() && I.has(ComponentTypes.HEALTH)) {
				double a = other.getComponent(ComponentTypes.ATTACK).getDamage();
				if(I.has(ComponentTypes.HEALTH)) {
					double h = I.getComponent(ComponentTypes.HEALTH).getCurrentHealth();
					I.getComponent(ComponentTypes.HEALTH).setCurrentHealth(h-a);
				}
			}
		};
		this.addFunction(func);
		this.addCondition(cond);
	}
}
