package gameobject.component;

import engine.physicsStrategies.IPhysicsStrategy;
import gameobject.component.type.ComponentType;
import gameobject.component.type.ComponentTypes;

public class PhysicsBehavior implements Component{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private IPhysicsStrategy myPhysicsStrategy;
	
	public PhysicsBehavior(IPhysicsStrategy physicsStrategy) {
		myPhysicsStrategy=physicsStrategy;
	}

	public IPhysicsStrategy getStrategy(){
		return myPhysicsStrategy;
	}
	
	public void setStrategy(IPhysicsStrategy strategy){
		myPhysicsStrategy=strategy;
	}
	
	@Override
	public Component copy() {
		return new PhysicsBehavior(myPhysicsStrategy);
	}

	@Override
	public ComponentType<? extends Component> getType() {
		return ComponentTypes.PHYSICS_BEHAVIOR;
	}

}
