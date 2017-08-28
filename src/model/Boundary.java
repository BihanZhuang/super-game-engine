package model;

import java.io.Serializable;

import engine.physicsStrategies.DefaultStrategy;
import engine.physicsStrategies.IPhysicsStrategy;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

@SuppressWarnings("serial")
public class Boundary implements Serializable {

    private double myX, myY, myWidth, myHeight;
    private IPhysicsStrategy myPhysicsStrategy;
    
    public Boundary(double x, double y, double width, double height) {
        myX = x;
        myY = y;
        myWidth = width;
        myHeight = height;
        myPhysicsStrategy = new DefaultStrategy(false, false, false);
    }
    
    public double getMinX() {
        return myX;
    }
    
    public double getMinY() {
        return myY;
    }
    
    public double getMaxX() {
        return myX + myWidth;
    }
    
    public double getMaxY() {
        return myY + myHeight;
    }
    
    public double getWidth() {
        return myWidth;
    }
    
    public double getHeight() {
        return myHeight;
    }
    
    public Bounds getBounds() {
        return new BoundingBox(myX, myY, myWidth, myHeight);
    }
    
    public IPhysicsStrategy getPhysicsStrategy() {
        return myPhysicsStrategy;
    }
    
    public void setMinX(double x) {
        myX = x;
    }
    
    public void setMinY(double y) {
        myY = y;
    }
    
    public void setMaxX(double x) {
        myWidth = x - myX;
    }
    
    public void setMaxY(double y) {
        myHeight = y - myY;
    }
    
    public void setWidth(double width) {
        myWidth = width;
    }
    
    public void setHeight(double height) {
        myHeight = height;
    }
    
    public void setPhysicsStrategy(IPhysicsStrategy physicsStrategy) {
        myPhysicsStrategy = physicsStrategy;
    }
    
    public boolean in(double x, double y) {
        return x >= myX && y >= myY && x < myX + myWidth && y < myY + myHeight;
    }
    
}
