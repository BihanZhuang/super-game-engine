package gameobject.component;

import java.util.EnumMap;

import gameobject.component.type.CommonType;
import util.Axes;

/**
 * Basic framework for <code>Components</code> that contains two values.
 * 
 * @author Bihan Zhuang
 * @author Yuxiang He
 *
 */
public class Vector2D implements Component {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private EnumMap<Axes, Double> components;
    private CommonType<Vector2D> myType;
    private final double DELTA=1;
    
    public Vector2D(CommonType<Vector2D> type) {
        this(0, 0, type);
    }

    public Vector2D(double x, double y, CommonType<Vector2D> type) {
        components = new EnumMap<Axes, Double>(Axes.class);
        components.put(Axes.X, x);
        components.put(Axes.Y, y);
        myType = type;
    }

    public double getX() {
        return get(Axes.X);
    }

    public double getY() {
        return get(Axes.Y);
    }

    public double get(Axes axis) {
        return components.get(axis);
    }

    public void setX(double x) {
    	set(Axes.X, x);
    }
    
    public void set(Axes axis, double value) {
    	double processedValue= Math.abs(value)<DELTA? 0: value;
        components.put(axis, processedValue);
    }

    public void setY(double y) {
    	set(Axes.Y, y);
    }
    
    public void set(double x, double y) {
        setX(x);
        setY(y);
    }

    public boolean equalInValue(Vector2D other) {
        return getX() == other.getX() && getY() == other.getY();
    }

    public Vector2D diff(Vector2D other) {
        if (this.getType().equals(other.getType())) {
            double x = diffX(other), y = diffY(other);
            return new Vector2D(x, y, getType());
        } else {
            throw new IllegalArgumentException("Cannot take difference of two vectors of different types");
        }
    }

    /**
     * Perform Gallilean transformation w.r.t to origin specified by other
     * NOTE WELL that the two Vector2D should be in the same coordinate system
     * @param other
     * @return transformed vector
     */
    public Vector2D gallileanTransform(Vector2D other){
    	return this.diff(other);
    }
    /**
     * 
     * @param other
     *            another 2DComponent
     * @return X difference with SIGN
     */
    public double diffX(Vector2D other) {
        return this.getX() - other.getX();
    }

    /**
     * 
     * @param other
     *            another 2DComponent
     * @return X difference with SIGN
     */
    public double diffY(Vector2D other) {
        return this.getY() - other.getY();
    }

    @Override
    public CommonType<Vector2D> getType() {
        return myType;
    }

    @Override
    public Vector2D copy() {
        return new Vector2D(getX(), getY(), myType);
    }

    @Override
    public String toString() {
        return String.format("x: %f, y: %f", getX(), getY());
    }
}
