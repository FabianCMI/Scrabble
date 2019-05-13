/**
 * Scrabble (APO Project - CMI L1) GameBoard.java - Represents the squares of
 * the gameboard's grid
 * 
 * @author Fabian Devel, Valentin Perignon
 */

public class Square {
    // ---------- Attributs ----------

    /** The tile on the square */
    private Tiles tile;
    /** The score multiplicator of the square */
    private int scoreMultiplicator;
    /** The score multiplicator's type */
    private boolean typeScoreMult;
    /** the abscissa of the square */
    private int line;
    /** the ordinate of the square */
    private int column;
     

    // ---------- Constructors ----------

    /**
	 * Square constructor
	 * 
	 * @param tile The tile on the square
	 * @param scoreMultiplicator  The score multiplicator of the square (values 1, 2 or 3)
     * @param typeScoreMult The score multiplicator's type (false for letter, true for entire word)
	 */
	Square(Tiles tile, int scoreMultiplicator, boolean typeScoreMult, int line, int column) {
        this.tile = tile;
        this.scoreMultiplicator = scoreMultiplicator;
        this.typeScoreMult = typeScoreMult;
        this.line = line;
        this.column = column;
    }

    // ---------- Methods ----------

    /**
     * Get the tile
     * 
     * @return The tile on the square
     */
    public Tiles getTile() {
        return (this.tile);
    }

    /**
     * Get the score multiplicator
     * 
     * @return The value of the multiplicator
     */
    public int getScoreMult() {
        return (this.scoreMultiplicator);
    }

    /**
     * Get the type of the multiplicator
     * 
     * @return The type of the multiplicator
     */
    public boolean getTypeScoreMult() {
        return (this.typeScoreMult);
    }

    /**
	 * Get the line's numero of the square 
	 * 
	 * @return The numero of the line 
	 */
    public int getLine(){
        return(this.line);
    }

    /**
	 * Get the column's numero of the square 
	 * 
	 * @return The numero of the column
	 */
    public int getColumn(){
        return(this.column);
    }

    /**
     * Set the tile
     * 
     * @param tile The tile to put on the square
     */
    public void setTile(Tiles tile) {
        this.tile = tile;
    }

    /**
     * Set the line on which is placed the square
     * 
     * @param n the number by which you want to increase the line's numero
     */
    public void setLine(int n){
        this.line += n;
    }

    /**
     * Set the column on which is placed the square
     * 
     * @param n the number by which you want to increase the column's numero
     */
    public void setColumn(int n){
        this.column += n;
    }

    /**
	 * Calcul the score of the square 
	 * 
	 * @return The score of the square
	 */
    public int tileScoreCalcul(){
        if(this.typeScoreMult){
            return(this.tile.getValue());
        }
        return (this.tile.getValue() * this.scoreMultiplicator);
    }

    public String toString(){
        if(this.typeScoreMult){
            return(this.tile + "\nMultiplicateur : " + this.scoreMultiplicator + 
            "\n Type : mot\nx:" + this.line + "\ny:" + this.column);
        }
        return(this.tile + "\nMultiplicateur : " + this.scoreMultiplicator + 
        "\nType : lettre\nx:" + this.line + "\ny:" + this.column);
    }
}