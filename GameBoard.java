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
		int multiplicator;
		boolean typeBonus;
		int lastN = this.grid.length-1;
		int midN = lastN/2;
		for (int i = 0; i <= lastN; i++) {
			for (int j = 0; j <= lastN; j++) {
				multiplicator = 1;
				typeBonus = false;
				/* ----Fill the square with the score multiplicator---- */
					// word x 3
				if(i == 0 || i == midN || i == lastN){
					if(j == 0 || j == midN || j == lastN){
						multiplicator = 3;
						typeBonus = true;
					}
				}
					// word x 2
				if(i >= 1 && i < lastN){
					if(j == i || j == lastN-i){
						multiplicator = 2;
						typeBonus = true;
					}
				}
					// letter x 2
				if(testLetterMult(i, j, 0, 3, lastN) //case 1, 8
				|| testLetterMult(i, j, 3, 0, lastN) //case 3, 6
				|| testLetterMult(i, j, 2, midN-1, lastN) // case 2, 7
				|| testLetterMult(i, j, midN-1, 2, lastN) // case 4a, 5a
				|| testLetterMult(i, j, midN-1, midN-1, lastN) // case 4b, 5b
				){
					multiplicator = 2;
					typeBonus = false;
				}	
					// letter x 3
				if(testLetterMult(i, j, 1, midN-2, lastN)
				|| testLetterMult(i, j, midN-2, 1, lastN)
				|| testLetterMult(i, j, midN-2, midN-2, lastN)
				){
					multiplicator = 3;
					typeBonus = false;
				}
				
				this.grid[i][j] = new Square(new Tiles(), multiplicator, typeBonus, i, j);
			}
		}
	}

	// ---------- Methods -----------

	/**
	 * test 2 columns(symetrics from the centered column) of the grid to 
	 * know if there are letter's score multiplicator on 2 of their squares
	 * 
	 * @param x the abscissa of the grid's square
	 * @param y the ordinate of the grid's square
	 * @param m the number to add or substract from the lines to obtain
	 * 	the square's abscissa
	 * @param n the number to add or substract from the columns to obtain 
	 * 	the square's ordinate
	 * @param lastN the last column or line of the grid 
	 * @return true if the coordinate given by the parameters correspond 
	 * 	to a score multiplicator's square
	 */
	private boolean testLetterMult(int i, int j, int m, int n, int lastN){
		boolean testOk = false;
		if(i == m || i == lastN-m){
			if(j == n || j == lastN-n){
				testOk = true;
			}
		}
		return(testOk);
	}

	/**
	 * Calcul the score of words 
	 * 
	 * @param firstLetter The square where the first letter of the word is
	 * @param wordLength The number of letter in the word
	 * @param horizontal The orientation of the word on the grid (true : horizontal, false : vertical)
	 * @param scrabble True if the player made a 'scrabble' (7 letters posed in once)
	 * @return The score of the word
	 */
    public int wordScoreCalcul(Square firstLetter, int wordLength, boolean horizontal, boolean scrabble){
		int score = 0;
		int tempMult = 0;
		int abs = firstLetter.getAbscissa();
		int ord = firstLetter.getOrdinate();
		int newAbs, newOrd;
		for(int i = 0; i < wordLength; i++){
			newAbs = abs+i;
			newOrd = ord+i;
    	if(horizontal){
				if(this.grid[abs][newOrd].getTypeScoreMult()){
					score += this.grid[abs][newOrd].tileScoreCalcul();
					tempMult = tempMult + this.grid[abs][newOrd].getScoreMult();
				} else {
					score += this.grid[abs][newOrd].tileScoreCalcul();
				}
      } else {
				if(this.grid[newAbs][ord].getTypeScoreMult()){
					score += this.grid[newAbs][ord].tileScoreCalcul();
					tempMult = tempMult * this.grid[newAbs][ord].getScoreMult();
				} else{
					score += this.grid[newAbs][ord].tileScoreCalcul();
				}
			}
		}
		score = score*tempMult;
		if(scrabble){
			score += 50;
		}
    return(score);
	}
	
	/**
	 * Get the grid
	 */
	public Square[][] getGrid(){
		return this.grid;
	}

	public String toString(){
		String str = "";
		for (int i = -1; i <= this.grid.length; i++) {
			for (int j = -1; j <= this.grid.length; j++) {
				switch (i) {
					case -1:
					if (j<this.grid.length)
						str += "~ ~ ";
					else 
						str += "\n";
					break;
					
					case 15:
						if (j<this.grid.length)
							str += "~ ~ ";
						else 
							str += "\n";
						break;

					default:
						switch (j) {
							case -1:
								str+="||";
								break;
							
							case 15:
								str += "|\n";
								break;
		
							default:
								if(this.grid[i][j].getTile().getLetter() == Character.MIN_VALUE){
									if(this.grid[i][j].getScoreMult()>1)
										str += " * |";
									else
										str += " . |";
								} else	
								str += " " + this.grid[i][j].getTile().getLetter() + " |";
								break;
						}
						break;
				}
			}
		}
		return(str);
	}
}