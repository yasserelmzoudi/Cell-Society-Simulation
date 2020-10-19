package model.grid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.GamePaneShapes.GamePane;
import view.GamePaneShapes.RectangleGamePane;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GamePanelTest {
  private SpreadingOfFireGrid grid;
  ResourceBundle resources = ResourceBundle.getBundle("resources.data");
  InputStream data = Grid.class.getClassLoader()
      .getResourceAsStream(resources.getString("SpreadingOfFireGridTest"));

  @BeforeEach
  public void setup() {
    grid = new SpreadingOfFireGrid(data, "Finite", "Cardinal");
  }

  @Test
  public void checkCellTypeMap() {
    GamePane testPane = new RectangleGamePane(grid, 50, 50);
    //assertTrue(20, grid.gridHeight);
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



}