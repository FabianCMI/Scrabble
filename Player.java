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

	// ---------- Constructors ----------
	/**
	 * Player constructor
	 * 
	 * @param name the name of the player
	 */
	Player(String name){
		this.name =  name;
		this.score = 0;
		this.rack = new Rack();
	}
	// ---------- Methods ----------
	/**
	 * Get the name of the player
	 * 
	 * @return the name
	 */
	public String getName(){
		return(this.name);
	}
	
	/**
	 * Get the player's score
	 * 
	 * @return the score
	 */
	public int getScore(){
		return(this.score);
	}
	
	/**
	 * Get the player's rack
	 * 
	 * @return the rack
	 */
	public Rack getRack(){
		return(this.rack);
	}
	
	/**
	 * Increase the player's score
	 * 
	 * @param addScore The number by which the score will be increase
	 */
	public void setScore(int addScore){
		this.score += addScore;
	}
	
	/**
	 * Draw new tiles from the bag 
	 * 
	 * @param nbLetters The number of tiles you want to draw
	 */
	public void setRack(int nbLetters){
		rack.setRack(nbLetters);
	}
	
	/**
	 * Player to String
	 */
	public String toString(){
		return("Joueur : " + this.name + "\n|| " + this.rack.toString() + "|");
	}
}