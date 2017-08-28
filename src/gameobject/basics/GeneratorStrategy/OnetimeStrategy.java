package gameobject.basics.GeneratorStrategy;

import gameobject.IGameObject;
import gameobject.component.type.ComponentTypes;
import util.SerializableBiPredicate;

@SuppressWarnings("serial")
public class OnetimeStrategy extends GeneratorStrategy {
	private double generatetime = 0.5;

	public OnetimeStrategy() {
		super();
		SerializableBiPredicate<IGameObject, Double> criteria = (I, time) -> {
			return (time - I.getComponent(ComponentTypes.GENERATOR).getDeathTime()) > I.getComponent(ComponentTypes.GENERATOR).getRebornTime();
		};
		this.addCondition(criteria);
	}

	public OnetimeStrategy(double time) {
		super();
		this.generatetime = time;
		instantize();
	}

	private void instantize() {
		SerializableBiPredicate<IGameObject, Double> criteria = (I, time) -> {
			return (time - I.getComponent(ComponentTypes.GENERATOR).getDeathTime()) > generatetime;
		};
		this.addCondition(criteria);
	}
}
