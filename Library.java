/**
 * Scrabble (APO Project - CMI L1) Library.java - Library of useful functions
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
		return low + (int) (Math.random() * (up - low));
	}

	/**
	 * Get a random integer in [0, up[
	 * 
	 * @param up Lower bound
	 * 
	 * @return A random integer
	 */
	public static int getRandomInt(int up) {
		return getRandomInt(0, up);
	}

	/**
	 * Put a String into an array of int
	 * 
	 * @param tabInt   The array of integers
	 * @param str      The String
	 * @param splitter The splitter (e.g. ",", "", ...)
	 */
	public static void StringtoArray(int[] tabInt, String str, String splitter) {
		// Variable
		String[] tabStr = new String[tabInt.length];

		// Split the array
		tabStr = str.split(splitter);

		// String to int
		for (int i = 0; i < tabInt.length; i++)
			tabInt[i] = Integer.parseInt(tabStr[i]);
	}

}