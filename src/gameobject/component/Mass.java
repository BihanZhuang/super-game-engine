package gameobject.component;

import gameobject.component.type.ComponentTypes;

public class Mass extends Scalar {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Mass(double val) {
        super(val, ComponentTypes.MASS);
    }

    @Override
    public Component copy() {
        return new Mass(getValue());
    }

}
