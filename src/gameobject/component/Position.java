package gameobject.component;

import gameobject.component.type.CommonType;

@SuppressWarnings("serial")
public class Position extends Vector2D {
    
    public static final CommonType<Vector2D> TYPE = new CommonType<Vector2D>("Position") {};

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Position() {
        super(TYPE);
    }
    
    public Position(double x, double y) {
        super(x, y, TYPE);
    }
    
    @Override
    public Position copy() {
        return new Position(getX(), getY());
    }

}
