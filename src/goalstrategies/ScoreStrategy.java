package goalstrategies;

/**
 * Score Strategy
 * @author Yanbo Fang
 *
 */
public class ScoreStrategy extends AbstractGoalStrategy {

	private static final double DEFAULT_SCORE_GOAL = 100.0;
	private double myScoreGoal;
	private double myCurrentScore;

	public ScoreStrategy() {
		this(DEFAULT_WEIGHT, DEFAULT_SCORE_GOAL);
	}

	public ScoreStrategy(double weight, double scoreGoal) {
		super(weight);
		myScoreGoal = scoreGoal;
		myCurrentScore = 0.0;
	}

	@Override
	protected void initializeFunction() {

		this.setFunction((info, engine, currentWeight) -> {
			myCurrentScore = info.getScore();
			if (myCurrentScore >= myScoreGoal) {
				currentWeight += this.getWeight();
				this.setFinished();
			}
			this.notifyObservers(this);
			return currentWeight;

		});
	}

	@Override
	public String getStatus() {
		return "Score goal: " + myCurrentScore + "/" + myScoreGoal;
	}
}
