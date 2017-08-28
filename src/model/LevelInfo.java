package model;

import java.util.List;

import goalstrategies.IGoal;

/**
 * Interface for Level, doesn't contain the information of World
 * @author Yanbo Fang
 *
 */
public interface LevelInfo {

	/**
	 * Get the list of goals for a level
	 * @return List<IGoal> 
	 */
    List<IGoal> getGoals();
    
    /**
     * Add a Goal into the level
     * @param strategy
     */
    void addGoal(IGoal strategy);
    
    /**
     * Get the target weight of a level
     * @return double
     */
    double getTargetWeight();
    
    /**
     * Set the target weight of a level
     * @param weight
     */
    void setGoalWeight(double weight);
}
