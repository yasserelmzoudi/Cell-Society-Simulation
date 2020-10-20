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

class SegregationGridTest {
  private SegregationGrid grid;
  ResourceBundle resources = ResourceBundle.getBundle("resources.data");
  InputStream data = Grid.class.getClassLoader()
      .getResourceAsStream(resources.getString("SegregationGridTest"));

  @BeforeEach
  public void setup() {
    grid = new SegregationGrid(data, "Finite", "Complete");
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
    List<String> expectedCellTypes = Arrays.asList("X","O","NO_RACE");
    List<String> actualCellTypes = grid.getAllTypes();
    assertTrue(expectedCellTypes.size() == actualCellTypes.size() &&
        expectedCellTypes.containsAll(actualCellTypes) &&
        expectedCellTypes.containsAll(actualCellTypes));
  }

  @Test
  public void checkGetGridType() {
    String actualGridType = grid.getGridType();
    assertEquals("Segregation", actualGridType);
  }

}