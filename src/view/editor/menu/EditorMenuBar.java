package view.editor.menu;

import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import model.IGame;
import model.Inventory;
import view.ViewBase;
import view.editor.menu.edit.EditorEditMenu;
import view.editor.menu.game.GameMenu;
import view.editor.menu.help.HelpMenu;
import view.editor.menu.inventory.InventoryMenu;
import view.editor.menu.view.ViewMenu;

/**
 * 
 * @author
 * @author Mike Liu
 *
 */
public class EditorMenuBar extends ViewBase<MenuBar> {
    
	public static final int HEIGHT = 12;
	
	public EditorMenuBar(double width, Stage stage, IGame game, Inventory inventory){
	    super(new MenuBar());
		getView().setPrefSize(width, HEIGHT);	
		getView().getMenus().addAll(
		        new InventoryMenu(stage, inventory).getView(),
		        new GameMenu(stage, game).getView(),
		        new EditorEditMenu(inventory, game).getView(),
		        new ViewMenu(stage).getView(),
		        new HelpMenu().getView()
		        );
	}
	
}
