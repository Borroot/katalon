package game.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import game.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GuiController implements Initializable{

	@FXML
	public BorderPane beginMenu;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	/**
	 * @param e
	 * This function loads the Board Layout in the window.
	 */
	public void startGame(MouseEvent e) {
		URL location = getClass().getResource("/BoardLayout.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(location);
		
		Parent boardPane = null;
		try {
			boardPane = fxmlLoader.load();
		} catch (IOException e2) {
			System.out.println("Could not load the fxml file.");
			System.exit(1);
		}
		Scene scene = new Scene(boardPane);
		
		Stage window = Main.getStage();
		window.setScene(scene);
	}
	
}
