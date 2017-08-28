package view.custom;


import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;


/**
 * Zoom a {view.model.Scalable} by scrolling
 * @author Feng
 *
 */
public class ZoomGesture {
	
	private static final double DELTA = 1.1;
	private static final double MIN_ZOOM = 0.1d;
	private static final double MAX_ZOOM = 10.0d;
	
	private Pane mPane;
	
	public ZoomGesture(Pane pane){
		mPane = pane;
	}
	
	public void enable() {
		mPane.addEventFilter(ScrollEvent.ANY, getScrollHandler());
	}
	
	public EventHandler<ScrollEvent> getScrollHandler(){
		return mScrollHandler;
	}
	
	private EventHandler<ScrollEvent> mScrollHandler = evt -> {
		double oldScale = mPane.getScaleX();
		double newScale = evt.getDeltaY() > 0 ? oldScale * DELTA : oldScale / DELTA;
		newScale = clamp(newScale);
		double ratio = newScale / oldScale;
		
		mPane.setScaleX(newScale);
		mPane.setScaleY(newScale);
		
		// Local Point does not change with scale
		Point2D localPoint = mPane.sceneToLocal(evt.getSceneX(), evt.getSceneY());
		double localX = localPoint.getX();
		double localY = localPoint.getY();
		// Deviation from the node center, which is the default scaling pivot 
		// Width/Height & dx/dy do not change with scale
		double dx = localX - mPane.getWidth()/2;
		double dy = localY - mPane.getHeight()/2;
		
		System.out.println("dx: " + dx);
		System.out.println("dy: " + dy);
		System.out.println("Scrolling X:" + localX);
		System.out.println("Scrolling Y:" + localY);
		
        // pivot value must be untransformed, i.e. without scaling
		setPivot(mPane, dx * (ratio -1 ), dy * (ratio - 1));
		
	};
	
	private double clamp(double scale){
		if(scale > MAX_ZOOM)
			return MAX_ZOOM;
		if(scale < MIN_ZOOM)
			return MIN_ZOOM;
		return scale;
	}
	
	private void setPivot(Pane pane, double x, double y) {
		pane.setTranslateX(pane.getTranslateX() - x);
		pane.setTranslateY(pane.getTranslateY() - y);
	}
	
}
