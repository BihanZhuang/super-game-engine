package view.editor.attribute;

import java.util.Arrays;
import java.util.List;

import engine.physicsStrategies.DefaultStrategy;
import gameobject.component.AI;
import gameobject.component.Actor;
import gameobject.component.Animation;
import gameobject.component.Attack;
import gameobject.component.Belongings;
import gameobject.component.BonusScore;
import gameobject.component.Boss;
import gameobject.component.Collider;
import gameobject.component.Component;
import gameobject.component.Generator;
import gameobject.component.Health;
import gameobject.component.Mass;
import gameobject.component.PhysicsBehavior;
import gameobject.component.Team;
import gameobject.component.Vector2D;
import gameobject.component.type.ComponentTypes;
import util.VoogaException;

public class ComponentFactory {
    
    public static final List<String> COMPONENTS = Arrays.asList(
            "Acceleration",
            "Animation",
            "Attack",
            "Belongings",
            "Bonus Score",
            "Boss",
            "Champion",
            "Collider",
            "Generator",
            "Health",
            "Mass",
            "Physics Behavior",
            "Velocity",
            "AI",
            "Team");

    public static Component getComponent(String name) {
        if(is(name, 0)) {
            return new Vector2D(ComponentTypes.ACCELERATION);
        } else if(is(name, 1)) {
            return new Animation();
        } else if(is(name, 2)) {
            return new Attack();
        } else if(is(name, 3)) {
            return new Belongings();
        } else if(is(name, 4)) {
            return new BonusScore();
        } else if(is(name, 5)) {
            return new Boss();
        } else if(is(name, 6)) {
            return new Actor(ComponentTypes.CHAMPION);
        } else if(is(name, 7)) {
            return new Collider();
        } else if(is(name, 8)) {
            return new Generator();
        } else if(is(name, 9)) {
            return new Health();
        } else if(is(name, 10)) {
            return new Mass(0);
        } else if(is(name, 11)) {
            return new PhysicsBehavior(new DefaultStrategy(true, true, true));
        } else if(is(name, 12)) {
            return new Vector2D(ComponentTypes.VELOCITY);
        } else if(is(name, 13)) {
        	return new AI(ComponentTypes.AI);
        } else if(is(name, 14)) {
        	return new Team();
        } else {
            throw new VoogaException("Component of name %s does not exist", name);
        }
    }
    
    private static boolean is(String name, int index) {
        return name.equals(COMPONENTS.get(index));
    }
}
