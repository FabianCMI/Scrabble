/**
 * Scrabble (APO Project - CMI L1) Scrabble.java - Main file
 * 
 * @author Fabian Devel, Valentin Perignon
 */

public class Scrabble {

	static int textHeight = 0;

	public static void main(String[] args) {
		// Variables
		final int MAIN_FRAME_WIDTH = 1100;
		final int SIDE_FRAME_WIDTH = 1920 - MAIN_FRAME_WIDTH;
		final int FRAME_HEIGHT = 1040;
		final int MAIN_INSIDE_WIDTH = MAIN_FRAME_WIDTH - 47;
		final int SIDE_INSIDE_WIDTH = SIDE_FRAME_WIDTH - 47;
		final int INSIDE_HEIGHT = 940;
		FenetreGraphique mainFg = new FenetreGraphique(0, 0, MAIN_FRAME_WIDTH, FRAME_HEIGHT, MAIN_INSIDE_WIDTH,
				INSIDE_HEIGHT, "Jeu de Scrabble");
		FenetreGraphique sideFg = new FenetreGraphique(MAIN_FRAME_WIDTH, 0, SIDE_FRAME_WIDTH, FRAME_HEIGHT,
				SIDE_INSIDE_WIDTH, INSIDE_HEIGHT, "Jeu de scrabble");

		// Graphic frame
		mainFg.setClearColor(50, 50, 50);
		mainFg.clear();
		sideFg.setClearColor(50, 50, 50);
		sideFg.clear();

		// Initialisation of the game
		Game game = new Game(sideFg);

		// Gameboard's drawing
		game.drawGameBoard(mainFg);

		// Main part of the game
		game.playGame(sideFg, mainFg);
		FenetreGraphique.exit();
	}

}