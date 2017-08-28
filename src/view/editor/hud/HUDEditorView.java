package view.editor.hud;


import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.HUDConfiguration;
import util.view.ConstraintsFactory;



/**
 * HUDEditorView is for the user to use to edit the HUD in their game.
 * It is a stage that will show and hid itself based on if the user
 * brings it up to edit their HUD
 */

public class HUDEditorView {
	public static final double HUD_EDITOR_WIDTH = 1500;
	public static final double HUD_EDITOR_HEIGHT = 1000;

	private Stage myHudEditorWindow;
	private Scene myScene;
	private GridPane myGridPane;
	private HUDEditorOverlord myController;
	private HUDInventoryView myInventory;
	
	private HUDCanvasView myHudCanvasView;
	
	private double myCanvasWidth, myCanvasHeight;
	
	public HUDEditorView(double canvasWidth, double canvasHeight, 
			HUDConfiguration hudConfig){
		myGridPane = setupGridPane();
		myScene = createScene(myGridPane);
		myHudEditorWindow = setupStage(myScene);
		
		myController = new HUDEditorOverlord();
		
		myHudCanvasView = new HUDCanvasView(canvasWidth, canvasHeight, hudConfig, myController);
		Pane HudCanvasViewPane = myHudCanvasView.getPane();
		
		myInventory = new HUDInventoryView(myController);
		
//		Pane test = new Pane();
//		test.setPrefSize(100, 1000);
//		test.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		setConstraints();
		myGridPane.add(myInventory.getPane(), 1, 0);
		GridPane.setHalignment(myInventory.getPane(), HPos.RIGHT);

		myGridPane.add(myHudCanvasView.getPane(), 0, 0);
		GridPane.setHalignment(HudCanvasViewPane, HPos.CENTER);
		GridPane.setValignment(HudCanvasViewPane, VPos.CENTER);
		
		


	}
	
	private void setConstraints(){
		myGridPane.getColumnConstraints().addAll(ConstraintsFactory.getColumnConstraints(1400),
				ConstraintsFactory.getColumnConstraints(100)
		);
		
	}
	
	public void show(){
		myHudEditorWindow.show();
	}
	
	/**
	 * change the size of the play screen (HUD Canvas)
	 * @param width
	 * @param height
	 */
	public void setPlayScreenSize(double width, double height){
		myCanvasWidth = width;
		myCanvasHeight = height;
		updateHUDCanvasSize();
	}
	
	/**
	 * change the width of the play screen (HUD Canvas)
	 * @param w
	 */
	public void setPlayScreenWidth(double w){
		myCanvasWidth = w;
		updateHUDCanvasSize();
	}
	
	/**
	 * change the height of the play screen (HUD Canvas)
	 * @param h
	 */
	public void setPlayScreenHeight(double h){
		myCanvasHeight = h;
		updateHUDCanvasSize();
	}
	
	/**
	 * updates the Canvas's prefheight and prefwidth
	 */
	private void updateHUDCanvasSize(){
		myHudCanvasView.setCanvasSize(myCanvasWidth, myCanvasHeight);
	}
	
	private GridPane setupGridPane(){
		GridPane g = new GridPane();
		g.setPrefWidth(HUD_EDITOR_WIDTH);
		g.setPrefHeight(HUD_EDITOR_HEIGHT);
		return g;
	}
	
	
	private Stage setupStage(Scene scene){
		Stage s = new Stage();
		s.setResizable(false);
		s.setScene(scene);
		return s;
	}
	
	private Scene createScene(Pane pane){
		Scene s = new Scene(pane);
//		s.setFill(Color.web("#323232"));
		return s;
	}
	
	
	
	
}