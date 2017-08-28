package view.editor.attribute.type;

import java.util.Arrays;
import java.util.Collection;

import javafx.scene.control.ListView;
import view.editor.attribute.AbstractAttributeView;

public class EnemyAttribute extends AbstractAttributeView{

	private static final Collection<Integer> COMPONENTS = Arrays.asList(0,1,2,3,7,9,10,11,12,13,14,15);
	
	public EnemyAttribute(ListView<String> components) {
		super(components, COMPONENTS);
	}
	
}


