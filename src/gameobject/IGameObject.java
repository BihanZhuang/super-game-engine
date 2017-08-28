package gameobject;

import util.Observable;

/**
 * Represents a game object.
 * The <code>Component</code>s it contains determine its behavior.
 * @author Mike Liu
 *
 */
public interface IGameObject extends ObjectInfo, Observable<IGameObject> {
    
    /**
     * Removes all <code>Component</code>s that this game object possesses
     */
    void removeAllComponents();
    
    /**
     * Makes a copy of this game object and all of its
     * <code>Component</code>s.
     * @param id
     * @return
     */
    IGameObject copy(int id);

}
