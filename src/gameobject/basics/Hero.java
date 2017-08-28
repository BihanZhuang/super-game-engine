package gameobject.basics;

import gameobject.basics.CollisionStrategy.MarioStrategy;
import gameobject.component.Actor;
import gameobject.component.Attack;
import gameobject.component.Collider;
import gameobject.component.Generator;
import gameobject.component.Team;
import gameobject.component.type.ComponentTypes;

@SuppressWarnings("serial")
public class Hero extends Character {

    public Hero(String imagePath) {
        super("Champion", "Hero", imagePath);
        addComponents(
        		new Actor(ComponentTypes.CHAMPION),
        		new Generator(), 
        		new Team(1),
        		new Attack(0.0,0.0)); 
        this.getPosition().setX(50.0);
        this.getPosition().setY(150.0);
        
        this.getComponent(ComponentTypes.HEALTH).setCurrentHealth(1000000);
        this.getComponent(ComponentTypes.HEALTH).setMaxHealth(1000000);
        this.getComponent(ComponentTypes.MASS).setValue(85.0);
		Collider c = this.getComponent(ComponentTypes.COLLIDE);
		c.add(new MarioStrategy());

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