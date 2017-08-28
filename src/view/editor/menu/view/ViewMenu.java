package view.editor.menu.view;

import java.util.Arrays;

import javafx.stage.Stage;
import view.MenuView;
import view.custom.MenuOptionList;

public class ViewMenu extends MenuView {

	public ViewMenu(Stage stage){
		super("View");
		MenuOptionList themeMenu = new MenuOptionList("Look and Feel");
		themeMenu.setOptions(Arrays.asList("Dracula", "Live"), "Dracula", option -> {
            
        });
		addItems(themeMenu.getView());
	}

}
