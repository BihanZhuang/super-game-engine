package engine.physicsUtil;

import java.util.function.Function;

import model.Environment;

public class PhysicsToolKit {
	private double frameTime;
	private CollisionDetector collisionAlgo;
	private Function<Environment.Constant, Double> constantsGetter;
	
	public PhysicsToolKit(Function<Environment.Constant, Double> constantsGetter) {
		collisionAlgo=new CollisionDetector();
		this.constantsGetter=constantsGetter;
	}
	
	public Function<Environment.Constant, Double> getConstantsGetter() {
		return constantsGetter;
	}

	public void setConstantsGetter(Function<Environment.Constant, Double> constantsGetter) {
		this.constantsGetter = constantsGetter;
	}

	public void setFrameTime(double frameTime){
		this.frameTime=frameTime;
	}

	public double getFrameTime() {
		return frameTime;
	}

	public CollisionDetector getCollisionAlgo() {
		return collisionAlgo;
	}

	public void setCollisionAlgo(CollisionDetector collisionAlgo) {
		this.collisionAlgo = collisionAlgo;
	}


}
