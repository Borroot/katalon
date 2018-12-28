package game.modes;

import game.gui.GuiController;
import game.logic.Board;
import game.logic.Logic;
import game.logic.Player;

public abstract class XvX {

	protected GuiController gui;
	private Board board;
	private Player onTurn = Player.RED;
	
	private int prevprevMove = -1;
	private int prevMove = -1;
	
	public XvX() {
	}
	
	public XvX(GuiController gui) {
		this.gui = gui;
		this.board = gui.getBoard();
	}
	
	/**
	 * @return Square number.
	 * Ask the Player which square the first move belongs to.
	 * This will be different for Humans then for Computers.
	 */
	protected abstract int firstMoveIsDouble(int cellNumber);
	
	/**
	 * @param cellNumber
	 * Make the move if the move is valid and check if it's game over.
	 */
	protected void move(int cellNumber) {
		
		if(prevMove == -1 && Logic.isDoubleCell(cellNumber))
			prevMove = firstMoveIsDouble(cellNumber);
		else if(Logic.validMove(board, cellNumber, prevMove, prevprevMove)) {
			prevMove = cellNumber;
			prevprevMove = prevMove;
			if(onTurn == Player.YELLOW) {
				board.getCellsArray()[cellNumber].setOccupy(Player.YELLOW);
				onTurn = Player.RED;
			}else {
				board.getCellsArray()[cellNumber].setOccupy(Player.RED);
				onTurn = Player.YELLOW;
			}
		}
		
		if(Logic.gameIsOver(board)) {
			System.out.println("Game Over!");
		}
	}
}
