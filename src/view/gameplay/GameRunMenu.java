package view.gameplay;

import controller.HostControl;
import util.view.ControlFactory;
import view.MenuView;

public class GameRunMenu extends MenuView {

    public GameRunMenu(HostControl controller) {
        super("Run");
        addItems(
                ControlFactory.createMenuItem("Run", e -> controller.run()),
                ControlFactory.createMenuItem("Pause", e -> controller.pause())
                );
    }

}
