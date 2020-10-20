package model.grid;

import java.io.InputStream;
import java.util.List;
import model.cell.CellType;
import model.cell.GameOfLifeCell;

/**
 * Class encapsulating logic for setting up Game of Life Grid.
 *
 * @author Umika Paul
 */
public class GameOfLifeGrid extends Grid {

  /**
   * Constructor for this class.
   *
   * @param data Data to read.
   */
  public GameOfLifeGrid(InputStream data, String edgePolicy, String neighborhoodPolicy) {
    super(data, edgePolicy, neighborhoodPolicy);
  }

  /**
   * Sets the type of grid.
   *
   * @return Type of grid.
   */
  public String getGridType() {
    return "GameOfLife";
  }

  /**
   * Sets up grid.
   *
   * <p>0 represents <code>DEAD</code></p>
   * <p>1 represents <code>ALIVE</code></p>
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

  @Override
  public List<String> getAllTypes() {
    return CellType.getGameOfLifeTypes();
  }

}
