package gameobject.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameobject.component.type.ComponentType;
import gameobject.component.type.ComponentTypes;

public class Animation implements Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum State {
		STAY, MOVE, INJURE, JUMP, DIE;
	}

	private Map<State, List<String>> myAnimations;
	private State myCurrentState;
	private double lasttime;
	private int index;

	public Animation() {
		this(new HashMap<State, List<String>>());
	}

	public Animation(Map<State, List<String>> animationMap) {
		myAnimations = animationMap;
		myCurrentState = State.STAY;
		index = 0;
	}

	/**
	 * Add a state and its corresponding image path to the animation map, update
	 * the image path if the state already existed in the map
	 * 
	 * @param State
	 *            - s
	 * @param String
	 *            - imagePath
	 */
	public void add(State s, String imagePath) {
		List<String> lst = new ArrayList<String>();
		if (myAnimations.containsKey(s)) {
			lst = myAnimations.get(s);
		}
		lst.add(imagePath);
		myAnimations.put(s, lst);
	}

	/**
	 * Set the current state to s
	 * 
	 * @param State
	 *            - s
	 */
	public void setState(State s) {
		myCurrentState = s;
	}

	/**
	 * Get the current state - STILL, MOVING, etc.
	 * 
	 * @return State
	 */
	public State getState() {
		return myCurrentState;
	}

	/**
	 * Get the image path of the current state
	 * 
	 * @return the image path as a String
	 */
	public String getImagePath() {
		try {
			String path = myAnimations.get(myCurrentState).get(index);
			if (index + 1 < myAnimations.get(myCurrentState).size()) {
				index++;
			} else {
				index = 0;
			}
			return path;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Get the image path of the input state, first in the list
	 * 
	 * @return the image path as a String
	 */
	public String getImagePath(State s) {
		return myAnimations.get(s).get(0);
	}

	@Override
	public ComponentType<? extends Component> getType() {
		return ComponentTypes.ANIMATION;
	}

	@Override
	public Component copy() {
		return new Animation(myAnimations);
	}

	public double getLasttime() {
		return lasttime;
	}

	public void setLasttime(double lasttime) {
		this.lasttime = lasttime;
	}

}