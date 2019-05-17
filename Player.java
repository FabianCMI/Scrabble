/**
 * Scrabble (APO Project - CMI L1) Player.java - Represents a player (with his
 * name, his score and his tiles's rack)
 * 
 * @author Fabian Devel, Valentin Perignon
 */

public class Player {

	// ---------- Attributs ----------

	/** Player's name */
	private String name;
	/** Player's score */
	private int score;
	/** Player's rack (with 7 tiles) */
	private Rack rack;

	// ---------- Constructors ----------

	/**
	 * Player constructor
	 * 
	 * @param name the name of the player
	 */
	Player(String name) {
		this.name = name;
		this.score = 0;
		this.rack = new Rack();
	}

	/**
	 * Player constructor
	 */
	Player() {
		this("Sans nom");
	}

	// ---------- Methods ----------

	/**
	 * Get the name of the player
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get the player's score
	 * 
	 * @return the score
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Get the player's rack
	 * 
	 * @return the rack
	 */
	public Rack getRack() {
		return this.rack;
	}

	/**
	 * Check if there is at least one letter on the rack
	 * 
	 * @return True if there is no remaining letters
	 */
	public boolean isRackNull() {
		// Variable
		boolean areAllNull = true;

		// Treatement
		for (int i = 0; i < this.rack.getTiles().length; i++) {
			if (this.rack.getTiles()[i] != null) {
				areAllNull = false;
			}
		}

		return areAllNull;
	}

	/**
	 * Increase the player's score
	 * 
	 * @param value The number by which the score will be increase
	 */
	public void increaseScore(int value) {
		this.score += value;
	}

	/**
	 * Player to String
	 */
	public String toString() {
		String aboutPlayer;

		// Name of the player
		aboutPlayer = this.name;

		// Score
		for (int i = 0; i < 20 - this.name.length(); i++) {
			aboutPlayer += " ";
		}
		aboutPlayer += "Score : " + this.score;

		// Rack
		aboutPlayer += "\n" + this.rack;

		return aboutPlayer;
	}

	/**
	 * Display the player on the screen
	 * 
	 * @param fg The window
	 */
	public void drawPlayer(FenetreGraphique fg) {
		// Name and score
		Game.drawText(fg, "", 50);
		Game.drawText(fg, "Joueur : " + this.name, 20);
		Game.drawText(fg, "Score : " + this.score + "pts", 20);

		// Rack
		this.rack.drawRack(fg);
		fg.setColor(255, 255, 255);
	}
}