/**
 * Scrabble (APO Project - CMI L1)
 * TilesBag.java - Represents the bag from where we randomly draw the tiles
 * 
 * @author Fabian Devel, Valentin Perignon
 */

 public class TilesBag {
	
	// ---------- Attributs ----------

	/** Array of all letters present in the bag */
	private Tiles[] letters = new Tiles[100];
	
	// ---------- Constructors ----------

	TilesBag() {
		char letter = 'A';
		int value;
		for(int i=0; i<letters.length; i++) {
			// Letters and values
			if(i == 46 || (i>=96 && i<= 99)) { // K, W, X, Y, Z
				value = 1;
			} else if((i>=13 && i<=15) || (i==33 || i==34) || (i>=52 && i<=54)) { // D, G, M
				value = 2;
			} else if((i>=9 && i<= 12) || (i==67 || i==68)) { // B, C, P
				value = 3;
			} else if((i==31 || i==32) || (i==35 || i==36) || (i==94 || i==95)) { // F, H, V
				value = 4;
			} else if(i==45 || i==69) { // J, Q
				value = 8;
			} else { // A, E, I, N, O, R, S, T, U, L
				value = 10;
			}

			// Put letter in the array
			letters[i] = new Tiles(letter, value);
		}
	}
	
	// ---------- Methods ----------
	
}