package game.modes;

import game.gui.GuiController;
import game.logic.Logic;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class PvPGameMode extends XvX{

	public PvPGameMode(GuiController gui) {
		super(gui);
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

	//TODO: Transform this into a listener (event-driven-programming).
	@Override
	protected int firstMoveIsDouble(int cellNumber) {
		return gui.chooseSquareScreen(Logic.getSquares(cellNumber));
	}
}




















