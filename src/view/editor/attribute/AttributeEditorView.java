package view.editor.attribute;

import gameobject.ObjectInfo;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import view.IView;

public class AttributeEditorView implements IView<ScrollPane> {
	public static final String id = "Attribute";
	public static final String REMOVE = "Remove Object";
	
	ScrollPane myPane;
	AbstractAttributeView myAttribute;
	Pane myRoot;
	
	public AttributeEditorView(double width, double height){
		init(width, height);	
	} 
	
	private void init(double width, double height ) {		
		myPane = new ScrollPane();
		myPane.setId(id);
		myPane.setPrefWidth(width);
		myPane.setPrefHeight(height);
	}
	@Override
	public ScrollPane getView(){
		return myPane;
	}

	public void generateAttributeView(ObjectInfo objInfo){
		VBox vBox = new VBox();
		vBox.setLayoutX(10);
		vBox.setLayoutY(10);
		Text title = new Text(objInfo.getName()+objInfo.getID());
		title.setFill(Color.WHITE);
		title.setFont(Font.font(20));
		vBox.getChildren().add(title);
		AttributeFactory aFact = new AttributeFactory(objInfo);
		for(AttributeSlider slider: aFact.generateSlider()){
			vBox.getChildren().add(slider.getView());
		}
		Button b = new Button(REMOVE);
		b.setOnMouseClicked(e->deleteObject(objInfo));
		vBox.getChildren().add(b);
		myPane.setContent(vBox);
		
	}

	private void deleteObject(ObjectInfo objInfo) {
		objInfo.setExpired();
	}
}