/**
 * Scrabble (APO Project - CMI L1) Scrabble.java - Main file
 * 
 * @author Fabian Devel, Valentin Perignon
 */

public class Scrabble {

	public static void main(String[] args) {
		// Variables
		Game game = new Game();
		/*game.getGameBoard().grid[7][4].setTile(new Tiles('V', 4));
		game.getGameBoard().grid[7][5].setTile(new Tiles('A', 1));
		game.getGameBoard().grid[7][6].setTile(new Tiles('L', 1));
		game.getGameBoard().grid[7][7].setTile(new Tiles('>', 0));
		game.getGameBoard().grid[7][8].setTile(new Tiles('A', 1));
		game.getGameBoard().grid[7][9].setTile(new Tiles('L', 1));
		game.getGameBoard().grid[7][10].setTile(new Tiles('L', 1));*/
		System.out.println(game.getGameBoard());
		System.out.println("Score : " + game.getGameBoard().wordScoreCalcul(game.getGameBoard().grid[7][4], 7, true));
		/*for(int i = 0; i<5; i++){
			for (int j = 0; j < 5; j++) {
				System.out.println(game.getGameBoard().grid[i][j]);
			}
		}*/
		
		
	}
}