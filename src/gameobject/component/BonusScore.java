package gameobject.component;

import gameobject.component.type.ComponentType;
import gameobject.component.type.ComponentTypes;

public class BonusScore implements Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int score = 0;

	public BonusScore() {
		this.score = 0;
	}

	public BonusScore(int score) {
		this.score = score;
	}

	public BonusScore(BonusScore other) {
		this.score = other.score;
	}

	public int getBonusScore() {
		return score;
	}

	public void setBonusScore(int s) {
		this.score = s;
	}

	@Override
	public Component copy() {
		return new BonusScore(this);
	}

	@Override
	public ComponentType<? extends Component> getType() {
		return ComponentTypes.BONUSSCORE;
	}

}
