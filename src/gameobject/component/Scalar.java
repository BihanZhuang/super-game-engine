package gameobject.component;

import gameobject.component.type.CommonType;
import gameobject.component.type.ComponentType;

/**
 * Basic framework for <code>Components</code> that only contains 
 * one value.
 *
 * @param <T>
 */
public abstract class Scalar implements Component {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private double myVal;
	private CommonType<Scalar> myType;
	
	public Scalar(double val, CommonType<Scalar> type) {
		myVal = val;
		myType = type;
	}
	
	public double getValue(){
		return myVal;
	}
	
	
	public void setValue(double value){
		myVal = value;
	}

    @Override
    public ComponentType<? extends Component> getType() {
        return myType;
    }

}
