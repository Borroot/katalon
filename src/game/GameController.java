package game;

import java.io.IOException;
import java.net.URL;

import game.gui.GuiController;
import game.logic.Board;
import game.modes.CvCGameMode;
import game.modes.PvPGameMode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * This class starts the right gamemode.
 * @author Bram Pulles
 */
public class GameController {
	
	public GameController() {
//		new PvPGameMode(guiGame());
		new CvCGameMode();
	}
	
	
	/**
	 * This method loads the FXML file and sets the content to a root.
	 * Then the general GuiController (gui) is set to the controller of the FXML loader.
	 * At last the screen is initialized (in the correct GuiController).
	 */
	private GuiController guiGame() {
		URL location = GuiController.class.getResource("/Screen.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(location);
		
		Parent root = null;
		try {
		    System.out.println(location);
			root = fxmlLoader.load();
		} catch (IOException e) {
			System.out.println("Could not load the fxml file: " + location);
			e.printStackTrace();
			System.exit(1);
		}
		
		GuiController gui = fxmlLoader.getController();
		gui.initiateScreen(root);

		return gui;
	}
}
