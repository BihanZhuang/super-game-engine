package view.editor.componentwindow;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.io.File;

import gameobject.component.Component;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Constants;
import view.editor.attribute.ComponentInterface;

public class ComponentView { 
	private Component myCom;
	private String myType;
	//private ListView<String> myListView;
	private ComponentInterface view;
	
	private ImageView image;
	
	private Pane componentviewpane;
	private ComponentController controller;
	
	public ComponentView(Component com, String type, ComponentInterface view) {
		this.myCom=com;
		this.myType=type;
		this.view=view;
		//this.myListView = listview;
		Label title = new Label(myType);
		Pane componentview=new VBox();
		componentview.setPrefHeight(500);
		controller = new ComponentController();
		//ComponentFactory cf = new ComponentFactory();
		//myData=cf.getData(type);
		componentview.getChildren().addAll(controller.getDisplay(com, type));
		//this.fieldSize=myData.getContent().size();
				
		HBox buttonPane = new HBox();
		buttonPane.getChildren().add(submit());
		//buttonPane.getChildren().add(load());
		buttonPane.getChildren().add(delete());
		
		this.image = new ImageView();
		this.image.setFitWidth(200);
		this.image.setFitHeight(200);
		
		this.componentviewpane=new VBox();
		componentviewpane.getChildren().add(componentview);
		componentviewpane.getChildren().add(this.image);
		componentviewpane.getChildren().add(buttonPane);
	}

	public Pane getDisplayContent() {
		return this.componentviewpane;
	}
	
	private void changeImage(File f) {
		System.out.println(f.getAbsolutePath());
		Image img = new Image(f.getName());
		this.image.setImage(img);
	}
	
	/*private Button load() {
		Button b = new Button("Load");
	    final FileChooser fileChooser = new FileChooser();
		b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                File file = fileChooser.showOpenDialog(null);
                if (file != null) {
                    changeImage(file);
                }
            }
        });
		return b;
	}*/
	private Button submit() {
		Button b = new Button("Submit");
		b.setOnMouseClicked(e->{
			createComponent();
			componentviewpane.getScene().getWindow().hide();
		});
		return b;
	}
	
	private Button delete() {
		Button b = new Button("Remove Component");
		b.setOnMouseClicked(e->{
			//myListView.getItems().remove(myType);
			view.removeComponent(myType);
			componentviewpane.getScene().getWindow().hide();
		});
		return b;
	}
	
	private void createComponent() {
		this.controller.produce(this.myCom, this.myType);
	}
	
	
	public void show() {
		Stage myStage = new Stage();
		DisplayMode mode = 
		        GraphicsEnvironment.getLocalGraphicsEnvironment().
		        getDefaultScreenDevice().getDisplayMode();
		myStage.setWidth(500);
		myStage.setHeight(500);
		Scene scene = new Scene(this.getDisplayContent());
		myStage.setScene(scene);
		scene.getStylesheets().add(Constants.RESOURCE_PACKAGE+"dracula.css");
		myStage.show();
	}
}
