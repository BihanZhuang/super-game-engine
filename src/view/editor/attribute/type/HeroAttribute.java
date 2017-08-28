package view.editor.attribute.type;

import java.util.Arrays;
import java.util.Collection;

import javafx.scene.control.ListView;
import view.editor.attribute.AbstractAttributeView;

public class HeroAttribute extends AbstractAttributeView{

	private static final Collection<Integer> COMPONENTS = Arrays.asList(0,1,2,3,6,7,8,9,10,11,12,13);
	
	public HeroAttribute(ListView<String> components) {
		super(components, COMPONENTS);
	}
	
}
