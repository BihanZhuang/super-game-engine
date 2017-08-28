package system;

import java.util.HashMap;
import java.util.Map;

import gameobject.IGameObject;
import gameobject.component.Vector2D;
import gameobject.component.type.ComponentTypes;
import javafx.scene.input.KeyCode;
import util.Constants;
import util.ResourceParser;

public class UserControl {

	public static final String KEYINPUT_FILE = "keyinputs";
	
	public enum Behavior {
		UP, DOWN, LEFT, RIGHT, ATTACK, USEITEM
	}

	private IGameObject myChampion;
	private Map<Behavior, KeyCode> keyMap;
	private double hSpeed, vSpeed;

	public UserControl(IGameObject champion, double x, double y) {
		myChampion = champion;
		keyMap = new HashMap<>();
		hSpeed = x;
		vSpeed = y;
		resetDefault();
	}
	
	public IGameObject getChampion() {
	    return myChampion;
	}
	
	public void setChampion(IGameObject champion) {
	    myChampion = champion;
	}

	public void onKeyPrssed(KeyCode e) {
		Vector2D velocity = myChampion.getComponent(ComponentTypes.VELOCITY);
		if (e == keyMap.get(Behavior.LEFT)) {
			velocity.setX(-1 * hSpeed);
		} else if (e == keyMap.get(Behavior.RIGHT)) {
			velocity.setX(hSpeed);
		} else if (e == keyMap.get(Behavior.DOWN)) {
			velocity.setY(vSpeed);
		} else if (e == keyMap.get(Behavior.UP)) {
			velocity.setY(-1 * vSpeed);
		} else if (e == keyMap.get(Behavior.ATTACK)) {
			double PosX = myChampion.getPosition().getX();
			double PosY = myChampion.getPosition().getY();
			myChampion.getComponent(ComponentTypes.BELONGINGS).attack(PosX, PosY);
			System.out.println("Space Detected");
		} else if (e == keyMap.get(Behavior.USEITEM)) {
			
		}
	}

	public void onKeyReleased(KeyCode e) {
		Vector2D velocity = myChampion.getComponent(ComponentTypes.VELOCITY);
		if (e == keyMap.get(Behavior.LEFT)) {
			velocity.setX(0);
		} else if (e == keyMap.get(Behavior.RIGHT)) {
			velocity.setX(0);
		} else if (e == keyMap.get(Behavior.UP)) {
			velocity.setY(0);
		} else if (e == keyMap.get(Behavior.DOWN)) {
			velocity.setY(0);
		}
	}

	public void setKeyCode(Behavior behavior, KeyCode code) {
		keyMap.put(behavior, code);
	}

	public void resetDefault() {
		ResourceParser parser = new ResourceParser(Constants.RESOURCE_PACKAGE + KEYINPUT_FILE);
		parser.parseAll((behavior, keyCode) -> keyMap.put(Behavior.valueOf(behavior), KeyCode.valueOf(keyCode)));
	}
	
	public void setKeyPref() {
		
	}
	
	public double getvSpeed() {
		return vSpeed;
	}

	public void setvSpeed(double vSpeed) {
		this.vSpeed = vSpeed;
	}

	public double gethSpeed() {
		return hSpeed;
	}

	public void sethSpeed(double hSpeed) {
		this.hSpeed = hSpeed;
	}

}
