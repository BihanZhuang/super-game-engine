package view.editor.menu.inventory;

import javafx.stage.Stage;
import model.Inventory;
import util.FileSelector;
import util.view.ControlFactory;
import view.MenuView;
import view.editor.attribute.CustomObjectView;

/**
 * Function:
 * 1. Load Existing XML/Asset file
 * 2. Save File in XML/Asset format
 * 3. Load Current Game
 * 
 * @author Feng
 * @author Mike Liu
 *
 */
public class InventoryMenu extends MenuView {

	public InventoryMenu(Stage stage, Inventory inventory) {
		super("Inventory");
		FileSelector inventorySelector = new FileSelector("Inventory", "*.inv");
		addItems(
                ControlFactory.createMenuItem("Load Inventory",
                        e -> inventorySelector.showOpenDialog(stage).ifPresent(inventory::loadInventory)),
                ControlFactory.createMenuItem("Save Inventory",
                        e -> inventorySelector.showSaveDialog(stage).ifPresent(inventory::saveInventory)),
                ControlFactory.createMenuItem("Create template", e -> new CustomObjectView(inventory).show())
                );
	}

}
