package gameobject.component;

import gameobject.component.type.ComponentType;
import gameobject.component.type.ComponentTypes;

public class Attack implements Component{
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public static final double DEFAULT_RANGE = 100;
	public static final double DEFAULT_DAMAGE = 10;
	
	private double myRange;
	private double myDamage;
	
	public Attack() {
		this(DEFAULT_RANGE, DEFAULT_DAMAGE);
	}
	
	public Attack(double range, double damage){
		myRange = range;
		myDamage = damage;
	}
	
	public double getRange(){
		return myRange;
	}

	public double getDamage(){
		return myDamage;
	}
	
	public void setRange(double range){
		myRange = range;
	}
	
	public void setDamage(double damage){
		myDamage = damage;
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
        return ComponentTypes.ATTACK;
	}

	@Override
	public Component copy() {
		return new Attack(myRange, myDamage);
	}

}
