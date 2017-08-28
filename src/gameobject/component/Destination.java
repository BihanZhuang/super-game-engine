package gameobject.component;

import gameobject.component.type.ComponentType;
import gameobject.component.type.ComponentTypes;

public class Destination implements Component{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component copy() {
		return new Destination();
	}

	@Override
	public ComponentType<? extends Component> getType() {
		return ComponentTypes.DESTINATION;
	}

}
