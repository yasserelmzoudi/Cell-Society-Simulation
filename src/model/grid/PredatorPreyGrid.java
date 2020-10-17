package model.grid;

import java.io.InputStream;
import java.util.List;
import model.cell.CellType;
import model.cell.PredatorPreyCell;

/**
 * Class encapsulating logic for setting up Predator Prey Grid.
 *
 * @author Umika Paul
 */
public class PredatorPreyGrid extends Grid {

  /**
   * Constructor for this class.
   *
   * @param data Data to read.
   */
  public PredatorPreyGrid(InputStream data) {
    super(data);
  }

  /**
   * Sets the type of grid.
   *
   * @return Type of grid.
   */
  @Override
  public String setGridType() {
    return "PREDATOR_PREY";
  }

  /**
   * Sets up grid. Initial reproduction count is set to 0 for both fish and sharks, and energy is
   * set to 0.
   *
   * <p> 0 represents <code>WATER</code></p>
   * <p> 1 represents <code>FISH</code></p>
   * <p> 2 represents <code>SHARK</code></p>
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
          state = CellType.WATER;
          gridOfCells[row][column] = new PredatorPreyCell(row, column, state.ordinal(),0,0);
        }
        else if (cellValue == 1) {
          state = CellType.FISH;
          gridOfCells[row][column] = new PredatorPreyCell(row, column, state.ordinal(),0,0);
        }
        else {
          state = CellType.SHARK;
          gridOfCells[row][column] = new PredatorPreyCell(row, column, state.ordinal(),0,2);
        }

      }
      row++;
    }
  }
  @Override
  public List<String> getAllTypes() {
    List<String> myTypes = super.getAllTypes();
    myTypes.add(CellType.SHARK.toString());
    myTypes.add(CellType.FISH.toString());
    myTypes.add(CellType.WATER.toString());
    return myTypes;
  }
}
