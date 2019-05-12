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
    private int x;
    /** the ordinate of the square */
    private int y;

    // ---------- Constructors ----------

    /**
     * Square constructor
     * 
     * @param tile               The tile on the square
     * @param scoreMultiplicator The score multiplicator of the square (values 1, 2
     *                           or 3)
     * @param typeScoreMult      The score multiplicator's type (false for letter,
     *                           true for entire word)
     */
    Square(Tiles tile, int scoreMultiplicator, boolean typeScoreMult, int x, int y) {
        this.tile = tile;
        this.scoreMultiplicator = scoreMultiplicator;
        this.typeScoreMult = typeScoreMult;
        this.x = x;
        this.y = y;
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
     * Get the abscissa of the square
     * 
     * @return The abscissa
     */
    public int getAbscissa() {
        return (this.x);
    }

    /**
     * Get the ordinate of the square
     * 
     * @return The ordinate
     */
    public int getOrdinate() {
        return (this.y);
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
     * Calcul the score of the square
     * 
     * @return The score of the square
     */
    public int tileScoreCalcul() {
        if (this.typeScoreMult) {
            return (this.tile.getValue());
        }
        return (this.tile.getValue() * this.scoreMultiplicator);
    }

    public String toString() {
        if (this.typeScoreMult) {
            return (this.tile + "\nMultiplicateur : " + this.scoreMultiplicator + "\n Type : mot\nx:" + this.x + "\ny:"
                    + this.y);
        }
        return (this.tile + "\nMultiplicateur : " + this.scoreMultiplicator + "\nType : lettre\nx:" + this.x + "\ny:"
                + this.y);
    }
}