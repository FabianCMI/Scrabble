/**
 * Scrabble (APO Project - CMI L1) Rack.java - Represents the tiles's rack of a
 * player
 * 
 * @author Fabian Devel, Valentin Perignon
 */

public class Rack {

	// ---------- Attributs ----------

	/** Array of 7 tiles */
	private Tiles[] rack = new Tiles[7];
	/** Bag of tiles */
	private TilesBag bag = new TilesBag();

	// ---------- Constructors ----------

	/**
	 * Rack constructor
	 */
	Rack() {
		for (int i = 0; i < this.rack.length; i++) {
			rack[i] = new Tiles();
			rack[i] = bag.drawLetter();
		}
	}

	// ---------- Methods ----------

	/**
	 * Get the rack
	 * 
	 * @return the rack of 7 tiles
	 */
	public Tiles[] getRack() {
		return this.rack;
	}

	/**
	 * Set the rack with new tiles from the tiles' bag
	 * 
	 * @param nbLetters the number of tiles you have to draw
	 */
	public void refreshRack(int nbLetters) {
		/*
		 * TODO: décaler les lettres restantes apres la pose d'un mot pour laisser la
		 * place au début du tableau pour les nouvelles lettres
		 */

		for (int i = 0; i < nbLetters; i++) {
			int n = Library.getRandomInt(bag.getTiles().length);
			this.rack[i] = this.bag.getTiles()[n];
		}
	}

	/**
	 * Rack to String
	 */
	public String toString() {
		String str = "";
		for (int i = 0; i < 7; i++) {
			str += this.rack[i] + "|";
		}
		return (str);
	}
}