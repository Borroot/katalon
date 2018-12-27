package game;

import java.io.IOException;
import java.net.URL;

import game.gui.GuiController;
import game.logic.Board;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class GameController {
	
	private Board board = new Board();
	private GuiController gui;
	
	public GameController() {
		guiGame();
	}
	
	private void guiGame() {
		URL location = GuiController.class.getResource("/Screen.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(location);
		
		Parent root = null;
		try {
			root = fxmlLoader.load();
		} catch (IOException e) {
			System.out.println("Could not load the fxml file.");
			System.out.println(e.getCause());
			System.exit(1);
		}
		
		gui = fxmlLoader.getController();
		
		gui.initiateScreen(fxmlLoader, root);
		gui.setBoard(board);
	}
}
