import java.security.interfaces.ECPrivateKey;

/**
 * Scrabble (APO Project - CMI L1) Rack.java - Represents the tiles's rack of a
 * player
 * 
 * @author Fabian Devel, Valentin Perignon
 */

public class Game {

	// ---------- Attributs ----------

	/** Number of players in the game */
	final int nbPlayer;
	/** Array of players currently playing */
	private Player[] player;
	/** Current state of the game (playing or not) */
	private boolean stateGame = true;
	/** Number of the game turn */
	private int nbTour;
	/** Number of "To skip the turn" followed */
	private int nextPlayer = 0;
	/** Gameboad */
	private GameBoard gameboard = new GameBoard();

	// ---------- Constructors ----------

	/**
	 * Game constructor
	 */
	Game() {
		// First text
		int nbPlayerInput;
		Ecran.afficherln("\n============================ SCRABBLE ============================");
		Ecran.afficher("Bonjour et bienvenue dans ce jeu de scrabble.\n\nSaisir le nombre de joueurs : ");
		nbPlayerInput = Clavier.saisirInt();
		while (nbPlayerInput < 2 || nbPlayerInput > 4) {
			Ecran.afficher("Vous devez avoir entre 2 et 4 joueurs. Saisir le nombre de joueurs : ");
			nbPlayerInput = Clavier.saisirInt();
		}
		this.nbPlayer = nbPlayerInput;
		player = new Player[this.nbPlayer];

		// Names
		String[] names = initGame();
		for (int i = 0; i < this.nbPlayer; i++) {
			if (names[i].length() < 1)
				player[i] = new Player();
			else
				player[i] = new Player(names[i]);
		}
		Ecran.sautDeLigne();

		// Tour
		this.nbTour = 1;
	}

	// ---------- Getters ----------

	/**
	 * Get a player
	 * 
	 * @param numPlayer The wanted player's numero
	 * 
	 * @return The player designed by the numero given in parameter
	 */
	public Player[] getPlayer() {
		return this.player;
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

	// --------------------------------------------------
	// ###################### Game ######################
	// --------------------------------------------------

	/**
	 * Ask players for their names and initialize the game
	 * 
	 * @return Array of names
	 */
	public String[] initGame() {
		// Variables
		String[] names = new String[this.nbPlayer];

		// Treatment
		for (int i = 0; i < this.nbPlayer; i++) {
			Ecran.afficher("Saisir le nom du joueur " + (i + 1) + ": ");
			names[i] = Clavier.saisirString();
		}

		// Beginning of the game
		Ecran.afficherln("\n----------------------- Début de la partie -----------------------");

		return names;
	}

	/**
	 * Main part of the game
	 */
	public void playGame() {
		// Variable
		boolean stateGame = true;
		int firstPlayer = Library.getRandomInt(this.nbPlayer);
		int currentPlayer = firstPlayer;

		// Main loop

		do {
			if (this.stateGame) {
				// Informations display
				Ecran.afficherln("C'est au tour de " + this.player[currentPlayer].getName() + " de jouer..."); // first
																												// message
				Ecran.afficherln("\n" + this.gameboard + "\n"); // game board
				Ecran.afficherln(this.player[currentPlayer] + "\n"); // informations about the player (name, score and
																		// rack)

				// Menu of actions
				selectAction(this.player[currentPlayer]);

				// Check if the game can continue
				if (areAllRacksNull()) // check if all racks all racks are empty
					this.stateGame = false;
				if (this.nextPlayer >= this.nbPlayer) // check if all players have passed their turn
					this.stateGame = false;

				// One more tour
				this.nbTour += 1;
			}
			if (currentPlayer == this.nbPlayer - 1)
				currentPlayer = 0;
			else
				currentPlayer += 1;

		} while (this.stateGame);

		// End of the game
		Ecran.afficherln("\n------------------------ Fin de la partie ------------------------");
		Ecran.afficherln("\nVous avez terminé la partie !\nVoilà les informations sur les joueurs :");
		for (Player aPlayer : this.player) {
			Ecran.sautDeLigne();
			Ecran.afficherln(aPlayer);
		}
	}

	// --------------------------------------------------
	// ##################### Actions ####################
	// --------------------------------------------------

	/**
	 * The player choose an action
	 * 
	 * @param player The player who is currently playing
	 */
	public void selectAction(Player player) {
		// Variables
		int numAction;

		// Action entry
		Ecran.afficher(
				"Que souhaitez-vous faire ?\n 1- Poser un mot\n 2- Piocher des lettres\n 3- Passer son tour\nQue souhaitez-vous faire : ");
		numAction = Clavier.saisirInt();
		while (numAction < 1 || numAction > 3) {
			Ecran.afficher("Numéro non valide.\nQue souhaitez-vous faire : ");
			numAction = Clavier.saisirInt();
		}
		Ecran.sautDeLigne();

		// Action
		switch (numAction) {
		// Put a word
		case 1:
			// Putting the word
			putWord(player);
			this.nextPlayer = 0;

			// Last display
			Ecran.afficherln("Votre mot a été placé.");
			break;

		// Change letters
		case 2:
			changeSomeLetters(player);
			this.nextPlayer = 0;
			break;

		// Don't want to play
		case 3:
			// Last display
			Ecran.afficherln("Vous passez votre tour.");
			nextPlayer++;
			break;
		}
	}

	/**
	 * Put a word on the grid
	 * 
	 * @param player Player who is currently working
	 */
	public void putWord(Player player) {
		// Variables
		boolean isHorizontal = true;
		int[] coordinate = { 7, 7 };
		int[] indexLetters;
		Tiles[] letters = new Tiles[7];
		String word;

		// Create a new rack
		for (int i = 0; i < letters.length; i++) {
			letters[i] = player.getRack().getTiles()[i];
		}

		// About the orientation
		int answerInt;
		Ecran.afficher("Souhaitez-vous poser un mot vertical ou horitonzal ?\n 1- Horizontal\n 2- Vertical\n > ");
		answerInt = Clavier.saisirInt();
		if (answerInt != 1)
			isHorizontal = false;

		// About the coordinates
		String answerStr;
		if (this.nbTour > 1) {
			// Enter of coordinates
			String[] splitStr;
			Ecran.afficher("\nSaisir les coordonnées de la première lettre (ex: \"2,C\"): ");
			answerStr = Clavier.saisirString();
			splitStr = answerStr.split(",");
			answerStr = splitStr[0] + letterCoordtoInt(splitStr[1].charAt(0));

			// Check the entry
			Library.StringtoArray(coordinate, answerStr, "");
			while (coordinate[0] < 1 || coordinate[0] > 15 || coordinate[1] < 1 || coordinate[1] > 15) {
				Ecran.afficher("\nSaisir les coordonnées de la première lettre (ex: \"2C\"): ");
				answerStr = Clavier.saisirString();
				splitStr = answerStr.split(",");
				answerStr = splitStr[0] + letterCoordtoInt(splitStr[1].charAt(0));

				// Check the entry
				Library.StringtoArray(coordinate, answerStr, ",");
			}
			coordinate[0] -= 1;
			coordinate[1] -= 1;
		}

		// About the word
		Ecran.afficherln("\nSaisir le mot que vous souhaitez placer: ");
		word = Clavier.saisirString().toUpperCase();

		// Check the length
		while (checkLengthWord(word, coordinate, isHorizontal)) {
			Ecran.afficherln("\nLe mot est trop long.");

			Ecran.afficherln("Saisir le mot que vous souhaitez placer: ");
			word = Clavier.saisirString().toUpperCase();
		}

		// Check the word
		indexLetters = new int[word.length()];
		boolean isOnTemp = false;
		for (int i = 0; i < word.length(); i++) {
			// Index of the letter
			indexLetters[i] = indexOnTheRack(letters, word.charAt(i));
			if (indexLetters[i] != -1)
				letters[indexLetters[i]] = null;

			// Check if the letter is already on the grid
			if (indexLetters[i] == -1) {
				boolean isOn = false;
				if (isHorizontal) {
					if (this.gameboard.getGrid()[coordinate[1]][coordinate[0] + i].getTile().getLetter() == word
							.charAt(i)) {
						isOn = true;
						isOnTemp = true;
					}
				} else {
					if (this.gameboard.getGrid()[coordinate[1] + i][coordinate[0]].getTile().getLetter() == word
							.charAt(i)) {
						isOn = true;
						isOnTemp = true;
					}
				}

				// If the letter isn't on the grid
				if (!isOn) {
					int answer;
					Ecran.afficherln("Vous ne pouvez pas placer ce mot...");
					putWord(player);
					return;
				}
			}
		}
		// Check if the word is connected with another
		if (!isOnTemp && this.nbTour > 1) {
			boolean isNear = false;
			for (int i = 0; i < word.length(); i++) {
				if (isHorizontal) {
					if (i == 0 && coordinate[1] != 0) {
						if (this.gameboard.getGrid()[coordinate[1] - 1][coordinate[0]].getTile().getValue() != 0) {
							isNear = true;
						}
					} else if (i == word.length() - 1 && coordinate[1] != 14) {
						if (this.gameboard.getGrid()[coordinate[1] + 1][coordinate[0]].getTile().getValue() != 0) {
							isNear = true;
						}
					}
					if (coordinate[0] == 0) {
						if (this.gameboard.getGrid()[coordinate[1] + i][coordinate[0] + 1].getTile().getValue() != 0) {
							isNear = true;
						}
					} else if (coordinate[0] == 14) {
						if (this.gameboard.getGrid()[coordinate[1] + i][coordinate[0] - 1].getTile().getValue() != 0) {
							isNear = true;
						}
					} else {
						if (this.gameboard.getGrid()[coordinate[1] + 1][coordinate[0] + i].getTile().getValue() != 0
								|| this.gameboard.getGrid()[coordinate[1] - 1][coordinate[0] + i].getTile()
										.getValue() != 0) {
							isNear = true;
						}
					}
				} else {
					if (i == 0 && coordinate[0] != 0) {
						if (this.gameboard.getGrid()[coordinate[1]][coordinate[0] - 1].getTile().getValue() != 0) {
							isNear = true;
						}
					} else if (i == word.length() - 1 && coordinate[0] != 14) {
						if (this.gameboard.getGrid()[coordinate[1]][coordinate[0] + 1].getTile().getValue() != 0) {
							isNear = true;
						}
					}
					if (coordinate[1] == 0) {
						if (this.gameboard.getGrid()[coordinate[1] + 1][coordinate[0] + i].getTile().getValue() != 0) {
							isNear = true;
						}
					} else if (coordinate[1] == 14) {
						if (this.gameboard.getGrid()[coordinate[1] - 1][coordinate[0] + i].getTile().getValue() != 0) {
							isNear = true;
						}
					} else {
						if (this.gameboard.getGrid()[coordinate[1] + 1][coordinate[0] + i].getTile().getValue() != 0
								|| this.gameboard.getGrid()[coordinate[1] - 1][coordinate[0] + i].getTile()
										.getValue() != 0) {
							isNear = true;
						}
					}
				}
			}
			if (!isNear) {
				Ecran.afficherln(
						"Vous devez positionner votre mot pour qu'il touche au moins une lettre deja sur le plateau ");
				putWord(player);
				return;
			}
		}
		// Put the word onto the grid
		for (int i = 0; i < indexLetters.length; i++) {
			if (isHorizontal) {
				if (indexLetters[i] != -1) {
					this.gameboard.getGrid()[coordinate[1]][coordinate[0] + i]
							.setTile(player.getRack().getTiles()[indexLetters[i]]);
				}
			} else {
				if (indexLetters[i] != -1) {
					this.gameboard.getGrid()[coordinate[1] + i][coordinate[0]]
							.setTile(player.getRack().getTiles()[indexLetters[i]]);
				}
			}
		}

		// score calcul
		int score = gameboard.wordScoreCalcul(this.gameboard.getGrid()[coordinate[1]][coordinate[0]],
				indexLetters.length, isHorizontal, isScrabble(indexLetters));
		player.increaseScore(score);
		Ecran.afficherln(player.getName() + " a marqué " + score + " points");

		// refreshing the rack
		player.getRack().refreshRack(indexLetters);
	}

	/**
	 * To change some letters in a rack
	 * 
	 * @param player The player
	 */
	public void changeSomeLetters(Player player) {
		// Variables
		int[] indexLetters;

		// Entry of indexes in a String
		String answerStr;
		Ecran.afficher("Saisir l'index des lettres que vous voulez changer (ex: 123) : ");
		answerStr = Clavier.saisirString();

		// Check length of the String
		while (answerStr.length() > 7) {
			Ecran.afficherln("Vous ne pouvez changer que 7 lettres maximum.");
			Ecran.afficher("Saisir l'index des lettres que vous voulez changer (ex: 123) : ");
			answerStr = Clavier.saisirString();
		}
		indexLetters = new int[answerStr.length()];
		Library.StringtoArray(indexLetters, answerStr, "");

		// Check values of the array of integers
		if (!Library.checkValuesOfArray(indexLetters, 1, 7)) {
			Ecran.afficherln("Vous avez saisi un ou plusieurs index incorrectes.");
			changeSomeLetters(player);
		} else {
			// Refresh the rack
			player.getRack().refreshRack(indexLetters);

			// Last display
			Ecran.afficherln("Votre chevalet a été rafrachî.");
		}
	}

	// --------------------------------------------------
	// ############# Methods ################
	// --------------------------------------------------

	/**
	 * Check if a word is too long the be on the grid
	 * 
	 * @param word         The word
	 * @param coordinate   Coordinate of the word
	 * @param isHorizontal The orientation f the word
	 * 
	 * @return True if the word is too long
	 */
	private boolean checkLengthWord(String word, int[] coordinate, boolean isHorizontal) {
		// Variable
		boolean isTooLong = false;
		int i = 1;

		// treatment
		if (isHorizontal) // check the orientation
			i = 0;
		if (coordinate[i] + word.length() - 1 > 14) // check if the word is too long
			isTooLong = true;

		return isTooLong;
	}

	/**
	 * Find he index of a letter on the rack
	 * 
	 * @param player Player who is currently playing
	 * @param letter The searched letter
	 * 
	 * @return The index of the letter, -1 if it's not on the rack
	 */
	private int indexOnTheRack(Tiles[] letters, char letter) {
		// Variables
		boolean isOn = false;
		int i = -1;

		// Search in the rack
		while (!isOn && i < 6) {
			i++;
			if (letters[i] != null) {
				if (letters[i].getLetter() == letter)
					isOn = true;
			}
		}
		if (!isOn) // not found
			i = -1;

		return i;
	}

	/**
	 * Check if all the racks are 'null'
	 * 
	 * @return True if there are
	 */
	private boolean areAllRacksNull() {
		// Variable
		boolean areNull = true;

		// Treatment
		for (int i = 0; i < this.nbPlayer; i++) {
			if (!this.player[i].isRackNull())
				areNull = false;
		}

		return areNull;
	}

	/**
	 * Transform a letter (char) into a integer
	 * 
	 * @param letter
	 * @return The integer of the char
	 */
	private int letterCoordtoInt(char letter) {
		// Variable
		letter = Character.toUpperCase(letter);

		return (int) letter - 64;
	}

	/**
	 * Check if the word is a "scrabble"
	 * 
	 * @param indexLetters Index of letters of the rack used
	 * 
	 * @return True if it's a "scrabble"
	 */
	private boolean isScrabble(int[] indexLetters) {
		int nbLetter = 0;
		boolean isScrabble = false;
		for (int i = 0; i < indexLetters.length; i++) {
			if (indexLetters[i] >= 0) {
				nbLetter += 1;
			}
		}
		if (nbLetter == 7)
			isScrabble = true;

		return (isScrabble);
	}
}