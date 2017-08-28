package view.custom;

import java.util.List;
import java.util.function.Consumer;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import view.MenuView;

/**
 * 
 * @author
 * @author Mike Liu
 *
 */
public class MenuOptionList extends MenuView {
    
	private ToggleGroup myToggle;
	private ChangeListener<Toggle> myListener;

	public MenuOptionList(String title){
		super(title);
		myToggle = new ToggleGroup();
	}
	
	public void setOptions(List<String> options, String defaultValue, Consumer<String> toggleHandler){
		getView().getItems().clear();
		// http://docs.oracle.com/javafx/2/ui_controls/menu_controls.html
		for(String option : options){
			 RadioMenuItem item = new RadioMenuItem(option);
			 item.setUserData(option);
			 item.setToggleGroup(myToggle);
			 getView().getItems().add(item);
			 if (option.equals(defaultValue)){
				 myToggle.selectToggle(item);
			 }
		}
		if(myListener != null) {
		    myToggle.selectedToggleProperty().removeListener(myListener);
		}
		myListener = (observable, oldValue, newValue) -> {
            if (myToggle.getSelectedToggle() != null) {
                toggleHandler.accept((String) myToggle.getSelectedToggle().getUserData());
            }
        };
		myToggle.selectedToggleProperty().addListener(myListener);
	}
}
