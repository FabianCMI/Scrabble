Objects model
===================================

## Tiles
### Attributs
char letter
int value
### Constructors
Tiles(char letter, int value)
Tiles()
### Getters
char getLetter()
int getValue()
### Methods
void setLetter(char letter)
String toString()

## TilesBag
### Attributs
Tiles[100] letters
int lettersLength = 100
### Constructors
TilesBag()
### Getters
Tiles[] getTiles()
int getLettersLength()
### Methods
Tiles drawLetter()

## Rack
### Attributs
Tiles[7] rack
TilesBag bag
### Constructors
Rack()
### Getters
Tiles[] getTiles()
### Methods
int getNullTiles()
void refreshRack(int[] indexLetter)
String toString()

## Player
### Attributs
String name
int score
Rack rack
### Constructors
Player(String name)
Player()
### Getters
String getName()
int getScore()
Rack getRack()
### Methods
boolean isRackNull()
void increaseScore(int value)
String toString()

## GameBoard
### Attributs
Square[15][15] grid
### Constructors
GameBoard()
### Getters
Square[][] getGrid()
### Methods
boolean testLetterMult(int i, int j, int m, int n, int lastN)
int wordScoreCalcul(Square firstLetter, int wordLength, boolean horizontal, boolean scrabble)
toString()

## Square
### Attributs
Tiles tile
int scoreMultiplicator
boolean typeScoreMult
int x
int y
### Constructors
Square(Tiles tile, int scoreMultiplicator, boolean typeScoreMult, int x, int y)
### Getters
Tiles getTile()
int getScoreMult()
boolean getTypeScoreMult()
int getAbscissa()
int getOrdinate()
### Methods
void setTile(Tiles tile)
int tileScoreCalcul()
String toString()

## Game
### Attributs
Player player1
Player player2
Gameboard gameboard
### Constructors
Game()
### Getters
getPlayer1()
getPlayer2()
getGameBoard()
### Methods
initGame()