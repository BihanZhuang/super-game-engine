package gameobject.basics.GeneratorStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gameobject.IGameObject;
import util.SerializableBiPredicate;

@SuppressWarnings("serial")
public class GeneratorStrategy implements Serializable {
	private ArrayList<SerializableBiPredicate<IGameObject, Double>> condition;

	public GeneratorStrategy() {
		this.condition = new ArrayList<SerializableBiPredicate<IGameObject, Double>>();
	}

	public List<SerializableBiPredicate<IGameObject, Double>> getJudge() {
		return this.condition;
	}
	
	public void addCondition(SerializableBiPredicate<IGameObject, Double> cond) {
		this.condition.add(cond);
	}

}
