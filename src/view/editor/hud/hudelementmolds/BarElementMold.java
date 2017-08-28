package view.editor.hud.hudelementmolds;

import gameobject.component.type.ComponentType;
import gameobject.component.type.ComponentTypes;
import hudelement.BarElement;
import hudelement.HUDElement;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import view.editor.hud.HUDCanvasController;

public class BarElementMold extends HUDElementMold {
	public static final String DEFAULT_BAR_COLOR = "#FF0000";
	public static final String DEFAULT_TEXT_COLOR = "#000000";
	public static final double DEFAULT_BAR_WIDTH = 150;
	public static final double DEFAULT_BAR_HEIGHT = 30;

	private Color barColor;
	private BarElement myElement;
	private ComponentType myCType;
	private String myType;
	
	public BarElementMold(double xPos, double yPos, HUDCanvasController cController){
		super(DEFAULT_BAR_WIDTH, DEFAULT_BAR_HEIGHT, xPos, yPos, cController);
		barColor = Color.web(DEFAULT_BAR_COLOR);
		
//		myElement = (BarElement) newBarElementInstance(xPos, yPos, null, null);
		setHUDElement(myElement);
		getPane().setBackground(new Background((new BackgroundFill(barColor, CornerRadii.EMPTY, Insets.EMPTY))));
	}
	
	@Override
	protected void showPopUpStartingInfo(){
		getStage().show();
		TextField fieldName = new TextField();
		fieldName.setMaxWidth(300);
		fieldName.setPromptText("Enter Bar Type (ex. health)");
		Button submit = new Button("Submit");
		ComboBox<String> componentCBox = new ComboBox<String>();
		componentCBox.setPromptText("Type");
		componentCBox.getItems().addAll("HEALTH");
		submit.setOnAction(e -> {
			setMyType(fieldName.getText());
			setComponentType(componentCBox.getValue());
			myElement = (BarElement) newBarElementInstance(getXPos(), getYPos(), myType, myCType);
			getLayout().getChildren().clear();
			getStage().close();
		});
		getLayout().getChildren().addAll(fieldName, componentCBox, submit);
	}
	
	@Override
	protected void showPopUpInformation(){
		getStage().show();
		TextField fieldName = new TextField();
		fieldName.setMaxWidth(300);
		fieldName.setPromptText("Enter Bar Type (ex. health)");
		
		TextField color = new TextField();
		color.setMaxWidth(300);
		color.setPromptText("Enter Web Color (ex. #FFFFFF)");
		

		getButton().setOnAction(e -> {
			if(!fieldName.getText().equals("")){
				setMyType(fieldName.getText());
			}
			if(!color.getText().equals("")){
				setColor(color.getText());
			}
			getLayout().getChildren().clear();
			getStage().close();
		});
		getLayout().getChildren().addAll(fieldName, color, getButton());
	}
	
	private void setColor(String color){
		myElement.setBarColor(color);
		barColor = Color.web(color);
		getPane().setBackground(new Background(new BackgroundFill(barColor, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	private void setMyType(String type){
		myType = type;
		if(myElement != null){
			myElement.setType(type);
		}
	}
	
	public void setComponentType(String c){
		myCType = ComponentTypes.HEALTH;
	}
	
	private HUDElement newBarElementInstance(double x, double y, String type, ComponentType ctype){
		BarElement b = new BarElement(x, y, DEFAULT_BAR_WIDTH, 
				DEFAULT_BAR_HEIGHT, type, ctype, DEFAULT_BAR_COLOR, 
				DEFAULT_TEXT_COLOR);
		return b;
	}
	


}
