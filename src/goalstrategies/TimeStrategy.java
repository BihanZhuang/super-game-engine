package goalstrategies;

/**
 * Time Strategy
 * @author Yanbo Fang
 *
 */
public class TimeStrategy extends AbstractGoalStrategy{

	private static final double DEFAULT_TIME_LIMIT = 60.0;
	private double myTimeLimit;
	private double myCurrentTime;
	
	
	public TimeStrategy(){
		this(Double.MAX_VALUE, DEFAULT_TIME_LIMIT);
	}
	
	public TimeStrategy(double weight, double timeLimit){
		super(weight);
		myTimeLimit = timeLimit;
		myCurrentTime = 0.0;
	}
	
	@Override
	protected void initializeFunction() {
		this.setFunction((info, engine, currentWeight) -> {
			myCurrentTime = info.systemTime();
			if(myCurrentTime >= myTimeLimit){
				currentWeight -= this.getWeight();
				this.setFinished();
			}
			this.notifyObservers(this);
			return currentWeight;
		});
	}

	@Override
	public String getStatus() {
		return "Time Limit: " + Math.round((myTimeLimit - myCurrentTime));
	}

}
