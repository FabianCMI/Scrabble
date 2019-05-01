Objects model
===================================

## Tiles
### Attributs
char letter
int value
### Constructors
Tiles(char letter, int value)
Tiles(Tiles otherTiles)
Tiles()
### Getters
getLetter()
getValue()
### Methods
setNull()
toString()

## TilesBag
### Attributs
Tiles[100] letters
int remainingLetters = 100
### Constructors
TilesBag()
### Getters
getTiles()
getRemainingLetters()
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
refreshRack()
increaseScore()
toString()

## GameBoard
### Attributs
Tiles[15][15] gameBoardTab
### Constructors
GameBoard()
### Methods
...
toString()