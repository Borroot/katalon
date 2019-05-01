package game.modes;

import game.gui.GuiController;
import game.logic.Cell;
import game.logic.Logic;
import game.logic.Position;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class PvPGameMode extends XvX{

	public PvPGameMode(GuiController gui) {
		super(gui);
		setListenerCircleClicked();
		setListenerChooseSquare();
	}
	
	/**
	 * The circleNumber property from the GuiController is connected to this listener.
	 * Whenever a circle is clicked the method to make the move is called.
	 */
	private void setListenerCircleClicked() {
		gui.getCircleNumberProperty().addListener(new ChangeListener<Object>(){
			@Override
			public void changed(ObservableValue<?> o, Object oldVal, Object cellNumber) {
		        if((int)cellNumber == -1)
		        	return; 
		        else
		        	move((int)cellNumber);
			}
		});
	}

	/**
	 * The chosenSquare property from the GuiController is connected to this listener.
	 */
	private void setListenerChooseSquare(){
		gui.getChosenSquareProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> o, Object oldVal, Object chosenSquare) {
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

	private void setNextPos(Position nextPos){
		super.nextPos = nextPos;
	}

}



