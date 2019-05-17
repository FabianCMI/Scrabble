
/**
 * Scrabble (APO Project - CMI L1) Rack.java - Represents the tiles's rack of a
 * player Excellent projet dont la plus-value principale est d'avoir été
 * programmé à moitié sur un mac, ce qui est réellement formidable.
 * 
 * @author Fabian Devel, Valentin Perignon
 */

public class Game {

	// ---------- Attributs ----------

	/** Array of players */
	private Player[] player;
	/** Number of player who are currently playing */
	final int nbPlayer;
	/** Current state of the game (running or not) */
	private boolean stateGame = true;
	/** Number of games turn */
	private int nbTour;
	/** Number of "To next player" */
	private int nextPlayer = 0;
	/** Gameboard */
	private GameBoard gameboard = new GameBoard();

	// ---------- Constructors ----------

	/**
	 * Game constructor
	 * 
	 * @param fg The window
	 */
	Game(FenetreGraphique fg) {
		// Variables
		final int midZone = fg.getBufferWidth() / 2;
		int nbPlayerInput = 0;

		// First text

		fg.drawString(5, Library.textHeight(fg, 20), 3,
				" =============================== SCRABBLE =============================");
		fg.drawString(5, Library.textHeight(fg, 40), 3, "Bonjour et bienvenue dans ce jeu de scrabble !");
		fg.drawString(5, Library.textHeight(fg, 20), 3, "Saisir le nombre de joueurs : ");

		// Drawing of the buttons
		int buttonHeight = Library.textHeight(fg, 10);
		drawButton(fg, midZone - 185, buttonHeight, 70, 40, 5, "2 ");
		drawButton(fg, midZone - 35, buttonHeight, 70, 40, 5, "3 ");
		drawButton(fg, midZone + 115, buttonHeight, 70, 40, 5, "4 ");

		// making the buttons clickable

		do {
			if (isClicked(fg, midZone - 185, buttonHeight, 70, 40))
				nbPlayerInput = 2;
			else if (isClicked(fg, midZone - 35, buttonHeight, 70, 40))
				nbPlayerInput = 3;
			else if (isClicked(fg, midZone + 115, buttonHeight, 70, 40))
				nbPlayerInput = 4;

		} while (nbPlayerInput < 2 || nbPlayerInput > 4);

		this.nbPlayer = nbPlayerInput;
		player = new Player[this.nbPlayer];

		// Names
		String[] names = initGame(fg);
		for (int i = 0; i < this.nbPlayer; i++) {
			if (names[i].length() < 1)
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
	 * @param fg The window
	 * 
	 * @return Array of names
	 */
	public String[] initGame(FenetreGraphique fg) {
		// Variables
		String[] names = new String[this.nbPlayer];

		// Treatment
		for (int i = 0; i < this.nbPlayer; i++) {
			String str = "Saisir le nom du joueur " + Integer.toString((i + 1)) + " : ";
			fg.setColor(255, 255, 255);
			fg.drawString(5, Library.textHeight(fg, 70), 3, str);
			fg.flush();
			names[i] = enterWord(fg);
		}
		fg.clear();
		Library.resetTextHeight();
		// Beginning of the game
		fg.drawString(5, Library.textHeight(fg, 0), 3,
				"--------------------------------- Debut de la partie --------------------------------");
		fg.flush();
		return names;
	}

	/**
	 * Main part of the game
	 * 
	 * @param fg     Second window
	 * @param mainFg Main window
	 */
	public void playGame(FenetreGraphique fg, FenetreGraphique mainFg) {
		// Variable
		boolean stateGame = true;
		int firstPlayer = Library.getRandomInt(this.nbPlayer);
		int currentPlayer = firstPlayer;

		// Main loop
		do {
			if (this.stateGame) {
				// Informations display
				drawText(fg, "C'est au tour de " + this.player[currentPlayer].getName() + " de jouer...", 20);

				// message
				player[currentPlayer].drawPlayer(fg);// informations about the player (name, score and rack)

				// Menu of actions
				selectAction(this.player[currentPlayer], fg, mainFg);

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

		// --------- End of the game -------------

		endingCalculScore();
		fg.clear();
		drawText(fg, "------------------------------------ Fin de la partie -------------------------------------", 0);
		drawText(fg, "Vous avez fini la partie !", 40);
		drawText(fg, "Informations sur les joueurs :", 50);
		for (Player aPlayer : this.player) {
			drawText(fg, aPlayer.getName() + "          score : " + aPlayer.getScore(), 40);
		}
		// Need another button
		int buttonX = fg.getBufferWidth() / 2 - 50;
		int buttonY = 800;
		drawButton(fg, buttonX, buttonY, 100, 50, 10, "Quitter");
		do {
			FenetreGraphique.wait(20);
		} while (!isClicked(fg, buttonX, buttonY, 100, 50));
	}

	// --------------------------------------------------
	// ##################### Actions ####################
	// --------------------------------------------------

	/**
	 * The player choose an action
	 * 
	 * @param player The player who is currently playing
	 * @param fg     Second window
	 * @param mainFg Main window
	 */
	public void selectAction(Player player, FenetreGraphique fg, FenetreGraphique mainFg) {
		// Variables
		int numAction = 0;

		// Action entry
		fg.setColor(255, 255, 255);
		drawText(fg, "Que souhaitez-vous faire ?", 140);

		// more buttons
		final int midZone = fg.getBufferWidth() / 2;
		int buttonHeight = Library.textHeight(fg, 30);

		fg.setColor(255, 0, 0);
		drawButton(fg, midZone - 300, buttonHeight, 150, 50, 9, "Poser un mot");
		drawButton(fg, midZone - 100, buttonHeight, 200, 50, 8, " Piocher des lettres    ");
		drawButton(fg, midZone + 150, buttonHeight, 150, 50, 8, "Passer son tour  ");

		do {
			if (isClicked(fg, midZone - 300, buttonHeight, 150, 50))
				numAction = 1;
			else if (isClicked(fg, midZone - 100, buttonHeight, 200, 50))
				numAction = 2;
			else if (isClicked(fg, midZone + 150, buttonHeight, 150, 50))
				numAction = 3;
		} while (numAction < 1 || numAction > 3);

		// Action
		switch (numAction) {
		// Put a word
		case 1:
			// Putting the word
			putWord(player, fg, mainFg);
			this.nextPlayer = 0;
			// draw the new gameboard
			drawGameBoard(mainFg);
			// Last display
			drawText(fg, "Votre mot est maintenant sur la grille.", 20);
			break;

		// Change letters
		case 2:
			changeSomeLetters(player, fg);
			this.nextPlayer = 0;
			break;

		// Don't want to play
		case 3:
			// Last display
			drawText(fg, "Vous passez votre tour.", 80);
			;
			nextPlayer++;
			break;
		}
		fg.wait(1500);
		fg.clear();
		Scrabble.textHeight = 20;
	}

	/**
	 * Put a word on the grid
	 * 
	 * @param player Player who is currently working
	 * @param fg     Second window
	 * @param mainFg Main window
	 */
	public void putWord(Player player, FenetreGraphique fg, FenetreGraphique mainFg) {
		// Variables
		boolean isHorizontal = true;
		int[] coordinate = { 7, 7 };
		int[] indexLetters;
		Tiles[] letters = new Tiles[7];
		String word;

		// About the orientation
		int answerInt = 0;
		drawText(fg, "Souhaitez-vous poser un mot vertical ou horitonzal ?", 100);

		// buttons
		final int midZone = fg.getBufferWidth() / 2;
		int buttonHeight = Library.textHeight(fg, 30);
		drawButton(fg, midZone - 200, buttonHeight, 150, 50, 9, "Vertical ");
		drawButton(fg, midZone + 50, buttonHeight, 150, 50, 9, "Horizontal");
		do {
			if (isClicked(fg, midZone - 200, buttonHeight, 150, 50))
				answerInt = 1;
			else if (isClicked(fg, midZone + 50, buttonHeight, 150, 50))
				answerInt = 2;
		} while (answerInt != 1 && answerInt != 2);

		if (answerInt != 1)
			isHorizontal = false;

		// About the coordinates
		String answerStr;
		if (this.nbTour > 1) {
			// Enter of coordinates
			String[] splitStr;
			drawText(fg, "Cliquez sur la case de la premiere lettre : ", 70);
			do {
				if (isClicked(mainFg, 178, 70, 750, 750)) { // click on the board
					int mouseX = mainFg.getMouseX() - 178;
					int mouseY = mainFg.getMouseY() - 70;
					for (int i = 0; i < 15; i++) {
						for (int j = 0; j < 15; j++) {
							if (mouseX / 50 == j && mouseY / 50 == i) {
								coordinate[1] = this.gameboard.grid[i][j].getColumn();
								coordinate[0] = this.gameboard.grid[i][j].getLine();
							}
						}
					}
				} else {
					coordinate[0] = -1;
				}
			} while (coordinate[0] < 0 || coordinate[0] > 14 || coordinate[1] < 0 || coordinate[1] > 14);
		}

		// About the word
		drawText(fg, "Saisir le mot que vous souhaitez placer: ", 80);
		do {
			word = enterWord(fg);
		} while (word == "");

		// Check the length
		while (checkLengthWord(word, coordinate, isHorizontal)) {
			drawText(fg, "Le mot est trop long.", 20);
			drawText(fg, "Saisir le mot que vous souhaitez placer: ", 20);
			do {
				word = enterWord(fg);
			} while (word == "");
		}

		// Check the word
		boolean isOnTemp = false;
		indexLetters = new int[word.length()];
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
					}
				}

				// If the letter isn't on the grid
				if (!isOn) {
					int answer;
					drawText(fg, "Vous ne pouvez pas placer ce mot...", 20);
					drawButton(fg, fg.getBufferWidth() / 2 - 50, 800, 100, 50, 9, "Continuer");
					fg.setColor(255, 255, 255);
					do {
						fg.wait(20);
					} while (!isClicked(fg, fg.getBufferWidth() / 2 - 50, 800, 100, 50));
					fg.clear();
					Library.resetTextHeight();
					player.drawPlayer(fg);
					Library.textHeight(fg, 80);
					putWord(player, fg, mainFg);
					return;
				}
			}
		}
		// Check if the word is connected with another
		if (!isOnTemp && this.nbTour > 1) {
			boolean isNear = false;
			for (int i = 0; i < word.length(); i++) {
				if (isHorizontal) {
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
				} else {
					if (i == 0 && coordinate[1] != 0) {
						if (this.gameboard.getGrid()[coordinate[1] - 1][coordinate[0]].getTile().getValue() != 0) {
							isNear = true;
						}
					} else if (i == word.length() - 1 && coordinate[1] != 14) {
						if (this.gameboard.getGrid()[coordinate[1]][coordinate[0] + 1].getTile().getValue() != 0) {
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
						if (this.gameboard.getGrid()[coordinate[1] + i][coordinate[0] + 1].getTile().getValue() != 0
								|| this.gameboard.getGrid()[coordinate[1] + i][coordinate[0] - 1].getTile()
										.getValue() != 0) {
							isNear = true;
						}
					}
				}
			}
			if (!isNear) {
				drawText(fg, "Vous devez positionner votre mot pour qu'il touche au moins une lettre", 20);
				drawText(fg, "deja sur le plateau ", 20);
				drawButton(fg, fg.getBufferWidth() / 2 - 50, 800, 100, 50, 9, "Continuer");
				fg.setColor(255, 255, 255);
				do {
					fg.wait(20);
				} while (!isClicked(fg, fg.getBufferWidth() / 2 - 50, 800, 100, 50));
				fg.clear();
				Library.resetTextHeight();
				player.drawPlayer(fg);
				Library.textHeight(fg, 80);
				putWord(player, fg, mainFg);
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
		drawText(fg, player.getName() + " marque " + score + " points", 20);

		// coordinates reset
		coordinate[0] = -1;
		coordinate[1] = -1;

		// refreshing the rack
		player.getRack().refreshRack(indexLetters);
	}

	/**
	 * To change some letters in a rack
	 * 
	 * @param player The player
	 * @param fg     The window
	 */
	public void changeSomeLetters(Player player, FenetreGraphique fg) {
		// Variables
		int[] indexLetters;

		// Entry of indexes in a String
		String answerStr;
		drawText(fg, "Saisir l'index des lettres que vous voulez changer (ex: abc) : ", 80);
		answerStr = enterWord(fg);

		// Check length of the String
		while (answerStr.length() > 7) {
			drawText(fg, "Vous ne pouvez changer que 7 tuiles au maximum.", 20);
			drawText(fg, "Saisir l'index des tuiles que vous voulez changer (ex: abc) : ", 20);
			answerStr = enterWord(fg);
		}
		indexLetters = new int[answerStr.length()];
		Library.charstoArray(indexLetters, answerStr, "");

		// Check values of the array of integers
		if (!Library.checkValuesOfArray(indexLetters, 1, 7)) {
			drawText(fg, "Vous avez saisi un ou plusieurs index incorrects.", 20);
			changeSomeLetters(player, fg);
		} else {
			// Refresh the rack
			player.getRack().refreshRack(indexLetters);

			// Last display
			drawText(fg, "Vous avez maintenant de nouvelles tuiles.", 20);
		}
	}

	/**
	 * Display the gameboard
	 * 
	 * @param mainFg The window
	 */
	public void drawGameBoard(FenetreGraphique mainFg) {
		// Variables
		int x, y;
		int lineNumber = 1;
		int colonneNumber = 1;
		char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O' };

		// Drawing
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				x = 178 + i * 50;
				y = 70 + j * 50;
				// letters and numbers
				if (x % 178 == 0 && lineNumber <= 15) { // letters
					mainFg.setColor(220, 220, 220);
					mainFg.drawText(x - 25, y + 33, 3, letters[lineNumber - 1]);
					lineNumber++;
				}
				if (y == 70 && colonneNumber <= 15) { // numbers
					mainFg.setColor(220, 220, 220);
					mainFg.drawText(x + 17, y - 10, 3, colonneNumber);
					colonneNumber++;
				}

				// lines
				mainFg.setColor(220, 220, 220);
				mainFg.drawLine(x, 70, x, 820);
				mainFg.drawLine(178, y, 928, y);
				mainFg.setColor(60, 100, 60);
				if (i < 15 && j < 15) {
					if (this.gameboard.getGrid()[i][j].getScoreMult() == 3) {
						if (this.gameboard.getGrid()[i][j].getTypeScoreMult()) {
							mainFg.setColor(175, 0, 0);
						} else {
							mainFg.setColor(0, 0, 175);
						}
					} else if (this.gameboard.getGrid()[i][j].getScoreMult() == 2) {
						if (this.gameboard.getGrid()[i][j].getTypeScoreMult()) {
							mainFg.setColor(255, 100, 100);
						} else {
							mainFg.setColor(100, 100, 255);
						}
					}
					mainFg.fillRect(x, y, 50, 50);
					if (this.gameboard.getGrid()[i][j].getTile().getLetter() != Character.MIN_VALUE) {
						mainFg.setColor(255, 255, 255);
						String str = Character.toString(gameboard.getGrid()[i][j].getTile().getLetter());
						mainFg.drawString(x + 20, y + 30, 3, str);
					}
				}
			}
		}
		mainFg.flush();
	}

	// --------------------------------------------------
	// ################### Private Methods ######################
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
	 * @param letters An array of letters
	 * @param letter  The searched letter
	 * 
	 * @return the index of the letter, -1 if it's not on the rack
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
	 * Checi if there is an "is Scrabble"
	 * 
	 * @param indexLetters Index of the letters used
	 * 
	 * @return True if the player posed his 7 letters
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

	/**
	 * Look if the button created by the coordonates is clicked
	 * 
	 * @param fg     The graphic frame
	 * @param x      The abscissa of the square's left-top corner pixel
	 * @param y      The ordinate of the square's left-top corner pixel
	 * @param width  The width of the square
	 * @param height The height of the square
	 * 
	 * @return The state of the click
	 */
	private boolean isClicked(FenetreGraphique fg, int x, int y, int width, int height) {
		if (fg.getMouseY() > y && fg.getMouseY() < y + height && fg.getMouseX() > x && fg.getMouseX() < x + width) {
			if (fg.getMouseState() == 1) {
				fg.wait(200);
				return (true);
			}
		}
		return (false);
	}

	/**
	 * To enter a word
	 * 
	 * @param fg The window
	 * 
	 * @return The word
	 */
	private String enterWord(FenetreGraphique fg) {
		String word = "";
		int height = Library.textHeight(fg, 20);
		int buttonX = fg.getBufferWidth() / 2 - 50;
		int buttonY = 800;
		drawButton(fg, buttonX, buttonY, 100, 50, 10, "Valider");
		do {
			char lastChar = fg.getKey();
			int lastCode = (int) lastChar;
			fg.drawString(5, height, 3, word);
			fg.flush();
			if (!(lastCode >= 65 && lastCode <= 90) && !(lastCode >= 97 && lastCode <= 122)) {
				lastChar = Character.MIN_VALUE;
			}
			if (lastChar != Character.MIN_VALUE) {
				word += lastChar;
			}
		} while (!isClicked(fg, buttonX, buttonY, 100, 50));
		return (word);
	}

	static void drawText(FenetreGraphique fg, String msg, int n) {
		fg.drawString(5, Library.textHeight(fg, n), 3, msg);
		fg.flush();
	}

	/**
	 * Draw a button
	 * 
	 * @param fg     The frame where the button will be draw
	 * @param x      The abscissa of the top left corner of the button
	 * @param y      The ordinate of the top left corner of the button
	 * @param width  The width of the button
	 * @param height The height of the button
	 * @param n      The number used to center the text in fonction of its length
	 * @param str    The text inside the button
	 */
	private void drawButton(FenetreGraphique fg, int x, int y, int width, int height, int n, String str) {
		fg.setColor(255, 0, 0);
		fg.drawRect(x, y, width, height);
		fg.setColor(255, 255, 255);
		fg.fillRect(x + 2, y + 2, width - 3, height - 3);
		fg.setColor(0, 0, 0);
		fg.drawString(x + (width / 2 - (str.length() / 2) * n), y + height / 2 + 5, 3, str);
		fg.setColor(255, 255, 255);
		fg.flush();
	}

	private void endingCalculScore() {
		boolean isLeft = false;
		int tempPlayerIndex = -1;
		int score = 0;
		// Look if there are a player without remaining tiles
		for (int i = 0; i < player.length; i++) {
			for (int j = 0; j < player[i].getRack().getTiles().length; j++) {
				if (player[i].getRack().getTiles()[j].getLetter() != Character.MIN_VALUE) {
					isLeft = true;
					j = player[i].getRack().getTiles().length - 1; // no need to test other tiles
				}
			}
			if (!isLeft) {
				tempPlayerIndex = i;
				i = player.length - 1; // No need to test other players
			}
		}
		if (tempPlayerIndex >= 0) {
			int i = 0;
			do {
				for (int j = 0; j < player[i].getRack().getTiles().length; j++) {
					score += player[i].getRack().getTiles()[j].getValue();
				}
				player[i].increaseScore(-score);
				player[tempPlayerIndex].increaseScore(score);
				i++;
			} while (i < player.length);
		}
	}
}