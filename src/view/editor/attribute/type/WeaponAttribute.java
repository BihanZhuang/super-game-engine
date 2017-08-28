package view.editor.attribute.type;

import java.util.Arrays;
import java.util.List;

import javafx.scene.control.ListView;
import view.editor.attribute.AbstractAttributeView;

public class WeaponAttribute extends AbstractAttributeView{

	private static final List<Integer> COMPONENTS = Arrays.asList(1,2,8);
	
	public WeaponAttribute(ListView<String> components) {
		super(components,COMPONENTS);
	}
	
}