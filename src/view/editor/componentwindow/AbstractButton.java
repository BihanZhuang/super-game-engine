package view.editor.componentwindow;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
/**
 *Abstract Button class that provides framework and basic functionality for button subclasses such as setting the input action
 * 
 * @author Derek
 *
 */
public abstract class AbstractButton {
	
	private static final int WIDTH = 100;
	private Button myButton;
	
	public AbstractButton(String myButtonText){
		myButton = new Button(myButtonText);
		myButton.setPrefWidth(WIDTH);
		setAction(myButton);
	}
	
	protected void setAction(Button myButton){
		myButton.setOnAction((ActionEvent e) -> action());
	}
	
	/**
	 * Abstract method that specifies the action for the button to perform when it is clicked.
	 * Subclasses will implement this method for the specific action desired for that button on a click.
	 */
	protected abstract void action();
	
	public Button getButton(){
		return myButton;
	}
}
