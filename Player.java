/**
 * Scrabble (APO Project - CMI L1) 
 * Player.java - Represents a player (with his name, his score and his tiles's rack)
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
	
	//builders
	
	// Methods
	toString(){
        Ecran.afficherln(name);
    }
	
}