package game.modes;

import game.logic.Player;
import game.logic.Position;

public class CvCGameMode extends XvX {

	public CvCGameMode(){
		super();
		board.getCell(0).setOccupy(Player.RED);
		System.out.println(board);
	}

	@Override
	protected Position firstMoveIsDouble(int cellNumber) {
		return null;
	}

	@Override
	protected void gameOver() {

	}
}
