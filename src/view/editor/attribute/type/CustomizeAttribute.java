package view.editor.attribute.type;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import gameobject.component.Component;
import javafx.scene.control.ListView;
import view.editor.attribute.AbstractAttributeView;
import view.editor.attribute.ComponentList;

public class CustomizeAttribute extends AbstractAttributeView{
	private ListView<String> myAttributes;
	private Map<String,Component> myComponents;
	
	public CustomizeAttribute(ListView<String> components) {
		super(components, null);
		myAttributes = components;
		myComponents = new HashMap<String,Component>();
		
	}
	@Override
	protected void listAttributes(Collection<Integer> attributes) {
		new ComponentList((component, name) -> {
			if(!myAttributes.getItems().contains(name)){
				myAttributes.getItems().add(name);
				myComponents.put(name, component);
			}
        }).show();	
	}
	@Override
	public Map<String,Component> getComponents(){
		return myComponents;
	}
}
