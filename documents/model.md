Objects model
===================================

## Player
### Attributs
String name
int score
Rack rack
### Constructors
Player(String name)
### Methods
getName()
getScore()
increaseScore()
toString()

## Tiles
### Attributs
char letter
int value
### Constructors
Tiles(char letter, int value)
Tiles(Tiles otherTiles)
### Methods
getLetter()
getValue()
toString()

## TilesBag
### Attributs
Tiles[100] letters
int remainingLetters = 100
### Constructors
TilesBag()
### Methods
getRemainingLetters()
drawLetter()

## Rack
### Attributs
Tiles[7] rack
### Constructors
Rack()
### Methods
getRack()
toString()

## GameBoard
### Attributs
Tiles[15][15] gameBoardTab
### Constructors
GameBoard()
### Methods
...