package view.editor.imageselector;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import gameobject.component.Animation;
import gameobject.component.Component;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AnimationImageSelectorController {
	private Animation component;
	//private Animation component;
	private AnimationImageSelectorView view;
	private ImageSelectorSquare stillSquare, moveSquare1, moveSquare2, hurtSquare, jumpSquare, dieSquare;
	private Map<ImageSelectorSquare, Animation.State> squareToStateMap;
	private Stage popupStage;
	
	public AnimationImageSelectorController(Component com, AnimationImageSelectorView v){
		component = (Animation) com;
		view = v;

		setSquareRefs();
		setUpSquareInteraction();
		setImagesIfPresent();
	}
	
	/*
	 * sets the images in the selector if they are already present in the component
	 */
	private void setImagesIfPresent(){
		for(ImageSelectorSquare s : squareToStateMap.keySet()){
			Animation.State stateOfSquare = squareToStateMap.get(s);
			try {
				s.setImage(component.getImagePath(stateOfSquare));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	/*
	 * get squares from the view
	 */
	private void setSquareRefs(){
		stillSquare = view.getStaySquare();
		moveSquare1 = view.getMoveSquare1();
		moveSquare2 = view.getMoveSquare2();
		hurtSquare = view.getInjuredSquare();
		jumpSquare = view.getJumpSquare();
		dieSquare = view.getDieSquare();
	}
	
	/*
	 * connect the squares to their associated states in a Map 
	 * and set up their click handlers
	 */
	private void setUpSquareInteraction(){
		mapSquaresToStates();
		setClickHandlers();
	}
	
	
	/*
	 * map the squares to their corresponding Animation.States
	 */
	private void mapSquaresToStates(){
		squareToStateMap = new HashMap<ImageSelectorSquare, Animation.State>();
		squareToStateMap.put(stillSquare, Animation.State.STAY);
		squareToStateMap.put(moveSquare1, Animation.State.MOVE);
//		squareToStateMap.put(moveSquare2, Animation.State.MOVE);
		squareToStateMap.put(hurtSquare, Animation.State.INJURE);
		squareToStateMap.put(jumpSquare, Animation.State.JUMP);
		squareToStateMap.put(dieSquare, Animation.State.DIE);
	}
	
	/*
	 * set up click handlers
	 */
	private void setClickHandlers(){
    	FileChooser fileChooser = new FileChooser();
		
    	for(ImageSelectorSquare s : squareToStateMap.keySet()){
			s.getSquare().setOnMouseClicked(new EventHandler<MouseEvent>(){
						@Override
						public void handle(MouseEvent event) {
							File file = fileChooser.showOpenDialog(popupStage);
	                        if (file != null) {
	                        	String filePath = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("\\")+1);
	                        	String fileName = file.getName();
	                        	System.out.println(fileName);
//	                        		                        	System.out.println(filePath);
//	                        	if(squareToStateMap.get(s).equals(Animation.State.STAY)){
//		                        	System.out.println("FJDJKFJASDKFJKSAJASDFK");
//	                        	}
	                        	
	                        	//set the Animation Components state's image path to the image selected by the user
								component.add(squareToStateMap.get(s), filePath);
								
								//set the image on the selection square so the user can see the image they selected
		                        s.setImage(fileName);
	                        }
						}
	    				
	    			}
			);
		}
	}
	
	
}
