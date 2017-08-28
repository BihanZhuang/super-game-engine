package engine.physicsUtil;

import gameobject.component.Vector2D;
import util.Axes;

public class Boundary {
	private Vector2D myPosition;
	private Vector2D myDimension;
	
	/**
	 * 
	 * @param position
	 * @param dimension
	 */
	public Boundary(Vector2D position, Vector2D dimension) {
		myPosition=position;
		myDimension=dimension;
	}
	
	public boolean hasOverlap(Boundary other, double threshold){
		return Math.abs(this.myPosition.diffX(other.myPosition)) +threshold <= this.myDimension.getX() / 2 + other.myDimension.getX() / 2
				&& Math.abs(this.myPosition.diffY(other.myPosition))+threshold  <= this.myDimension.getY() / 2 + other.myDimension.getY() / 2;
	}
	
	/**
	 * 
	 * @param other
	 * @param threshold
	 * @param axis
	 * @return  overlap value
	 */
	public double getOverlap(Boundary other, double threshold, Axes axis){
//		if(!this.hasOverlap(other, threshold)){
//			System.out.println("Boundary line 35");
//			System.out.println("this: "+this);
//			System.out.println("other: "+other+"\n");
//			System.out.println("threshold: "+threshold);
//			throw new VoogaException("the two objects do not overlap");
//		} else {
			return (this.getLength(axis)/2+other.getLength(axis)/2)-Math.abs((this.getCenter(axis)-other.getCenter(axis)))-threshold;
//		}
	}
	
	public double getLength(Axes axis){
		return myDimension.get(axis);
	}
	
	@Deprecated
	public double getWidth(){
		return myDimension.getX();
	}
	@Deprecated
	public double getHeight(){
		return myDimension.getY();
	}
	
	public double getLeft(){
		return myPosition.getX()-myDimension.getX()/2;
	}
	
	public double getRight(){
		return myPosition.getX()+myDimension.getX()/2;
	}
	
	public double getTop(){
		return myPosition.getY()-myDimension.getY()/2;
	}
	
	public double getBottom(){
		return myPosition.getY()+myDimension.getY()/2;
	}
	
	public double getCenter(Axes axis){
		return myPosition.get(axis);
	}
	
	@Deprecated
	public double getCenterX(){
		return myPosition.getX();
	}
	@Deprecated
	public double getCenterY(){
		return myPosition.getY();
	}
	
	public Vector2D getCenter(){
		return myPosition;
	}
	
	
	public boolean overlappingInDirection(Boundary other, Axes axis){
		return Math.abs(this.getCenter().get(axis)-other.getCenter().get(axis))<  (this.getLength(axis)/2+other.getLength(axis)/2 );
	}
	
	public String toString(){
		return String.format("center: (%f, %f)\nwidth: %f, height: %f", this.getCenter().getX(), this.getCenter().getY(), this.getLength(Axes.X), this.getLength(Axes.Y));
	}
}
