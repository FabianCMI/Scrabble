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
### Methods
getLetter()
getValue()
toString()

## TilesBag
### Attributs
Tiles[100] letters
### Constructors
TilesBag()
### Methods
getNumberRemainingLetters()
drawLetter()
toString()

## Rack
### Attributs
Tiles[7] letters
### Constructors
Rack()
### Methods
...
toString()