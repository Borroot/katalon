package game.logic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This class represents a single cell/circle on the playing board.
 * @author Bram Pulles
 */
public class Cell {
	
	private final int id;
	
	private Player occupies;
	private Circle circle;
	
	public Cell(int id) {
		this.id = id;
		occupies = Player.NONE;
	}
	
	/**
	 * @param player
	 * Change occupies to the player and change the color accordingly.
	 */
	public void setOccupy(Player player) {
		occupies = player;
		setColor();
	}
	
	/**
	 * When the Cell gets occupied the color is automatically changed.
	 */
	public void setColor() {
		if(this.getCircle() != null) {
			if (occupies == Player.YELLOW)
				this.getCircle().setFill(Color.rgb(252, 255, 76));
			else if (occupies == Player.RED)
				this.getCircle().setFill(Color.INDIANRED);
			else
				this.getCircle().setFill(Color.DODGERBLUE);
		}
	}
	
	/**
	 * @return The player which occupies this cell.
	 */
	public Player getOccupy() {
		return occupies;
	}
	
	/**
	 * @return True if the cell is not yet occupied by another player.
	 */
	public boolean isFree() {
		return occupies == Player.NONE;		
	}

	/**
	 * Set the graphical representation of this Cell.
	 * @param circle
	 */
	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	/**
	 * @return The graphical representation of this Cell.
	 */
	public Circle getCircle() {
		return circle;
	}
	
	/**
	 * @return The id of the cell.
	 * This function is solely used for testing purposes.
	 */
	public int getId() {
		return id;
	}
}
