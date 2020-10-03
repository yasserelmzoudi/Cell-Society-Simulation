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
    private String myType;
    public RockPaperScissorsObject(String rpsType) {
    myType = rpsType;
}
    public abstract Player topplayer(List<Players> allplayers);
     public abstract boolean compare(RockPaperScissorsObject rps);
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
    private String myType;
    private String myName;
    private String myScore = 0;
    private RockPaperScissorsObject currentRDS;
    public Player(String name, String type){
    myName = name;
    myType = type;
    }
    public RockPaperScissorsObject selectRockPaperScissorsObject(String selectedRockPaperScissorsObject);
    public void resetScore(){
    myScore = 0;
    }
    public void increaseScore() {
    myScore++;
    }
    public String getName() {
    return myName; }
    public int getScore(){
    return myScore; }
    public RockPaperScissorsObject selectRockPaperScissorsObject(String rpstype){
        currentRPS = new RockPaperScissorsObject(rdstype);
        return currentRPS;
    }
    public RockPaperScissors currentRockPaperScissorsObject() {
        return currentRPS;}  
    }
```

This class's purpose is to represent `SimulatedPlayer`s:
```java
 public abstract class SimulatedPlayer extends Player {
private static final String MYTYPE = "SIMULATED";
    public SimulatedPlayer() {
    super(getRandomName(), MYTYPE);
}
    public String getRandomName(){
} 
}
```

This class's purpose is to represent `ActualPlayer`s:
```java
 public abstract class ActualPlayer extends Player {
private static final String MYTYPE = "REAL"
public ActualPlayer(String myName) {
    super(myName, MYTYPE);
}
}
```

### Use Cases

 * A new game is started with five players, their scores are reset to 0.
 ```java
public class Gamecontroller{
    private List<Player> allplayers = new ArrayList<>();
    List<Player> roundwineers = new ArrayList<>();
    public GameController() {
setupPlayers();
checkRelationships();
    }

    public void setupPlayers() {
        int numberactualplayers = 0;
        int numbersimplayers = 0;
        System.out.println("please enter the number of actual players");
        String input = keyboard.nextLine();
        if(isnumber(input)) {
        numberactualplayers = converttoint(input);
        }
        System.out.println("please enter the number of simulated players");
        input = keyboard.nextLine();
        if(isnumber(input)) {
        numbersimplayers = converttoint(input);
        }
        for (int i = 0; i < numberactualplayers; i++) {
        System.out.println("please enter a player's name");
        String playername = keyboard.nextLine();
        Player player = new ActualPlayer(playername);
        allplayers.add(player);
        }
        for (int i = 0; i < numbersimplayers; i++) {
                Player player = new SimulatedPlayer();
                allplayers.add(player);
        }
}

}
 ```

 * A player chooses his RPS "weapon" with which he wants to play for this round.
 ```java
for(Player eachplayer: allplayers) {
if(playersturn(eachplayer)){
if(eachplayer instanceof SimulatedPlayer) {
eachplayer.selectRockPapersScissorsObject("")}
}
else {
System.out.println("Enter a weapon: R for Rock, S for scissors, or P for Paper");
String weapontype = keyboard.nextLine();
eachplayer.selectRockPaperScissorsObject(weapontype);
}
}

 ```

 * Given three players' choices, one player wins the round, and their scores are updated.
 ```java

private void checkRelationships() {
roundwinners = new ArrayList<>();
for Player eachplayer: allplayers) {
    if(eachplayer.currentRockPaperScissorsObject.compare(allplayers) >= 0) {
    eachplayer.increaseScore();
    roundwinners.add(eachplayer);
    }
}}




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
        
- Potential Classes:
    - `Cell` abstract class
    - `Grid` class
    - Enum to generate different types of Cells
    - `FileReader` class
    - `FileWriter` class
    - `NeighboringCells` interface
    - `Display` class
    - `Controller` class
    
        
### CRC Card Classes

This class's purpose or value is to define the different possible types of cells:
```java
public abstract class Cell implements NeighboringCells {
    public void update();
    public String getState();
    public void setState(String state);
    public void createCell(Enum cellType);
}
```

This interface contains the necessary methods for retrieving and updating neighboring cells:
```java
public interface NeighboringCells {
    public List<Cell> getNeighbors();
    public void updateNeighbors();

}
```

This class's purpose or value is to define and populate the layout of Cells:
```java
public class Grid {
    public void setUp(String fileName);
    public List<Cell> getCells();
    public void updateDisplay();
}
```

This class's purpose or value is to read in the layout of the Grid:
```java
public class FileReader {
    public void readFile(String fileName);
}
```

This class's purpose or value is to write the final state of a Grid to a file:
```java
public class FileWriter {
    public void writeFile(Grid cells);
}
```

This class's purpose or value is to Display the Model to users:
```java
public class Display {
    public Scene makeScene(int width, int height);
    public void updateDisplay(Scene model);
}
```

This class's purpose or value is to interact between the Model and the Display:
```java
public class Controller {
    public void showModel(Grid cells);
    public void showError (String message);
}
```

This Enum's purpose is to represent the different types of Cells:
```java
public Enum cellType {
    public Cell createCell(int row, int column);
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