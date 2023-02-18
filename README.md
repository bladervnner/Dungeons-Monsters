# Description
#### Program that allows the user to explore a 5x5 map representing a dungeon maze. User can control the direction they want to go within the map, the user may encounter monsters along the way. The user can find health potions, as well as keys that can be used to progress to the next map. 

## Class descriptions
### Entity - abstract - descrives a character within the game
#### an Entity has a name, some hit points
#### heal method resets hp to the meaxHp value
#### takeDamage method decreases the Entity's hp by the amount passed in. 
#### toString method displays the name and hp over maxHp

### Hero - describes the user's character
#### the Hero is constructed with a name, begins map at level 1 at the start position denoted by 's', is given default values for hp, gold, potions, and keys
#### direction methods update the Hero's location, reveal that location, get character at that location, and return it 
#### levelUp method increments the hero's level and loads the next map
#### attack method calls the selected ability method. Each does different amout of damage to the enemy within a random range and returns a string representing that damage. 
#### toString displays the name, hp, level, gold, potions, keys, and map. 

### Map represents the dungeon maze. Map is Singleton
#### map has a 5x5 set of characters representing the types of rooms in the maze, and a 5x5 set of booleans thar allow you to determine if that room has been visited yet
#### loadMap reads in the map from the file and stores it in the character array. 
#### mapToString returns a string of the mapwith the Hero's current position, revealed rooms, and any unrevealed rooms represents by 'x's

### Enemy - abstract - represents an enemy the Hero will encounter
#### Abstract attack method will be overridden by the different types of enemies. 

### Warrior/Wizard/Ranger - different types of enemies
#### attack method randomly selects one of the enemy's abilities to attack the Hero with. Each of the ability methods for each enemy type should do random damage to the Hero and return a string representing that damage.

### Fighter/Magical/Archer - interfaces that define the abilities of the Hero and the enemies. 

### EnemyGenerator - factory to create random enemies to encounter on the map. 
#### constructor reads the file and adds the different enemies and their base hp to the HashMap
#### generateEnemy method randomly selects an enemy from the map, then randomly selects an ability type (Fighter/Magical/Archer), then copies over the name and base hp to construct a new enemy of that type. Uses the level value passed in to modify the base hp so that the Hero and Enemy levels increase as the user progresses to the next map

### Main
#### prompts user to enrer name, constructs a Hero with name
#### displays Hero with the map and has the user choose a direction
#### gets the resulting character from the hero's direction method
##### x - location was out of bounds
##### n - nothing here
##### s - start - item store where the Hero can buy potions or keys
##### f - finish - if the Hero has a key, then increase the Hero's level and load to the next map
##### i - item - the Hero randomly finds a potion or a key 
##### m - monster - creates an enemy to fight then calls monsterRoom

#### monsterRooom displays the enemy and then repeatedly prompts the user to fight, run away, or drink potion if Hero has one. If they choose to fight, calls fight method. If they run away, then choose a random direction to move the Hero. Returns true if Hero is alive after attack

#### fight does a single round of damage by allowing the user to choose to do a phisical, magical, or ranged attack. Then, depending on their selection, displays the corresponding submenu. Those two selections are passed to the Hero's attack method to attack the enemy. The enemy then attacks back if still alive

#### repeats until user quits or Hero dies



