package gameobject.component;

import gameobject.component.type.CommonType;

/**
 * A common framework for components that are movable in the game.  
 * @author Bihan Zhuang
 *
 */
@SuppressWarnings("serial")
public class Actor implements Component {

    public static final double HSPEED = 100, VSPEED = 200;
    public static final Facing DEFAULT_FACING = Facing.RIGHT;
    
    public enum Facing {
    	LEFT, RIGHT;
    }
	
	private double myHSpeed, myVSpeed, myLives;
	private CommonType<? extends Actor> myType;
	private Facing myFacing;
	
	public Actor(CommonType<? extends Actor> champion) {
		this(HSPEED, VSPEED, champion);
		setFacing(DEFAULT_FACING);
	}

	public Actor(double hSpeed, double vSpeed, CommonType<? extends Actor> type) {
		myHSpeed = hSpeed;
		myVSpeed = vSpeed;
		myType = type;
	}
	
	public Actor(Actor other) {
		myHSpeed = other.myHSpeed;
		myVSpeed = other.myVSpeed;
		myType = other.myType;
	}

	@Override
	public Actor copy() {
		return new Actor(this);
	}

	@Override
	public CommonType<? extends Component> getType() {
		return myType;
	}

	public double getHSpeed() {
		return myHSpeed;
	}
	
	public double getVSpeed() {
		return myVSpeed;
	}
	
	public void setHSpeed(double hspeed) {
		myHSpeed = hspeed;
	}
	
	public void setVSpeed(double vspeed) {
		myVSpeed = vspeed;
	}

	public double getLives() {
		return myLives;
	}

	public void setLives(double value) {
		myLives = value;
	}

	public Facing getFacing() {
		return myFacing;
	}

	public void setFacing(Facing facing) {
		myFacing = facing;
	}
}
