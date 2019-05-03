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
	 */
	Game() {
		String[] names = initGame();
		player1 = new Player(names[0]);
		player2 = new Player(names[1]);
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

	// ---------- Methods ----------

	/**
	 * Ask players for their names
	 * 
	 * @return Array of names
	 */
	public String[] initGame() {
		// Variables
		String[] names = new String[2];

		// Treatment
		Ecran.afficher("Saisir le nom du premier joueur : ");
		names[0] = Clavier.saisirString();
		Ecran.afficher("Saisir le nom du second joueur : ");
		names[1] = Clavier.saisirString();

		return names;
	}

}