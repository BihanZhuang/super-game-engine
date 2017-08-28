package view.editor.hud;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import hudelement.HUDElement;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import model.HUDConfiguration;
import util.reflection.Reflection;
import view.editor.hud.hudelementmolds.HUDElementMold;

public class HUDCanvasController {
    
	public static final String HUD_ELEMENT_MOLD_PACKAGE = "view.editor.hud.hudelementmolds.";
	
	private Set<HUDElement> myHudElementSet;
	private Map<String, HUDElementMold> myMoldMap;
	private HUDConfiguration hudConfiguration;
	private Pane myHUDCanvasPane;
	private HUDEditorOverlord parentController;
	private Button saveButton;

	public HUDCanvasController(HUDConfiguration h, Pane canvas, HUDEditorOverlord pController, Button save){
		saveButton = save;
		myHudElementSet = new HashSet<HUDElement>();
		myMoldMap = new HashMap<String, HUDElementMold>();
		hudConfiguration = h;
		myHUDCanvasPane = canvas;
		parentController = pController;
		setupDragEvents();
		setSaveButton();
	}
	
	private void setSaveButton(){
		saveButton.setOnAction(e -> setConfigHUDElementSet());
	}
	
	private void setConfigHUDElementSet(){
		myHudElementSet.clear();
		for(HUDElementMold mold : myMoldMap.values()){
			myHudElementSet.add(mold.getHudElement());
		}
		hudConfiguration.replaceElementsSet(myHudElementSet);
	}
	
	private void setupDragEvents(){
//		Point2D mouseLocation = myHUDCanvasPane.sceneToLocal(e.getSceneX(), e.getSceneY());
		myHUDCanvasPane.setOnDragOver(e -> onDragOver(e));
		myHUDCanvasPane.setOnDragDropped(e -> onDragDropped(e));
	}
	
	private void onDragOver(DragEvent e){
		e.acceptTransferModes(TransferMode.ANY);
		e.consume();
	}
	
	private void onDragDropped(DragEvent e){
		Dragboard d = e.getDragboard();
		String stringFromDragContent = d.getString();
//		System.out.println(stringFromDragContent);
		Point2D mouseLocation = myHUDCanvasPane.sceneToLocal(e.getSceneX(), e.getSceneY());
		if(myMoldMap.containsKey(stringFromDragContent)){
			HUDElementMold m = myMoldMap.get(stringFromDragContent);
			m.getPane().setTranslateX(mouseLocation.getX());
			m.getPane().setTranslateY(mouseLocation.getY());
			myHUDCanvasPane.getChildren().add(m.getPane());
			m.setPosition(mouseLocation.getX(), mouseLocation.getY());
		}
		if(!myMoldMap.containsKey(stringFromDragContent)){
			HUDElementMold mold = (HUDElementMold) Reflection.createInstance(HUD_ELEMENT_MOLD_PACKAGE + stringFromDragContent + "ElementMold", mouseLocation.getX(), 
					mouseLocation.getY(), this);
			Pane moldPane = mold.getPane();
			myHUDCanvasPane.getChildren().add(moldPane);
			myMoldMap.put(mold.getMyKey(), mold);
		}		
		e.setDropCompleted(true);
		e.consume();
	}
	
	public Map<String, HUDElementMold> getMoldMap(){
		return myMoldMap;
	}
	
	public Pane getPane(){
		return myHUDCanvasPane;
	}
	
}