package view.editor.componentwindow;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Variable Button Implementation, allows users to edit the value of the variable by clicking on it
 * 
 * @author Derek
 *
 */
public class VariableButton extends AbstractButton{
	
	private String name;
	private String value;
	private TextField editTextField = new TextField();
	public static final int EDIT_TEXT_WIDTH = 50;
	
	public VariableButton(String name, String value) {
		super(getTextAppearance(name, value));
		getButton().setPrefWidth(150);
		this.name = name;
		this.value = value;
		editTextField.setOnAction((ActionEvent e) -> updateText());
		editTextField.setPrefWidth(EDIT_TEXT_WIDTH);
	}
	
	private static String getTextAppearance(String name, String value){
		return name + " : " + value;
	}

	@Override
	protected void setAction(Button myButton){
		myButton.setOnMouseClicked((MouseEvent e) -> action());
	}

	@Override
	protected void action(){
		System.out.println("editing variable");
		editTextField.setText(value);
		getButton().setGraphic(editTextField);
	}
	
	
	private void updateText(){
		System.out.println("updating variable");
		value = editTextField.getText();
		getButton().setGraphic(null);
		getButton().setText(getTextAppearance(name, value));
	}
	
	
}
