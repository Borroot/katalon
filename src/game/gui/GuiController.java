package game.gui;

import game.Main;
import game.logic.Board;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class GuiController {

	private Stage window = Main.getWindow();
	private Board board;
	
	private Circle circles[] = new Circle[21];
	
	@FXML
	public BorderPane titleScreen;
	@FXML
	public StackPane boardScreen;
	
	/**
	 * This cannot be done in an array.
	 */
	@FXML
	public Circle circle0;
	@FXML
	public Circle circle1;
	@FXML
	public Circle circle2;
	@FXML
	public Circle circle3;
	@FXML
	public Circle circle4;
	@FXML
	public Circle circle5;
	@FXML
	public Circle circle6;
	@FXML
	public Circle circle7;
	@FXML
	public Circle circle8;
	@FXML
	public Circle circle9;
	@FXML
	public Circle circle10;
	@FXML
	public Circle circle11;
	@FXML
	public Circle circle12;
	@FXML
	public Circle circle13;
	@FXML
	public Circle circle14;
	@FXML
	public Circle circle15;
	@FXML
	public Circle circle16;
	@FXML
	public Circle circle17;
	@FXML
	public Circle circle18;
	@FXML
	public Circle circle19;
	@FXML
	public Circle circle20;
	
	public GuiController() {	
	}
	
	/**
	 * @param root 
	 * Open the window and set the root (opening screen) as scene.
	 */
	public void initiateScreen(Parent root) {
		Scene scene = new Scene(root);
		
		window.setScene(scene);
		window.setTitle("Katalon");
		window.show();
	}
	
	/**
	 * Set the window to the title screen.
	 */
	public void titleScreen() {
		boardScreen.setVisible(false);
		titleScreen.setVisible(true);
	}
	
	/**
	 * @param e
	 * Processed when the start is clicked.
	 */
	public void loadBoardScreen(MouseEvent e) {
		boardScreen();
		setCellsToCircles();
	}
	
	/**
	 * Set the window to the board scene.
	 */
	private void boardScreen() {
		titleScreen.setVisible(false);
		boardScreen.setVisible(true);
	}
	
	/**
	 * Set the circles from the board to the visual circles.
	 */
	private void setCellsToCircles() {
		initializeCirclesArray();
		
		for(int i = 0; i < this.board.getCellsArray().length; i++) {
			board.getCellsArray()[i].setCircle(circles[i]);
		}
	}
	
	/**
	 * Set all the circles to an array for easy future reference.
	 */
	private void initializeCirclesArray() {
		circles[0] = circle0;
		circles[1] = circle1;
		circles[2] = circle2;
		circles[3] = circle3;
		circles[4] = circle4;
		circles[5] = circle5;
		circles[6] = circle6;
		circles[7] = circle7;
		circles[8] = circle8;
		circles[9] = circle9;
		circles[10] = circle10;
		circles[11] = circle11;
		circles[12] = circle12;
		circles[13] = circle13;
		circles[14] = circle14;
		circles[15] = circle15;
		circles[16] = circle16;
		circles[17] = circle17;
		circles[18] = circle18;
		circles[19] = circle19;
		circles[20] = circle20;
	}
	
	/**
	 * @param board
	 * Set the board of the GuiController.
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	
}
