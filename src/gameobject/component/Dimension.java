package gameobject.component;

import gameobject.component.type.CommonType;

@SuppressWarnings("serial")
public class Dimension extends Vector2D {
    
    public static final CommonType<Vector2D> TYPE = new CommonType<Vector2D>("Dimension") {};

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Dimension() {
        super(TYPE);
    }
    
    public Dimension(double x, double y) {
        super(x, y, TYPE);
    }
    
    @Override
    public Dimension copy() {
        return new Dimension(getX(), getY());
    }

}
