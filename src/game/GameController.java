package game;

import game.gui.GuiController;
import game.logic.Board;
import game.logic.Position;

public class GameController {
	
	private GuiController gui = new GuiController();
	
	private Board board = new Board();
	
	public GameController() {
		guiGame();
	}
	
	private void guiGame() {
		gui.titleScreen();
		gui.setCellsToCircles(board);
	}
	
}
