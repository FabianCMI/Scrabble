import javax.swing.JOptionPane;

/**
 * Scrabble (APO Project - CMI L1) Scrabble.java - Main file
 * 
 * @author Fabian Devel, Valentin Perignon
 */

public class Scrabble {

	public static void main(String[] args) {
		// Variables
		Game game = new Game();
		
		// game execution
		System.out.println(game.getGameBoard());
		int firstPlayer = (int)(Math.random()*game.nbPlayer);		
		Ecran.afficherln(game.getPlayer()[firstPlayer].getName() + " commence !\nVoici votre chevalet : " + game.getPlayer()[firstPlayer].getRack());
		game.playerAction(game.getPlayer()[firstPlayer]);
		System.out.println(game.getGameBoard());
	}
}