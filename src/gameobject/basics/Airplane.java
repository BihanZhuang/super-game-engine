package gameobject.basics;

import gameobject.basics.CollisionStrategy.MarioStrategy;
import gameobject.component.Actor;
import gameobject.component.Animation;
import gameobject.component.Attack;
import gameobject.component.Collider;
import gameobject.component.Generator;
import gameobject.component.Team;
import gameobject.component.type.ComponentTypes;
import engine.physicsStrategies.DefaultStrategy;

@SuppressWarnings("serial")
public class Airplane extends Character {

    public Airplane(String imagePath) {
        super("Airplane", "Hero", imagePath);
        addComponents(
        		new Actor(ComponentTypes.CHAMPION),
        		new Generator(), 
        		new Team(1),
        		new Animation(),
        		new Attack(0.0,0.0)); 
        this.getPosition().setX(50.0);
        this.getPosition().setY(150.0);
        
        this.getComponent(ComponentTypes.HEALTH).setCurrentHealth(500);
        this.getComponent(ComponentTypes.HEALTH).setMaxHealth(500);
        this.getComponent(ComponentTypes.MASS).setValue(85.0);
        
        this.getComponent(ComponentTypes.VELOCITY).setY(-50);
        this.getComponent(ComponentTypes.ANIMATION).add(Animation.State.STAY, "airplane1.png");
        this.getComponent(ComponentTypes.ANIMATION).add(Animation.State.MOVE, "airplane1.png");
        this.getComponent(ComponentTypes.ANIMATION).add(Animation.State.MOVE, "airplane2.png");
        this.getComponent(ComponentTypes.ANIMATION).add(Animation.State.INJURE, "airplane_hurt.png");

        
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

		Weapon t = new Weapon("empty.png");
		this.getComponent(ComponentTypes.BELONGINGS).addBelonging(t.newInstance(0));

		this.getComponent(ComponentTypes.PHYSICS_BEHAVIOR).setStrategy(new DefaultStrategy(false, true, true));

    }

}