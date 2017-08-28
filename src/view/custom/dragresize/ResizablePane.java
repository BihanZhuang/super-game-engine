package view.custom.dragresize;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * A container that is resizable by dragging
 * Allow wrapping any Region or ImageView
 * If a unit is passed in, the resizing will always snap to unit,
 * and will set the minimum size to be the unit
 * @author Feng
 *
 */
public class ResizablePane implements IResizable {

	private static final double MARGIN = 8d;
	private Pane host;
	private Direction dragDirection;
	private double unit = 1;
	private boolean dragging;
	private Runnable runnable;
	
	
	private double oldX;
	private double oldY;
	private double oldW;
	private double oldH;
	
	public ResizablePane(double width, double height) {
		host = new Pane();
		host.setPrefSize(width, height);
		dragging = false;
		dragDirection = Direction.DEFAULT;
		makeResizable(host);
	}
	
	public Pane getHost() {
		return host;
	}
	
	public double getUnit() {
		return unit;
	}
	
	public void setUnit(double u) {
		unit = u;
	}
	
	public void setOnResized(Runnable runnable) {
		this.runnable = runnable;
	}
	
	@Override
	public boolean isResizing() {
		return dragging;
	}

	@Override
	public double getWidth() {
		return host.getPrefWidth();
	}

	@Override
	public double getHeight() {
		return host.getPrefHeight();
	}

	private void makeResizable(Pane pane) {
		pane.setOnMouseMoved(event -> {
			Direction direction = getDirection(event);
			host.setCursor(direction.cursor());
		});
		
		pane.setOnMousePressed(event -> {
			if (!dragZone(event)) {
				event.consume();
				return;
			}
			//System.out.println(TAG + "Mouse Moved X: " + event.getX());
			dragging = true;
			dragDirection = getDirection(event);
			initializeDimension();
		});

		pane.setOnMouseDragged(event -> {
			if(!dragging){
				event.consume();
				return;
			}
			double newX = oldX;
			double newY = oldY;
			double newW = oldW;
			double newH = oldH;
			
		    if(dragDirection.top()) {
		    	// Since position will be transformed, need to update oldY and oldH
		    	newY = oldY + event.getY();
		    	oldY = newY;
		    	newH = oldH - event.getY() / host.getScaleY();
		    	oldH = newH;
		    }
		    
		    if(dragDirection.left()) {
		    	// Since position will be transformed, need to update oldY and oldH
		    	newX = oldX + event.getX();
		    	oldX = newX;
		    	newW = oldW - event.getX() / host.getScaleX(); 
		    	oldW = newW;
		    }
		    
		    if(dragDirection.right()) {
		    	newW = event.getX();
		    }
			
		    if(dragDirection.bottom()) {
		    	newH = event.getY();
		    }
			
		    if(newW >= unit && newH >= unit){
			    transform(newX, newY, newW, newH);
			    runnable.run();
		    }
		});

		pane.setOnMouseReleased(event -> {
			dragging = false;
			host.setCursor(Cursor.DEFAULT);
			event.consume();
		});
	}
	
	private void initializeDimension() {
		// x & y translation
		oldX = edgeX();
		oldY = edgeY();
		oldW = getWidth(); 
	    oldH = getHeight();
	}
	
	private void transform(double translateX, double translateY, double width, double height) {
		host.setTranslateX(snap(translateX));
		host.setTranslateY(snap(translateY));
	    host.setPrefSize(snap(width), snap(height));
	}
	
	private double snap(double value) {
		double reminder = value % unit;
		double implement = unit - reminder;
		if(reminder <= unit / 2)
			return value - reminder;
		else 
			return value + implement;
	}
	
	private Direction getDirection(MouseEvent event) {
		double XOnPane = event.getX();
		double YOnPane = event.getY();
		boolean left = leftZone(XOnPane, YOnPane);
        boolean right = rightZone(XOnPane, YOnPane);
        boolean top = topZone(XOnPane, YOnPane);
        boolean bottom = bottomZone(XOnPane, YOnPane);
        
        return Direction.get(top, bottom, left, right);
	}

	private boolean dragZone(MouseEvent event) {
		double x = event.getX();
		double y = event.getY();
		return leftZone(x, y) || rightZone(x, y) || bottomZone(x, y) || topZone(x, y);
	}
	
	private boolean leftZone(double x, double y){
		return touch(x, 0) && within(y, 0, getHeight());
	}
	
	private boolean rightZone(double x, double y){
		return touch(x, getWidth()) && within(y, 0, getHeight());
	}
	
	private boolean topZone(double x, double y){
		return touch(y, 0) && within(x, 0, getWidth());
	}
	
	private boolean bottomZone(double x, double y) {
		return touch(y, getHeight()) && within(x, 0, getWidth());
	}
	
	private boolean touch(double cur, double target) {
		return target + MARGIN >= cur && target - MARGIN <= cur;
	}
	
	private boolean within(double cur, double a, double b) {
		return (cur <= a && cur >= b) || (cur <= b && cur >= a);
	}
	
	private double edgeX() {
		return host.getTranslateX();
	}
	
	private double edgeY() {
		return host.getTranslateY();
	}
	
}
