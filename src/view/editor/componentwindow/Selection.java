package view.editor.componentwindow;

import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

public class Selection {
	private VBox content;
	private VBox extended;
	private ComponentController controller;
	private ComponentData componentdata;
	
	
	public Selection(String key, List<String> value, ComponentData cd) {
		this.controller = new ComponentController(cd);
		//ComponentFactory cf = new ComponentFactory();
		this.componentdata=cd;
		this.componentdata.put(key, value.get(0));
		initialize(key, value);
	}
	
	/*public Selection(String key, List<String> value, ComponentData cd) {
		this.controller = new ComponentController(cd);
		//ComponentFactory cf = new ComponentFactory();
		initialize(key, value);
	}*/

	private void initialize(String key, List<String> value) {
		ChoiceBox<String> temp = new ChoiceBox();
		content=new VBox();
		extended=new VBox();
		temp.setItems(FXCollections.observableArrayList(value));
		temp.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
					componentdata.remove(key, oldValue);
					componentdata.put(key, newValue);
					extended.getChildren().clear();
					//System.out.println(newValue+" "+extended.getChildren().size()+" "+controller.getDisplay(newValue).size());
					extended.getChildren().addAll(controller.getDisplay(newValue));
					//System.out.println((String) newValue);
				});
		this.content.getChildren().add(temp);
		this.content.getChildren().add(extended);
	}
	
	public Node getDisplayContent() {
		return this.content;
	}

}
