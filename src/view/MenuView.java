package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public abstract class MenuView extends ViewBase<Menu> {

    public MenuView(String title) {
        super(new Menu(title));
    }

    protected void addItems(MenuItem... elements) {
        getView().getItems().addAll(elements);
    }

}
