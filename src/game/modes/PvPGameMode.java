package game.modes;

import game.gui.GuiController;
import game.logic.Logic;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class PvPGameMode extends XvX{

	public PvPGameMode(GuiController gui) {
		super(gui);
		setListener();
		setListenerChooseSquare();
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
	 * The squareNumber property from the GuiController is connected to this listener.
	 * Whenever a square is chosen the method to set the prevMove is called with the right value.
	 */
	private void setListenerChooseSquare(){
		gui.getSquareNumberProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {
				if((int)newVal == -1)
					return;
				else
				    setPrevMove((int)newVal);
			}
		});
	}

	/**
	 * This function loads the calls the function in the gui so the player can choose to which square
	 * the first move belongs.
	 * @param cellNumber
	 * @return -1, the real value is set using event driven functions.
	 */
	@Override
	protected int firstMoveIsDouble(int cellNumber) {
	    gui.chooseSquareScreen(Logic.getSquares(cellNumber));
		return -1;
	}

	public void setPrevMove(int prevMove){
		super.prevMove = prevMove;
	}
}



