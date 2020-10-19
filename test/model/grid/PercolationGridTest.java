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

class PercolationGridTest {
  private PercolationGrid grid;
  ResourceBundle resources = ResourceBundle.getBundle("resources.data");
  InputStream data = Grid.class.getClassLoader()
      .getResourceAsStream(resources.getString("PercolationGridTest"));
  private static final int row = 2;
  private static final int column = 2;

  @BeforeEach
  public void setup() {
    grid = new PercolationGrid(data, "Torodial", "Cardinal");
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
    List<String> expectedCellTypes = Arrays.asList("EMPTY_OPEN","FULL_OPEN","BLOCKED");
    List<String> actualCellTypes = grid.getAllTypes();
    assertTrue(expectedCellTypes.size() == actualCellTypes.size() &&
        expectedCellTypes.containsAll(actualCellTypes) &&
        expectedCellTypes.containsAll(actualCellTypes));
  }

  @Test
  public void checkSetGridType() {
    String actualGridType = grid.setGridType();
    assertEquals("PERCOLATION", actualGridType);
  }

  @Test
  public void checkCardinalNeighbors() {
    List<Cell> neighbors = grid.getEdgeTypeTorodial(grid.getAllCells(),row,column);
    List<Cell> newNeighbors = grid.getEdgeTypeTorodial(grid.getAllCells(),row,column);
    grid.setNeighborCardinal(neighbors,newNeighbors,row,column);
    assertEquals(4,neighbors.size());
    }
  }




