package game.modes;

import game.gui.GuiController;
import game.logic.Board;
import game.logic.Logic;
import game.logic.Player;
import game.logic.Position;

public abstract class XvX {

	protected GuiController gui;
	private Board board;
	private Player onTurn = Player.RED;

	protected int tempCellNumber;
	protected Position prevMoveCircle = null;
	protected Position prevMoveSquare = null;

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
		System.out.println(prevMoveSquare + " " + prevMoveCircle);

		if (prevMoveSquare == null && Logic.isDoubleCell(cellNumber)){
			tempCellNumber = cellNumber;
			prevMoveSquare = firstMoveIsDouble(cellNumber);
			board.getCellsArray()[cellNumber].setOccupy(onTurn);
			changeTurn();
		}else if (Logic.validMove(board, onTurn, cellNumber, prevMoveCircle, prevMoveSquare)) {
			prevMoveCircle = Logic.getCircle(cellNumber, prevMoveSquare);
			prevMoveSquare = Logic.getSquare(cellNumber, prevMoveSquare);
            board.getCellsArray()[cellNumber].setOccupy(onTurn);
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
}
