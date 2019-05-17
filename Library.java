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

	/**
	 * Put a String into an array of int
	 * 
	 * @param tabInt   The array of integers
	 * @param str      The String
	 * @param splitter The splitter (e.g. ",", "", ...)
	 */
	public static void charstoArray(int[] tabInt, String str, String splitter) {
		// Variable
		String[] tabStr = new String[tabInt.length];
		char[] tabChar = new char[tabInt.length];

		// Split the array
		tabStr = str.split(splitter);

		// Array of char
		for (int i = 0; i < tabChar.length; i++)
			tabChar[i] = tabStr[i].charAt(0);

		// String to int
		for (int i = 0; i < tabInt.length; i++)
			tabInt[i] = charToInt(tabChar[i]);
	}

	/**
	 * Transform a char into an integer
	 * 
	 * @param letter Letter to transform
	 */
	private static int charToInt(char letter) {
		// Variables
		int number;
		letter = Character.toUpperCase(letter);

		// Treatment
		switch (letter) {
		case 'A':
			number = 1;
			break;
		case 'B':
			number = 2;
			break;
		case 'C':
			number = 3;
			break;
		case 'D':
			number = 4;
			break;
		case 'E':
			number = 5;
			break;
		case 'F':
			number = 6;
			break;
		case 'G':
			number = 7;
			break;
		default:
			number = 0;
			break;
		}

		return number;
	}

	/**
	 * Check if the values of an array are in [min, max]
	 */
	public static boolean checkValuesOfArray(int[] tab, int min, int max) {
		// Variables
		boolean isOk = true;
		int i = 0;

		// Treatment
		while (i < tab.length && isOk) {
			if (tab[i] < min || tab[i] > max)
				isOk = false;
			else
				i++;
		}

		return isOk;
	}

}