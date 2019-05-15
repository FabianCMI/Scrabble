
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
		FenetreGraphique mainFg = new FenetreGraphique(0, 0, MAIN_FRAME_WIDTH, FRAME_HEIGHT, MAIN_INSIDE_WIDTH, INSIDE_HEIGHT, "Jeu de Scrabble");
		FenetreGraphique sideFg = new FenetreGraphique(MAIN_FRAME_WIDTH, 0, SIDE_FRAME_WIDTH, FRAME_HEIGHT, SIDE_INSIDE_WIDTH, INSIDE_HEIGHT, "Jeu de scrabble");

		// Graphic frame
		mainFg.setClearColor(50, 50, 50);
		mainFg.clear();
		sideFg.setClearColor(50, 50, 50);
		sideFg.clear();

		// Initialisation of the game
		Game game = new Game(sideFg);

		// Gameboard's drawing
		drawGameBoard(mainFg, game);
		
		// Main part of the game
		//game.playGame();
		//FenetreGraphique.exit();
	}

	public static void drawGameBoard(FenetreGraphique mainFg, Game game){
		// Variables
		int x, y;
		int lineNumber = 1;
		int colonneNumber = 1;
		char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O'};

		// Drawing
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				x = 178 + i*50;
				y = 70 + j*50;
				//letters and numbers
				if(x%178 == 0 && lineNumber <= 15) { // letters
					mainFg.setColor(220, 220, 220);
					mainFg.drawText(x - 25, y + 33, 3, letters[lineNumber-1]);
					lineNumber++;
				}
				if(y == 70 && colonneNumber <= 15) { // numbers
					mainFg.setColor(220, 220, 220);
					mainFg.drawText(x + 17, y - 10, 3, colonneNumber);
					colonneNumber++;
				}

				// lines
				mainFg.setColor(220, 220, 220);
				mainFg.drawLine(x, 70, x, 820);
				mainFg.drawLine(178, y, 928, y);
				mainFg.setColor(60, 100, 60);
				if(i < 15 && j < 15){
					if(game.getGameBoard().getGrid()[i][j].getScoreMult() == 3){
						if(game.getGameBoard().getGrid()[i][j].getTypeScoreMult()){
							mainFg.setColor(175, 0, 0);
						} else {
							mainFg.setColor(0, 0, 175);
						}
					} else if(game.getGameBoard().getGrid()[i][j].getScoreMult() == 2){
						if(game.getGameBoard().getGrid()[i][j].getTypeScoreMult()){
							mainFg.setColor(255, 100, 100);
						} else {
							mainFg.setColor(100, 100, 255);
						}
					}
					mainFg.fillRect(x, y, 50, 50);
				}
			}
		}
		mainFg.flush();
	}

	public void drawRackPlayer(FenetreGraphique mainFg, Game game, Player pl) {
		
	}

}