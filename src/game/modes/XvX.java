package game.modes;

import game.gui.GuiController;
import game.logic.Board;
import game.logic.Logic;
import game.logic.Player;
import game.logic.Position;

public abstract class XvX {

	protected GuiController gui;
	protected Board board;
	private Player onTurn = Player.RED;

	protected Position nextPos = null;

	public XvX() {
	}

	public XvX(GuiController gui) {
		this.gui = gui;
		this.board = gui.getBoard();
	}

	/**
	 * Ask the Player which square the first move belongs to.
	 * This will be different for Humans then for Computers.
	 * @return Square number.
	 */
	protected abstract Position firstMoveIsDouble(int cellNumber);

	/**
	 * Make the move if the move is valid and check if it's game over.
	 * @param cellNumber
	 */
	protected void move(int cellNumber) {

		if (nextPos == null && Logic.isDoubleCell(cellNumber)){
			firstMoveIsDouble(cellNumber);
			occupy(cellNumber);
			changeTurn();
		}else if (Logic.validMove(board, onTurn, cellNumber, nextPos)) {
			nextPos = Logic.getPos(cellNumber, nextPos);
			occupy(cellNumber);
            changeTurn();
		}

		if (Logic.gameIsOver(board, onTurn)) {
			System.out.println("Game Over!");
		}
	}

	private void changeTurn(){
		if(onTurn == Player.RED)
			onTurn = Player.YELLOW;
		else
			onTurn = Player.RED;
	}

	private void occupy(int cellNumber){
		board.getCellsArray()[cellNumber].setOccupy(onTurn);
	}
}
