package system;

import engine.PhysicsEngine;
import gameobject.IGameObject;
import gameobject.component.Animation;
import gameobject.component.Health;
import gameobject.component.type.ComponentTypes;

public class AnimationSystem extends AbstractSystem {

    public static final double BACKTIME = 400;

    public AnimationSystem(ISystemInfo info, PhysicsEngine engine) {
        super(info, engine, ComponentTypes.ANIMATION);
    }

    @Override
    protected void singleStep(IGameObject obj, double elapsedTime) {
        Animation anim = obj.getComponent(ComponentTypes.ANIMATION);
        if (getSystemInfo().systemTime() - anim.getLasttime() >= BACKTIME) {
            anim.setState(Animation.State.STAY);
        }
    }

}
