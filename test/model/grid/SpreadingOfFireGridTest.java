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

class SpreadingOfFireGridTest {
  private SpreadingOfFireGrid grid;
  ResourceBundle resources = ResourceBundle.getBundle("resources.data");
  InputStream data = Grid.class.getClassLoader()
      .getResourceAsStream(resources.getString("SpreadingOfFireGridTest"));

  @BeforeEach
  public void setup() {
    grid = new SpreadingOfFireGrid(data, "Finite", "Cardinal");
  }

  @Test
  public void checkGridHeight() {
    assertEquals(20, grid.gridHeight);
  }

  @Test
  public void checkGridWidth() {
    assertEquals(20, grid.gridHeight);
  }

  @Test
  public void getCellTypes() {
    List<String> expectedCellTypes = Arrays.asList("TREE","BURNING","EMPTY");
    List<String> actualCellTypes = grid.getAllTypes();
    assertTrue(expectedCellTypes.size() == actualCellTypes.size() &&
        expectedCellTypes.containsAll(actualCellTypes) &&
        expectedCellTypes.containsAll(actualCellTypes));
  }


  @Test
  public void checkGetGridType() {
    String actualGridType = grid.getGridType();
    assertEquals("SpreadingOfFire", actualGridType);
  }

}