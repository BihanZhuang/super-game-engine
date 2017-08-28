package engine;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import engine.physicsUtil.ObjectPair;
import engine.physicsUtil.PhysicsToolKit;
import gameobject.IGameObject;
import gameobject.ObjectInfo;
import gameobject.component.Collider;
import gameobject.component.PhysicsBehavior;
import gameobject.component.Vector2D;
import gameobject.component.type.ComponentType;
import gameobject.component.type.ComponentTypes;
import model.Environment;
import util.Direction;

/**
 * Physics related math stuff
 * 
 * @author Yuxiang He
 *
 */
public class PhysicsEngine {

	private Iterable<IGameObject> allObjects;
	private PhysicsToolKit kit;

	private final boolean NON_PARALLEL_STREAM_STATE = false;
	private final ComponentType<Collider> COLLIDER = ComponentTypes.COLLIDE;
	private final ComponentType<PhysicsBehavior> PHYSICS_BEHAVIOR = ComponentTypes.PHYSICS_BEHAVIOR;

	public PhysicsEngine(Iterable<IGameObject> allObjects, Function<Environment.Constant, Double> constantsGetter) {
		this.allObjects = allObjects;
		kit = new PhysicsToolKit(constantsGetter);
	}
	
	public boolean hasCollision(ObjectInfo a, ObjectInfo b){
		return kit.getCollisionAlgo().haveCollision(a, b);
	}

	
	/**
	 * Given a object, return all objects colliding with this object
	 * 
	 * @param protagonist
	 * @return
	 */
	public Iterable<IGameObject> getColliders(ObjectInfo protagonist) {
		// System.out.println("PhysicsEngine line 57");
		return getRelatedObjects(protagonist, (a, b) -> kit.getCollisionAlgo().haveCollision(a, b));
	}

	
	/**
	 * Checks if two objects are overlapping (touching).
	 * Collision is a strict subset of overlapping
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean hasOverlap(ObjectInfo a, ObjectInfo b){
		return kit.getCollisionAlgo().haveOverlap(a, b);
	}
	
	
	/**
	 * 
	 * @param protagonist
	 * @param relation
	 *            defines how the returned IGameObject's should be related to
	 *            protagonist
	 * @return IGameObject's related to protagonist
	 */
	private Iterable<IGameObject> getRelatedObjects(ObjectInfo protagonist,
			BiPredicate<ObjectInfo, ObjectInfo> relation) {
		ArrayList<IGameObject> out = new ArrayList<>();
		getObjectStream(allObjects, NON_PARALLEL_STREAM_STATE).filter(object -> relation.test(protagonist, object))
				.forEach(filteredObj -> out.add(filteredObj));
		return out;
	}

	/**
	 * Gets a stream from a iterableSource of IGameObject. Makes the code more
	 * readable
	 * 
	 * @param iterableSource
	 * @param parallelStream
	 *            boolean, specifies if the stream parallel streams the iterable
	 *            source
	 * @return a stream of game objects from the iterable source
	 */
	private Stream<IGameObject> getObjectStream(Iterable<IGameObject> iterableSource, boolean parallelStream) {
		return StreamSupport.stream(iterableSource.spliterator(), parallelStream);
	}

	/**
	 * Update the velocities of all objects
	 * 
	 * @param protagonist
	 *            the object currently under analysis
	 * @param target
	 *            the object colliding with obj
	 * @return Vector2D the final velocity
	 */

	public void updateVelocities(double frameTime) {
		kit.setFrameTime(frameTime);
		getObjectStream(allObjects, NON_PARALLEL_STREAM_STATE).filter(obj -> obj.getComponent(PHYSICS_BEHAVIOR) != null)
				.forEach(obj -> {
//					System.out.println(obj);
					obj.getComponent(PHYSICS_BEHAVIOR).getStrategy().updatePosition(obj, kit,
							getOverlappersAtPosition(obj, obj.getPosition()));

				});
		
		HashSet<ObjectPair> handeledPairs=new HashSet<>();
		
		getObjectStream(allObjects, NON_PARALLEL_STREAM_STATE).filter(obj -> obj.getComponent(COLLIDER) != null)
				.forEach(filteredObj -> {
					getObjectStream(filteredObj.getComponent(COLLIDER).getColliders(), NON_PARALLEL_STREAM_STATE)
							.forEach(collisionTarget -> {								
								filteredObj.getComponent(PHYSICS_BEHAVIOR).getStrategy().handleCollision(filteredObj, collisionTarget,
										kit, handeledPairs);
							});
				});
		
		
	}

	/**
	 * 
	 * @param protagonist
	 * @param target
	 * @return surface w.r.t. the TARGET
	 */
	public Direction getCollisionSurface(ObjectInfo protagonist, ObjectInfo target) {
		return kit.getCollisionAlgo().getTouchingSurface(protagonist, target);
	}

	/**
	 * <p>
	 * check if the character at the given position should be falling/bumping
	 * into a wall etc...
	 * </p>
	 * <p>
	 * note this returns ALL overlappers, INCLUDING NON-COLLIDABLE objects
	 * </p>
	 * 
	 * @param object
	 * @param futurePosition
	 * @return {@code Map<Direction, Set<IGameObject>>} all objects to be
	 *         overlapping with object, key is the direction with respect to the
	 *         object
	 */
	public Map<Direction, Set<IGameObject>> getOverlappersAtPosition(ObjectInfo object, Vector2D futurePosition) {
		EnumMap<Direction, Set<IGameObject>> output = new EnumMap<Direction, Set<IGameObject>>(Direction.class);
		Iterable<IGameObject> futureOverlappers = getRelatedObjects(object,
				(a, b) -> kit.getCollisionAlgo().hasOverlap(futurePosition, a.getDimension(), b));

		getObjectStream(futureOverlappers, NON_PARALLEL_STREAM_STATE).forEach(obj -> {
			Direction direction = kit.getCollisionAlgo().getTouchingSurface(obj, object);
			if (output.containsKey(direction)) {
				output.get(direction).add(obj);
			} else {
				HashSet<IGameObject> set = new HashSet<>();
				set.add(obj);
				output.put(direction, set);
			}
		});
		return output;
	}

}