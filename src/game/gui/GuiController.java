package game.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import game.Main;
import game.logic.Board;
import game.logic.Position;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GuiController implements Initializable{

	private Stage window = Main.getWindow();
	
	private Circle circles[] = new Circle[21];
	
	@FXML
	public BorderPane titleScreen;
	
	@FXML
	public Circle circle0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	/**
	 * Set the window to the title screen.
	 */
	public void titleScreen() {
		URL location = getClass().getResource("/TitleScreen.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(location);
		
		Parent root = null;
		try {
			root = fxmlLoader.load();
		} catch (IOException e) {
			System.out.println("Could not load the fxml file.");
			System.exit(1);
		}
		Scene scene = new Scene(root);
		
		window.setScene(scene);
		window.setTitle("Katalon");
		window.show();
	}
	
	/**
	 * @param e
	 * Processed when the start is clicked.
	 */
	public void loadBoardScreen(MouseEvent e) {
		boardScreen();
	}
	
	/**
	 * Set the window to the board scene.
	 */
	private void boardScreen() {
		URL location = getClass().getResource("/BoardScreen.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(location);
		
		Parent boardPane = null;
		try {
			boardPane = fxmlLoader.load();
		} catch (IOException e) {
			System.out.println("Could not load the fxml file.");
			System.exit(1);
		}
		Scene boardScene = new Scene(boardPane);
		
		window.setScene(boardScene);
	}

	
	public void setCellsToCircles(Board board) {
		initializeCirclesArray();
		
		//TODO: The initialization can only be done when the pane is loaded, otherwise all the values are just null.
		board.getCell(Position.TOPLEFT, Position.TOPLEFT).setCircle(circles[0]);
		
	}
	
	private void initializeCirclesArray() {
		circles[0] = circle0;
	}
	
}
