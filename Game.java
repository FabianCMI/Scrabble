/**
 * Scrabble (APO Project - CMI L1) Rack.java - Represents the tiles's rack of a
 * player
 * Excellent projet dont la plus-value principale est d'avoir été programmé à moitié sur un mac, ce qui est réellement formidable.
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
		// First text
		int nbPlayerInput;
		Ecran.afficher ("Bonjour et bienvenue dans ce jeu de scrabble.\nSaisir le nombre de joueurs : ");
		nbPlayerInput = Clavier.saisirInt();
		while(nbPlayerInput < 2 || nbPlayerInput > 4) {
			Ecran.afficher("Vous devez avoir entre 2 et 4 joueurs. Saisir le nombre de joueurs : ");
			nbPlayerInput = Clavier.saisirInt();
		}
		this.nbPlayer = nbPlayerInput;
		player = new Player[nbPlayer];

		// Names
		String[] names = initGame();
		for (int i = 0; i < this.nbPlayer; i++) {
			if(names[i].length() < 1)
				player[i] = new Player();
			else 
				player[i] = new Player(names[i]);
		}

		// Tour
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
			Ecran.afficher("Saisir le nom du joueur " + (i+1) + ": ");
			names[i] = Clavier.saisirString();
		}

		return names;
	}

	/**
	 * The player choose an action 
	 */
	public void playerAction(Player player){
		// Variables
		int numAction;
		this.nbTour += 1;

		// Action entry
		Ecran.afficherln("1 : Poser un mot\n2 : Piocher des tuiles\n3 : Passer son tour ");
		numAction = correctCapture("l'action voulue", 1, 3);
		switch (numAction) {
			case 1:
				wordPose(player);
				break;
			
			case 2:
				
				break;

			default:

				break;
		}
	}

	/******** TO-DO : faire les tests pour empecher de sortir du tableau *********
	si on rajoute une tuile sur une case au bords, verifier le calcul de point en vertical*/

	private void wordPose(Player player){
		// Variables
		boolean isHorizontal = true;
		String word = "";
		int line = 0; 
		int column = 0;
		Integer[] nbTile = {1, 2, 3, 4, 5, 6, 7};

		// Treatment
		int nbTiles = correctCapture("le nombre de tuiles à posées", 1, 7);
		while(this.nbTour == 1 && nbTiles == 1){
			Ecran.afficher("Erreur - Vous devez obligatoirement poser un mot d'au moins 2 lettres en premier ");
			nbTiles = correctCapture("le nombre de tuiles à posées", 1, 7);
		}
		Ecran.afficherln("Quelle est l'orientation du mot ?\n0 : horizontal\n1 : vertical");
		int n = correctCapture("l'orientation", 0, 1);
		if(n == 1){
			isHorizontal = false;
		}
		for(int i=0; i < nbTiles; i++){
			Ecran.afficherln("Voici votre chevalet :\n" + player.getRack() + "\nQuelle lettre voulez vous poser ?");
			word += Clavier.saisirString();
			while(indexOnTheRack(player, word.charAt(i)) == -1){
				Ecran.afficherln("Cette tuile n'est pas dans votre chevalet, veuillez en choisir une autre : ");
				word = word.substring(0, word.length()-1); // delete the last char of the string for the next test
				word += Clavier.saisirString(); // replace it by a new one
			}
			if(i == 0){
				line = correctCapture("le numéro de la ligne", 0, 14);
				column = correctCapture("le numéro de la colonne", 0, 14);
			} else {
				if(isHorizontal){
					column = correctCapture("le numéro de la colonne", 0, 14);
					while(getGameBoard().getGrid()[line][column].getTile().getValue() != 0){
						Ecran.afficherln("Erreur - Il y a déjà une tuile posée sur cette case ");
						column = correctCapture("le numéro de la colonne", 0, 14);
					}
					while(getGameBoard().getGrid()[line][column-1].getTile().getValue() == 0 
					&& getGameBoard().getGrid()[line][column+1].getTile().getValue() == 0){
						Ecran.afficherln("Erreur - La tuile doit être posée avant ou après une tuile déjà posée");
						column = correctCapture("le numéro de la colonne", 0, 14);
					}
				} else {
					line = correctCapture("le numéro de la ligne", 0, 14);
					while(getGameBoard().getGrid()[line][column].getTile().getValue() != 0){
						Ecran.afficherln("Erreur - Il y a déjà une tuile posée sur cette case ");
						line = correctCapture("le numéro de la ligne", 0, 14);
					}
					while(getGameBoard().getGrid()[line-1][column].getTile().getValue() == 0 
					&& getGameBoard().getGrid()[line+1][column].getTile().getValue() == 0){
						Ecran.afficherln("Erreur - La tuile doit être posée avant ou après une tuile déjà posée");
						line = correctCapture("le numéro de la ligne", 0, 14);
					}
				}
			} 
			setTileOnGrid(line, column, player, indexOnTheRack(player, word.charAt(i)));
			System.out.println(getGameBoard());
		}
		
		if (isHorizontal){
			if(nbTiles == 7)
				player.increaseScore(getGameBoard().wordScoreCalcul(getGameBoard().grid[line][column-(nbTiles-1)], nbTiles, true, true));
			else 
				player.increaseScore(getGameBoard().wordScoreCalcul(getGameBoard().grid[line][column-(nbTiles-1)], nbTiles, true, false));

		} else {
			if(nbTiles == 7)
				player.increaseScore(getGameBoard().wordScoreCalcul(getGameBoard().grid[line-(nbTiles-1)][column], nbTiles, false, true));
			else 
				player.increaseScore(getGameBoard().wordScoreCalcul(getGameBoard().grid[line-(nbTiles-1)][column], nbTiles, false, false));
		}
		Ecran.afficherln(player.getName() + " a maintenant " + player.getScore() + " points");
	}

	/**
	 * Make sure the asked value is bewteen 0 and 14 
	 * 
	 * @param coord the name of the value asked
	 * @return the value
	 */
	private int correctCapture (String msg, int min, int max){
		Ecran.afficherln("Veuillez saisir " + msg + " : ");
		int n = Clavier.saisirInt();
		while(n < min || n > max){
			Ecran.afficherln("Le nombre saisi doit être compris entre " + min + " et " + max + " (compris)");
			n = Clavier.saisirInt();
		}
		return n;
	}

	private void setTileOnGrid(int numLine, int numColumn, Player player, int numTileInRack){
		Square tile = this.getGameBoard().getGrid()[numLine][numColumn];
		tile.setTile(player.getRack().getTiles()[numTileInRack]);
	}

	private int indexOnTheRack (Player player, char letter){
		boolean isOn = false;
		int i = -1;
		while(!isOn && i < 6){
			i++;
			if(player.getRack().getTiles()[i].getLetter() == letter){
				isOn = true;
			}
			
		}
		if(!isOn){ //not found
			i = -1;
		}
		return(i);
	}
} 