/**
 * Scrabble (APO Project - CMI L1) Rack.java - Represents the tiles's rack of a
 * player
 * 
 * @author Fabian Devel, Valentin Perignon
 */

public class Game {

	// ---------- Attributs ----------

	Player player1;
	Player player2;
	GameBoard gameboard = new GameBoard();

	// ---------- Constructors ----------

	/**
	 * Game constructor
	 * 
	 * @param namePlayer1 Name of the first player
	 * @param namePlayer2 Name of the second player
	 */
	Game(String namePlayer1, String namePlayer2) {
		this.player1 = new Player(namePlayer1);
		this.player2 = new Player(namePlayer2);
	}

	/**
	 * Game constructor
	 */
	Game() {
		this.player1 = new Player("Player1");
		this.player2 = new Player("Player2");
	}

	// ---------- Getters ----------

	/**
	 * Get the first player
	 * 
	 * @return The player n°1
	 */
	public Player getPlayer1() {
		return this.player1;
	}

	/**
	 * Get the second player
	 * 
	 * @return The player n°2
	 */
	public Player getPlayer2() {
		return this.player2;
	}

	/**
	 * Get the game board
	 * 
	 * @return The game board
	 */
	public GameBoard getGameBoard() {
		return this.gameboard;
	}

}