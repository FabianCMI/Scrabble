/**
 * Scrabble (APO Project - CMI L1) Rack.java - Represents the tiles's rack of a
 * player
 * Excellent projet dont la plus-value principale est d'avoir été programmé à moitié sur un mac, ce qui est réellement formidable.
 * @author Fabian Devel, Valentin Perignon
 */
import javax.lang.model.util.ElementScanner6;
import javax.swing.JOptionPane;

public class Game {

	// ---------- Attributs ----------

	final int nbPlayer;
	private boolean stateGame = true;
	private int nbTour;
	private int nextPlayer = 0;
	private Player[] player;
	private GameBoard gameboard = new GameBoard();

	// ---------- Constructors ----------

	/**
	 * Game constructor
	 */
	Game(FenetreGraphique fg) {
		// Variables
		final int midZone = fg.getBufferWidth()/2;
		int nbPlayerInput = 0;

		// First text
		
		fg.drawString(5, textHeight(fg, 20), 3, " =============================== SCRABBLE =============================");
		fg.drawString(5,textHeight(fg, 40), 3, "Bonjour et bienvenue dans ce jeu de scrabble !");
		fg.drawString(5, textHeight(fg, 20), 3, "Saisir le nombre de joueurs : ");

		// Drawing of the buttons
		int buttonHeight = textHeight(fg, 10);
		fg.setColor(255, 0, 0);
		fg.drawRect( midZone -185, buttonHeight, 70, 40);
		fg.drawRect( midZone -35, buttonHeight, 70, 40);
		fg.drawRect( midZone +115, buttonHeight, 70, 40);
		fg.setColor(255, 255, 255);
		fg.fillRect( midZone -183, buttonHeight+2, 67, 37);
		fg.fillRect( midZone -33, buttonHeight+2, 67, 37);
		fg.fillRect( midZone +117, buttonHeight+2, 67, 37);
		fg.setColor(0, 0, 0);
		fg.drawString( midZone -155, buttonHeight+27, 3, "2");
		fg.drawString( midZone -5, buttonHeight+27, 3, "3");
		fg.drawString( midZone +146, buttonHeight+27, 3, "4");
		fg.flush();

		// making the buttons clickable
		
		do {
			if(isClicked(fg, midZone-185, buttonHeight, 70, 40))
				nbPlayerInput = 2;
			else if(isClicked(fg, midZone-35, buttonHeight, 70, 40))
				nbPlayerInput = 3;
			else if(isClicked(fg, midZone+115, buttonHeight, 70, 40))
				nbPlayerInput = 4;

		} while(nbPlayerInput < 2 || nbPlayerInput > 4);
		
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
		Ecran.sautDeLigne();

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
	public String[] initGame(FenetreGraphique fg) {
		// Variables
		String[] names = new String[this.nbPlayer];

		// Treatment
		for (int i = 0; i < this.nbPlayer; i++) {
			String str = "Saisir le nom du joueur " + Integer.toString((i+1)) + " : ";
			fg.setColor(255, 255, 255);
			fg.drawString(5, textHeight(fg, 70), 3, str);
			fg.flush();
			int buttonWidth = fg.getBufferWidth()/2-50;
			int buttonHeight = 500;
			fg.setColor(255, 0, 0);
			fg.drawRect(buttonWidth, buttonHeight, 100, 50);
			fg.setColor(255, 255, 255);
			fg.fillRect(buttonWidth+2, buttonHeight+2, 97, 47);
			fg.setColor(0, 0, 0);
			fg.drawString(buttonWidth+20 , buttonHeight+30, 3, "Valider");
			fg.setColor(255, 255, 255);
			names[i] = enterName(fg, buttonWidth, buttonHeight);
		}
		fg.clear();
		resetTextHeight();
		// Beginning of the game
		fg.drawString(5, textHeight(fg, 0), 3, "--------------------------------- Debut de la partie --------------------------------");
		fg.flush();
		return names;
	}

	/**
	 * Main part of the game
	 */
	public void playGame(FenetreGraphique fg) {
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
				selectAction(this.player[currentPlayer], fg);

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
	public void selectAction(Player player, FenetreGraphique fg) {
		// Variables
		int numAction = 0;

		// Action entry
		drawText(fg, "Que souhaitez-vous faire ?", 25);

		// more buttons
		final int midZone = fg.getBufferWidth()/2;
		int buttonHeight = textHeight(fg, 30);

		fg.setColor(255, 0, 0);
		fg.drawRect( midZone - 300, buttonHeight, 150, 50);
		fg.drawRect( midZone - 100, buttonHeight, 200, 50);
		fg.drawRect( midZone + 150, buttonHeight, 150, 50);
		fg.setColor(220, 220, 220);
		fg.fillRect( midZone - 298, buttonHeight+2, 147, 47);
		fg.fillRect( midZone - 98, buttonHeight+2, 197, 47);
		fg.fillRect( midZone + 152, buttonHeight+2, 147, 47);
		fg.setColor(0, 0, 0);
		fg.drawString( midZone - 280, buttonHeight+32, 3, "Poser un mot");
		fg.drawString( midZone - 85, buttonHeight+32, 3, "Piocher des lettres ");
		fg.drawString( midZone + 160, buttonHeight+32, 3, "Passer son tour");
		fg.flush();

		do{
			if(isClicked(fg, midZone - 300, buttonHeight, 150, 50))
				numAction = 1;
			else if(isClicked(fg, midZone - 100, buttonHeight, 200, 50))
				numAction = 2;
			else if(isClicked(fg, midZone +150, buttonHeight, 150, 50))
				numAction = 3;
		} while(numAction < 1 || numAction > 3);

		// Action
		switch (numAction) {
		// Put a word
		case 1:
			// Putting the word
			putWord(player);
			nextPlayer = 0;

			// Last display
			Ecran.afficherln("Votre mot a été placé.");
			break;

		// Change letters
		case 2:
			changeSomeLetters(player);
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
		String word;

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
			splitStr = answerStr.split("");
			answerStr = splitStr[0] + letterCoordtoInt(splitStr[1].charAt(0));

			// Check the entry
			Library.StringtoArray(coordinate, answerStr, "");
			while (coordinate[0] < 1 || coordinate[0] > 15 || coordinate[1] < 1 || coordinate[1] > 15) {
				Ecran.afficher("\nSaisir les coordonnées de la première lettre (ex: \"2C\"): ");
				answerStr = Clavier.saisirString();
				splitStr = answerStr.split("");
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
		for (int i = 0; i < word.length(); i++) {
			// Index of the letter
			indexLetters[i] = indexOnTheRack(player, word.charAt(i));

			// Check if the letter is already on the grid
			if (indexLetters[i] == -1) {
				boolean isOn = false;
				if (isHorizontal) {
					if (this.gameboard.getGrid()[coordinate[1]][coordinate[0] + i].getTile().getLetter() == word
							.charAt(i)) {
						isOn = true;
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
					Ecran.afficherln("Vous ne pouvez pas placer ce mot...");
					putWord(player);
					return;
				}
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
	// ################### Methods ######################
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
	 * @return the index of the letter, -1 if it's not on the rack
	 */
	private int indexOnTheRack(Player player, char letter) {
		// Variables
		boolean isOn = false;
		int i = -1;

		// Search in the rack
		while (!isOn && i < 6) {
			i++;
			if (player.getRack().getTiles()[i].getLetter() == letter)
				isOn = true;
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
	 * @return
	 */
	private int letterCoordtoInt(char letter) {
		// Variable
		letter = Character.toUpperCase(letter);

		return (int) letter - 64;
	}

	/**
	 * 
	 * @param indexLetters
	 * @return true if the player posed his 7 letters
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
	 * look if the button created by the coordonates is clicked
	 * 
	 * @param fg the graphic frame
	 * @param x the abscissa of the square's left-top corner pixel 
	 * @param y the ordinate of the square's left-top corner pixel 
	 * @param width the width of the square
	 * @param height the height of the square
	 * @return the state of the click
	 */
	private boolean isClicked(FenetreGraphique fg, int x, int y, int width, int height) {
		if(fg.getMouseY() > y && fg.getMouseY() < y+height 
		&& fg.getMouseX() > x
		&& fg.getMouseX() < x + width){
			if(fg.getMouseState() == 2){
				return(true);
			}
		}
		return(false);
	}
	/**
	 * Calcul the height of the next text line
	 * 
	 * @param fg the frame to write in
	 * @param n	the size in pixel of the interline
	 * @return the new height of the textline
	 */
	private static int textHeight(FenetreGraphique fg, int n){
		if(Scrabble.textHeight + n > fg.getBufferHeight())
			resetTextHeight();
		else 
			Scrabble.textHeight += n;

		return(Scrabble.textHeight);
	}

	private static void resetTextHeight(){
		Scrabble.textHeight = 20;
	}

	private String enterName(FenetreGraphique fg, int x, int y){
		String playerName = "";
		int height = textHeight(fg, 20);
		do{
			char lastChar = fg.getKey();
			int lastCode = (int)lastChar;
			fg.drawString(5, height, 3, playerName);
			fg.flush();
			if(!(lastCode >= 65 && lastCode <= 90) && !(lastCode >= 97 && lastCode <= 122)){
				lastChar = Character.MIN_VALUE;
			}
			if(lastChar != Character.MIN_VALUE){
				playerName += lastChar;
			}
		}while(!isClicked(fg, x, y, 100, 50));
		fg.wait(200);
		return(playerName);
	}

	static void drawText(FenetreGraphique fg, String msg, int n){
		fg.drawString(5, textHeight(fg, n), 3, msg);
		fg.flush();
	}
	
}