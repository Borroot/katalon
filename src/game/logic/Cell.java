package game.logic;

import javafx.scene.shape.Circle;

public class Cell {
	
	private final int id;
	
	private Player occupies;
	private Circle circle;
	
	public Cell(int id) {
		this.id = id;
		occupies = Player.NONE;
	}
	
	public void setOccupy(Player player) {
		occupies = player;
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
	
	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	
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
