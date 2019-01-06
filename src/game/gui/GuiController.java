package game.gui;

import game.Main;
import game.logic.Board;
import game.logic.Position;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class GuiController {

	private Stage window = Main.getWindow();
	private Board board;
	
	private Circle circles[] = new Circle[21];
	
	private IntegerProperty circleNumber = new SimpleIntegerProperty(-1);
	
	private Rectangle doubleSmallRectangle;
	private Rectangle bigRectangle;
	
	@FXML
	public BorderPane titleScreen;
	@FXML
	public StackPane boardScreen;
	@FXML
	public StackPane chooseSquareScreen;
	
	@FXML
	public Rectangle topleftSquare;
	@FXML
	public Rectangle toprightSquare;
	@FXML
	public Rectangle centerSquare;
	@FXML
	public Rectangle bottomleftSquare;
	@FXML
	public Rectangle bottomrightSquare;
	
	@FXML
	public Rectangle topleftSmallSquare;
	@FXML
	public Rectangle toprightSmallSquare;
	@FXML
	public Rectangle bottomleftSmallSquare;
	@FXML
	public Rectangle bottomrightSmallSquare;
	
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
		chooseSquareScreen.setVisible(false);
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
		chooseSquareScreen.setVisible(false);
	}
	
	/**
	 * @param e
	 * Whenever a circle is clicked this method is called.
	 * The circleNumberProperty is updated to the according circleNumber.
	 * This value can be used by listeners. The listeners should check whether the 
	 * value is equal to -1, if this is the case then the value should be discarded.
	 */
	public void circleClicked(MouseEvent e) {
		for(int i = 0; i < board.getCellsArray().length; i++) {
			if(e.getSource() == board.getCellsArray()[i].getCircle()) {
				circleNumber.set(i);
			}
		}
		circleNumber.set(-1);
	}
	
	/**
	 * Load the screen where a square can be chosen.
	 * The ones which you can choose from are in another colour.
	 */
	public int chooseSquareScreen(Position[] pos) {
		titleScreen.setVisible(false);
		boardScreen.setVisible(false);
		chooseSquareScreen.setVisible(true);
		
		centerSquare.setFill(Color.ORANGE); // pos[1] is always the centre square
		
		bigRectangle = getRectangle(pos[0]);
		bigRectangle.setFill(Color.ORANGE);
		
		Rectangle[] smallRectangles = smallRectangleArray();
		doubleSmallRectangle = getSmallRectangle(pos[0]);
		
		for(int i = 0; i < smallRectangles.length; i++) {
			if(smallRectangles[i] != doubleSmallRectangle) {
				smallRectangles[i].setFill(Color.ORANGE);
			}		
		}
		
		//TODO: Finish user input square.
		
		return 0;
	}
	
	public void rectangleClicked(MouseEvent e) {
		//TODO: Extract the right number.
		System.out.println("Clicked: " + e.getSource());
	}
	
	/**
	 * @param pos
	 * @return The correct rectangle given a position.
	 */
	private Rectangle getRectangle(Position pos) {
		switch(pos) {
			case TOPLEFT: 		return topleftSquare;
			case TOPRIGHT: 		return toprightSquare;
			case BOTTOMLEFT: 	return bottomleftSquare;
			case BOTTOMRIGHT: 	return bottomrightSquare;
			default: return null;
		}
	}
	
	/**
	 * @param pos
	 * @return The correct small rectangle given a position.
	 */
	private Rectangle getSmallRectangle(Position pos) {
		switch(pos) {
			case TOPLEFT: 		return topleftSmallSquare;
			case TOPRIGHT: 		return toprightSmallSquare;
			case BOTTOMLEFT: 	return bottomleftSmallSquare;
			case BOTTOMRIGHT: 	return bottomrightSmallSquare;
			default: return null;
		}
	}
	
	/**
	 * @return An array with all four small rectangles.
	 */
	private Rectangle[] smallRectangleArray() {
		Rectangle[] rectangles = {topleftSmallSquare, toprightSmallSquare, bottomleftSmallSquare, bottomrightSmallSquare};
		return rectangles;
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
	
	public Board getBoard() {
		return board;
	}
	
	public IntegerProperty getCircleNumberProperty() {
		return circleNumber;
	}
	
	public int getCircleNumber() {
		return circleNumber.get();
	}
	
}
