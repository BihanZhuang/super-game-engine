package view.editor.menu.game;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.IGame;
import util.Constants;

/**
 * View for editing goals
 * @author Yanbo Fang
 *
 */
public class CustomGoalView {

	private static final double SCREEN_COL_RATIO = 0.4;
	private static final double SCREEN_ROW_RATIO = 0.6;
	
	private Stage myStage;
	private GridPane myRoot;
	private GoalFactory myFactory;
	private Scene myScene;
	private IGame myGame;

	public CustomGoalView(IGame game) {
		myGame = game;
		myFactory = new GoalFactory();
		myStage = createStage();
		myRoot = createGridPane();		
		myScene = createScene();
		myStage.setScene(myScene);
		myStage.show();
	}

	private Scene createScene() {
		Scene scene = new Scene(myRoot, myRoot.getPrefWidth(), myRoot.getPrefHeight());
		scene.getStylesheets().add(Constants.RESOURCE_PACKAGE + "dracula.css");
		return scene;
	}

	private GridPane createGridPane() {
		GridPane grid = new GridPane();
		grid.prefWidthProperty().bind(myStage.widthProperty());
		grid.prefHeightProperty().bind(myStage.heightProperty());
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Label label1 = new Label("Target Weight: ");
		grid.add(label1, 0, 1);
		TextField parameter1 = new TextField();
		grid.add(parameter1, 1, 1);
		Label label2 = new Label("Goal Weight: ");
		grid.add(label2, 0, 2);
		TextField parameter2 = new TextField();
		grid.add(parameter2, 1, 2);
		Label label3 = new Label();
		label3.setVisible(false);
		grid.add(label3, 0, 3);
		TextField parameter3 = new TextField();
		parameter3.setVisible(false);
		grid.add(parameter3, 1, 3);
		ComboBox<String> box = createComboBox(label3, parameter3);
		grid.getChildren().add(box);

		HBox hbBtn = createHBoxButton(parameter1, parameter2, parameter3, box);
		grid.add(hbBtn, 1, 4);

		return grid;
	}
	
	private HBox createHBoxButton(TextField parameter1, TextField parameter2, TextField parameter3, ComboBox<String> box){
		Button btn = new Button("Submit");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					myGame.getLevelInfo().setGoalWeight(Double.parseDouble(parameter1.getText()));
					myFactory.setWeight(Double.parseDouble(parameter2.getText()));
					if (parameter3.getText() != null && parameter3.isVisible()) {
						myFactory.setParameter(Double.parseDouble(parameter3.getText()));
					}
				} catch (NumberFormatException e) {
					Alert fail = new Alert(AlertType.INFORMATION);
					fail.setHeaderText("Failure");
					fail.setContentText("Wrong type of input");
					fail.showAndWait();
				}
				myGame.getLevelInfo().addGoal(myFactory.getGoal(box.getValue()));
				myStage.hide();
			}
		});
		return hbBtn;
	}

	private ComboBox<String> createComboBox(Label text, TextField input) {
		ComboBox<String> box = new ComboBox<String>();
		box.setPromptText("Goals");
		box.getItems().addAll(GoalFactory.GOALS);
		box.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue == GoalFactory.BOSS || newValue == GoalFactory.DESTINATION) {
					text.setVisible(false);
					input.setVisible(false);
				} else {
					text.setVisible(true);
					input.setVisible(true);
					if (newValue == GoalFactory.MONSTER) {
						text.setText("Amount of Monsters to kill: ");
					} else if (newValue == GoalFactory.SCORE) {
						text.setText("Score to reach : ");
					} else if (newValue == GoalFactory.TIME) {
						text.setText("Time limit: ");
					}
				}
			}
		});
		return box;
	}

	private Stage createStage() {
		Stage s = new Stage();

		DisplayMode mode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
		s.setWidth(mode.getWidth() * SCREEN_COL_RATIO);
		s.setHeight(mode.getHeight() * SCREEN_ROW_RATIO);
		
		return s;
	}
}
