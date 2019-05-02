package game.logic;

import java.util.Arrays;

/**
 * This class represents a playing board for Katalon.
 * @author Bram Pulles
 */
public class Board {
	
	private Cell cells[] = new Cell[21];
	
	public Board() {
		initializeCells();
	}

	/**
	 * Give the board cells.
	 */
	private void initializeCells() {
		for(int i = 0; i < cells.length; i++) {
			cells[i] = new Cell(i);
		}
	}

	/**
	 * @param cellNumber
	 * @return The corresponding cell.
	 */
	public Cell getCell(int cellNumber){
		return cells[cellNumber];
	}

	/**
	 * @param squarePos
	 * @param circlePos
	 * @return The cell which corresponds to the relative positions given.
	 */
	public Cell getCell(Position squarePos, Position circlePos) {
		Cell[] square = getSquare(squarePos);
		
		switch(circlePos) {
			case TOPLEFT: 		return square[0];
			case TOPRIGHT: 		return square[1];
			case CENTER: 		return square[2];
			case BOTTOMLEFT:	return square[3];
			case BOTTOMRIGHT:	return square[4];
			default: return null;
		}
	}

	/**
	 * @param pos
	 * @return The square (an array of cells) which corresponds to the relative position given.
	 */
	public Cell[] getSquare(Position pos) {
		Cell[] square = new Cell[5]; 
		
		switch(pos) {
			case TOPLEFT: 		square = Arrays.copyOfRange(cells, 0, 5); 	break;
			case TOPRIGHT: 		square = Arrays.copyOfRange(cells, 5, 10);	break;
			case BOTTOMLEFT: 	square = Arrays.copyOfRange(cells, 10, 15);	break;
			case BOTTOMRIGHT: 	square = Arrays.copyOfRange(cells, 15, 20);	break;
			case CENTER: 		square[0] = cells[4];
								square[1] = cells[8];
								square[2] = cells[20];
								square[3] = cells[11];
								square[4] = cells[15]; break;
		}
		
		return square; 
	}

	/**
	 * @return If the board is full.
	 */
	public boolean isFull() {
		for(int i = 0; i < cells.length; i++) {
			if(cells[i].isFree())
				return false;
		}
		return true;
	}

	/**
	 * @return An array with all the Cells on the board.
	 */
	public Cell[] getCellsArray() {
		return cells;
	}
}
