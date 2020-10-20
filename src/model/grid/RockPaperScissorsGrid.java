package model.grid;

import java.io.InputStream;
import java.util.List;
import model.cell.CellType;
import model.cell.RockPaperScissorsCell;

/**
 * Class encapsulating logic for setting up Rock Paper Scissors Grid.
 *
 * @author Umika Paul
 */
public class RockPaperScissorsGrid extends Grid{

  /**
   * Constructor for this class.
   *
   * @param data InputStream whose CSV file is read to initialize Grid
   */
  public RockPaperScissorsGrid(InputStream data, String edgePolicy, String neighborhoodPolicy) {
    super(data, edgePolicy, neighborhoodPolicy);
  }

  /**
   * Sets the type of grid.
   *
   * @return Type of grid.
   */
  public String getGridType() {
    return "RockPaperScissors";
  }

  /**
   * Sets up grid. Initial reproduction count is set to 0 for both fish and sharks, and energy is
   * set to 0.
   *
   * <p> 0 represents <code>ROCK</code></p>
   * <p> 1 represents <code>PAPER</code></p>
   * <p> 2 represents <code>SCISSORS</code></p>
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
          state = CellType.ROCK;
        }
        else if (cellValue == 1) {
          state = CellType.PAPER;
        }
        else {
          state = CellType.SCISSORS;
        }
        gridOfCells[row][column] = new RockPaperScissorsCell(row, column, state.ordinal());
      }
      row++;
    }
  }


  @Override
  public List<String> getAllTypes() {
    return CellType.getRockPaperScissorsTypes();
  }

}
