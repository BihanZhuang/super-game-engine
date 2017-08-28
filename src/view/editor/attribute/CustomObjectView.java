package view.editor.attribute;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.util.EventObject;
import java.util.Map;

import gameobject.Template;
import gameobject.basics.Hero;
import gameobject.component.Component;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Inventory;
import util.Constants;
import util.reflection.Reflection;
import util.view.ConstraintsFactory;
import view.editor.attribute.type.CustomizeAttribute;
import view.editor.componentwindow.ComponentView;

public class CustomObjectView implements ComponentInterface {
	
	public static final String LOAD_IMAGE = "Load Image";
	public static final String SUBMIT = "Submit";
	public static final String ADD_ATTRIBUTE = "Add Attribute";
	public static final double SCREEN_COL_RATIO = 0.4;
	public static final double SCREEN_ROW_RATIO = 0.6;
	public static final double COL_CONSTRAINT1 = 30;
	public static final double COL_CONSTRAINT2 = 40;
	public static final double COL_CONSTRAINT3 = 30;
	public static final double ROW_CONSTRAINT1 = 10;
	public static final double ROW_CONSTRAINT2 = 10;
	public static final double ROW_CONSTRAINT3 = 70;
	public static final double ROW_CONSTRAINT4 = 10;
	private static final String HERO = "Hero";
	private static final String ENEMY = "Enemy";
	private static final String WEAPON = "Weapon";
	private static final String BRICK = "Obstacle";
	private static final String CUSTOMIZE = "Customize";
	private static final String ATTRIBUTE = "Attribute";
	private static final String FILEPATH = "view.editor.attribute.type.";
	private ListView<String> myList;
	private Map<String,Component> myComponents;
	private Stage myStage;
	private Button myLoadButton, mySubmitButton, myAddButton;
	private GridPane myRoot;
	private TextField myName;
	private TextField myDescription;
	private ComboBox<String> myType;
	private String myFilePath;
	private ImageView myImageView;
	private Inventory myInventory;
	private Template myTemplate;
	private AbstractAttributeView myAttributes;
	
	public CustomObjectView(Inventory inventory){
		myInventory = inventory;
		initScene();
		initButtons();
        listAttributes();
        initTextType();
        createHandlers();
	}

	private void initTextType() {
		myName = new TextField();
		myName.setPromptText("Enter object name");
		myDescription = new TextField();
		myDescription.setPromptText("Enter description");
		myRoot.add(myName, 0, 0,2,1);	
		myRoot.add(myDescription, 0, 1,3,1);
		myType = new ComboBox<String>();
		myType.setPromptText("Type");
		myType.getItems().addAll(HERO,ENEMY,WEAPON,BRICK,CUSTOMIZE);
		myRoot.add(myType, 2, 0);
		myType.setOnAction(e->chooseType(e));
		//Tested Purpose
//		myTemplate = new Template(myName.getText(), myType.getValue(), myFilePath, myDescription.getText());
	}

	private void chooseType(EventObject e) {
		myList.getItems().clear();
		String c = FILEPATH+((ComboBox<String>)e.getSource()).getValue()+ATTRIBUTE;	
		myAttributes = (AbstractAttributeView)Reflection.createInstance(c, myList);
		myComponents = myAttributes.getComponents();
	}


	private void initScene() {
		myStage = new Stage();
		DisplayMode mode = 
		        GraphicsEnvironment.getLocalGraphicsEnvironment().
		        getDefaultScreenDevice().getDisplayMode();
		myStage.setWidth(mode.getWidth() * SCREEN_COL_RATIO);
		myStage.setHeight(mode.getHeight() * SCREEN_ROW_RATIO);
        myRoot = createGrid(myStage);
        myStage.setScene(createScene(myRoot));
	}
    
    private Scene createScene(Pane root) {
        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
        scene.getStylesheets().add(Constants.RESOURCE_PACKAGE+"dracula.css");
        return scene;
    }
    
    private GridPane createGrid(Stage stage) {
        GridPane grid = new GridPane();
        grid.prefWidthProperty().bind(stage.widthProperty());
        grid.prefHeightProperty().bind(stage.heightProperty());
       
        grid.getColumnConstraints().addAll(
                ConstraintsFactory.getColumnConstraints(COL_CONSTRAINT1),
                ConstraintsFactory.getColumnConstraints(COL_CONSTRAINT2),
                ConstraintsFactory.getColumnConstraints(COL_CONSTRAINT3)
                );
        grid.getRowConstraints().addAll(
                ConstraintsFactory.getRowConstraints(ROW_CONSTRAINT1),
                ConstraintsFactory.getRowConstraints(ROW_CONSTRAINT2),
                ConstraintsFactory.getRowConstraints(ROW_CONSTRAINT3),
                ConstraintsFactory.getRowConstraints(ROW_CONSTRAINT4)
                );
        return grid;
    }
    
    private void listAttributes(){
		myList = new ListView<String>();
		myList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//		        System.out.println("ListView selection changed from oldValue = " 
//		                + oldValue + " to newValue = " + newValue);
		    	if(newValue!=null){
		    		newComponentView(newValue);
		    	}      
		    }
		});
		myRoot.add(myList, 0, 2,2,1);
    }
    
    private void newComponentView(String newValue) {
		 ComponentView cv = new ComponentView(myComponents.get(newValue),newValue, this);
		 cv.show();
    	
    }
    private void initButtons(){
		myLoadButton = new Button(LOAD_IMAGE);
    	mySubmitButton = new Button(SUBMIT);
    	myAddButton = new Button(ADD_ATTRIBUTE);
    	myRoot.add(myLoadButton, 0, 3);
    	myRoot.add(myAddButton, 1, 3);
    	myRoot.add(mySubmitButton, 2, 3);
    }
    
    private void createHandlers() {
    	FileChooser fileChooser = new FileChooser();
    	myLoadButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        File file = fileChooser.showOpenDialog(myStage);
                        if (file != null) {
                        	String[] parts = file.getPath().split("/");
                            myFilePath = parts[parts.length-1];
                            myRoot.getChildren().remove(myImageView);
                            myImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(myFilePath)));
                            myImageView.setFitHeight(myRoot.getHeight()*(COL_CONSTRAINT3)/100);
                            myImageView.setFitWidth(myRoot.getHeight()*(COL_CONSTRAINT3)/100);
                            myRoot.add(myImageView, 2, 2);
                        }
                        
                    }
                });
    	mySubmitButton.setOnAction(e->addTemplate());
    	myAddButton.setOnAction(
    			new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						CustomizeAttribute custom = new CustomizeAttribute(myList);	
						myComponents = custom.getComponents();
					}
    			}
    			);
	}
	
	private void addTemplate() {
		if(checkValid()){
			myTemplate = myType.equals(HERO) ? new Hero(myFilePath):new Template(myName.getText(), myType.getValue(), myFilePath, myDescription.getText());
			//System.out.println("Type="+myTemplate.getType());
			for(String component: myComponents.keySet()){
				myTemplate.addComponent(myComponents.get(component));
			}
			myInventory.addTemplate(myTemplate);
			myStage.hide();
		}	
	}
	
	private boolean checkValid(){
		if(myFilePath==null){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Please choose an image!");
			alert.show();
			return false;
		}
		return true;
	}
	
	public void removeComponent(String component){
		myComponents.remove(component);
		myList.getItems().remove(component);
	}

	public void show(){
		myStage.setFullScreen(false);
		myStage.show();
    }
    
}
