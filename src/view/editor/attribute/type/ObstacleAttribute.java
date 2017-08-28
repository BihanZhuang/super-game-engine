package view.editor.attribute.type;

import java.util.Arrays;
import java.util.List;

import javafx.scene.control.ListView;
import view.editor.attribute.AbstractAttributeView;

public class ObstacleAttribute extends AbstractAttributeView{

	private static final List<Integer> COMPONENTS = Arrays.asList(4,6,13);
	
	public ObstacleAttribute(ListView<String> components) {
		super(components,COMPONENTS);
	}
	
}
