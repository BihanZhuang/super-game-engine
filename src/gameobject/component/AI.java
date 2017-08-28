package gameobject.component;

import gameobject.component.type.CommonType;

/**
 * Provides framework for all the AI's in the game.
 * @author Bihan Zhuang
 *
 */
@SuppressWarnings("serial")
public class AI extends Actor {
	
	private double left, right;
	private double initX, initY;

    public AI(CommonType<AI> type) {
		super(type);
	}

	public AI(double hSpeed, double vSpeed, CommonType<AI> type) {
		super(hSpeed, vSpeed, type);
	}

	public AI(AI other) {
		super(other);
		initX = other.initX;
		initY = other.initY;
		left = other.left;
		right = other.right;
	}
	
	@Override
	public AI copy() {
		return new AI(this);
	}

	public double getLeft() {
		return left;
	}

	public void setLeft(double left) {
		this.left = left;
	}

	public double getRight() {
		return right;
	}

	public void setRight(double right) {
		this.right = right;
	}

	public double getInitX() {
		return initX;
	}

	public void setInitX(double initX) {
		this.initX = initX;
	}

	public double getInitY() {
		return initY;
	}

	public void setInitY(double initY) {
		this.initY = initY;
	}
}
