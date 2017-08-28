package view.editor.view;

import javafx.stage.Stage;
import model.Game;
import model.IGame;
import model.Inventory;
import model.World;
import view.editor.base.EditorBase;
import view.editor.view.core.EditorCoreView;
import view.editor.view.decorator.MenuBarDecorator;

public class EditorView {

	private EditorBase<?> editor;

	public EditorView(Stage stage) {
		Inventory inventory = new Inventory();
		IGame game = new Game(new World());
		editor = new MenuBarDecorator(new EditorCoreView(stage, game, inventory));
	}

	public void show() {
		editor.show();
	}

}
