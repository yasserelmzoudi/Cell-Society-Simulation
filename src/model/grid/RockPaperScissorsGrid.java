package model.grid;

import java.io.InputStream;
import java.util.List;
import model.cell.CellType;
import model.cell.PercolationCell;
import model.cell.RockPaperScissorsCell;

public class RockPaperScissorsGrid extends Grid{

  /**
   * Constructor for this class.
   *
   * @param data InputStream whose CSV file is read to initialize Grid
   */
  public RockPaperScissorsGrid(InputStream data) {
    super(data);
  }

  @Override
  public String setGridType() {
    return "ROCK_PAPER_SCISSORS";
  }

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
}
