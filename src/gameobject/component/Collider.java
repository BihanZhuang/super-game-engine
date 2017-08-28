package gameobject.component;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import gameobject.IGameObject;
import gameobject.basics.CollisionStrategy.CollisionStrategy;
import gameobject.component.type.ComponentType;
import gameobject.component.type.ComponentTypes;
import util.Direction;
import util.SerializablePredicate;
import util.TriConsumer;

public class Collider implements Component {
    
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private ArrayList<TriConsumer<IGameObject, IGameObject, Direction>> function;
	private ArrayList<SerializablePredicate<IGameObject>> condition;
	private Iterable<IGameObject> colliders;
	private Map<Direction, Boolean> surfaceReflectivity = new EnumMap<Direction, Boolean>(Direction.class);

	public Collider() {
		super();
		this.function= new ArrayList<TriConsumer<IGameObject, IGameObject, Direction>>();
		this.condition = new ArrayList<SerializablePredicate<IGameObject>>();
		surfaceReflectivity.put(Direction.UP, true);
		surfaceReflectivity.put(Direction.DOWN, true);
		surfaceReflectivity.put(Direction.LEFT, true);
		surfaceReflectivity.put(Direction.RIGHT, true);
	}
	
	public Collider(Collider c) {
		super();
		this.function=c.function;
		this.condition=c.condition;
		surfaceReflectivity.put(Direction.UP, true);
		surfaceReflectivity.put(Direction.DOWN, true);
		surfaceReflectivity.put(Direction.LEFT, true);
		surfaceReflectivity.put(Direction.RIGHT, true);		
	}

	public Iterable<IGameObject> getColliders() {
		return colliders;
	}

	public void setColliders(Iterable<IGameObject> colliders) {
		this.colliders = colliders;
	}

	public boolean getSurfaceReflectivity(Direction direction) {
		return surfaceReflectivity.get(direction);
	}

	public void changeSurfaceReflectivity(Direction direction, boolean state) {
		surfaceReflectivity.put(direction, state);
	}

	/*
	 * public Collider(Predicate<IGameObject> condition, Consumer<IGameObject>
	 * function) { this.function.add(function); this.condition.add(condition); }
	 */
	public void add(SerializablePredicate<IGameObject> condition, TriConsumer<IGameObject, IGameObject, Direction> function) {
		this.function.add(function);
		this.condition.add(condition);
//		System.out.println(this.function.size());
	}
	
	public void add(CollisionStrategy newStrategy) {
		this.function.addAll(newStrategy.getAction());
		this.condition.addAll(newStrategy.getJudge());
	}

	public List<TriConsumer<IGameObject, IGameObject, Direction>> test(IGameObject other) {
		ArrayList<TriConsumer<IGameObject, IGameObject, Direction>> funcList = new ArrayList<TriConsumer<IGameObject, IGameObject, Direction>>();
		//System.out.print("IN Check Module: ");
		//System.out.println(this.function.size());
		for (int i = 0; i < condition.size(); i++) {
			//System.out.println("I'm Checking");
			if (condition.get(i).test(other)) {
				//System.out.println("Check Complete");
				funcList.add(function.get(i));
			}
		}
		return funcList;
	}

	@Override
	public ComponentType<? extends Component> getType() {
		// TODO Auto-generated method stub
		return ComponentTypes.COLLIDE;
	}

	@Override
	public Collider copy() {
		// TODO Auto-generated method stub
		return new Collider(this);
	}

	/*
	 * public void interact(IGameObject other) { for (int i=0;
	 * i<condition.size();i++) { if (condition.get(i).test(other)) {
	 * function.get(i).accept(other); } } }
	 */
}