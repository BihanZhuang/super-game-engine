package view.editor.hud.hudelementmolds;

import java.util.Random;

import hudelement.HUDElement;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.editor.hud.HUDCanvasController;
import view.editor.hud.HUDEditorOverlord;

/**
 * The purpose of a Hud element
 * 
 * An abstract class defining the methods that are necessary for all hud
 * element templates
 */
public abstract class HUDElementMold{
	private double myElementWidth, myElementHeight;
	private double myXPos, myYPos;
	private Pane myPane;
	private String myKey;
//	private ResizablePane myResizableContainer;
	private HUDElement myHudElement;
	private HUDEditorOverlord editorController;
//	private Map<String, HUDElementMold> canvasMoldMap;
	private HUDCanvasController canvasController;
	private Pane popUpPane;
	private Scene myScene;
	private Stage myStage;
	private VBox layout;
	private Button submit;
	
	public HUDElementMold(double width, double height, double xPos, double yPos, HUDCanvasController canvC){

		setupScene();
		myElementWidth = width;
		myElementHeight = height;
		myXPos = xPos;
		myYPos = yPos;
		canvasController = canvC;
		myKey = makeRandomString();
//		myResizableContainer = setUpResizeablePane();
		myPane = new Pane();
		myPane.setPrefWidth(width);
		myPane.setPrefHeight(height);
//		canvasMoldMap = moldMap;
		updatePanePosition();
		setOnClick();
		setDragBehavior();
		showPopUpStartingInfo();
	}
	
	protected Stage getStage(){
		return myStage;
	}
	
	protected VBox getLayout(){
		return layout;
	}
	
	protected Button getButton(){
		return submit;
	}
	
	private void setupScene(){
		popUpPane = new Pane();
		myScene = new Scene(popUpPane);
		myStage = new Stage();
		myStage.setScene(myScene);
		myStage.setHeight(500);
		myStage.setWidth(500);
		layout = new VBox();
		layout.setPrefWidth(500);
		submit = new Button("Submit");
		popUpPane.getChildren().add(layout);


	}
	
	public String getMyKey(){
		return myKey;
	}
	
	private String makeRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder builder = new StringBuilder();
        Random rnd = new Random();
        while(true){
            while (builder.length() < 6) { // length of the random string.
                int index = (int) (rnd.nextFloat() * characters.length());
                builder.append(characters.charAt(index));
            }
            if(!canvasController.getMoldMap().containsKey(builder.toString())){
            	break;
            } else{
            	builder.delete(0, builder.length() - 1);
            }
        }
        String randomString = builder.toString();
        return randomString;

    }
	
	private void setDragBehavior(){
		myPane.setOnDragDetected(e -> startDrag(e));
	}
	
	private void startDrag(MouseEvent e){
		Dragboard d = myPane.startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		content.putString(myKey);
		d.setContent(content);
		canvasController.getPane().getChildren().remove(myPane);
		e.consume();
	}
	
//	private ResizablePane setUpResizeablePane(){
//		Pane n = new Pane();
//		n.setPrefWidth(myElementWidth);
//		n.setPrefHeight(myElementHeight);
//		ResizablePane s = new ResizablePane(n);
//		return s;
//	}
	
	private void setOnClick(){
		myPane.setOnMouseClicked(e -> showPopUpInformation());
	}
	
	public double getXPos(){
		return myXPos;
	}
	
	public double getYPos(){
		return myYPos;
	}
	
	protected abstract void showPopUpStartingInfo();
	
	protected abstract void showPopUpInformation();
	
	/**
	 * returns the hudeditorcontroller for use in displaying the HUD element mold's
	 * hud element's properties
	 * @return
	 */
	public HUDEditorOverlord getEditorController(){
		return editorController;
	}
	
	private void updatePanePosition(){
		myPane.setTranslateX(myXPos);
		myPane.setTranslateY(myYPos);
	}
	
	public void setPosition(double xPos, double yPos){
		myXPos = xPos;
		myYPos = yPos;
		myHudElement.setXPos(myXPos);
		myHudElement.setYPos(myYPos);
	}
	
	/**
	 * sets the xPos of the HUDElementMold and the HUDElement
	 * @param xPos
	 */
	public void setXPos(double xPos){
		myXPos = xPos;
		myHudElement.setXPos(xPos);
	}
	
	/**
	 * sets the yPos of the HUDElementMold and the HUDElement
	 * @param yPos
	 */
	public void setYPos(double yPos){
		myYPos = yPos;
		myHudElement.setYPos(yPos);
	}
	
	/**
	 * sets width of the HUDElementMold and the HUDElement
	 */
	public void setWidth(double w){
		myElementWidth = w;
		myHudElement.setWidth(w);
	}
	
	/**
	 * sets height of the HUDElementMold and the HUDElement
	 * @param h
	 */
	public void setHeight(double h){
		myElementHeight = h;
		myHudElement.setHeight(h);
	}
	
	/**
	 * gets width of HUDElementMold
	 * @return
	 */
	public double getWidth(){
		return myElementWidth;
	}
	
	/**
	 * gets height of HUDElementMold
	 * @return
	 */
	public double getHeight(){
		return myElementHeight;
	}
	
	public Pane getPane(){
		return myPane;
	}

	protected void setHUDElement(HUDElement elem) {
		myHudElement = elem;
	}
	
	public HUDElement getHudElement(){
		return myHudElement;
	}
	
}
