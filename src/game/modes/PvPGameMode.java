package game.modes;

import game.gui.GuiController;
import game.logic.Board;
import game.logic.Logic;
import game.logic.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class PvPGameMode {
	
	private GuiController gui;
	private Board board;
	private Player onTurn = Player.RED;
	
	private int prevprevMove = -1;
	private int prevMove = -1;

	public PvPGameMode(GuiController gui) {
		this.gui = gui;
		this.board = gui.getBoard();
		
		setListener();
	}
	
	/**
	 * The circleNumber property from the GuiController is connected to this listener.
	 * Whenever a circle is clicked the method to make the move is called.
	 */
	private void setListener() {
		gui.getCircleNumberProperty().addListener(new ChangeListener<Object>(){
			@Override
			public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {
		        if((int)newVal == -1)
		        	return; 
		        else
		        	move((int)newVal);
			}
		});
	}
	
	/**
	 * @param cellNumber
	 * Make the move if the move is valid and check if it's game over.
	 */
	private void move(int cellNumber) {
		//TODO: Check whether the first move is on a double one.
		//		If so ask the Player which square it belongs to and update the prevMove accordingly.
		
		if(Logic.validMove(board, cellNumber, prevMove, prevprevMove)) {
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




















