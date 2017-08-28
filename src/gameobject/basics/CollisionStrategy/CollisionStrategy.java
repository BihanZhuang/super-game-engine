package gameobject.basics.CollisionStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gameobject.IGameObject;
import util.Direction;
import util.SerializablePredicate;
import util.TriConsumer;

@SuppressWarnings("serial")
public class CollisionStrategy implements Serializable {
	private ArrayList<TriConsumer<IGameObject, IGameObject, Direction>> function;
	private ArrayList<SerializablePredicate<IGameObject>> condition;

	public CollisionStrategy() {
		this.function = new ArrayList<TriConsumer<IGameObject, IGameObject, Direction>>();
		this.condition = new ArrayList<SerializablePredicate<IGameObject>>();
	}

	public List<TriConsumer<IGameObject, IGameObject, Direction>> getAction() {
		return this.function;
	}

	public List<SerializablePredicate<IGameObject>> getJudge() {
		return this.condition;
	}
	
	public void addCondition(SerializablePredicate<IGameObject> cond) {
		this.condition.add(cond);
	}
	
	public void addFunction(TriConsumer<IGameObject, IGameObject, Direction> func) {
		this.function.add(func);
	}
}
