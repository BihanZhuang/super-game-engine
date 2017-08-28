package goalstrategies;

import engine.PhysicsEngine;
import system.ISystemInfo;
import util.QuickNotifyObservable;
import util.TriFunction;

/**
 * Abstract Goal Strategy
 * @author Yanbo Fang
 *
 */
public abstract class AbstractGoalStrategy extends QuickNotifyObservable<IGoal> implements IGoal{

	protected static final double DEFAULT_WEIGHT = 5.0;
	
	private double myWeight;
	private TriFunction<ISystemInfo, PhysicsEngine, Double, Double> myOperation;
	private boolean isFinished;
	
	public AbstractGoalStrategy(){
		this(DEFAULT_WEIGHT);
	}
	
	public AbstractGoalStrategy(double weight){
		myWeight = weight;
		isFinished = false;
		this.initializeFunction();
	}
	
	@Override
	public void setWeight(double weight){
		myWeight = weight;
	}
	
	@Override
	public Double getWeight(){
		return myWeight;
	}
	
	@Override
	public TriFunction<ISystemInfo, PhysicsEngine, Double, Double> getFunction(){
		return myOperation;
	}
	
	@Override
	public boolean isFinished(){
		return isFinished;
	}
	
	@Override
	public abstract String getStatus();
	
	
	protected void setFinished(){
		isFinished = true;
	}
	
	protected void setFunction(TriFunction<ISystemInfo, PhysicsEngine, Double, Double> function){
		myOperation = function;
	}
	
	protected abstract void initializeFunction();
	
	protected IGoal notification(){
		return this;
	}
	
}
