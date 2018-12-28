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
	
}
