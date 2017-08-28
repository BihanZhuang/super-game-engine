package gameobject.component.type;

import gameobject.component.Component;

@SuppressWarnings("serial")
public abstract class CommonType<T extends Component> extends ComponentType<T> {
    
    private String myName;

    public CommonType(String name) {
        myName = name;
    }
    
    @Override
    public boolean equals(Object other) {
        return super.equals(other) && (other instanceof CommonType<?>) &&
                myName.equals(((CommonType<?>) other).myName);
    }
    
    @Override
    public int hashCode() {
        return 41 * myName.hashCode() + super.hashCode();
    }
    
}
