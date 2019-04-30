package game.logic;

import javafx.geometry.Pos;

public class Logic {

    private static final int AMOUNT_OF_STONES = 12;

	/**
	 * This class should not be instantiated.
	 */
	private Logic() {
	}
	
	/**
	 * @param board
	 * @param cellNumber
	 * @param prevMoveCircle
	 * @param prevMoveSquare
	 * @return If a move is valid or not.
	 */
	public static boolean validMove(Board board, Player onTurn, int cellNumber, Position prevMoveCircle, Position prevMoveSquare) {
	    if(gameIsOver(board, onTurn))
	    	return false;
		if(prevMoveCircle == null || prevMoveSquare == null)
			return true;
		
		//TODO: Finish if a move is valid.

		// square is according to prevmove
        Position[] pos = getSquares(cellNumber);
        if(pos.length == 1){
        	// non-double square

		}else{
        	// double square

		}

        // cell is taken
		if(!board.getCellsArray()[cellNumber].isFree()){
		    // the square is full

		}else{
		    // the square is not full

		}

		return true;
	}
	
	/**
	 * A board state is game over if one of the following three conditions holds:
	 * 	1. One square is filled by a single Player.
	 * 	2. The whole board is filled.
	 * 	3. One Player cannot make a move because it doesn't have stones left.
	 *
	 * @param board
	 * @return If a board state is game over or not.
	 */
	public static boolean gameIsOver(Board board, Player onTurn) {
		return oneSquareIsFull(board) || stonesEmpty(board, onTurn) || board.isFull();
	}
	
	private static boolean stonesEmpty(Board board, Player onTurn) {
	    Cell[] cells = board.getCellsArray();
	    int count = 0;
	    for(Cell cell : cells){
	    	if(cell.getOccupy() == onTurn)
	    		count++;
		}
		return count >= AMOUNT_OF_STONES;
	}
	
	/**
	 * @param board
	 * @return If one of the squares is full.
	 */
	private static boolean oneSquareIsFull(Board board) {
		for(Position pos : Position.values()){
			Cell[] square;
			square = board.getSquare(pos);
			if(thisSquareIsFull(square))
				return true;
		}
		return false;
	}
	
	/**
	 * @param square
	 * @return If this square is full, aka all cells are occupied by the same Player which not Player.NONE.
	 */
	private static boolean thisSquareIsFull(Cell[] square) {
		if(square[0].getOccupy() == Player.NONE)
		    return false;

		for(int i = 0; i < 4; i++)
			if(square[i].getOccupy() != square[i+1].getOccupy())
			    return false;

		return true;
	}
	
	public static boolean isDoubleCell(int cellNumber) {
		return cellNumber == 4 || cellNumber == 8 || cellNumber == 11 || cellNumber == 15;
	}
	
	/**
	 * @param cellNumber
	 * @return 	Return an Position-array with as elements the squares a given cellNumber is in.
	 * 			If the cellNumber is in two squares the last element is always the centre square.
	 */
	public static Position[] getSquares(int cellNumber) {
		assert(cellNumber >= 0 && cellNumber < 21);
		
		Position[] pos;
		
		if(Logic.isDoubleCell(cellNumber)) {
			pos = new Position[2];
			switch(cellNumber) {
				case 4: pos[0] = Position.TOPLEFT; pos[1] = Position.CENTER; break;
				case 8: pos[0] = Position.TOPRIGHT; pos[1] = Position.CENTER; break;
				case 11: pos[0] = Position.BOTTOMLEFT; pos[1] = Position.CENTER; break;
				case 15: pos[0] = Position.BOTTOMRIGHT; pos[1] = Position.CENTER; break;
			}
		}else{
			pos = new Position[1];
			if(cellNumber < 5)
				pos[0] = Position.TOPLEFT;
			else if(cellNumber < 10)
				pos[0] = Position.TOPRIGHT;
			else if(cellNumber < 15)
				pos[0] = Position.BOTTOMLEFT;
			else if(cellNumber < 20)
				pos[0] = Position.BOTTOMRIGHT;
			else
				pos[0] = Position.CENTER;
		}
		
		return pos;
	}

	/**
	 * @param cellNumber
	 * @param prevMoveSquare
	 * @return The square the current cellnumber belongs to.
	 */
	public static Position getSquare(int cellNumber, Position prevMoveSquare){
		if(isDoubleCell(cellNumber)){
			if(prevMoveSquare == Position.CENTER)
				return getSquares(cellNumber)[0];
			else
				return getSquares(cellNumber)[1];
		}else{
			return getSquares(cellNumber)[0];
		}
	}

	/**
	 * @param cellNumber
	 * @param prevMoveSquare
	 * @return The position the current cellnumber corresponds to.
	 */
	public static Position getCircle(int cellNumber, Position prevMoveSquare){
		if(isDoubleCell(cellNumber)){
			Position pos = getSquare(cellNumber, prevMoveSquare);
			if(pos == Position.CENTER){
				switch (cellNumber){
					case 4:	return Position.TOPLEFT;
					case 8: return Position.TOPRIGHT;
					case 11: return Position.BOTTOMLEFT;
					case 15: return Position.BOTTOMRIGHT;
				}
			}else{
				switch(cellNumber){
					case 4: return Position.BOTTOMRIGHT;
					case 8: return Position.BOTTOMLEFT;
					case 11: return Position.TOPRIGHT;
					case 15: return Position.TOPLEFT;
				}
			}
		}
		return null;
	}

	public static Position getCircleCurrent(int cellNumber, Position currentMoveSquare){
		if(currentMoveSquare == Position.CENTER){
			switch (cellNumber){
				case 4:	return Position.TOPLEFT;
				case 8: return Position.TOPRIGHT;
				case 11: return Position.BOTTOMLEFT;
				case 15: return Position.BOTTOMRIGHT;
			}
		}else{
			switch(cellNumber){
				case 4: return Position.BOTTOMRIGHT;
				case 8: return Position.BOTTOMLEFT;
				case 11: return Position.TOPRIGHT;
				case 15: return Position.TOPLEFT;
			}
		}
		return null;
	}

	/**
	 * @param pos
	 * @return Return the centre circle of a given square.
	 */
	public static int getCenter(Position pos){
		switch (pos){
			case TOPLEFT:		return 2;
			case TOPRIGHT:		return 7;
			case BOTTOMLEFT:	return 12;
			case BOTTOMRIGHT:	return 17;
			case CENTER:		return 20;
			default: return -1;
		}
	}
	
}
