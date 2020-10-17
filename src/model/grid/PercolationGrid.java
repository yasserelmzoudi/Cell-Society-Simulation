package model.grid;

import java.io.InputStream;
import java.util.List;
import model.cell.CellType;
import model.cell.PercolationCell;

/**
 * Class encapsulating logic for setting up Percolation Grid.
 *
 * @author Umika Paul
 */
public class PercolationGrid extends Grid{

  /**
   * Constructor for this class.
   *
   * @param data Data to read.
   */
  public PercolationGrid(InputStream data, String edgePolicy, String neighborhoodPolicy) {
    super(data, edgePolicy, neighborhoodPolicy);
  }

  /**
   * Sets the type of grid.
   *
   * @return Type of grid.
   */
  @Override
  public String setGridType() {
    return "PERCOLATION";
  }

  /**
   * Sets up grid.
   *
   * <p> 0 represents <code>EMPTY_OPEN</code></p>
   * <p> 1 represents <code>FULL_OPEN</code></p>
   * <p> 2 represents <code>BLOCKED</code></p>
   *
   * @param readLines List of lines
   */
  @Override
  public void gridSetUp(List<String[]> readLines) {
    int row = 0;
    CellType state;
    for (String[] cellsInRow : readLines) {
      for (int column = 0; column < gridWidth; column++) {
        int cellValue = Integer.parseInt(cellsInRow[column]);
        if (cellValue == 0) {
          state = CellType.EMPTY_OPEN;
        }
        else if (cellValue == 1) {
          state = CellType.FULL_OPEN;
        }
        else {
          state = CellType.BLOCKED;
        }
        gridOfCells[row][column] = new PercolationCell(row, column, state.ordinal());
      }
      row++;
    }
  }

  @Override
  public List<String> getAllTypes() {
    return CellType.getPercolationTypes();
  }

}
