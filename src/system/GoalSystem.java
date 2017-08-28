package system;

import engine.PhysicsEngine;
import gameobject.IGameObject;
import goalstrategies.IGoal;

/**
 * Goal System, in charge of the goals
 * @author Yanbo Fang
 *
 */
public class GoalSystem extends AbstractSystem {
	
	private Double myCurrentWeight;
	
	public GoalSystem(ISystemInfo info, PhysicsEngine engine) {
		super(info, engine);
		myCurrentWeight = 0.0;
	}

	@Override
    public void step(double elapsedTime) {
		for(IGoal strategy: this.getSystemInfo().getGoalInfo().getGoals()){
			if(!strategy.isFinished()){
				myCurrentWeight = strategy.getFunction().apply(this.getSystemInfo(), this.getEngine(), myCurrentWeight);
				if (myCurrentWeight >= this.getSystemInfo().getGoalInfo().getTargetWeight()){
					this.getSystemInfo().setWin();
				}else if(myCurrentWeight < 0.0){
					this.getSystemInfo().setFail();
				}
			}
		}
	}


	@Override
	protected void singleStep(IGameObject obj, double elapsedTime) {
	}
	
	
}
