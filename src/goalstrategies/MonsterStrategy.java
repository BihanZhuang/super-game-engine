package goalstrategies;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import gameobject.component.type.ComponentTypes;
import system.ISystemInfo;

/**
 * Monster Strategy
 * @author Yanbo Fang
 *
 */
public class MonsterStrategy extends AbstractGoalStrategy {

	private static final double DEFAULT_MONSTER_GOAL = 10;
	private double myMonsterGoal;
	private double myKills;
	private boolean myFirstRun;
	private double myNewCount;
	private double myOldCount;
	
	
	public MonsterStrategy() {
		this(DEFAULT_WEIGHT, DEFAULT_MONSTER_GOAL);
	}

	public MonsterStrategy(double weight, double monsterGoal) {
		super(weight);
		myMonsterGoal = monsterGoal;
		myKills = 0;
		myFirstRun = true;
		myNewCount = 0;
	    myOldCount = 0;
	}

	@Override
	protected void initializeFunction() {
		this.setFunction((info, engine, currentWeight) -> {

			if (myFirstRun) {
				myFirstRun = false;
				myOldCount = this.countMonsters(info);
			}

			myNewCount = this.countMonsters(info);
			
			if(myNewCount < myOldCount){
				myKills += myOldCount - myNewCount;
			}
			
			if (myKills >= myMonsterGoal) {
				currentWeight += this.getWeight();
				this.setFinished();
			}

			myOldCount = myNewCount;
			
			this.notifyObservers(this);

			return currentWeight;

		});
	}

	private int countMonsters(ISystemInfo info) {
		return Math.toIntExact(StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(info.getWorld().iterator(), Spliterator.ORDERED), false)
				.filter(obj -> obj.has(ComponentTypes.HEALTH) && obj.has(ComponentTypes.VELOCITY)
						&& !obj.has(ComponentTypes.CHAMPION))
				.count());
	}

	@Override
	public String getStatus() {
		return "Monsters goal: " + Math.round(myKills) + "/" + Math.round(myMonsterGoal);
	}

}
