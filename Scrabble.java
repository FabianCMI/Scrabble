
/**
 * Scrabble (APO Project - CMI L1) Scrabble.java - Main file
 * 
 * @author Fabian Devel, Valentin Perignon
 */

public class Scrabble {

	public static void main(String[] args) {
		// Variables
		final int FRAME_WIDTH = 1700;
		final int FRAME_HEIGHT = 1040;
		final int INSIDE_WIDTH = 1657;
		final int INSIDE_HEIGHT = 940;
		FenetreGraphique mainFg = new FenetreGraphique(110, 0, FRAME_WIDTH, FRAME_HEIGHT, INSIDE_WIDTH, INSIDE_HEIGHT, "Jeu de Scrabble");

		// Graphic frame
		mainFg.setClearColor(50, 50, 50);
		mainFg.clear();

		// Initialisation of the game
		Game game = new Game(mainFg);

		// Gameboard's drawing
		gameBoardDrawing(mainFg, game);
		
		// Main part of the game
		//game.playGame();
	}

	public static void gameBoardDrawing(FenetreGraphique mainFg, Game game){
		// Variables
		int x, y;

		// Drawing
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				x = 178 + i*50;
				y = 70 + j*50;
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

}