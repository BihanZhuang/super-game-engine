package view;

import java.io.IOException;

import controller.Controller;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Constants;
import util.view.ControlFactory;

public class GUI {
	public static final double HEIGHT_RATIO = 0.9;
	public static final double ASPECT_RATIO = 1.3;
	public static final double LEFT_CONSTRAINT = 75;
	public static final double TOP_CONSTRAINT = 55;
	public static final String MUSICFILE = "music/world.mp3";

	private GridPane myRoot;
	private Controller myControl;
	private Stage myStage;
	private Sound myMusic;

	public GUI(Stage stage) {
		myStage = stage;
		myControl = new Controller();
		myRoot = createRoot();
		initView();
		myMusic = new Sound(MUSICFILE);
		myMusic.play();
	}

	public void show() {
		myStage.setScene(createScene());
		myStage.show();
	}

	private GridPane createRoot() {
		GridPane root = new GridPane();
		root.setPrefHeight(300);
		root.setPrefWidth(260);
		root.getColumnConstraints().addAll(new ColumnConstraints(50), new ColumnConstraints(160),
				new ColumnConstraints(50));
		root.getRowConstraints().addAll(new RowConstraints(80), new RowConstraints(50), new RowConstraints(50),
				new RowConstraints(50),new RowConstraints(50));
		return root;
	}

	private Scene createScene() {
		Scene scene = new Scene(myRoot);
        scene.getStylesheets().add(Constants.RESOURCE_PACKAGE + Constants.DRACULA_STYLE);
		return scene;
	}

	private void initView() {
		Text title = new Text("VOOGASalad");
		title.setFont(Font.font(30));
		title.getStyleClass().add("my-title");
		myRoot.add(ControlFactory.createButton("Make my own game", e -> {
			//myStage.close();
			myMusic.pause();
			myControl.launchEditor();
		}), 1, 1);
		myRoot.add(ControlFactory.createButton("Play game", e -> {
			try {
				//myStage.close();
				myMusic.pause();
				myControl.launchGame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}), 1, 2);
		myRoot.add(ControlFactory.createButton("Connect to game", e -> {
			try {
				//myStage.close();
				myMusic.pause();
				myControl.connectToGame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}), 1, 3);
		myRoot.add(ControlFactory.createButton("Join chat room", e -> myControl.launchChat()), 1, 4);
		myRoot.add(title, 1, 0);
		Animation animation = addAnimation(title);
        animation.play();
	}

	private Animation addAnimation(Text title) {
		Animation animation = new Transition() {	
            {
                setCycleDuration(Duration.millis(100000));
                setInterpolator(Interpolator.LINEAR);
            }
            @Override
            protected void interpolate(double frac) {
                Color vColor = new Color(Math.abs(Math.sin(10*frac)), Math.abs(Math.sin(5*frac)), 1-Math.abs(Math.sin(10*frac)),1);
                Color sColor = new Color(1-vColor.getRed(),1-vColor.getGreen(),1-vColor.getBlue(),1);
                title.setFill(sColor);
                myRoot.setBackground(new Background(new BackgroundFill(vColor, null, null)));
            }
        };
		return animation;
	}

}
