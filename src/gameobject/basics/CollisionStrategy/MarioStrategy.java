package gameobject.basics.CollisionStrategy;

import gameobject.IGameObject;
import gameobject.component.Animation;
import gameobject.component.type.ComponentTypes;
import util.Direction;
import util.SerializablePredicate;
import util.TriConsumer;

@SuppressWarnings("serial")
public class MarioStrategy extends CollisionStrategy {

    public MarioStrategy() {
        super();
        SerializablePredicate<IGameObject> cond = e -> e.has(ComponentTypes.ATTACK);
        TriConsumer<IGameObject, IGameObject, Direction> func = (I, other, direction) -> {
            if (direction == Direction.UP) {

            } else {
                I.getComponent(ComponentTypes.HEALTH)
                        .changeCurrentHealth(-other.getComponent(ComponentTypes.ATTACK).getDamage());
                I.getComponent(ComponentTypes.ANIMATION).setState(Animation.State.INJURE);
            }
        };
        this.addFunction(func);
        this.addCondition(cond);
    }
}
