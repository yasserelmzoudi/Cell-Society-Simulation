package model.grid;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import model.cell.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RockPaperScissorsGridTest {
  private RockPaperScissorsGrid grid;
  ResourceBundle resources = ResourceBundle.getBundle("resources.data");
  InputStream data = Grid.class.getClassLoader()
      .getResourceAsStream(resources.getString("RockPaperScissorsTest"));

  @BeforeEach
  public void setup() {
    grid = new RockPaperScissorsGrid(data, "Finite", "Complete");
  }

  @Test
  public void checkGridHeight() {
    assertEquals(30, grid.gridHeight);
  }

  @Test
  public void checkGridWidth() {
    assertEquals(30, grid.gridHeight);
  }

  @Test
  public void getCellTypes() {
    List<String> expectedCellTypes = Arrays.asList("ROCK","PAPER","SCISSORS");
    List<String> actualCellTypes = grid.getAllTypes();
    assertTrue(expectedCellTypes.size() == actualCellTypes.size() &&
        expectedCellTypes.containsAll(actualCellTypes) &&
        expectedCellTypes.containsAll(actualCellTypes));
  }

  @Test
  public void checkSetGridType() {
    String actualGridType = grid.setGridType();
    assertEquals("ROCK_PAPER_SCISSORS", actualGridType);
  }


}