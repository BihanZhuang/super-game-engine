package view.editor.attribute;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import gameobject.component.Component;
import javafx.scene.control.ListView;

public abstract class AbstractAttributeView{

	protected ListView<String> myComponents;
	private Map<String,Component> myAttributes;
	
	public AbstractAttributeView(ListView<String> components, Collection<Integer> attributes){
		myComponents = components;
		listAttributes(attributes);
	}
	
	protected void listAttributes(Collection<Integer> attributes){
		myAttributes = new HashMap<String,Component>();
		for(Integer i: attributes){
			myComponents.getItems().add((ComponentFactory.COMPONENTS.get(i)));
			myAttributes.put(ComponentFactory.COMPONENTS.get(i),ComponentFactory.getComponent(ComponentFactory.COMPONENTS.get(i)));
		}
	}
	
	public Map<String,Component> getComponents(){
		return myAttributes;
	}
	
}
