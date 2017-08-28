package view.gameplay;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;

import gameobject.Template;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.ChampionPool;
import util.view.ConstraintsFactory;

public class ChampionSelector {
	
	private static final double SCREEN_COL_RATIO = 0.7;
	private static final double SCREEN_ROW_RATIO = 0.5;
	
	private Stage myStage;
	private GridPane myRoot;
	private Template myChampion;
	private Scene myScene;
	
	public ChampionSelector(ChampionPool pool){
		initScene();
		initChampionPool(pool);
		initText();
	}
	
	
	private void initChampionPool(ChampionPool pool) {
		myRoot.getChildren().clear();
		HBox box = new HBox();
		for(Template template:pool.getChampions()){
			ImageView imageview = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(template.getImagePath())));
			imageview.setFitHeight(myRoot.getHeight()*SCREEN_ROW_RATIO);
			imageview.setFitWidth(myRoot.getHeight()*SCREEN_ROW_RATIO);
			box.getChildren().add(imageview);
			imageview.setOnMouseClicked(e->{
				myChampion = template;
				myStage.hide();
			});
		}
//		ImageView imageview = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("Mario1.png")));
//		imageview.setFitHeight(myRoot.getHeight()*SCREEN_ROW_RATIO);
//		imageview.setFitWidth(myRoot.getHeight()*SCREEN_ROW_RATIO);
//		box.getChildren().add(imageview);
//		imageview.setOnMouseClicked(e->{
//			myChampion = new Hero();
//			myStage.hide();
//		});
//		
//		ImageView imageview2 = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("Mario2.png")));
//		imageview2.setFitHeight(myRoot.getHeight()*SCREEN_ROW_RATIO);
//		imageview2.setFitWidth(myRoot.getHeight()*SCREEN_ROW_RATIO);
//		box.getChildren().add(imageview2);
//		imageview2.setOnMouseClicked(e->{
//			myChampion = pool.getChampionFromImage(imageview2.getImage());
//			myStage.hide();
//		});
		
		myRoot.add(box, 0, 1);
	}


	private void initText() {
		Text title = new Text("Please select your hero");
		title.setFont(Font.font(30));
		title.setFill(Color.WHEAT);
		myRoot.add(title,0,0);
		
	}

	private void initScene() {
		myStage = new Stage();
		DisplayMode mode = 
		        GraphicsEnvironment.getLocalGraphicsEnvironment().
		        getDefaultScreenDevice().getDisplayMode();
		myStage.setWidth(mode.getWidth() * SCREEN_COL_RATIO);
		myStage.setHeight(mode.getHeight() * SCREEN_ROW_RATIO);
        myRoot = createGrid(myStage);
        myStage.setScene(createScene(myRoot));
	}
	
	private GridPane createGrid(Stage stage) {
        GridPane grid = new GridPane();
        grid.prefWidthProperty().bind(stage.widthProperty());
        grid.prefHeightProperty().bind(stage.heightProperty());
       
        grid.getRowConstraints().addAll(
                ConstraintsFactory.getRowConstraints(30),
                ConstraintsFactory.getRowConstraints(70)
                );
        grid.setStyle("-fx-background-color:#000000 ;");
        return grid;
    }
	
	private Scene createScene(Pane root) {
        myScene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
        return myScene;
    }
	
	public Template show(){
		myStage.setFullScreen(false);
		myStage.showAndWait();
		return myChampion;
    }
}
