package model.grid;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import model.cell.Cell;
import model.cell.RockPaperScissorsCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RockPaperScissorsGridTest {
  private RockPaperScissorsGrid grid;
  ResourceBundle resources = ResourceBundle.getBundle("resources.data");
  InputStream data = Grid.class.getClassLoader()
      .getResourceAsStream(resources.getString("RockPaperScissorsTest"));
  private static final int row = 2;
  private static final int column = 2;

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

  @Test
  public void checkCompleteNeighbors() {
    List<Cell> neighbors = grid.getEdgeTypeFinite(grid.getAllCells(),row,column);
    List<Cell> newNeighbors = grid.getEdgeTypeFinite(grid.getAllCells(),row,column);
    grid.setNeighborComplete(neighbors,newNeighbors,row,column);
    assertEquals(8,neighbors.size());
  }

  @Test
  public void completeRandomizeGrid() {
    Cell[][] cells = grid.getAllCells();
    Cell[][] copiedGrid = new Cell[cells.length][cells[0].length];
    for (int row = 0; row<cells.length;row++) {
      for (int col = 0; col<cells[0].length;col++) {
        copiedGrid[row][col] = new RockPaperScissorsCell(cells[row][col]);
      }
    }
    grid.completeRandomizeGrid();
    assertNotEquals(copiedGrid,cells);
  }

  @Test
  public void swapRandomizeGrid() {
    Cell[][] cells = grid.getAllCells();
    Cell[][] copiedGrid = new Cell[cells.length][cells[0].length];
    for (int row = 0; row<cells.length;row++) {
      for (int col = 0; col<cells[0].length;col++) {
        copiedGrid[row][col] = new RockPaperScissorsCell(cells[row][col]);
      }
    }
    grid.swapRandomizeGrid();
    assertNotEquals(copiedGrid,cells);
  }


}