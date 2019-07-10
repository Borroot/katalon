package game.logic;

/**
 * This class represents a player.
 * @author Bram Pulles
 */
public enum Player {
	NONE("."), YELLOW("Y"), RED("R");

	private String string;

	Player(String string){
		this.string = string;
	}

	@Override
	public String toString(){
		return string;
	}
}
