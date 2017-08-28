package gameobject.component;

import gameobject.component.type.ComponentType;
import gameobject.component.type.ComponentTypes;

/**
 * Component that manages the health level of a game object.
 * 
 * @author Bihan Zhuang
 *
 */
public class Health extends MaxAndCurrComponent implements Component  {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public final static double DEFAULT_HEALTH = 100;

    private double myMax, myCurrent;

    public Health() {
        this(DEFAULT_HEALTH);
    }

    /*
     * The use of this constructor is for customizing the maximum health when
     * making a GameObject in the editor
     */
    public Health(double max) {
        this(max, max);
    }

    private Health(double max, double current) {
        myMax = max;
        myCurrent = current;
    }

    public ComponentType<Health> getType() {
        return ComponentTypes.HEALTH;
    }

    public double getMaxHealth() {
        return myMax;
    }

    public void setMaxHealth(double value) {
        myMax = value;
    }

    public double getCurrentHealth() {
        return myCurrent;
    }

    public void setCurrentHealth(double value) {
        myCurrent = value;
    }

    public void changeCurrentHealth(double value) {
        myCurrent += value;
    }

    /*
     * public void die() { //TODO }
     */

    public Health copy() {
        return new Health(myMax, myCurrent);
    }


	@Override
	public double getMaxValue() {
		return myMax;
	}

	@Override
	public double getCurrValue() {
		return myCurrent;
	}
}
