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
	private int nbTour;
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
			if(names[i].length() < 1 ){
				player[i] = new Player();
			} else {
				player[i] = new Player(names[i]);
			}
		}
		this.nbTour = 1;
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
		this.nbTour += 1;
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
		// Variables
		boolean isHorizontal = true;
		String word = "";
		int line = 0; 
		int column = 0;
		Integer[] nbTile = {1, 2, 3, 4, 5, 6, 7};

		// Treatment
		int nbTiles = JOptionPane.showOptionDialog(null,
		"Combien de tuiles voulez-vous poser ? ", 
		"Pose de tuile sur le plateau", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
		null, nbTile, nbTile[6]) + 1;
		while(this.nbTour == 1 && nbTiles == 1){
			nbTiles = JOptionPane.showOptionDialog(null,
			"Au premier tour, vous devez obligatoirement posé un mot d'au moins 2 lettres\nVeuillez Recommencer", 
			"Pose de tuile sur le plateau", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, 
			null, nbTile, nbTile[6]) + 1;
		}
		String[] placement = {"Horizontal", "Vertical"};
		int n = JOptionPane.showOptionDialog(null,
		"Quelle sera l'orientation de votre mot ? ", 
		"Orientation du mot", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
		null, placement, placement[1]);
		if(n == 1){
			isHorizontal = false;
		}
		for(int i=0; i < nbTiles; i++){
			word += JOptionPane.showInputDialog(null, "Voici votre chevalet :\n" + player.getRack() + "\nQuelle lettre voulez vous posée ?");
			if(i == 0){
				line = correctCapture("ligne");
				column = correctCapture("colonne");
			} else {
				if(isHorizontal){
					column = correctCapture("colonne");
					while(getGameBoard().getGrid()[line][column].getTile().getValue() != 0){
						JOptionPane.showMessageDialog(null, "Erreur - Il y a une tuile déjà posée à cet emplacement", "Erreur - pose de tuile", JOptionPane.ERROR_MESSAGE);
						column = correctCapture("colonne");
					}
					if(column > 0 && column < 14){
						while(getGameBoard().getGrid()[line][column-1].getTile().getValue() == 0 
						&& getGameBoard().getGrid()[line][column+1].getTile().getValue() == 0){
							JOptionPane.showMessageDialog(null, "Erreur - La tuile doit être posée avant ou après une tuile déjà posée", "Erreur - pose de tuile", JOptionPane.ERROR_MESSAGE);
							column = correctCapture("colonne");
						}
					}
					getGameBoard().getGrid()[line][column].setTile(player.getRack().getTiles()[1]);
				} else {
					line = correctCapture("ligne");
					while(getGameBoard().getGrid()[line-1][column].getTile().getValue() == 0 
					&& getGameBoard().getGrid()[line+1][column].getTile().getValue() == 0){
						JOptionPane.showMessageDialog(null, "Erreur - La tuile doit être posée avant ou après une tuile déjà posée", "Erreur - pose de tuile", JOptionPane.ERROR_MESSAGE);
						line = correctCapture("ligne");
					}
					getGameBoard().getGrid()[line][column].setTile(player.getRack().getTiles()[1]);
				}
				
			} System.out.println(getGameBoard());
		}
		
		if (isHorizontal){
			if(nbTiles == 7)
				player.increaseScore(getGameBoard().wordScoreCalcul(getGameBoard().grid[line][column-(nbTiles-1)], nbTiles, true, true));
			else 
				player.increaseScore(getGameBoard().wordScoreCalcul(getGameBoard().grid[line][column-(nbTiles-1)], nbTiles, true, false));

			JOptionPane.showMessageDialog(null, player.getName() + " a maintenant " + player.getScore() + " points");
		} else {
			if(nbTiles == 7)
				player.increaseScore(getGameBoard().wordScoreCalcul(getGameBoard().grid[line-(nbTiles-1)][column], nbTiles, false, true));
			else 
				player.increaseScore(getGameBoard().wordScoreCalcul(getGameBoard().grid[line-(nbTiles-1)][column], nbTiles, false, false));

			JOptionPane.showMessageDialog(null, player.getName() + " a maintenant " + player.getScore() + " points");
		}
	}

	/**
	 * Make sure the asked value is bewteen 0 and 14 
	 * 
	 * @param coord the name of the value asked
	 * @return the value
	 */
	private int correctCapture (String coord){
		int n = 0;
		n = Integer.parseInt(JOptionPane.showInputDialog(null, "Dans quelle " + coord + " voulez vous la mettre ?", coord, JOptionPane.QUESTION_MESSAGE));
		while(n < 0 || n > 14){
			n = Integer.parseInt(JOptionPane.showInputDialog(null, "Erreur : La valeur doit être comprise entre 0 et 14.\nVeuillez recommencer", coord + " - ERREUR", JOptionPane.ERROR_MESSAGE));
		}
		return n;
	}
}