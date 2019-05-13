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
	public Tiles[] getTiles() {
		return this.rack;
	}

	public int getNullTiles() {
		int nbNullTiles = 0;

		for (int i = 0; i < this.rack.length; i++) {
			if (this.rack[0] == null)
				nbNullTiles++;
		}

		return nbNullTiles;
	}

	/**
	 * Change some tiles in a rack
	 * 
	 * @param nbLetters the number of tiles you have to draw
	 */
	public void refreshRack(int[] indexLetter) {
		if (indexLetter.length <= 7 && bag.getLettersLength() > 0) {
			for (int i = 0; i < indexLetter.length; i++) {
				if(indexLetter[i] >= 0) {
					this.rack[indexLetter[i]] = this.bag.drawLetter();
				}
			}
		} else {
			for (int i = 0; i < indexLetter.length; i++) {
				if(indexLetter[i] >= 0) {
					this.rack[indexLetter[i]] = null;
				}
				
			}
		}
	}

	/**
	 * Rack to String
	 */
	public String toString() {
		String str = "-----------------------------\n| ";
		for (int i = 0; i < 7; i++) {
			str += this.rack[i] + " | ";
		}
		str += "\n-----------------------------\n  1   2   3   4   5   6   7";
		return (str);
	}
}