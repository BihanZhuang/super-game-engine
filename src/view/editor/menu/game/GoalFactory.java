package view.editor.menu.game;

import java.util.Arrays;
import java.util.List;

import goalstrategies.BossStrategy;
import goalstrategies.DestinationStrategy;
import goalstrategies.IGoal;
import goalstrategies.MonsterStrategy;
import goalstrategies.ScoreStrategy;
import goalstrategies.TimeStrategy;
/**
 * Goal Factory
 * @author Yanbo Fang
 *
 */
public class GoalFactory {

	public static final String BOSS = "BossStrategy";
	public static final String DESTINATION = "DestinationStrategy";
	public static final String MONSTER = "MonsterStrategy";
	public static final String SCORE = "ScoreStrategy";
	public static final String TIME = "TimeStrategy";
	public static final List<String> GOALS = Arrays.asList("BossStrategy", "DestinationStrategy", "MonsterStrategy",
			"ScoreStrategy", "TimeStrategy");
	
	private double myWeight;
	private double myParameter;

	public GoalFactory() {
		myWeight = 0.0;
		myParameter = 0.0;
	}

	/**
	 * Set the weight for a goal
	 * @param weight
	 */
	public void setWeight(double weight) {
		myWeight = weight;
	}

	/**
	 * Set the parameter of a goal
	 * @param parameter
	 */
	public void setParameter(double parameter) {
		myParameter = parameter;
	}

	/**
	 * Get the goal based on its name
	 * @param name
	 * @return
	 */
	public IGoal getGoal(String name) {
		if (name == BOSS) {
			return (myWeight != 0) ? new BossStrategy(myWeight) : new BossStrategy();
		} else if (name == DESTINATION) {
			return (myWeight != 0) ? new DestinationStrategy(myWeight) : new DestinationStrategy();
		} else if (name == MONSTER) {
			return (myWeight != 0 && myParameter != 0) ? new MonsterStrategy(myWeight, myParameter)
					: new MonsterStrategy();
		} else if (name == SCORE) {
			return (myWeight != 0 && myParameter != 0) ? new ScoreStrategy(myWeight, myParameter) : new ScoreStrategy();
		} else if (name == TIME) {
			return (myWeight != 0 && myParameter != 0) ? new TimeStrategy(myWeight, myParameter) : new TimeStrategy();
		}
		return null;
	}

}
