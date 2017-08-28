package gameobject.component;

import gameobject.component.type.ComponentType;
import gameobject.component.type.ComponentTypes;

public class Boss implements Component{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component copy() {
		return new Boss();
	}

	@Override
	public ComponentType<? extends Component> getType() {
		return ComponentTypes.BOSS;
	}

}
