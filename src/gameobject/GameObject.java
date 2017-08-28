package gameobject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import gameobject.component.Component;
import gameobject.component.Dimension;
import gameobject.component.Position;
import gameobject.component.type.ComponentType;
import gameobject.component.type.ComponentTypes;
import javafx.geometry.Dimension2D;
import util.QuickNotifyObservable;
import util.VoogaException;

/**
 * An implementation of the IGameObject interface
 * @author Mike Liu
 *
 */
public class GameObject extends QuickNotifyObservable<IGameObject> implements IGameObject {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final Dimension2D DEFAULT_SIZE = new Dimension2D(50, 50);
    
    private int myID;
    private String myName, myType;
    private Map<ComponentType<? extends Component>, Component> myComponents;
    private boolean expired;
    private String myImagePath;
    private Position myPosition;
    private Dimension myDimension;
    
    public GameObject(int id, String name, String type, String imagePath) {
    	myName = name;
    	myType = type;
        myID = id;
        myImagePath = imagePath;
        myComponents = new HashMap<ComponentType<? extends Component>, Component>();
        expired = false;    
        myPosition = new Position();
        myDimension = new Dimension();
        myDimension.setX(DEFAULT_SIZE.getWidth());
        myDimension.setY(DEFAULT_SIZE.getHeight());
    }

    @Override
    public int getID() {
        return myID;
    }

    @Override
    public Position getPosition() {
        return myPosition;
    }

    @Override
    public Dimension getDimension() {
        return myDimension;
    }

    @Override
    public String getImagePath() {
        return myImagePath;
    }

    @Override
    public void setImagePath(String imagePath) {
        myImagePath = imagePath;
    }

    @Override
    public boolean has(ComponentType<?> type) {
        return myComponents.containsKey(type);
    }

    @Override
    public boolean hasAll(Collection<ComponentType<?>> types) {
        return myComponents.keySet().containsAll(types);
    }

    @Override
    public Iterable<ComponentType<?>> getComponentTypes() {
        return myComponents.keySet();
    }

    @Override
    public void addComponent(Component component) {
        if (!component.getType().matches(component)) {
            throw new VoogaException(VoogaException.COMPONENT_TYPE_MISMATCH, component.getClass(), component.getType());
        }
        myComponents.put(component.getType(), component);
        notifyObservers();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Component> T getComponent(ComponentType<T> type) {
        if(myComponents.containsKey(type)) {
            return (T) myComponents.get(type);
        }
        throw new VoogaException(VoogaException.COMPONENT_NOT_EXIST, type);
    }

    @Override
    public void removeComponent(ComponentType<?> type) {
        myComponents.remove(type);
        notifyObservers();
    }

    @Override
    public void removeAllComponents() {
        myComponents.clear();
        notifyObservers();
    }

    @Override
    public boolean expired() {
        return expired;
    }

    @Override
    public void setExpired() {
        expired = true;
        notifyObservers();
    }

    @Override
    public IGameObject copy(int id) {
        GameObject obj = new GameObject(id, myName, myType, myImagePath);
        obj.myPosition = myPosition.copy();
        obj.myDimension = myDimension.copy();
        myComponents.values().forEach(c -> obj.addComponent(c.copy()));
        return obj;
    }

	@Override
	public String getName() {
		return myName;
	}

	@Override
	public String getType() {
		return myType;
	}
	
	@Override
	public String toString(){
		return String.format("Name: %s, Type: %s, ID: %d", myName, myType, myID);
	}

    @Override
    protected IGameObject notification() {
        return this;
    }

}
