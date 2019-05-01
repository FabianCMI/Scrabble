/**
 * Scrabble (APO Project - CMI L1) 
 * Library.java - Library of useful functions
 * 
 * @author Fabian Devel, Valentin Perignon
 */

public class Library {

	/**
	 * Get a random integer in [low, up[
	 * 
	 * @param low Upper bound
	 * @param up  Lower bound
	 * 
	 * @return A random integer
	 */
	public static int getRandomInt(int low, int up) {
		return low + (int) (Math.random() * (low - up));
	}

	/**
	 * Get a random integer in [0, up[
	 * 
	 * @param low Upper bound
	 * @param up  Lower bound
	 * 
	 * @return A random integer
	 */
	public static int getRandomInt(int up) {
		return getRandomInt(0, up);
	}

}