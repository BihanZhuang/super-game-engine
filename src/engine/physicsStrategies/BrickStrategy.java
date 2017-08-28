package engine.physicsStrategies;

@SuppressWarnings("serial")
public class BrickStrategy extends NullVelocityStrategy {

	private final double FRICTION_COEFF=0.9;
	
	public BrickStrategy() {
		super(true);
	}

	public double getFrictionCoeff() {
		return FRICTION_COEFF;
	}

}
