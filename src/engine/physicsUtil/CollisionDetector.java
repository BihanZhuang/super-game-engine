package engine.physicsUtil;

import engine.physicsStrategies.BrickStrategy;
import gameobject.ObjectInfo;
import gameobject.component.Vector2D;
import gameobject.component.type.ComponentType;
import gameobject.component.type.ComponentTypes;
import util.Axes;
import util.Direction;

public class CollisionDetector {

	private final ComponentType<Vector2D> V_TYPE = ComponentTypes.VELOCITY;
	private final double THRESHOLD = 0;// in pixels!

	public boolean haveCollision(ObjectInfo protagonist, ObjectInfo target) {
		return haveCollision(protagonist, target, Axes.X) || haveCollision(protagonist, target, Axes.Y);
	}
	
	public boolean haveCollision(ObjectInfo protagonist, ObjectInfo target, Axes axis){
		if (protagonist.getID() == target.getID()) {
			return false;
		} else if (haveOverlap(protagonist, target)) {

			Vector2D pPos = protagonist.getPosition(), tPos = target.getPosition();
			Vector2D pVelocity = protagonist.getComponent(V_TYPE), tVelocity = target.getComponent(V_TYPE);
			if (pVelocity == null) {
				pVelocity = new Vector2D(ComponentTypes.VELOCITY);
				pVelocity.setX(0);
				pVelocity.setY(0);
			}

			if (tVelocity == null) {
				tVelocity = new Vector2D(ComponentTypes.VELOCITY);
				tVelocity.setX(0);
				tVelocity.setY(0);
			}

			Vector2D pPos_WRT_Target = pPos.diff(tPos);
			Vector2D pVelocity_WRT_Target = pVelocity.diff(tVelocity);
			return pPos_WRT_Target.get(axis) * pVelocity_WRT_Target.get(axis) < 0;
		} else {
			// System.out.println("CollDetector line 36");
			return false;
		}
	}

	/**
	 * When a and b qualify for collision conditions, check if they overlap with
	 * each other
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean haveOverlap(ObjectInfo a, ObjectInfo b) {
		Vector2D aDimension = a.getDimension();
		Vector2D aPos = a.getPosition();
		return hasOverlap(aPos, aDimension, b);
	}

	public boolean hasOverlap(Vector2D aPos, Vector2D aDimension, ObjectInfo target) {
		if (aPos.equalInValue(target.getPosition()) && aDimension.equalInValue(target.getDimension())) {
			// Things cannot "overlap" with itself
			return false;
		}
		Vector2D bDimension = target.getDimension();
		Vector2D bPos = target.getPosition();
		Boundary aBoundary = new Boundary(aPos, aDimension), bBoundary = new Boundary(bPos, bDimension);
		return aBoundary.hasOverlap(bBoundary, THRESHOLD);
	}

	/**
	 * <p>
	 * Assumes both protagonist and target HAVE boundary, and ALREADY touching
	 * each other
	 * </p>
	 * <p>
	 * returns TARGET's surface for collision
	 * </p>
	 * 
	 * @param protagonist
	 * @param target
	 * @return TARGET's surface for collision
	 */
	public Direction getTouchingSurface(ObjectInfo protagonist, ObjectInfo target) {
		boolean printOn = false;
//		int id=22;
//		if(protagonist.getID()>=id){
//			printOn=true;
//			id++;
//		}
		Boundary tBoundary = new Boundary(target.getPosition(), target.getDimension());
		Boundary pBoundary = new Boundary(protagonist.getPosition(), protagonist.getDimension());

		double xOverlap, yOverlap;
		xOverlap = pBoundary.getOverlap(tBoundary, THRESHOLD, Axes.X);
		yOverlap = pBoundary.getOverlap(tBoundary, THRESHOLD, Axes.Y);

		if (Math.abs(yOverlap) > Math.abs(xOverlap)) {
			Direction direc = pBoundary.getCenter(Axes.X) > tBoundary.getCenter(Axes.X) ? Direction.RIGHT
					: Direction.LEFT;
			if(printOn)System.out.println(protagonist.getID()+": "+direc);
			return direc;
		} else if (Math.abs(yOverlap) <= Math.abs(xOverlap)) {
			Direction direc = pBoundary.getCenter(Axes.Y) > tBoundary.getCenter(Axes.Y) ? Direction.DOWN : Direction.UP;
			if(printOn)System.out.println(protagonist.getID()+": "+direc);
			return direc;
		} else {
			throw new Error("We missed some cases in  collision surface detection :'("+"xoverLap="+xOverlap+"yoverlap="+yOverlap+"\n");
		}
	}

	
	
	/**
	 * Checks if an object is a brick
	 * 
	 * @param object
	 * @return
	 */
	public boolean isBrick(ObjectInfo object) {
		/*
		 * Mass of wall, ground, etc==Infinity
		 */
		return object.getComponent(ComponentTypes.PHYSICS_BEHAVIOR).getStrategy().getClass()
				.equals(BrickStrategy.class) || Double.isInfinite(object.getComponent(ComponentTypes.MASS).getValue());
	}
}