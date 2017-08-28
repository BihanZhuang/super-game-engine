package view.editor.menu.edit;

import model.IGame;
import model.Inventory;
import util.view.ControlFactory;
import view.MenuView;
import view.editor.attribute.CustomObjectView;

public class EditorEditMenu extends MenuView {
    
    public EditorEditMenu(Inventory inventory, IGame game) {
    	super("Edit");
    	addItems(ControlFactory.createMenuItem("Create template", e -> new CustomObjectView(inventory).show()),
    			ControlFactory.createMenuItem("Modify Constants", e -> new ConstantModifier(game.getWorld()).show()));
    }
    
}
