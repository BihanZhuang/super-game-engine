package view.editor.componentwindow;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;

public class CheckPropertyBox {
	private ComponentData database;
	private HBox content;
	
	public CheckPropertyBox(String key, String value, ComponentData database) {
		this.database=database;
		this.content=new HBox();
		CheckBox cb = new CheckBox(key);
		cb.selectedProperty().addListener((obV,ov, nv)->{
			if (nv)
				database.put(key, "1");
			else 
				database.put(key, "0");
		});
		this.content.getChildren().add(cb);
	}
	
	public Node getDisplayContent() {
		return this.content;
	}
}
