package view.editor.base;

import javafx.stage.Stage;
import model.IGame;
import model.Inventory;

public interface IEditor {
    public static final String STYLESHEET = "dracula.css";

	public IGame getGame();
	
	public Inventory getInventory();
	
	public Stage getStage();

	public void show();
}
