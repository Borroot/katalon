package game;

import java.io.IOException;
import java.net.URL;

import game.gui.GuiController;
import game.logic.Board;
import game.modes.PvPGameMode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * @author Borroot
 * This class handles all the logic in the game.
 */
public class GameController {
	
	private Board board = new Board();
	private GuiController gui;
	
	public GameController() {
		guiGame();
		new PvPGameMode(gui);
	}
	
	
	/**
	 * This method loads the FXML file and sets the content to a root.
	 * Then the general GuiController (gui) is set to the controller of the FXML loader.
	 * At last the screen is initialized (in the correct GuiController).
	 */
	private void guiGame() {
		URL location = GuiController.class.getResource("/Screen.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(location);
		
		Parent root = null;
		try {
			root = fxmlLoader.load();
		} catch (IOException e) {
			System.out.println("Could not load the fxml file: " + location);
			System.out.println(e.getCause());
			System.exit(1);
		}
		
		gui = fxmlLoader.getController();
		
		gui.initiateScreen(root);
		gui.setBoard(board);
	}
}
