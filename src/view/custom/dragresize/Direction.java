package view.custom.dragresize;

import javafx.scene.Cursor;

public enum Direction {
	DEFAULT (false, false, false, false),
	W (false, false, true, false),
	E (false, false, false, true),
	N (true, false, false, false),
	S (false, true, false, false),
	SW (false, true, true, false),
	SE (false, true, false, true),
	NW (true, false, true, false),
	NE (true, false, false, true);

	private final boolean top;
	private final boolean bottom;
	private final boolean left;
	private final boolean right;
	
	Direction(boolean top, boolean bottom, boolean left, boolean right) {
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
	}
	
	public Cursor cursor() {
		switch (this) {
		case NW:
			return Cursor.NW_RESIZE;
		case SW:
			return Cursor.SW_RESIZE;
		case NE:
			return Cursor.NE_RESIZE;
		case SE:
			return Cursor.SE_RESIZE;
		case E:
			return Cursor.E_RESIZE;
		case W:
			return Cursor.W_RESIZE;
		case N:
			return Cursor.N_RESIZE;
		case S:
			return Cursor.S_RESIZE;
		default:
			return Cursor.DEFAULT;
		}
	}
	
	public static Direction get(boolean top, boolean bottom, boolean left, boolean right) {
		for(Direction dir : Direction.values()) {
			if(dir.bottom() == bottom && dir.top() == top && dir.left() == left && dir.right() == right)
				return dir;
		}
        return DEFAULT;
	}
	
	public boolean top() {
		return top;
	}
	
	public boolean bottom() {
		return bottom;
	}
	
	public boolean left() {
		return left;
	}
	
	public boolean right() {
		return right;
	}
}
