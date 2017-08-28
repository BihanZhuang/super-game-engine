package view.editor.view.core;

import gameobject.basics.Enemy;
import gameobject.basics.FireflowerBrick;
import gameobject.basics.Hero;
import gameobject.basics.MushroomBrick;
import gameobject.basics.Stone;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.IGame;
import model.Inventory;
import util.Constants;
import util.view.ConstraintsFactory;
import view.Sound;
import view.custom.ZoomGesture;
import view.custom.dragndrop.DragGesture;
import view.custom.dragresize.RegionResizablePane;
import view.custom.dragresize.ResizablePane;
import view.editor.attribute.AttributeEditorView;
import view.editor.base.EditorBase;
import view.editor.canvas.CanvasStack;
import view.editor.inventory.InventoryView;
import view.editor.minimap.MinimapView;

/**
 * 
 * Create the game authoring environment UI and pass in the Model. Inside the
 * Model a brand new World should be initialized and Inventory will contain all
 * the prototypes for the game authoring. ********************* * * * * * *
 * ********************* * * * * * * ********************* * * * * * * * * *
 * *********************
 * 
 * @author Feng
 * @author Mike Liu
 *
 */
public class EditorCoreView extends EditorBase<GridPane> {

	public static final double SCREEN_RATIO = 0.9;
	public static final double COLUMN_CONSTRAINT1 = 70;
	public static final double COLUMN_CONSTRAINT2 = 30;
	public static final double ROW_CONSTRAINT1 = 30;
	public static final double ROW_CONSTRAINT2 = 40;
	public static final double ROW_CONSTRAINT3 = 30;
	public static final double COLUMN_CONSTRAINT1_FRACTION = .7;
	public static final double COLUMN_CONSTRAINT2_FRACTION = .3;
	public static final double ROW_CONSTRAINT1_FRACTION = .3;
	public static final double ROW_CONSTRAINT2_FRACTION = .4;
	public static final double ROW_CONSTRAINT3_FRACTION = .3;
	public static final String MUSICFILE = "music/world2.mp3";

	private Stage myStage;
	private IGame myGame;
	private Inventory myInventory;
	private Sound myMusic;

	public EditorCoreView(Stage stage, IGame game, Inventory inventory) {
		super(new GridPane(), stage.getWidth(), stage.getHeight());
		myStage = stage;
		myGame = game;
		// TODO
		myInventory = inventory;
		myInventory.addTemplate(new Hero("mario.gif"));
		myInventory.addTemplate(new Stone("Brick.png"));
		myInventory.addTemplate(new MushroomBrick("PowerupBlock.png"));
		myInventory.addTemplate(new FireflowerBrick("PowerupBlock.png"));
		myInventory.addTemplate(new Enemy("EvilMushroom", "EvilMushroom.png"));
		myInventory.addTemplate(new Enemy("Luigi", "Luigi.png"));
		myInventory.addTemplate(new Enemy("Yoshi", "Yoshi.png"));
		initializeGrid(stage.getWidth(), stage.getHeight());
		myMusic = new Sound(MUSICFILE);
		myMusic.play();
	}

	private void initializeGrid(double totalWidth, double totalHeight) {
		GridPane grid = this.getView();

		grid.getColumnConstraints().addAll(ConstraintsFactory.getColumnConstraints(COLUMN_CONSTRAINT1),
				ConstraintsFactory.getColumnConstraints(COLUMN_CONSTRAINT2));
		grid.getRowConstraints().addAll(ConstraintsFactory.getRowConstraints(ROW_CONSTRAINT1),
				ConstraintsFactory.getRowConstraints(ROW_CONSTRAINT2),
				ConstraintsFactory.getRowConstraints(ROW_CONSTRAINT3));

		AttributeEditorView attrEditor = new AttributeEditorView(totalWidth * COLUMN_CONSTRAINT2_FRACTION,
				totalHeight * (ROW_CONSTRAINT1_FRACTION + ROW_CONSTRAINT2_FRACTION));
		CanvasStack canvasStack = new CanvasStack(totalWidth * COLUMN_CONSTRAINT1_FRACTION,
				totalHeight * (ROW_CONSTRAINT1_FRACTION + ROW_CONSTRAINT2_FRACTION), myGame.getWorld(), myInventory,
				obj -> attrEditor.generateAttributeView(obj));
		ResizablePane resizableCanvas = new RegionResizablePane(canvasStack.getView());
		new DragGesture(getView(), resizableCanvas.getHost()).enable();
		new ZoomGesture(resizableCanvas.getHost()).enable();
		InventoryView inventoryView = new InventoryView(totalWidth * COLUMN_CONSTRAINT1_FRACTION,
				totalHeight * ROW_CONSTRAINT3_FRACTION, myInventory, myGame.getChampionPool());
		MinimapView minimap = new MinimapView(totalWidth * COLUMN_CONSTRAINT2_FRACTION,
				totalHeight * (ROW_CONSTRAINT3_FRACTION), canvasStack);
		// indexList.setObjectHighlightHandler(id -> canvasStack.focus(id));
		grid.add(resizableCanvas.getHost(), 0, 0, 1, 2);
		grid.add(inventoryView.getView(), 0, 2);
		grid.add(attrEditor.getView(), 1, 0, 1, 2);
		grid.add(minimap.getView(), 1, 2);
	}

	@Override
	public Inventory getInventory() {
		return myInventory;
	}

	@Override
	public IGame getGame() {
		return myGame;
	}

	@Override
	public Stage getStage() {
		return myStage;
	}

	@Override
	public void show() {
		Scene scene = new Scene(getView());
		scene.getStylesheets().add(Constants.RESOURCE_PACKAGE + STYLESHEET);
		myStage.setScene(scene);
		myStage.show();
	}

}
