/**
 * Scrabble (APO Project - CMI L1) TilesBag.java - Represents the bag from where
 * we randomly draw the tiles
 * 
 * @author Fabian Devel, Valentin Perignon
 */

public class TilesBag {

	// ---------- Attributs ----------

	/** Array of all letters present in the bag */
	private Tiles[] letters = new Tiles[100];
	/** Number of remaining letters in the bag */
	private int remainingLetters = 100;

	// ---------- Constructors ----------

	TilesBag() {
		// Variables
		char letter;
		int value;

		// Letters and values
		for (int i = 0; i < this.letters.length; i++) {
			if (i >= 95) { // K, W, X, Y, Z
				value = 10;
				if (i == 99)
					letter = 'Z';
				else if (i == 98)
					letter = 'Y';
				else if (i == 97)
					letter = 'X';
				else if (i == 96)
					letter = 'W';
				else
					letter = 'K';
			} else if (i >= 93) { // J, Q
				value = 8;
				if (i == 94)
					letter = 'Q';
				else
					letter = 'J';
			} else if (i >= 87) { // F, H, V
				value = 4;
				if (i >= 91)
					letter = 'V';
				else if (i >= 89)
					letter = 'H';
				else
					letter = 'F';
			} else if (i >= 81) { // B, C, P
				value = 3;
				if (i >= 85)
					letter = 'P';
				else if (i >= 83)
					letter = 'C';
				else
					letter = 'B';
			} else if (i >= 73) { // D, G, M
				value = 2;
				if (i >= 78)
					letter = 'M';
				else if (i >= 76)
					letter = 'G';
				else
					letter = 'D';
			} else { // A, E, I, N, O, R, S, T, U, L
				value = 1;
				if (i >= 68)
					letter = 'L';
				else if (i >= 62)
					letter = 'U';
				else if (i >= 56)
					letter = 'T';
				else if (i >= 50)
					letter = 'S';
				else if (i >= 44)
					letter = 'R';
				else if (i >= 38)
					letter = 'O';
				else if (i >= 32)
					letter = 'N';
				else if (i >= 24)
					letter = 'I';
				else if (i >= 15)
					letter = 'A';
				else
					letter = 'E';
			}

			// Put letter in the array
			this.letters[i] = new Tiles(letter, value);
		}
	}

	// ---------- Getters ----------

	/**
	 * Get the array of tiles of the bag
	 * 
	 * @return The array with all the tiles
	 */
	public Tiles[] getTiles() {
		return this.letters;
	}

	/**
	 * Get the number of remaining letters
	 * 
	 * @return The value of remaining letters
	 */
	public int getRemainingLetters() {
		return this.remainingLetters;
	}

	// ---------- Methods ----------

	public Tiles drawLetter() {
		// Variables
		int value;
		Tiles letter;

		// Get random letter
		do {
			value = Library.getRandomInt(this.letters.length);
		} while (this.letters[value].getLetter() == Character.MIN_VALUE);

		// Pick letter
		letter = new Tiles(this.letters[value]);
		this.remainingLetters -= 1;
		this.letters[value].setNull();

		return letter;
	}

}