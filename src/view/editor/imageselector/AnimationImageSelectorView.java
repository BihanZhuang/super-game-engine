package view.editor.imageselector;

import java.util.List;

import gameobject.component.Component;
import javafx.scene.layout.GridPane;
import util.view.ConstraintsFactory;

public class AnimationImageSelectorView {
    
	public final double DEFAULT_IMAGESELECTOR_SIZE = 64;
	public final String DEFAULT_STILL_IMAGE = "Still.png";
	public final String DEFAULT_HURT_IMAGE = "Hurt.png";
	public final String DEFAULT_JUMP_IMAGE = "Jump.png";
	public final String DEFAULT_MOVE1_IMAGE = "Move1.png";
	public final String DEFAULT_MOVE2_IMAGE = "Move2.png";
	public final String DEFAULT_DIE = "Die.png";

//	private Stage imageSelectorPopupStage;
	private GridPane gridPane;
	private ImageSelectorSquare stillSquare, moveSquare1, moveSquare2, hurtSquare, jumpSquare, dieSquare;
	private AnimationImageSelectorController controller;
	private List<ImageSelectorSquare> list;

	public AnimationImageSelectorView(Component com) {
		gridPane = new GridPane();

		createSquares();
		setupLayout();

		controller = new AnimationImageSelectorController(com, this);

	}

	private void createSquares() {
		stillSquare = new ImageSelectorSquare(DEFAULT_STILL_IMAGE, DEFAULT_IMAGESELECTOR_SIZE);
		moveSquare1 = new ImageSelectorSquare(DEFAULT_MOVE1_IMAGE, DEFAULT_IMAGESELECTOR_SIZE);
		moveSquare2 = new ImageSelectorSquare(DEFAULT_MOVE2_IMAGE, DEFAULT_IMAGESELECTOR_SIZE);
		hurtSquare = new ImageSelectorSquare(DEFAULT_HURT_IMAGE, DEFAULT_IMAGESELECTOR_SIZE);
		jumpSquare = new ImageSelectorSquare(DEFAULT_JUMP_IMAGE, DEFAULT_IMAGESELECTOR_SIZE);
		dieSquare = new ImageSelectorSquare(DEFAULT_DIE, DEFAULT_IMAGESELECTOR_SIZE);
	}

	/*
	 * sets layout of the animation image selector
	 */
	private void setupLayout() {
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		gridPane.getColumnConstraints().addAll(ConstraintsFactory.getColumnConstraints(64),
				ConstraintsFactory.getColumnConstraints(64), ConstraintsFactory.getColumnConstraints(64),
				ConstraintsFactory.getColumnConstraints(64)

		);
		gridPane.getRowConstraints().addAll(ConstraintsFactory.getRowConstraints(64),
				ConstraintsFactory.getRowConstraints(64), ConstraintsFactory.getRowConstraints(64));

		gridPane.add(jumpSquare.getSquare(), 1, 0);
		gridPane.add(hurtSquare.getSquare(), 0, 1);
		gridPane.add(stillSquare.getSquare(), 1, 1);
		gridPane.add(moveSquare1.getSquare(), 2, 1);
		gridPane.add(moveSquare2.getSquare(), 3, 1);
		gridPane.add(dieSquare.getSquare(), 1, 2);
	}

//	/*
//	 * Stage for displaying the contents of the scene
//	 */
//	private void setupStage() {
//		imageSelectorPopupStage = new Stage();
//		imageSelectorPopupStage.setResizable(false);
//		imageSelectorPopupStage.setScene(createScene());
//	}

//	/*
//	 * creates the Scene to be displayed on the new stage (window)
//	 */
//	private Scene createScene() {
//		Scene s = new Scene(gridPane, gridPane.getPrefWidth(), gridPane.getPrefHeight());
//		return s;
//	}

	/*
	 * getter for the controller
	 */
	public AnimationImageSelectorController getController() {
		return controller;
	}

	/*
	 * getters for the different squares
	 */
	public ImageSelectorSquare getStaySquare() {
		return stillSquare;
	}

	public ImageSelectorSquare getMoveSquare1() {
		return moveSquare1;
	}

	public ImageSelectorSquare getMoveSquare2() {
		return moveSquare2;
	}

	public ImageSelectorSquare getInjuredSquare() {
		return hurtSquare;
	}

	public ImageSelectorSquare getJumpSquare() {
		return jumpSquare;
	}

	public ImageSelectorSquare getDieSquare() {
		return dieSquare;
	}

	public GridPane getGridPane() {
		return gridPane;
	}

}
