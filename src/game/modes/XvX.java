package game.modes;

import game.gui.GuiController;
import game.logic.Board;
import game.logic.Logic;
import game.logic.Player;
import game.logic.Position;

public abstract class XvX {

	protected Board board = new Board();
	protected Player onTurn = Player.RED;

	protected int lastCellNumber = -1;
	protected Position nextPos = null;

	public XvX() {
	}

	/**
	 * Ask the Player which square the first move belongs to.
	 * This will be different for Humans and Computers.
	 * @return The chosen square number position.
	 */
	protected abstract Position firstMoveIsDouble(int cellNumber);

	/**
	 * Show either a gui in a player vs player game or do some computations for analysis.
	 */
	protected abstract void gameOver();

	/**
	 * Make the move if the move is valid and check if it's game over.
	 * @param cellNumber
	 */
	protected void move(int cellNumber) {
		if (nextPos == null && Logic.isDoubleCell(cellNumber)){
			nextPos = firstMoveIsDouble(cellNumber);
			occupy(cellNumber);
			changeTurn();
		}else if (Logic.validMove(board, onTurn, cellNumber, lastCellNumber, nextPos)) {
			nextPos = Logic.getPos(cellNumber, nextPos);
			occupy(cellNumber);
            changeTurn();
		}

		if (Logic.gameIsOver(board, onTurn)) {
			gameOver();
		}
	}

	/**
	 * Change the onTurn to the next player.
	 */
	private void changeTurn(){
	    onTurn = Logic.otherPlayer(onTurn);
	}

	/**
	 * Occupy the given cell by the onTurn player.
	 * @param cellNumber
	 */
	private void occupy(int cellNumber){
		board.getCell(cellNumber).setOccupy(onTurn);
		lastCellNumber = cellNumber;
	}
}
