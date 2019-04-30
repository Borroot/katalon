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
	protected int prevMove = -1;

	public XvX() {
	}

	public XvX(GuiController gui) {
		this.gui = gui;
		this.board = gui.getBoard();
	}

	/**
	 * Ask the Player which square the first move belongs to.
	 * This will be different for Humans then for Computers.
	 *
	 * @return Square number.
	 */
	protected abstract int firstMoveIsDouble(int cellNumber);

	/**
	 * Make the move if the move is valid and check if it's game over.
	 *
	 * @param cellNumber
	 */
	protected void move(int cellNumber) {

		if (prevMove == -1 && Logic.isDoubleCell(cellNumber)){
			prevMove = firstMoveIsDouble(cellNumber);
			board.getCellsArray()[cellNumber].setOccupy(onTurn);
			changeTurn();
		}else if (Logic.validMove(board, cellNumber, prevMove, prevprevMove)) {
			prevMove = cellNumber;
			prevprevMove = prevMove;
            board.getCellsArray()[cellNumber].setOccupy(onTurn);
            changeTurn();
		}

		if (Logic.gameIsOver(board)) {
			System.out.println("Game Over!");
		}
	}

	private void changeTurn(){
		if(onTurn == Player.RED)
			onTurn = Player.YELLOW;
		else
			onTurn = Player.RED;
	}
}
