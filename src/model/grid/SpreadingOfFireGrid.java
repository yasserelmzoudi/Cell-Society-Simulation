package model.grid;

import java.io.InputStream;
import java.util.List;
import model.cell.CellType;
import model.cell.SpreadingOfFireCell;

/**
 * Class encapsulating logic for setting up Spreading of Fire Grid.
 *
 * @author Umika Paul
 */
public class SpreadingOfFireGrid extends Grid {
  /**
   * Constructor for this class.
   *
   * @param data InputStream whose CSV file is read to initialize Grid
   */
  public SpreadingOfFireGrid(InputStream data, String edgePolicy, String neighborhoodPolicy) {
    super(data, edgePolicy, neighborhoodPolicy);
  }

  /**
   * Sets the type of grid.
   *
   * @return Type of grid.
   */
  @Override
  public String setGridType() {
    return "SPREADING_OF_FIRE";
  }


  /**
   * Sets up grid.
   *
   * <p> 0 represents empty cell <code>EMPTY</code></p>
   * <p> 1 represents a tree <code>TREE</code></p>
   * <p> 2 represents a tree burning <code>BURNING</code></p>
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
          state = CellType.EMPTY;
        }
        else if (cellValue == 1) {
          state = CellType.TREE;
        }
        else {
          state = CellType.BURNING;
        }
        gridOfCells[row][column] = new SpreadingOfFireCell(row, column, state.ordinal());
      }
      row++;
    }
  }
  @Override
  public List<String> getAllTypes() {
    return CellType.getSpreadingOfFireTypes();
  }
}
