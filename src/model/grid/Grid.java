package model.grid;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;

import model.cell.Cell;
import model.cell.CellType;
import model.cell.GameOfLifeCell;

import java.util.ResourceBundle;
import model.cell.PercolationCell;
import model.cell.PredatorPreyCell;
import model.cell.RockPaperScissorsCell;
import model.cell.SegregationCell;
import model.cell.SpreadingOfFireCell;
import model.exceptions.InvalidCSVFileException;


/**
 * Class encapsulating logic for initializing a Grid from a given data file. It converts the data
 * file into a 2D grid, obtains the neighboring cells, and updates them with the Game of Life
 * rules.
 *
 * @author Umika Paul, Yasser Elmzoudi
 */
public abstract class Grid {

  private static final int HEADER_ROW = 0;
  private static final int NUM_ROWS_INDEX = 0;
  private static final int NUM_COLUMNS_INDEX = 1;
  protected final Cell[][] gridOfCells;
  protected final int gridWidth;
  protected final int gridHeight;
  private final InputStream data;
  private final ResourceBundle errorMessageSource;
  private static final String EXCEPTION_RESOURCE = "resources.exceptionMessages";
  private final String edgePolicy;
  private final String neighborhoodPolicy;
  private final List<CellType> gridTypes;
  private Map<String, Integer> totalCellTypeCounts;
  private Map<String, Integer> currentCellTypeCounts;

  /**
   * Constructor for this class.
   *
   * @param data InputStream whose CSV file is read to initialize Grid
   */
  public Grid(InputStream data, String edgePolicy, String neighborhoodPolicy) {
    errorMessageSource = ResourceBundle.getBundle(EXCEPTION_RESOURCE);
    this.edgePolicy = edgePolicy;
    this.neighborhoodPolicy = neighborhoodPolicy;
    this.data = data;

    currentCellTypeCounts = new HashMap<>();
    totalCellTypeCounts = new HashMap<>();
    resetCellTypeCounts();

    List<String[]> readLines = readAll();
    gridWidth = Integer.parseInt(readLines.get(HEADER_ROW)[NUM_COLUMNS_INDEX]);
    gridHeight = Integer.parseInt(readLines.get(HEADER_ROW)[NUM_ROWS_INDEX]);
    checkCSVFile(readLines);
    readLines.remove(0);
    gridOfCells = new Cell[gridHeight][gridWidth];
    gridSetUp(readLines);
    gridTypes = new ArrayList<>();
    setUpGridTypes();
  }



  /**
   * Abstract method to set up grid for each type of simulation
   *
   * @param readLines List of lines
   */
  public abstract void gridSetUp(List<String[]> readLines);

  /**
   * Abstract method to get all the types of cells in a simulation.
   * @return List of types.
   */
  public abstract List<String> getAllTypes();

  /**
   * Code adopted from Professor Duvall to read CSV files
   *
   * @return List<String [ ]> representing all of the lines read from data
   * @author Robert C. Duvall
   */
  public List<String[]> readAll() throws InvalidCSVFileException {
    try (CSVReader csvReader = new CSVReader(new InputStreamReader(data))) {
      return csvReader.readAll();
    } catch (IOException | CsvException e) {
      throw new InvalidCSVFileException(errorMessageSource.getString("InvalidCSVFile"));
    }
  }

  private void checkCSVFile(List<String[]> readLines) {
    if (readLines.isEmpty()  || readLines.size() < 2 || gridHeight != readLines.size() - 1 || gridWidth != readLines.get(1).length) {
      throw new InvalidCSVFileException(errorMessageSource.getString("InvalidCSVFile"));
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
        if (setGridType().equals("GAME_OF_LIFE")) {
          copyOfGrid[row][column] = new GameOfLifeCell(this.gridOfCells[row][column]);
        }
        if (setGridType().equals("PERCOLATION")) {
          copyOfGrid[row][column] = new PercolationCell(this.gridOfCells[row][column]);
        }
        if (setGridType().equals("ROCK_PAPER_SCISSORS")) {
          copyOfGrid[row][column] = new RockPaperScissorsCell(this.gridOfCells[row][column]);
        }
        if (setGridType().equals("SEGREGATION")) {
          copyOfGrid[row][column] = new SegregationCell(this.gridOfCells[row][column]);
        }
        if (setGridType().equals("SPREADING_OF_FIRE")) {
          copyOfGrid[row][column] = new SpreadingOfFireCell(this.gridOfCells[row][column]);
        }
        if (setGridType().equals("PREDATOR_PREY")) {
          copyOfGrid[row][column] = new PredatorPreyCell(this.gridOfCells[row][column]);
        }
      }
    }
    return copyOfGrid;
  }

  /**
   * Updates the cell in the next step.
   */
  public void performNextStep() {
    Cell[][] copyGrid = copyGrid();
    boolean[][] isUpdated = new boolean[gridHeight][gridWidth];
    for (int row = 0; row < gridHeight; row++) {
      for (int column = 0; column < gridWidth; column++) {
        List<Cell> neighbors = new ArrayList<>();
        List<Cell> newNeighbors = new ArrayList<>();
        try {
          Method neighborType = Grid.class.getMethod("getEdgeType" + edgePolicy,
              Cell[][].class, int.class, int.class);
          Method edgeType = Grid.class.getMethod("setNeighbor" + neighborhoodPolicy,
              List.class, List.class, int.class, int.class);
          neighbors = (List<Cell>) neighborType.invoke(this, copyGrid, row, column);
          newNeighbors = (List<Cell>) neighborType.invoke(this, this.gridOfCells, row, column);
          edgeType.invoke(this, neighbors, newNeighbors, row, column);
        } catch (Exception e) {
          e.printStackTrace();
        }
        if (!isUpdated[row][column]) {
          this.gridOfCells[row][column].update(neighbors, newNeighbors, isUpdated);
        }
      }
    }
    updateCellTypeCount();
    //System.out.println(totalCellTypeCounts.get("SHARK"));
  }

  public void updateCellTypeCount() {
    Cell[][] grid = getAllCells();
    for (int row = 0; row < gridHeight; row++) {
      for (int column = 0; column < gridWidth; column++) {
        String cellTypeName = grid[row][column].getState().name();
        totalCellTypeCounts.put(cellTypeName, totalCellTypeCounts.get(cellTypeName) + 1);
      }
    }
  }

  public void resetCellTypeCounts() {
    for (String cellTypeName : getAllTypes()) {
      totalCellTypeCounts.put(cellTypeName, 0);
    }
  }

  public void setUpGridTypes() {
    for (int row = 0; row < gridHeight; row++) {
      for (int column = 0; column < gridWidth; column++) {
        Cell differentCellTypes = gridOfCells[row][column];
        gridTypes.add(differentCellTypes.getState());
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
  public List<Cell> getEdgeTypeFinite(Cell[][] grid, int row, int column) {
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

  /**
   * Gets the neighbors for the Torodial edge policy.
   *
   * @param grid   The grid from which to read the neighbors.
   * @param row    The row of the given cell.
   * @param column The column of the given cell.
   * @return List of neighbors.
   */
  public List<Cell> getEdgeTypeTorodial(Cell[][] grid, int row, int column) {
    int minRow = 0;
    int maxRow = 0;
    int minCol = 0;
    int maxCol = 0;
    return findMinMaxRowsCols(minRow, maxRow, minCol, maxCol, row, column, grid);
  }

  /**
   * Gets the neighbors for the Klein Bottle edge policy.
   *
   * @param grid   The grid from which to read the neighbors.
   * @param row    The row of the given cell.
   * @param column The column of the given cell.
   * @return List of neighbors.
   */
  public List<Cell> getEdgeTypeKleinBottle(Cell[][] grid, int row, int column) {
    int minRow = 0;
    int maxRow = 0;
    int minCol = 0;
    int maxCol = 0;
    List<Cell> kleinBottleCells = findMinMaxRowsCols(minRow, maxRow, minCol, maxCol, row, column,
        grid);
    int newMinRow = (gridHeight - row - 1) % gridHeight;
    if (newMinRow < 0) {
      newMinRow += gridHeight;
    }
    int newMaxRow = (gridHeight - row + 1) % gridHeight;
    if (newMaxRow < 0) {
      newMaxRow += gridHeight;
    }
    int newRow = (gridHeight - row) % gridHeight;
    if (newRow < 0) {
      newRow += gridHeight;
    }
    if (column == 0) {
      kleinBottleCells.removeAll(Arrays.asList(grid[minRow][minCol],
          grid[row][minCol], grid[maxRow][minCol]));
      kleinBottleCells.addAll(Arrays.asList(grid[newMinRow][minCol],
          grid[newMaxRow][minCol], grid[newRow][minCol]));
    }
    if (column == (gridWidth - 1)) {
      kleinBottleCells.removeAll(Arrays.asList(grid[minRow][maxCol],
          grid[row][maxCol], grid[maxRow][maxCol]));
      kleinBottleCells.addAll(Arrays.asList(grid[newMinRow][maxCol],
          grid[newMaxRow][maxCol], grid[newRow][maxCol]));
    }
    return kleinBottleCells;
  }

  /**
   * Gets neighbors of a cell with the cardinal neighborhood policy.
   *
   * @param neighbors    The cells from the complete neighborhood policy.
   * @param newNeighbors The neighboring cells that have been updated thus far.
   * @param row          The row of the cell.
   * @param column       The column of the cell.
   */
  public void setNeighborCardinal(List<Cell> neighbors, List<Cell> newNeighbors, int row,
      int column) {
    for (int i = neighbors.size() - 1; i >= 0; i--) {
      Cell neighbor = neighbors.get(i);
      if (((neighbor.getRow() == (row - 1) % gridHeight) &&
          (neighbor.getColumn() == (column - 1) % gridWidth)) ||
          ((neighbor.getRow() == (row - 1) % gridHeight) &&
              (neighbor.getColumn() == (column + 1) % gridWidth)) ||
          ((neighbor.getRow() == (row + 1) % gridHeight) &&
              (neighbor.getColumn() == (column - 1) % gridWidth)) ||
          ((neighbor.getRow() == (row + 1) % gridHeight)
              && (neighbor.getColumn() == (column + 1) % gridWidth))) {
        neighbors.remove(i);
        newNeighbors.remove(i);
      }
    }
  }

  /**
   * Gets neighbors of a cell with the diagonal neighborhood policy.
   *
   * @param neighbors    The cells from the complete neighborhood policy.
   * @param newNeighbors The neighboring cells that have been updated thus far.
   * @param row          The row of the cell.
   * @param column       The column of the cell.
   */
  public void setNeighborDiagonal(List<Cell> neighbors, List<Cell> newNeighbors, int row,
      int column) {
    for (int i = neighbors.size() - 1; i >= 0; i--) {
      Cell neighbor = neighbors.get(i);
      if (((neighbor.getRow() == (row - 1) % gridHeight) &&
          (neighbor.getColumn() == column % gridWidth)) ||
          ((neighbor.getRow() == row % gridHeight) &&
              (neighbor.getColumn() == (column + 1) % gridWidth)) ||
          ((neighbor.getRow() == (row + 1) % gridHeight) &&
              (neighbor.getColumn() == column % gridWidth)) ||
          ((neighbor.getRow() == row % gridHeight)
              && (neighbor.getColumn() == (column - 1) % gridWidth))) {
        neighbors.remove(i);
        newNeighbors.remove(i);
      }
    }
  }

  /**
   * Gets neighbors of a cell with the complete neighborhood policy.
   *
   * @param neighbors    The cells from the complete neighborhood policy.
   * @param newNeighbors The neighboring cells that have been updated thus far.
   * @param row          The row of the cell.
   * @param column       The column of the cell.
   */
  public void setNeighborComplete(List<Cell> neighbors, List<Cell> newNeighbors, int row,
      int column) {
  }

  private List<Cell> findMinMaxRowsCols(int minRow, int maxRow, int minCol, int maxCol, int row,
      int column, Cell[][] grid) {
    List<Cell> cells = new ArrayList<>();
    minRow = (row - 1) % gridHeight;
    if (minRow < 0) {
      minRow += gridHeight;
    }
    maxRow = (row + 1) % gridHeight;
    minCol = (column - 1) % gridWidth;
    if (minCol < 0) {
      minCol += gridWidth;
    }
    maxCol = (column + 1) % gridWidth;
    cells.addAll(Arrays.asList(grid[minRow][minCol], grid[minRow][column],
        grid[minRow][maxCol], grid[row][maxCol], grid[row][minCol], grid[maxRow][minCol],
        grid[maxRow][column], grid[maxRow][maxCol]));
    return cells;
  }


  public void completeRandomizeGrid() {
    for (int row = 0; row < gridHeight; row++) {
      for (int column = 0; column < gridWidth; column++) {
        gridOfCells[row][column].setCellType(getRandomCellType());
      }
    }
  }

  public void swapRandomizeGrid() {
    Collections.shuffle(gridTypes);
    int gridTypesCount = 0;
    for (int row = 0; row < gridHeight; row++) {
      for (int column = 0; column < gridWidth; column++) {
        gridOfCells[row][column].setCellType(gridTypes.get(gridTypesCount));
        gridTypesCount++;
      }
    }
  }

  /**
   * Sets the grid type to a particular simulation.
   *
   * @return Type of grid required for simulation.
   */
  public String setGridType() {
    String myType = "";
    return myType;
  }

  /**
   * Gets all cells from the grid.
   *
   * @return A copy of the grid.
   */
  public Cell[][] getAllCells() {
    return copyGrid();
  }

  public int gridColumns() {
    return gridOfCells[0].length;
  }

  public int gridRows() {
    return gridOfCells.length;
  }

  public Cell getCell(int row, int column) {
    return gridOfCells[row][column];
  }

  public int getCellTypeState(int row, int column) {
    return getCell(row, column).getNumericState();
  }

  public int getGridHeight() {
    return gridHeight;
  }

  public int getGridWidth() {
    return gridWidth;
  }

  public void noRandomization() {
    return;
  }

  public Map<String, Integer> getTotalCellTypeCounts() {
    return totalCellTypeCounts;
  }

  private CellType getRandomCellType() {
    return CellType.valueOf(getAllTypes().get(getRandomIndex(getAllTypes().size())));
  }

  private int getRandomIndex(int range) {
    return (int) (Math.random() * range);
  }


}