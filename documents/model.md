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
getLetter()
getValue()
### Methods
toString()

## TilesBag
### Attributs
Tiles[100] letters
int lettersLength = 100
### Constructors
TilesBag()
### Getters
getTiles()
getLettersLength()
### Methods
drawLetter()

## Rack
### Attributs
Tiles[7] rack
TilesBag bag
### Constructors
Rack()
### Getters
getRack()
### Methods
toString()

## Player
### Attributs
String name
int score
Rack rack
### Constructors
Player(String name)
Player()
### Getters
getName()
getScore()
getRack()
### Methods
refreshRack(int... indexLetters)
increaseScore(int value)
toString()

## GameBoard
### Attributs
Tiles[15][15] gameBoardTab
### Constructors
GameBoard()
### Methods
...
toString()

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