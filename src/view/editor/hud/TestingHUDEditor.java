package view.editor.hud;

import javafx.application.Application;
import javafx.stage.Stage;
import model.HUDConfiguration;

public class TestingHUDEditor extends Application {
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		HUDConfiguration n = new HUDConfiguration();
		HUDEditorView h = new HUDEditorView(1000, 800, n);
		h.show();
	}
	
}
