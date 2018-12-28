package game.logic;

public class Logic {
	
	/**
	 * This class should not be instantiated.
	 */
	private Logic() {
	}
	
	/**
	 * @param board
	 * @param cellNumber
	 * @param previousMove
	 * @return If a move is valid or not.
	 */
	public static boolean validMove(Board board, int cellNumber, int prevMove, int prevprevMove) {
		if(gameIsOver(board))
			return false;
		if(prevMove == -1)
			return true;
		
		//TODO: Finish if a move is valid.
		
		return true;
	}
	
	/**
	 * @param board
	 * @return If a board state is game over or not.
	 * 
	 * A board state is game over if one of the following three conditions holds:
	 * 	1. One square is filled by a single Player.
	 * 	2. The whole board is filled.
	 * 	3. One Player cannot make a move because it doesn't have stones left.
	 * 	   Each player has TODO: (amount of stones) stones.
	 */
	public static boolean gameIsOver(Board board) {
		if(oneSquareIsFull(board))
			return true;
		if(stonesEmpty(board))
			return true;
		if(board.isFull())
			return true;
		
		return false;
	}
	
	private static boolean stonesEmpty(Board board) {
		//TODO: Implement if someone doesn't have any stones left.
		
		return false;
	}
	
	/**
	 * @param board
	 * @return If one of the squares is full.
	 */
	private static boolean oneSquareIsFull(Board board) {
		
		Cell[] square;
		
		square = board.getSquare(Position.TOPLEFT);
		if(thisSquareIsFull(square))
			return true;
		
		square = board.getSquare(Position.TOPRIGHT);
		if(thisSquareIsFull(square))
			return true;
		
		square = board.getSquare(Position.CENTER);
		if(thisSquareIsFull(square))
			return true;
		
		square = board.getSquare(Position.BOTTOMLEFT);
		if(thisSquareIsFull(square))
			return true;
		
		square = board.getSquare(Position.BOTTOMRIGHT);
		if(thisSquareIsFull(square))
			return true;
		
		return false;
	}
	
	/**
	 * @param square
	 * @return If this square is full, aka all cells are occupied by the same Player which not Player.NONE.
	 */
	private static boolean thisSquareIsFull(Cell[] square) {
		return square[0].getOccupy() != Player.NONE
			&& square[0].getOccupy() == square[1].getOccupy()
			&& square[1].getOccupy() == square[2].getOccupy()
			&& square[2].getOccupy() == square[3].getOccupy()
			&& square[3].getOccupy() == square[4].getOccupy();
	}
	
	public static boolean isDoubleCell(int cellNumber) {
		return cellNumber == 4 || cellNumber == 8 || cellNumber == 11 || cellNumber == 15;
	}
	
	/**
	 * @param cellNumber
	 * @param prevMove
	 * @return Return an Position-array with as elements the squares a given cellNumber is in.
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
	
}
