package model.grid;

import java.util.ArrayList;
import java.util.List;
import model.cell.Cell;

/**
 * Class encapsulating logic for initializing a Grid from a given data file. It converts the
 * data file into a 2D grid, obtains the neighboring cells, and updates them with the Game
 * of Life rules.
 */
public class Grid {

  private final Cell[][] gridOfCells;
  private static final String FILE_DELIMITER = ",";
  private final Integer gridWidth;
  private final Integer gridHeight;


  /**
   * Constructor for this class.
   *
   * @param filename File from which to read the initial data.
   */
  public Grid(String filename) {
    List<String> fileLines = GridReader.getAllLinesInFile(filename);
    String[] splitLine = fileLines.get(0).split(FILE_DELIMITER);
    this.gridWidth = Integer.parseInt(splitLine[0]);
    this.gridHeight = Integer.parseInt(splitLine[1]);
    fileLines.remove(0);

    this.gridOfCells = new Cell[gridWidth][gridHeight];

    int row = 0;
    boolean isAlive = false;
    for (String line : fileLines) {
        String[] cellsInRow = line.split(FILE_DELIMITER);
        for (int column = 0; column < gridWidth; column++) {
          int cellValue = Integer.parseInt(cellsInRow[column]);
          if (cellValue == 1) {
            isAlive = true;
          }
          else {
            isAlive = false;
          }
          this.gridOfCells[row][column] = new Cell(row, column, isAlive);
      }
      row++;
    }
  }

  /**
   * The grid is copied into another new grid so that when updating the cells, the original
   * cell values are known.
   *
   * @return copy of the grid
   */
  private Cell[][] copyGrid() {
    Cell[][] copyOfGrid = new Cell[gridWidth][gridHeight];
    for (int row = 0; row < gridWidth; row++) {
      for (int column = 0; column < gridHeight; column++) {
        copyOfGrid[row][column] = new Cell(this.gridOfCells[row][column]);
      }
    }
    return copyOfGrid;
  }

  /**
   * Gets all cells from the grid.
   *
   * @return A copy of the grid.
   */
  public Cell[][] getAllCells() {
    return copyGrid();
  }

  /**
   * Updates the cell in the next step.
   */
  public void performNextStep() {
    Cell[][] grid = copyGrid();
    for (int row = 0; row < gridWidth; row++) {
      for (int column = 0; column < gridHeight; column++) {
        List<Cell> neighbors = getNeighbors(grid, row, column);
        this.gridOfCells[row][column].update(neighbors);
      }
    }
  }

  /**
   * Gets all the neighbors of a cell. This includes all eight cells from columns above and below
   * the given cell, and rows to the left and right of the cell. It does not include the cell.
   *
   * @param grid The grid to get neighboring cells from.
   * @param row Row of cell.
   * @param column Column of cell.
   * @return list of neighbors
   */
  private List<Cell> getNeighbors(Cell[][] grid, int row, int column) {
    List<Cell> listOfCells = new ArrayList<Cell>();
    int minRow = Math.max(0, row-1);
    int maxRow = Math.min(gridWidth-1, row+1);
    int minCol = Math.max(0, column-1);
    int maxCol = Math.max(gridHeight-1, column+1);
    for (int rowIndex = minRow; rowIndex < maxRow; rowIndex++) {
      for (int colIndex = minCol; colIndex < maxCol; colIndex++) {
        if(!((rowIndex == row) && (colIndex == column))) {
          listOfCells.add(grid[rowIndex][colIndex]);
        }
      }
    }
    return listOfCells;
  }


}