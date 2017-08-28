package view.editor.canvas;

import java.util.Collection;
import java.util.function.Consumer;

import gameobject.ObjectInfo;
import javafx.scene.layout.Pane;
import model.Inventory;
import model.WorldInfo;
import util.Constants;
import util.Observable;
import util.Observer;
import view.PaneViewBase;
import view.custom.dragndrop.DragGesture;
import view.custom.dragresize.ImageResizablePane;
import view.editor.canvas.grid.Grid;

/**
 * CanvasView is the section where user could drag their game objects onto and
 * change position and attribute. It always binds to the size of its parent,
 * which supposedly is the CanvasStack. It is observed by the MinimapView to
 * give the user an idea of the whole game design.
 * 
 * @author Feng
 * @author Mike Liu
 *
 */
public class CanvasView extends PaneViewBase<Pane> implements Observable<CanvasView> {

	private CanvasController controller;
	private Observer<CanvasView> myMinimapObserver;
	private DragGesture dragGesture;

	public CanvasView(Layer layer, Pane parent, WorldInfo world, Inventory inventory,
			Consumer<ObjectInfo> objectClickHandler) {
		super(new Pane(), parent.getPrefWidth(), parent.getPrefHeight());
		controller = new CanvasController(getView(), objectClickHandler);
		dragGesture = new DragGesture(getView(), parent);

		initView(layer, parent); // TODO

		world.addInfoObserver(arg -> {
			controller.updateGameObject(arg);
			notifyObservers(this);
		});
		world.getStartConfig().addObserver(arg -> {
			controller.updateChampion(arg);
			notifyObservers(this);
		});
		getView().setOnDragOver(e -> {
			controller.onDragOver(e);
		});
		getView().setOnDragDropped(e -> {
			controller.onDragDropped(e, world, inventory);
			notifyObservers(this);
		});
	}

	public Collection<ImageResizablePane> getImages() {
		return controller.getObjectImages();
	}

	private void initView(Layer layer, Pane parent) {
		getView().setId("Canvas");
		getView().prefWidthProperty().bind(parent.prefWidthProperty());
		getView().prefHeightProperty().bind(parent.prefHeightProperty());

		// Add Grid
		Grid grid = new Grid(getView().prefWidthProperty(), getView().prefHeightProperty());
		grid.setUnit(Constants.CELL_SIZE);
		getView().getChildren().add(grid.draw());
	}

	@Override
	public void addObserver(Observer<CanvasView> obs) {
		myMinimapObserver = obs;
		myMinimapObserver.update(this);
	}

	@Override
	public void removeObserver(Observer<CanvasView> obs) {
		myMinimapObserver = null;
	}

	@Override
	public void notifyObservers(CanvasView arg) {
		myMinimapObserver.update(arg);
	}

}