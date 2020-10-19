package model.cell;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class CellTypeTest {


  @Test
  void gameOfLifeTypes() {
    List<String> actualCellTypes = CellType.getGameOfLifeTypes();
    List<String> expectedCellTypes = Arrays.asList("DEAD","ALIVE");
    assertTrue(expectedCellTypes.size() == actualCellTypes.size() &&
        expectedCellTypes.containsAll(actualCellTypes) &&
        expectedCellTypes.containsAll(actualCellTypes));
  }

  @Test
  void percolationTypes() {
    List<String> actualCellTypes = CellType.getPercolationTypes();
    List<String> expectedCellTypes = Arrays.asList("EMPTY_OPEN","FULL_OPEN", "BLOCKED");
    assertTrue(expectedCellTypes.size() == actualCellTypes.size() &&
        expectedCellTypes.containsAll(actualCellTypes) &&
        expectedCellTypes.containsAll(actualCellTypes));
  }

  @Test
  void rockPaperScissorsTypes() {
    List<String> actualCellTypes = CellType.getRockPaperScissorsTypes();
    List<String> expectedCellTypes = Arrays.asList("ROCK","PAPER","SCISSORS");
    assertTrue(expectedCellTypes.size() == actualCellTypes.size() &&
        expectedCellTypes.containsAll(actualCellTypes) &&
        expectedCellTypes.containsAll(actualCellTypes));
  }

  @Test
  void segregationTypes() {
    List<String> actualCellTypes = CellType.getSegregationTypes();
    List<String> expectedCellTypes = Arrays.asList("X","O","NO_RACE");
    assertTrue(expectedCellTypes.size() == actualCellTypes.size() &&
        expectedCellTypes.containsAll(actualCellTypes) &&
        expectedCellTypes.containsAll(actualCellTypes));
  }

  @Test
  void predatorPreyTypes() {
    List<String> actualCellTypes = CellType.getPredatorPreyModelTypes();
    List<String> expectedCellTypes = Arrays.asList("SHARK","FISH","WATER");
    assertTrue(expectedCellTypes.size() == actualCellTypes.size() &&
        expectedCellTypes.containsAll(actualCellTypes) &&
        expectedCellTypes.containsAll(actualCellTypes));
  }

  @Test
  void spreadingOfFireTypes() {
    List<String> actualCellTypes = CellType.getSpreadingOfFireTypes();
    List<String> expectedCellTypes = Arrays.asList("BURNING","TREE","EMPTY");
    assertTrue(expectedCellTypes.size() == actualCellTypes.size() &&
        expectedCellTypes.containsAll(actualCellTypes) &&
        expectedCellTypes.containsAll(actualCellTypes));
  }

}