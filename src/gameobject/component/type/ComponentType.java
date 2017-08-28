package gameobject.component.type;

import java.io.Serializable;

import com.google.common.reflect.TypeToken;

import gameobject.component.Component;

/**
 * An object that represents the type of a <code>Component</code>
 * Has a one-to-one to a type of <code>Component</code>s
 * @author Mike Liu
 *
 * @param <T>
 */
public abstract class ComponentType<T extends Component> implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private TypeToken<T> myType;
    
    @SuppressWarnings("serial")
    protected ComponentType() {
        myType = new TypeToken<T>(getClass()) {};
    }
    
    public boolean matches(Component component) {
        return myType.isSupertypeOf(component.getClass());
    }
    
    @Override
    public boolean equals(Object other) {
        return this == other || other != null && other instanceof ComponentType<?> &&
                myType.equals(((ComponentType<?>) other).myType);
    }
    
    @Override
    public int hashCode() {
        return 37 * myType.hashCode() + 31;
    }
    
    @Override
    public String toString() {
    	return myType.toString();
    }
    
}
