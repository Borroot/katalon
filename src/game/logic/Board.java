package game.logic;

import java.util.Arrays;

/**
 * @author Borroot
 * 
 * The layout of the board is as follows: 
 * _______________
 * | 0   1| 5   6|
 * |   2__|__ 7  |
 * | 3 | 4| 8|  9|
 * ------ 20 -----
 * |10 |11|15| 16|
 * |  12--|--17  |
 * |13  14|18  19|
 * ---------------
 */
public class Board {
	
	private Cell cells[] = new Cell[21];
	
	public Board() {
		initializeCells();
	}
	
	private void initializeCells() {
		for(int i = 0; i < cells.length; i++) {
			cells[i] = new Cell(i);
		}
	}
	
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
	
	public boolean isFull() {
		for(int i = 0; i < cells.length; i++) {
			if(cells[i].isFree())
				return false;
		}
		return true;
	}
	
	public Cell[] getCellsArray() {
		return cells;
	}
}
