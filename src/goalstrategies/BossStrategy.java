package goalstrategies;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import gameobject.component.type.ComponentTypes;

/**
 * Boss Strategy
 * @author Yanbo Fang
 *
 */
public class BossStrategy extends AbstractGoalStrategy {

	private boolean isBossDead;

	public BossStrategy() {
		super(DEFAULT_WEIGHT);
	}
	
	public BossStrategy(double weight) {
		super(weight);
	}
	
	@Override
	protected void initializeFunction() {
		this.setFunction((info, engine, currentWeight) -> {

			if (StreamSupport
					.stream(Spliterators.spliteratorUnknownSize(info.getWorld().iterator(), Spliterator.ORDERED), false)
					.filter(obj -> obj.has(ComponentTypes.BOSS))
					.allMatch(boss -> boss.getComponent(ComponentTypes.HEALTH).getCurrentHealth() <= 0.0)) {
				isBossDead = true;
				currentWeight += this.getWeight();
			}

			this.notifyObservers(this);

			return currentWeight;
		});
	}

	@Override
	public String getStatus() {
		String status = "Boss Goal: ";
		status += (isBossDead) ? "Reached" : "Not Reached";
		return status;
	}

}
