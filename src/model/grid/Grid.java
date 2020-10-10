package model.grid;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import model.cell.Cell;
import model.cell.CellType;
import model.cell.GameOfLifeCell;

/**
 * Class encapsulating logic for initializing a Grid from a given data file. It converts the data
 * file into a 2D grid, obtains the neighboring cells, and updates them with the Game of Life
 * rules.
 */
public class Grid {

  private static final String FILE_DELIMITER = ",";
  private static final int HEADER_ROW = 0;
  private static final int NUM_ROWS_INDEX = 0;
  private static final int NUM_COLUMNS_INDEX = 1;
  private final Cell[][] gridOfCells;
  private final Integer gridWidth;
  private final Integer gridHeight;

  private GridReader gridReader;


  /**
   * Constructor for this class.
   *
   * @param gridReader GridReader that reads CSV file to initialize Grid
   */
  public Grid(GridReader gridReader) {
    this.gridReader = gridReader;
    List<String[]> readLines = gridReader.readAll();
    gridWidth = Integer.valueOf(readLines.get(HEADER_ROW)[NUM_COLUMNS_INDEX]);
    gridHeight = Integer.valueOf(readLines.get(HEADER_ROW)[NUM_ROWS_INDEX]);
    readLines.remove(0);
    gridOfCells = new Cell[gridHeight][gridWidth];


    int row = 0;
    CellType state;
    for (String[] cellsInRow : readLines) {
      for (int column = 0; column < gridWidth; column++) {
        int cellValue = Integer.parseInt(cellsInRow[column]);
        if (cellValue == 1) {
          state = CellType.ALIVE;
        } else {
          state = CellType.DEAD;
        }
        gridOfCells[row][column] = new GameOfLifeCell(row, column, state.ordinal());
      }
      row++;
    }
  }

  /**
   * The grid is copied into another new grid so that when updating the cells, the original cell
   * values are known.
   *
   * @return copy of the grid
   */
  public Cell[][] copyGrid() {
    Cell[][] copyOfGrid = new Cell[gridHeight][gridWidth];
    for (int row = 0; row < gridHeight; row++) {
      for (int column = 0; column < gridWidth; column++) {
        copyOfGrid[row][column] = new GameOfLifeCell(this.gridOfCells[row][column]);
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
    for (int row = 0; row < gridHeight; row++) {
      for (int column = 0; column < gridWidth; column++) {
        List<Cell> neighbors = getNeighbors(grid, row, column);
        this.gridOfCells[row][column].update(neighbors);
      }
    }
  }

  /**
   * Gets all the neighbors of a cell. This includes all eight cells from columns above and below
   * the given cell, and rows to the left and right of the cell. It does not include the cell.
   *
   * @param grid   The grid to get neighboring cells from.
   * @param row    Row of cell.
   * @param column Column of cell.
   * @return list of neighbors
   */
  public List<Cell> getNeighbors(Cell[][] grid, int row, int column) {
    List<Cell> listOfCells = new ArrayList<>();
    int minRow = Math.max(0, row - 1);
    int maxRow = Math.min(gridHeight - 1, row + 1);
    int minCol = Math.max(0, column - 1);
    int maxCol = Math.min(gridWidth - 1, column + 1);
    for (int rowIndex = minRow; rowIndex <= maxRow; rowIndex++) {
      for (int colIndex = minCol; colIndex <= maxCol; colIndex++) {
        if (!((rowIndex == row) && (colIndex == column))) {
          listOfCells.add(grid[rowIndex][colIndex]);
        }
      }
    }
    return listOfCells;
  }

  public int gridColumns() {
    return gridOfCells[0].length;
  }

  public int gridRows() {
    return gridOfCells.length;
  }

  public double cellWidth(int framewidth) {
    double cellWidth = framewidth/ gridColumns() ;
    return cellWidth;
  }

  public double cellHeight(int frameheight) {
    double cellHeight = frameheight / gridRows();
    return cellHeight;
  }

  public Cell getCell(int row, int column) {
    return gridOfCells[row][column];
  }

  /*public void gridlayout(Grid grid) {
    Cell[][] newGrid = grid.getAllCells();
    int numRows =  newGrid.length;
    int numColumns = newGrid[0].length;
    for (int row = 0; row < numRows; row++) {
      for (int column = 0; column < numColumns; column++) {
        System.out.print(newGrid[row][column].isAlive()+ " ");
      }
      System.out.println("");
    }
  }*/

}