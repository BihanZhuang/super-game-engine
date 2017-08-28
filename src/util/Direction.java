package util;

public enum Direction {
	UP,
	DOWN,
	LEFT,
	RIGHT,
	NONE;
	
	public Direction reverse(){
		if(this==UP){
			return DOWN;
		} else if(this==DOWN){
			return UP;
		} else if(this==LEFT){
			return RIGHT;
		} else if(this==RIGHT){
			return LEFT;
		} else {
			return NONE;
		}
	}
}
