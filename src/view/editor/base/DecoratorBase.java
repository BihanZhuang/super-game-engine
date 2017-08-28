package view.editor.base;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.IGame;
import model.Inventory;
import util.Constants;

public class DecoratorBase<T extends Pane> extends EditorBase<T>{

	private EditorBase<?> base;
	
	public DecoratorBase(T layout, EditorBase<?> base) {
		super(layout, base.getWidth(), base.getHeight());
		this.base = base;
	}

	@Override
	public IGame getGame() {
		return base.getGame();
	}

	@Override
	public Inventory getInventory() {
		return base.getInventory();
	}

	@Override
	public Stage getStage() {
		return base.getStage();
	}

	@Override
    public void show() {
    	 Scene scene = new Scene(getView());
         scene.getStylesheets().add(Constants.RESOURCE_PACKAGE + STYLESHEET);
         getStage().setScene(scene);
         getStage().show();
    }

}
