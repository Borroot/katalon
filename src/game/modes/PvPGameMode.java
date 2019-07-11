package game.modes;

import game.gui.GuiController;
import game.logic.Board;
import game.logic.Cell;
import game.logic.Logic;
import game.logic.Position;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Player vs Player gamemode with a GUI.
 * @author Bram Pulles
 */
public class PvPGameMode extends XvX{

	protected GuiController gui;

	public PvPGameMode(GuiController gui) {
	    this.gui = gui;
	    gui.setBoard(board);

		setListenerCircleClicked();
		setListenerChooseSquare();
		setListenerReset();
	}
	
	/**
	 * The circleNumber property from the GuiController is connected to this listener.
	 * Whenever a circle is clicked the method to make the move is called.
	 */
	private void setListenerCircleClicked() {
		gui.getCircleNumberProperty().addListener((ChangeListener<Object>) (o, oldVal, cellNumber) -> {
			if((int)cellNumber == -1)
				return;
			else
				move((int)cellNumber);
		});
	}

	/**
	 * The chosenSquare property from the GuiController is connected to this listener.
	 * The nextPos is set whenever the player choose a square.
	 */
	private void setListenerChooseSquare(){
		gui.getChosenSquareProperty().addListener((ChangeListener<Object>) (o, oldVal, chosenSquare) -> {
			if((int)chosenSquare == -1)
				return;
			else{
				// Check which cellNumber is colored.
				int cellNumber = -1;

				Cell[] cells = PvPGameMode.super.board.getCellsArray();
				for(Cell cell : cells)
					if(!cell.isFree())
						cellNumber = cell.getId();

				// The next position is calculated with the chosen square and the chosen cellNumber.
			   	setNextPos(Logic.getPos(cellNumber, Position.getPos((int)chosenSquare)));
			}
		});
	}

	/**
	 * The reset property from the GuiController is connected to this listener.
	 * Whenever the player clicks on the winscreen this function calls the resetGame function.
	 */
	private void setListenerReset(){
		gui.getResetProperty().addListener((ChangeListener<Object>) (o, oldVal, newVal) -> {
			if((Boolean)newVal){
				resetGame();
			}
		});
	}

	/**
	 * This function loads the calls the function in the gui so the player can choose to which square
	 * the first move belongs.
	 * @param cellNumber
	 * @return null, the real value is set using event driven functions, so this is just a placeholder.
	 */
	@Override
	protected Position firstMoveIsDouble(int cellNumber) {
	    gui.chooseSquareScreen(Logic.getDoubleSquare(cellNumber));
		return null;
	}

	/**
	 * This function show the winscreen with the appropriate text.
	 */
	@Override
	protected void gameOver(){
		gui.winScreen(Logic.winner(board, onTurn));
	}

	/**
	 * This function starts a new game.
	 */
	private void resetGame(){
		setNextPos(null);
		onTurn = Logic.otherPlayer(Logic.winner(board, onTurn));
		lastCellNumber = -1;
		board = new Board();
		gui.setBoard(board);
		gui.resetGameVariables();
		gui.loadBoardScreen(null);
	}

	/**
	 * @param nextPos
	 */
	private void setNextPos(Position nextPos){
		super.nextPos = nextPos;
	}

}



