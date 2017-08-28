package view.gameplay;

import controller.HostControl;
import javafx.scene.control.MenuBar;
import view.ViewBase;

public class GameMenuBar extends ViewBase<MenuBar> {

    public GameMenuBar(HostControl controller) {
        super(new MenuBar());
        getView().getMenus().addAll(
                new GameFileMenu(controller).getView(),
                new GameRunMenu(controller).getView()
                );
    }

}
