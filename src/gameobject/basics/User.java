package gameobject.basics;

import gameobject.IGameObject;
import gameobject.component.Animation;
import gameobject.component.Collider;
import gameobject.component.Generator;
import gameobject.component.type.ComponentTypes;
import util.Direction;
import util.SerializablePredicate;
import util.TriConsumer;

@SuppressWarnings("serial")
public class User extends Character {

	public User() {
		super("User", "Character", "Mario1.png");
		this.addComponents(new Generator());
		Collider c = this.getComponent(ComponentTypes.COLLIDE);
		SerializablePredicate<IGameObject> cond = e -> e.has(ComponentTypes.ATTACK);
		TriConsumer<IGameObject, IGameObject, Direction> func = (I, other, direction) -> {
			if (direction == Direction.UP) {
			} else {
				I.getComponent(ComponentTypes.HEALTH).changeCurrentHealth(other.getComponent(ComponentTypes.ATTACK).getDamage());
				I.getComponent(ComponentTypes.ANIMATION).setState(Animation.State.INJURE);
			}
		};
		c.add(cond, func);

		Generator g = this.getComponent(ComponentTypes.GENERATOR);
		g.changeRebornCheck((e,ctime) -> {
			double dtime = e.getComponent(ComponentTypes.GENERATOR).getDeathTime();
			boolean flag=true;
			if (e.has(ComponentTypes.CHAMPION)) {
				flag=e.getComponent(ComponentTypes.CHAMPION).getLives()>0;
			}
			return (flag && (ctime-dtime)>e.getComponent(ComponentTypes.GENERATOR).getRebornTime());
		});
		//A GUN HERE Not sure how to add
		//Need A Default Image Here
		
		
		Weapon t = new Weapon("Gun.png");
		this.getComponent(ComponentTypes.BELONGINGS).addBelonging(t.newInstance(0));
		/*
		//A Cake Here Not Sure How to Add
		this.getComponent(ComponentTypes.BELONGINGS).addBelonging(t.newInstance(0));
		
		//Weapon c= this.getComponent(ComponentTypes.)*/
		// Collider c = this.getComponent(ComponentTypes.)
	}
}
