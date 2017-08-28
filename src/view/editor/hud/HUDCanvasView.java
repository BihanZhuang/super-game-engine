package view.editor.hud;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.HUDConfiguration;

public class HUDCanvasView {
	/*
	 * Implementation notes
	 * 
	 * TODO: ArrayList for holding the HUDMolds so that they can be easily removed
	 * TODO: OnDragDropped needs to 
	 * TODO: BarElementMolds need to popup a stage that has things for setting the TYPE for the bar and
	 * the component that the bar will be attached to. 
	 * 
	 * TODO: ScoreElementMolds need to 
	 * TODO: StaticImageElementMold needs to be resizable and allowed to reflect the actual thing
	 * 
	 * TODO: HUDElements need to be able to be added to the Configuration
	 * TODO: Molds need to use resizablePanes
	 */
	
	private HUDCanvasController myController;

	private Pane myPane;
	private double myWidth, myHeight;
	private Button saveButton;
	
	public HUDCanvasView(double width, double height, HUDConfiguration hudConfig, HUDEditorOverlord o){
		myPane = new Pane();
		myPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		setCanvasSize(width, height);
		saveButton = new Button("Save");
		myPane.getChildren().add(saveButton);
		myController = new HUDCanvasController(hudConfig, myPane, o, saveButton);
		
	}
	
	public void setCanvasSize(double width, double height){
		myPane.setPrefWidth(width);
		myPane.setPrefHeight(height);
		myPane.setMaxWidth(width);
		myPane.setMaxHeight(height);
//		myPane.setMinwIDTH(width);
//		myPane.setPrefHeight(height);
	}
	
	public HUDCanvasController getController(){
		return myController;
	}
	
	public Pane getPane(){
		return myPane;
	}
	

}
