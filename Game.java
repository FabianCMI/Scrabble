/**
 * Scrabble (APO Project - CMI L1) Rack.java - Represents the tiles's rack of a
 * player
 * 
 * @author Fabian Devel, Valentin Perignon
 */
import javax.swing.JOptionPane;
public class Game {

	// ---------- Attributs ----------
	final int nbPlayer;
	private Player[] player;
	private GameBoard gameboard = new GameBoard();

	// ---------- Constructors ----------

	/**
	 * Game constructor
	 */
	Game() {
		Integer[] numPlayer = {2, 3, 4};
		this.nbPlayer = JOptionPane.showOptionDialog(null,
		"Bonjour et bienvenue dans ce jeu de scrabble.\nCombien y'a t'il de joueurs ? ", 
		"Nombre de joueurs", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
		null, numPlayer, numPlayer[2]) + 2;
		String[] names = initGame();
		player = new Player[nbPlayer];
		for (int i = 0; i < this.nbPlayer; i++) {
			player[i] = new Player(names[i]);
		}
	}

	// ---------- Getters ----------

	/**
	 * Get a player
	 * 
	 * @param numPlayer The wanted player's numero
	 * @return The player designed by the numero given in parameter
	 */
	public Player getPlayer(int numPlayer) {
		return this.player[numPlayer];
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
		String[] names = new String[this.nbPlayer];

		// Treatment
		for (int i = 0; i < this.nbPlayer; i++) {
			names[i] = JOptionPane.showInputDialog(null, "Saisir le nom du joueur " + (i+1) + " : ", "Nom joueur " + (i+1), JOptionPane.QUESTION_MESSAGE);
		}
		return names;
	}

	/**
	 * The player choose an action 
	 */
	public void playerAction (Player player){
		String[] action = {"Poser des lettres", "Piocher des lettres", "Passer votre tour"};
		int numAction = JOptionPane.showOptionDialog(null,
		"Que voulez vous faire ?", 
		"Actions possibles", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
		null, action, action[2]);
		switch (numAction) {
			case 0:
				wordPose(player);
				break;
			
			case 1:
				
				break;

			default:

				break;
		}
	}

	private void wordPose (Player player){
		String word = "";
		String column = "";
		String line = "";
		Integer[] nbTile = {1, 2, 3, 4, 5, 6, 7};
		int nbTiles = JOptionPane.showOptionDialog(null,
		"Combien de tuiles voulez-vous poser ? ", 
		"Pose de tuile sur le plateau", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
		null, nbTile, nbTile[6]) + 1;
		for(int i=0; i < nbTiles; i++){
			word += JOptionPane.showInputDialog(null, "Voici votre chevalet :\n" + player.getRack() + "\nQuelle lettre voulez vous posee ?");
			line = JOptionPane.showInputDialog(null, "Dans quelle ligne voulez vous la mettre ?", "Ligne", JOptionPane.QUESTION_MESSAGE);
			column = JOptionPane.showInputDialog(null, "Dans quelle colonne voulez vous la mettre ?", "Colonne", JOptionPane.QUESTION_MESSAGE);
			getGameBoard().getGrid()[Integer.parseInt(line)][Integer.parseInt(column)].getTile().setLetter(word.charAt(i));
		}
		if(nbTiles == 7)
			player.increaseScore(getGameBoard().wordScoreCalcul(getGameBoard().grid[Integer.parseInt(line)][Integer.parseInt(column)-(nbTiles-1)], nbTiles, true, true));
		else 
			player.increaseScore(getGameBoard().wordScoreCalcul(getGameBoard().grid[Integer.parseInt(line)][Integer.parseInt(column)-(nbTiles-1)], nbTiles, true, false));
	}

}