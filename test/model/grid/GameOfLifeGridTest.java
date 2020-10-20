package model.grid;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import model.cell.Cell;
import model.exceptions.InvalidCSVFileException;
import model.exceptions.InvalidSimulationSettingsFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameOfLifeGridTest {
  private GameOfLifeGrid grid;
  ResourceBundle resources = ResourceBundle.getBundle("resources.data");
  InputStream data = Grid.class.getClassLoader()
      .getResourceAsStream(resources.getString("TestSource"));
  InputStream invalidData = Grid.class.getClassLoader()
      .getResourceAsStream(resources.getString("InvalidCSVTest"));
  private static final int row = 2;
  private static final int column = 2;

  @BeforeEach
  public void setup() {
    grid = new GameOfLifeGrid(data, "KleinBottle", "Diagonal");
  }

  @Test
  public void invalidGrid() {
    assertThrows(InvalidCSVFileException.class, () -> new GameOfLifeGrid(invalidData, "KleinBottle", "Diagonal"));
  }

  @Test
  public void checkGridHeight() {
    assertEquals(5, grid.gridHeight);
  }

  @Test
  public void checkGridWidth() {
    assertEquals(5, grid.gridHeight);
  }

  @Test
  public void getCellTypes() {
    List<String> expectedCellTypes = Arrays.asList("ALIVE", "DEAD");
    List<String> actualCellTypes = grid.getAllTypes();
    assertTrue(expectedCellTypes.size() == actualCellTypes.size() &&
        expectedCellTypes.containsAll(actualCellTypes) &&
        expectedCellTypes.containsAll(actualCellTypes));
  }

  @Test
  public void checkSetGridType() {
    String actualGridType = grid.setGridType();
    assertEquals("GAME_OF_LIFE", actualGridType);
  }

  @Test
  public void checkDiagonalNeighbors() {
    List<Cell> neighbors = grid.getEdgeTypeKleinBottle(grid.getAllCells(),row,column);
    List<Cell> newNeighbors = grid.getEdgeTypeKleinBottle(grid.getAllCells(),row,column);
    grid.setNeighborDiagonal(neighbors,newNeighbors,row,column);
    assertEquals(4,neighbors.size());
  }


}