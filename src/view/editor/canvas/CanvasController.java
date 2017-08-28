package view.editor.canvas;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import gameobject.ObjectInfo;
import gameobject.Template;
import gameobject.component.Position;
import gameobject.component.type.ComponentTypes;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import model.Inventory;
import model.WorldInfo;
import util.Constants;
import util.view.ImageProcessor;
import view.custom.dragresize.ImageResizablePane;

public class CanvasController {
	private static final String START_POS_TEXT = "Start%d";

	private Pane canvas;
	private Map<Integer, ImageResizablePane> objectImages;
	private Map<Integer, ImageResizablePane> championImages;
	private Consumer<ObjectInfo> myObjClickHandler;

	public CanvasController(Pane pane, Consumer<ObjectInfo> objClickHandler) {
		canvas = pane;
		objectImages = new HashMap<>();
		championImages = new HashMap<>();
		myObjClickHandler = objClickHandler;
	}

	public void updateGameObject(ObjectInfo obj) {
		update(obj, objectImages, () -> createResizableView(obj, Integer.toString(obj.getID())));
	}

	public void updateChampion(ObjectInfo obj) {
		update(obj, championImages, () -> createChampionView(obj));
	}

	public Collection<ImageResizablePane> getObjectImages() {
		return objectImages.values();
	}

	public Collection<ImageResizablePane> getChampionImages() {
		return championImages.values();
	}

	public void onDragOver(DragEvent e) {
		if (e.getGestureSource() != this && e.getDragboard().hasString()) {
			e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			e.consume();
		}
	}

	public void onDragDropped(DragEvent e, WorldInfo world, Inventory inventory) {
		Dragboard dragBoard = e.getDragboard();
		if (dragBoard.hasString()) {
			Point2D local = canvas.sceneToLocal(e.getSceneX(), e.getSceneY());
			String content = dragBoard.getString();
			double dropX = snap(local.getX()), dropY = snap(local.getY());
			if (content.matches("^\\d+$")) {
				canvas.getChildren().add(updateObjectView(Integer.parseInt(content), dropX, dropY));
			} else if (content.matches("^Start\\d+$")) {
				canvas.getChildren().add(updateChampionView(Integer.parseInt(content.substring(5)), dropX, dropY));
			} else {
				Template template = inventory.getTemplate(content);
				if (template.has(ComponentTypes.CHAMPION)) {
					world.getStartConfig().addConfig(template, dropX, dropY);
				} else {
					world.addObject(inventory.getTemplate(content), dropX, dropY);
				}
			}
		}
		e.setDropCompleted(true);
		e.consume();
	}

	private double snap(double x) {
		return x - x % Constants.CELL_SIZE;
	}

	private ImageResizablePane createChampionView(ObjectInfo obj) {
		return createResizableView(obj, String.format(START_POS_TEXT, obj.getID()));
	}

	private ImageResizablePane createResizableView(ObjectInfo obj, String textContext) {
		ImageView image = new ImageView(
				ImageProcessor.loadImage(obj.getImagePath(), obj.getDimension().getX(), obj.getDimension().getY()));
		ImageResizablePane resizable = new ImageResizablePane(image, Constants.CELL_SIZE);
		resizable.setOnResized(() -> onResized(obj, resizable));
		Pane child = resizable.getHost();
		Position pos = obj.getPosition();
		child.setTranslateX(pos.getX());
		child.setTranslateY(pos.getY());
		child.translateXProperty().addListener((observable, oldValue, newValue) -> pos.setX(newValue.doubleValue()));
		child.translateYProperty().addListener((observable, oldValue, newValue) -> pos.setY(newValue.doubleValue()));
		child.setOnDragDetected(e -> imageOnDragged(e, resizable, textContext));
		child.setOnDragDone(e -> e.consume());
		child.setOnMouseClicked(e -> myObjClickHandler.accept(obj));
		return resizable;
	}

	private Pane updateChampionView(int id, double x, double y) {
		return updateObjectView(championImages.get(id), x, y);
	}

	private Pane updateObjectView(int id, double x, double y) {
		return updateObjectView(objectImages.get(id), x, y);
	}

	private Pane updateObjectView(ImageResizablePane resizable, double x, double y) {
		Pane child = resizable.getHost();
		child.setTranslateX(x);
		child.setTranslateY(y);
		return child;
	}

	/**
	 * Update the canvas based on any changes in the WorldInfo
	 * 
	 * @param arg,
	 *            the modified object
	 * @param target
	 * @param imageSupplier
	 */
	private void update(ObjectInfo arg, Map<Integer, ImageResizablePane> contents,
			Supplier<ImageResizablePane> imageSupplier) {
		if (arg.expired()) {
			canvas.getChildren().remove(contents.remove(arg.getID()).getHost());
		} else {
			if (!contents.containsKey(arg.getID())) {
				ImageResizablePane image = imageSupplier.get();
				contents.put(arg.getID(), image);
				canvas.getChildren().add(image.getHost());
			}
		}
	}

	private void imageOnDragged(MouseEvent e, ImageResizablePane source, String textContent) {
		if (!source.isResizing()) {
			Dragboard dragBoard = source.getHost().startDragAndDrop(TransferMode.MOVE);
			canvas.getChildren().remove(source.getHost());
			ClipboardContent content = new ClipboardContent();
			content.putString(textContent);
			dragBoard.setContent(content);
			dragBoard.setDragView(((ImageView) source.getHost().getChildren().get(0)).getImage());
		}
	}

	private void onResized(ObjectInfo obj, ImageResizablePane source) {
		obj.getDimension().setX(source.getWidth());
		obj.getDimension().setY(source.getHeight());
	}
}
