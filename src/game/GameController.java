package game;

import game.gui.GuiController;
import game.logic.Board;

public class GameController {
	
	private Board board = new Board();
	private GuiController gui = new GuiController();
	
	public GameController() {
		guiGame();
	}
	
	private void guiGame() {
		gui.setBoard(board);
		gui.titleScreen();
	}
}
