package model.grid;

import java.io.InputStream;
import java.util.List;
import model.cell.CellType;
import model.cell.SegregationCell;

/**
 * Class encapsulating logic for setting up Segregation Grid.
 */
public class SegregationGrid extends Grid{

  /**
   * Constructor for this class.
   *
   * @param data InputStream whose CSV file is read to initialize Grid
   */
  public SegregationGrid(InputStream data) {
    super(data);
  }

  /**
   * Sets the type of grid.
   *
   * @return Type of grid.
   */
  @Override
  public String setGridType() {
    return "SEGREGATION";
  }

  /**
   * Sets up grid.
   *
   * <p> 0 represents the agent <code>O</code></p>
   * <p> 1 represents the agent <code>X</code></p>
   * <p> 2 represents no agent i.e. empty cell <code>NO_RACE</code></p>
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
          state = CellType.O;
        }
        else if (cellValue == 1) {
          state = CellType.X;
        }
        else {
          state = CellType.NO_RACE;
        }
        gridOfCells[row][column] = new SegregationCell(row, column, state.ordinal());
      }
      row++;
    }
  }
}
