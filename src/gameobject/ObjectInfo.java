package gameobject;

import java.io.Serializable;
import java.util.Collection;

import gameobject.component.Component;
import gameobject.component.Dimension;
import gameobject.component.Position;
import gameobject.component.type.ComponentType;

/**
 * Presents basic information about a game object
 * @author Mike Liu
 *
 */
public interface ObjectInfo extends Serializable {

    /**
     * Returns the ID of the game object.
     * @return
     */
    int getID();
    
    /**
     * Returns the path to the image file of this game object.
     * @return
     */
    String getImagePath();
    
    /**
     * Sets the path to the image file of this game object.
     * @param imagePath
     */
    void setImagePath(String imagePath);
    
    /**
     * Returns the position of the game object
     * @return
     */
    Position getPosition();
    
    /**
     * Returns the dimension of the game object
     * @return
     */
    Dimension getDimension();
    
    /**
     * Checks if the game object has the <code>Component</code>
     * represented by <code>type</code>
     * @param type
     * @return
     */
    boolean has(ComponentType<?> type);

    /**
     * Checks if the game object has all the <code>Component</code>s
     * represented by <code>types</code>
     * @param types
     * @return
     */
    boolean hasAll(Collection<ComponentType<?>> types);
    
    /**
     * Adds <code>component</code> to this game object.
     * @param component
     */
    void addComponent(Component component);
    
    /**
     * Removes the <code>Component</code> represented by <code>type</code>.
     * @param type
     */
    void removeComponent(ComponentType<?> type);
    
    Iterable<ComponentType<?>> getComponentTypes();

    /**
     * Returns the <code>Component</code> represented by <code>type</code>.
     * Returns null if this game object does not have the <code>Component</code>
     * @param type
     * @return
     */
    <T extends Component> T getComponent(ComponentType<T> type);

    /**
     * Returns if this game object is expired, i.e. should be removed from the game.
     * @return
     */
    boolean expired();
    
    /**
     * Sets this game object to be expired.
     */
    void setExpired();
    
    /**
     * Returns the name of the game object
     * @return
     */
    String getName();

    /**
     * Returns the type of the game object
     * @return
     */
    String getType();

}