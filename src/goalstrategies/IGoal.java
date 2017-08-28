package goalstrategies;

import engine.PhysicsEngine;
import system.ISystemInfo;
import util.Observable;
import util.TriFunction;

/**
 * Interface for Goal Strategies
 * 
 * @author Yanbo Fang
 *
 */
public interface IGoal extends Observable<IGoal>{

	/**
	 * Set the weight for a goal strategy
	 * 
	 * @param double weight
	 */
	void setWeight(double weight);

	/**
	 * Return a double value of the weight of that goal
	 * 
	 * @return Double
	 */
	Double getWeight();

	/**
	 * Return a function for the specific goal strategy. This function accepts
	 * an ISystemInfo, a PhysicsEngine and a Double, returns a Double (the
	 * current weight)
	 * 
	 * @return TriConsumer<ISystemInfo, PhysicsEngine, Double>
	 */
	TriFunction<ISystemInfo, PhysicsEngine, Double, Double> getFunction();
	
	/**
	 * Check if a goal is finished or not, since we don't want to repeatedly checking the goal once it's finished
	 * 
	 * @return boolean, true if the goal is finished, false otherwise
	 */
	boolean isFinished();
	
	/**
	 * THe status of the goal
	 * @return String, the status of the goal
	 */
	String getStatus();
	
}
