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
	 * @param nextPos
	 * @return If a move is valid or not.
	 */
	public static boolean validMove(Board board, Player onTurn, int cellNumber, Position nextPos) {
	    if(gameIsOver(board, onTurn))
	    	return false;
		if(nextPos == null)
			return true;
		
		//TODO: Finish if a move is valid.

		// square is according to prevmove
        if(!isDoubleCell(cellNumber)){
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
	 * @return The square the double cell belongs to apart from the centre square.
	 */
	public static Position getDoubleSquare(int cellNumber) {
		assert(cellNumber >= 0 && cellNumber < 21 && Logic.isDoubleCell(cellNumber));
		
		switch(cellNumber) {
            case 4: return Position.TOPLEFT;
            case 8: return Position.TOPRIGHT;
            case 11: return Position.BOTTOMLEFT;
            case 15: return Position.BOTTOMRIGHT;
			default: return null;
        }
	}

	public static Position getPos(int cellNumber, Position square){
        if(square == Position.CENTER){
            switch (cellNumber) {
				case 4: return Position.TOPLEFT;
				case 8: return Position.TOPRIGHT;
				case 11: return Position.BOTTOMLEFT;
				case 15: return Position.BOTTOMRIGHT;
			}
		}else{
            int mod = cellNumber % 5;
            switch (mod){
				case 2: return Position.CENTER;
				case 0: return Position.TOPLEFT;
				case 1: return Position.TOPRIGHT;
				case 3: return Position.BOTTOMLEFT;
				case 4: return Position.BOTTOMRIGHT;
			}
        }
        return null;
	}

}
