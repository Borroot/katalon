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

	protected int lastCellNumber = -1;
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

		System.out.println(nextPos);
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
			System.out.println("Game Over! The winner is: " + Logic.winner(board, onTurn));
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
