# Simulation Lab Discussion
## Names and NetIDs
- Yasser Elmzoudi (ye9)
- Fernanda Rizo (fec6)
- Umika Paul (usp3)


## Rock Paper Scissors

### High Level Design Ideas
- `RockPaperScissorsObject` abstract class
    - Represents any possible relationship
    - `compare(RockPaperScissorsObject rps)`
        - Compare the given Object to the current Object in order to determine the victor
            - Returns 1 for win, -1 for loss, and 0 for tie
- `Result` class
    - Keeps track of total score for each player
- `Player` abstract class
    - `SimulatedPlayer` class
        - Generates random `RockPaperScissorsObject` per given turn
    - `ActualPlayer` class
        - Reads in `RockPaperScissorsObject` from given data file
- `GameController` class
    - Dictates the setup of the game (to how many rounds)
    - Controls scenes 

### CRC Card Classes

This class's purpose is to represent all possible `RockPaperScissorsObject`s:
```java
 public abstract class RockPaperScissorsObject {
     public abstract boolean compare (RockPaperScissorsObject rps);
 }
```

This class's purpose is to represent the result for the given `Player`s:
```java
 public class Result {
     public int getOverallScore();
     public void setScore(int score);
 }
```
This class's purpose is to represent all possible `Player`s:
```java
 public abstract class Player {
      public void resetScore();
      public String getName();
      public int getScore();
      public abstract RockPaperScissorsObject selectRockPaperScissorsObject(String possibleRockPaperScissorsObjects);
}
```

This class's purpose is to represent `SimulatedPlayer`s:
```java
 public abstract class SimulatedPlayer extends Player {
      public RockPaperScissorsObject selectRockPaperScissorsObject(String selectedRockPaperScissorsObject);
      public String getRandomRockPaperScissorsObject(List<String> possibleRockPaperScissorsObject);
}
```

This class's purpose is to represent `ActualPlayer`s:
```java
 public abstract class ActualPlayer extends Player {
      public RockPaperScissorsObject selectRockPaperScissorsObject(String selectedRockPaperScissorsObject);
}
```

### Use Cases

### Use Cases

 * A new game is started with five players, their scores are reset to 0.
 ```java
List<Player> players = new ArrayList<>();
for (int i = 0; i < NUM_PLAYERS; i++) {
Player player = new ActualPlayer();
players.add(player);
}
 ```

 * A player chooses his RPS "weapon" with which he wants to play for this round.
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

 * Given three players' choices, one player wins the round, and their scores are updated.
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

 * A new choice is added to an existing game and its relationship to all the other choices is updated.
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

 * A new game is added to the system, with its own relationships for its all its "weapons".
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```


## Cell Society

### High Level Design Ideas
- Commonalities between models:
    - Dynamic cells that change based upon certain conditions
    - Element of replication (creating same Objects multiple times and populating respective cells accordingly)
    - Characteristics of cells depend on neighboring cells and the Objects therein
    - Potential method to determine if Update is necessary for current cell and for neighboring cells
    - initialize method could creat Grid and populate all of the cells accordingly
    - Elements in a potential configuration file:
        - Size of Grid
        - Type of simulation 


### CRC Card Classes

This class's purpose or value is to manage something:
```java
public class Something {
    public int getTotal (Collection<Integer> data)
    public Value getValue ()
}
```

This class's purpose or value is to be useful:
```java
public class Value {
    public void update (int data)
}
```

### Use Cases

* Apply the rules to a cell: set the next state of a cell to dead by counting its number of neighbors using the Game of Life rules for a cell in the middle (i.e., with all of its neighbors)
```java
Something thing = new Something();
Value v = thing.getValue();
v.update(13);
```

* Move to the next generation: update all cells in a simulation from their current state to their next state
```java
Something thing = new Something();
Value v = thing.getValue();
v.update(13);
```

* Switch simulations: load a new simulation from a data file, replacing the current running simulation with the newly loaded one
```java
Something thing = new Something();
Value v = thing.getValue();
v.update(13);
```