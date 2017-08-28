package system;

import gameobject.IGameObject;
import util.Observer;

public interface GameSystem extends Observer<IGameObject> {

    void step(double elapsedTime);
    
}
