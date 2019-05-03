/**
 * Scrabble (APO Project - CMI L1) GameBoard.java - Represents the scrabble game
 * board
 * 
 * @author Fabian Devel, Valentin Perignon
 */

public class GameBoard {

	// ---------- Attributs ----------

	/** Tab game board */
	Square[][] grid = new Square[15][15];

	// ---------- Constructors ----------

	/**
	 * gameboard's constructor
	 */
	GameBoard() {

	}

	// ---------- Methods ----------

	/**
	 * Calcul the score of words 
	 * 
	 * @param firstLetter The square where the first letter of the word is
	 * @param wordLength The number of letter in the word
	 * @return The score of the word
	 */
    public int wordScoreCalcul(Square firstLetter, int wordLength){
        if(this.typeScoreMult){
			//for
            this.grid[firstLetter.x][firstLetter.y].tileScoreCalcul();
        }
        return(this.tile.getValue()*this.scoreMultiplicator);
    }

}