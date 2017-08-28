package gameobject;
import java.io.Serializable;
import java.util.Arrays;

import gameobject.component.Component;
import gameobject.component.Dimension;
import gameobject.component.Position;
import gameobject.component.type.ComponentType;

/**
 * 
 *
 */
public class Template implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IGameObject myObject;
	private String myDescription;
    
    public Template(String name, String type, String imagePath, String description) {
        myObject = new GameObject(0, name, type, imagePath);
        myDescription = description;
    }

    public Template(IGameObject obj) {
        myObject = obj;
        myDescription = "An object";
    }

    public String getName() {
        return myObject.getName();
    }

    public String getType() {
        return myObject.getType();
    }

    public String getDescription() {
        return myDescription;
    }

    public String getImagePath() {
        return myObject.getImagePath();
    }

    public Position getPosition() {
        return myObject.getPosition();
    }

    public Dimension getDimension() {
        return myObject.getDimension();
    }

    /**
     * Checks if the <code>Template</code> has a component of <code>type</code>.
     * 
     * @param type
     * @return
     */
    public boolean has(ComponentType<?> type) {
        return myObject.has(type);
    }

    /**
     * Returns all the component types that the <code>Template</code> contains.
     * 
     * @return
     */
    public Iterable<ComponentType<?>> getComponentTypes() {
        return myObject.getComponentTypes();
    }

    public void addComponent(Component component) {
        myObject.addComponent(component);
    }

    public void addComponents(Component... components) {
        Arrays.stream(components).forEach(component -> addComponent(component));
    }

    /**
     * Returns a <code>Component</code> of <code>type</code>.
     * 
     * @param type
     * @return
     */
    public <T extends Component> T getComponent(ComponentType<T> type) {
        return myObject.getComponent(type);
    }

    /**
     * Removes the <code>Component</code> of <code>type</code>.
     * 
     * @param type
     */
    public void removeComponent(ComponentType<?> type) {
        myObject.removeComponent(type);
    }

    /**
     * Makes a new instance of the <code>IGameObject</code> that the template
     * represents.
     * 
     * @param id
     * @return
     */
    public IGameObject newInstance(int id) {
        return myObject.copy(id);
    }
}