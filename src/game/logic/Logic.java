package game.logic;

public class Logic {

    private static final int AMOUNT_OF_STONES = 1;

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
	public static boolean validMove(Board board, Player onTurn, int cellNumber, int lastCellNumber, Position nextPos) {
	    if(gameIsOver(board, onTurn))
	    	return false;
		if(nextPos == null)
			return true;
		
		return inValidSquare(cellNumber, nextPos) && tookValidPiece(board, onTurn, cellNumber, lastCellNumber, nextPos);
	}

	/**
	 *
	 * @param board
	 * @param onTurn
	 * @param cellNumber
	 * @param lastCellNumber
	 * @param nextPos
	 * @return If the cell can be occupied by this person.
	 */
	private static boolean tookValidPiece(Board board, Player onTurn, int cellNumber, int lastCellNumber, Position nextPos){
		if(board.getCell(cellNumber).isFree()){
			return true;
		}else{
			// Check if this square is already yours.
			if(board.getCell(cellNumber).getOccupy() == onTurn)
				return false;

			// Check if there is a free cell in the square.
			Cell[] square = board.getSquare(nextPos);
			for(Cell cell : square)
				if(cell.isFree())
					return false;

			// Check if this cell was occupied with the last move.
			if(lastCellNumber == cellNumber){
				// Check if this is the only non-free cell in the square.
				int count = 0;
				for(Cell cell : square)
					if(cell.getOccupy() != onTurn)
						count++;
				if(count > 1)
					return false;
			}
		}
		return true;
	}

	/**
	 * @param cellNumber
	 * @param nextPos
	 * @return If the move is made in a valid square.
	 */
	private static boolean inValidSquare(int cellNumber, Position nextPos){
		if(isDoubleCell(cellNumber)){
			if(nextPos == Position.CENTER)
				return true;
			else
				return nextPos == getDoubleSquare(cellNumber);
		}
		return nextPos == getSquare(cellNumber);
	}

	public static Player otherPlayer(Player player){
	    return (player == Player.RED)? Player.YELLOW : Player.RED;
	}
	/**
	 * @param board
	 * @param onTurn This is the person who did NOT make the last move.
	 * @return The winner of the current game.
	 */
	public static Player winner(Board board, Player onTurn){
	    if(!board.isFull()){
	    	if(oneSquareIsFull(board))
	    		return otherPlayer(onTurn);
	    	else
	    		return onTurn;
		}else{
	    	int squareCount = 0;
	    	for(Position pos : Position.values()){
	    		int cellCount = 0;
	    		Cell[] cells = board.getSquare(pos);

	    		for(Cell cell : cells)
	    			if(cell.getOccupy() == onTurn)
	    				cellCount++;

	    		if(cellCount > 2)
	    			squareCount++;
			}
	    	return (squareCount > 2)? onTurn : otherPlayer(onTurn);
		}
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

	/**
	 * @param board
	 * @param onTurn
	 * @return If someone has run out of stones.
	 */
	public static boolean stonesEmpty(Board board, Player onTurn) {
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
	 * This function is only meant to be called for non-double cells.
	 * @param cellNumber
	 * @return The square this cell belongs to.
	 */
	public static Position getSquare(int cellNumber){
		assert(!Logic.isDoubleCell(cellNumber));

		if(cellNumber < 4)
			return Position.TOPLEFT;
		else if(cellNumber < 10)
			return Position.TOPRIGHT;
		else if(cellNumber < 15)
			return Position.BOTTOMLEFT;
		else if(cellNumber < 20)
			return Position.BOTTOMRIGHT;
		else
			return Position.CENTER;
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

	/**
	 *
	 * @param cellNumber
	 * @param square
	 * @return The position of the cellNumber relative to the square given.
	 */
	public static Position getPos(int cellNumber, Position square){
        if(square == Position.CENTER){
            switch (cellNumber) {
				case 4: return Position.TOPLEFT;
				case 8: return Position.TOPRIGHT;
				case 11: return Position.BOTTOMLEFT;
				case 15: return Position.BOTTOMRIGHT;
				case 20: return Position.CENTER;
			}
		}else{
        	// This is a really nasty corner case.
            if(square == null && cellNumber == 20)
            	return Position.CENTER;

            int mod = cellNumber % 5;
            switch (mod){
				case 0: return Position.TOPLEFT;
				case 1: return Position.TOPRIGHT;
				case 2: return Position.CENTER;
				case 3: return Position.BOTTOMLEFT;
				case 4: return Position.BOTTOMRIGHT;
			}
        }
        return null;
	}

}
