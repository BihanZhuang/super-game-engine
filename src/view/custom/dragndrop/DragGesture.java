package view.custom.dragndrop;

import com.google.common.base.Predicate;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * Get handlers to drag the node around. Need the parent node and the current node
 * to correctly position the node in its parent
 * 
 * @author Feng
 *
 */
public class DragGesture {

	private Node parent;
	private Node current;
	private DragContext dragContext = new DragContext();;

	public DragGesture(Node parent, Node current) {
		this.current = current;
		this.parent = parent;
	}
	
	public void enable() {
		 current.addEventFilter(MouseEvent.MOUSE_PRESSED, secondaryMousePressedHandler());
		 current.addEventFilter(MouseEvent.MOUSE_DRAGGED, secondaryMouseDraggedHandler());
	}

	public EventHandler<MouseEvent> primaryMousePressedHandler() {
		return mousePressedHandler(evt -> evt.isPrimaryButtonDown());
	}
	
	public EventHandler<MouseEvent> secondaryMousePressedHandler() {
		return mousePressedHandler(evt -> evt.isSecondaryButtonDown());
	}
	
	public EventHandler<MouseEvent> primaryMouseDraggedHandler() {
		return mouseDraggedHandler(evt -> evt.isPrimaryButtonDown());
	}
	
	public EventHandler<MouseEvent> secondaryMouseDraggedHandler() {
		return mouseDraggedHandler(evt -> evt.isSecondaryButtonDown());
	}

	private EventHandler<MouseEvent> mousePressedHandler(Predicate<MouseEvent> mouseTest) {
		return event -> {
			if (!mouseTest.test(event))
				return;
			System.out.println("Mouse Pressed." + ((Node) event.getSource()));

			Point2D anchorInParent = parent.sceneToLocal(event.getSceneX(), event.getSceneY());
			dragContext.mouseAnchorX = anchorInParent.getX();
			dragContext.mouseAnchorY = anchorInParent.getY();
			Node node = (Node) event.getSource();
			dragContext.nodeAnchorX = node.getTranslateX();
			dragContext.nodeAnchorY = node.getTranslateY();
		};
	}
	
	private EventHandler<MouseEvent> mouseDraggedHandler(Predicate<MouseEvent> mouseTest) {
		return event -> {
			if (!mouseTest.test(event))
				return;
			
			Node node = (Node) event.getSource();
			Point2D anchorInParent = parent.sceneToLocal(event.getSceneX(), event.getSceneY());
			node.setTranslateX(
					dragContext.nodeAnchorX + (anchorInParent.getX() - dragContext.mouseAnchorX));
			node.setTranslateY(
					dragContext.nodeAnchorY + (anchorInParent.getY() - dragContext.mouseAnchorY));
			event.consume();
		};
	}	

}
