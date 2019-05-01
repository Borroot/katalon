package game.gui;

import game.Main;
import game.logic.Board;
import game.logic.Cell;
import game.logic.Player;
import game.logic.Position;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class GuiController {

	private Stage window = Main.getWindow();
	private Board board;
	
	private Circle circles[] = new Circle[21];

	private BooleanProperty reset = new SimpleBooleanProperty(false);
	
	private IntegerProperty circleNumber = new SimpleIntegerProperty(-1);
	private IntegerProperty chosenSquare = new SimpleIntegerProperty(-1);
	
	private Rectangle doubleSmallRectangle;
	private Rectangle bigRectangle;

	@FXML
	public Label winnerLabel;

	@FXML
	public AnchorPane root;
	@FXML
	public BorderPane titleScreen;
	@FXML
	public StackPane boardScreen;
	@FXML
	public BorderPane winScreen;
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
	
	// This cannot be done in an array.
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
	 * Open the window and set the root (opening screen) as scene.
	 * @param root
	 */
	public void initiateScreen(Parent root) {
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/BoardScreen.css");

		window.setScene(scene);
		window.setTitle("Katalon");
		window.show();

		titleScreen();
	}

	public void setScreen(Node node){
		for(Node child : root.getChildren()){
			if(child.equals(node))
				child.setVisible(true);
			else
				child.setVisible(false);
		}
	}
	/**
	 * Set the window to the title screen.
	 */
	public void titleScreen() {
		setScreen(titleScreen);
	}

	/**
	 * Set the window to the win screen.
	 * @param winner
	 */
	public void winScreen(Player winner){
	    if(winner == Player.RED)
	    	winnerLabel.setText("The Winner is: RED");
	    else
	    	winnerLabel.setText("The Winner is: YELLOW");

		boardScreen.setOpacity(0.3);
		winScreen.setVisible(true);
	}

	/**
	 * Set the reset property to true.
	 */
	public void resetGame(){
		reset.set(true);
	}

	/**
	 * Reset the game variables so a new game can be started in the same gui controller.
	 */
	public void resetGameVariables(){
		boardScreen.setOpacity(1);
		reset.set(false);
		circleNumber.set(-1);
		chosenSquare.set(-1);

        for(Circle circle : circles) {
        	circle.getStyleClass().clear();
        	circle.getStyleClass().add("circle");
		}

        chooseSquareScreen.getStyleClass().clear();
        chooseSquareScreen.getStyleClass().add("rectangle");
	}
	
	/**
	 * Processed when the start is clicked.
	 * @param e
	 */
	public void loadBoardScreen(MouseEvent e) {
		boardScreen();
		setCellsToCircles();
	}
	
	/**
	 * Set the window to the board scene.
	 */
	private void boardScreen() {
	    setScreen(boardScreen);
	}
	
	/**
	 * Whenever a circle is clicked this method is called.
	 * The circleNumberProperty is updated to the according circleNumber.
	 * This value can be used by listeners. The listeners should check whether the 
	 * value is equal to -1, if this is the case then the value should be discarded.
	 * @param e
	 */
	public void circleClicked(MouseEvent e) {
		for(int i = 0; i < board.getCellsArray().length; i++) {
			if(e.getSource() == board.getCell(i).getCircle()) {
				circleNumber.set(i);
			}
		}
		circleNumber.set(-1);
	}
	
	/**
	 * Load the screen where a square can be chosen.
	 * The ones which you can choose from are in another colour.
	 */
	public void chooseSquareScreen(Position pos) {
        setScreen(chooseSquareScreen);

		centerSquare.setFill(Color.ORANGE);
		
		bigRectangle = getRectangle(pos);
		bigRectangle.setFill(Color.ORANGERED);
		
		Rectangle[] smallRectangles = smallRectangleArray();
		doubleSmallRectangle = getSmallRectangle(pos);
		
		for(int i = 0; i < smallRectangles.length; i++) {
			if(smallRectangles[i] != doubleSmallRectangle) {
				smallRectangles[i].setFill(Color.ORANGE);
			}		
		}
	}

	/**
	 * Set the chosenSquare property to the correct position code.
	 * @param e
	 */
	public void rectangleClicked(MouseEvent e) {
		if(e.getSource() instanceof Rectangle){
			Rectangle rec = (Rectangle)e.getSource();
			if(rec.getFill() == Color.ORANGE){
			    chosenSquare.set(Position.CENTER.getCode());
                setScreen(boardScreen);
			}
			else if(rec.getFill() == Color.ORANGERED){
			    chosenSquare.set(getPosition(rec).getCode());
				setScreen(boardScreen);
			}
		}
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
	 * @param rec
	 * @return The correct position given a rectangle.
	 */
	private Position getPosition(Rectangle rec){
		if (topleftSquare.equals(rec)) {
			return Position.TOPLEFT;
		} else if (toprightSquare.equals(rec)) {
			return Position.TOPRIGHT;
		} else if (bottomleftSquare.equals(rec)) {
			return Position.BOTTOMLEFT;
		} else if (bottomrightSquare.equals(rec)) {
			return Position.BOTTOMRIGHT;
		}
		return null;
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
			board.getCell(i).setCircle(circles[i]);
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
	 * Set the board of the GuiController.
	 * @param board
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public Board getBoard() {
		return board;
	}

	public BooleanProperty getResetProperty(){
		return reset;
	}

	public IntegerProperty getCircleNumberProperty() {
		return circleNumber;
	}

	public IntegerProperty getChosenSquareProperty() {
		return chosenSquare;
	}
	
}
