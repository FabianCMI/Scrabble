/**
 * Scrabble (APO Project - CMI L1) Tiles.java - Represents the different tiles
 * of the game
 * 
 * @author Fabian Devel, Valentin Perignon
 */

public class Tiles {

	// ---------- Attributs ----------

	/** Letter */
	private char letter;
	/** Value */
	private int value;

	// ---------- Constructors ----------

	/**
	 * Tiles constructor
	 * 
	 * @param letter The letter of the tile
	 * @param value  The value of the tile
	 */
	Tiles(char letter, int value) {
		this.letter = letter;
		this.value = value;
	}

	/**
	 * Tiles constructor
	 * 
	 * @param otherTile Another tile
	 */
	Tiles(Tiles otherTile) {
		this(otherTile.getLetter(), otherTile.getValue());
	}

	/**
	 * Tiles constructor
	 */
	Tiles() {
		this(Character.MIN_VALUE, 0);
	}

	// ---------- Getters ----------

	/**
	 * Get the letter
	 * 
	 * @return The letter of the tile
	 */
	public char getLetter() {
		return this.letter;
	}

	/**
	 * Get the value
	 * 
	 * @return The value of the tile
	 */
	public int getValue() {
		return this.value;
	}

	// ---------- Methods ----------

	/**
	 * Tile to string
	 */
	public String toString() {
		return String.valueOf(this.letter);
	}

	/**
	 * Transform the tile into a null tile
	 */
	public void setNull() {
		this.letter = Character.MIN_VALUE;
	}

}