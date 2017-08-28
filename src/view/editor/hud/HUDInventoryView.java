package view.editor.hud;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class HUDInventoryView {
	public static final double HUD_INVENTORY_WIDTH = 100;
	
	private StackPane myPane;
	private HUDEditorOverlord myHudEditorController;
	private VBox myVBox;
	
	private Pane barButton, imageButton;
	
	public HUDInventoryView(HUDEditorOverlord o){
		myHudEditorController = o;
		myPane = new StackPane();
		myVBox = new VBox();
		
		setButtons();
//		setButtonActions();
		
		
		myPane.setPrefWidth(HUD_INVENTORY_WIDTH);
		myPane.setPrefHeight(HUDEditorView.HUD_EDITOR_HEIGHT);
		
		myPane.setMinHeight(HUDEditorView.HUD_EDITOR_HEIGHT);
		
		myPane.setBackground(new Background(new BackgroundFill(Color.web("#323232"), CornerRadii.EMPTY, Insets.EMPTY)));
		
		myPane.getChildren().add(myVBox);
		StackPane.setAlignment(myVBox, Pos.BOTTOM_LEFT);

		
	}
	
	private void setButtons(){
		HUDInventoryItem barButton = new HUDInventoryItem("Bar");
		HUDInventoryItem imageButton = new HUDInventoryItem("StaticDecorativeImage");

		myVBox.getChildren().addAll(barButton.getPane(), imageButton.getPane());
	}
	
//	private void setButtonActions(){
//		barButton.setOnAction(e -> setBarButton());
//		imageButton.setOnAction(e -> setImageButton());
//	}
	
	private void setImageButton(){
		myHudEditorController.setCurrentMold("Image");
	}
	
	private void setBarButton(){
		myHudEditorController.setCurrentMold("Bar");
	}
	
	private void setupLayout(){
		myVBox.setPrefWidth(HUD_INVENTORY_WIDTH);
	}
	
	
	public Pane getPane(){
		return myPane;
	}
}
