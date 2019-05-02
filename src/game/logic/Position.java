package game.logic;

/**
 * This class represents a relative position on the playing board.
 * The positions can be used for the Cells and for the Squares.
 * @author Bram Pulles
 */
public enum Position {
	TOPLEFT(0), TOPRIGHT(1), BOTTOMLEFT(2), BOTTOMRIGHT(3), CENTER(4);

	private final int code;

	Position(int code){
		this.code = code;
	}

	/**
	 * @return The code of this position.
	 */
	public int getCode(){
		return code;
	}

	/**
	 * @param code
	 * @return The position using the code.
	 */
	public static Position getPos(int code){
		for(Position pos : Position.values())
			if(pos.getCode() == code)
				return pos;
		return null;
	}
}
