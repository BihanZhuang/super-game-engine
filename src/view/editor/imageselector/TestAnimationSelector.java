package view.editor.imageselector;

import gameobject.component.Animation;
import gameobject.component.Component;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestAnimationSelector extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Component c = new Animation();
		AnimationImageSelectorView hi = new AnimationImageSelectorView(c);
	}
}
