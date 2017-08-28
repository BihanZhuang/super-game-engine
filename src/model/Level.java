package model;

import java.util.ArrayList;
import java.util.List;

import goalstrategies.IGoal;

/**
 * Level class, contains the World and the Goals for a level
 * @author Yanbo Fang
 *
 */
public class Level implements ILevel {

	private IWorld myWorld;
    private double myTargetWeight;
    private List<IGoal> myGoals;

	public Level(IWorld world){
		myWorld = world;
        myGoals = new ArrayList<IGoal>();
        myTargetWeight = 1.0;
	}
	
	@Override
	public List<IGoal> getGoals(){
    	return myGoals;
    }
    
	@Override
	public void addGoal(IGoal strategy) {
		myGoals.add(strategy);
	}
	
	@Override
    public double getTargetWeight(){
    	return myTargetWeight;
    }
    
	@Override
    public void setGoalWeight(double weight){
    	myTargetWeight = weight;
    }
	
	@Override
	public IWorld getWorld(){
		return myWorld;
	}

    @Override
    public void copyFrom(ILevel other) {
        myWorld.copyFrom(other.getWorld());
        myGoals = new ArrayList<>(other.getGoals());
    }

    @Override
    public void clear() {
        myWorld.clear();
        myGoals.clear();
        myTargetWeight = 1.0;
    }
	
}
