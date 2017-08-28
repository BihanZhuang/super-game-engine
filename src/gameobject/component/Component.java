package gameobject.component;

import java.io.Serializable;

import gameobject.component.type.ComponentType;
import util.Copyable;

/**
 * An object that represents a characteristic of a game object
 * @author Mike Liu
 *
 */
public interface Component extends Copyable<Component>, Serializable {

	/**
	 * Returns the type of the <code>Component</code>.
	 * The generic parameter of the returned type must be the implementing class
	 * of the <code>Component</code> or its superclass.
	 * @return
	 */
    ComponentType<? extends Component> getType();
    
}
