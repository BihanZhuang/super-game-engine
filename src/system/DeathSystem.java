package system;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import engine.PhysicsEngine;
import gameobject.IGameObject;
import gameobject.ObjectInfo;
import gameobject.component.Actor;
import gameobject.component.Animation;
import gameobject.component.Health;
import gameobject.component.type.ComponentTypes;

public class DeathSystem extends AbstractSystem {

    public DeathSystem(ISystemInfo info, PhysicsEngine engine) {
        super(info, engine, ComponentTypes.HEALTH);
    }

    @Override
    protected void singleStep(IGameObject obj, double elapsedTime) {
        Health health = obj.getComponent(ComponentTypes.HEALTH);
        if ((health.getCurrentHealth()) <= 0) {
        	//Set Invisible / Reborn if needed
        	//this.getSystemInfo().getWorld().removeObject(obj);
        	//System.out.println(obj.getName());
//        	if (obj.has(ComponentTypes.ANIMATION)) {
//        		obj.getComponent(ComponentTypes.ANIMATION).setState(Animation.State.INJURE);
//    			obj.setImagePath(obj.getComponent(ComponentTypes.ANIMATION).getImagePath());
//        	}
        	//Add Score/Add PowerUp if it's enemy
        	if (obj.has(ComponentTypes.BONUSSCORE)) {
        		getSystemInfo().addScore(obj.getComponent(ComponentTypes.BONUSSCORE).getBonusScore());
        	}
        	/*if (obj.has(ComponentTypes.GENERATOR) && obj.has(ComponentTypes.HEALTH)) {
        		obj.getComponent(ComponentTypes.GENERATOR).dieAt(this.getSystemInfo().systemTime());
        		//obj.getComponent(ComponentTypes.GENERATOR).setGenerate(true);
        		obj.getComponent(ComponentTypes.GENERATOR).setGeneratePosX(obj.getComponent(ComponentTypes.POSITION).getX());
        		obj.getComponent(ComponentTypes.GENERATOR).setGeneratePosY(obj.getComponent(ComponentTypes.POSITION).getY());
        	}*/
        	//Change Status (-lives) if it's player
        	if (obj.has(ComponentTypes.CHAMPION)) {
        		Actor cur = obj.getComponent(ComponentTypes.CHAMPION);
        		cur.setLives(cur.getLives()-1);
        		this.getSystemInfo().setFail();
        	}
        	
            obj.setExpired();
        }
    }

}
