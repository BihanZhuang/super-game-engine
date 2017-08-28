package view.gameplay;

import controller.HostControl;
import util.view.ControlFactory;
import view.MenuView;

public class GameFileMenu extends MenuView {

    public GameFileMenu(HostControl controller) {
        super("File");
        addItems(
                ControlFactory.createMenuItem("Save Game", e -> controller.saveGame()),
                ControlFactory.createMenuItem("Load Game", e -> controller.loadGame()),
                ControlFactory.createMenuItem("Save Record", e -> controller.saveRecord()),
                ControlFactory.createMenuItem("Load Record", e -> controller.loadRecord())
                );
    }

}
