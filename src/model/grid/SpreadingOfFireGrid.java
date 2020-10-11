package model.grid;

import java.io.InputStream;
import java.util.List;
import model.cell.CellType;
import model.cell.RockPaperScissorsCell;
import model.cell.SpreadingOfFireCell;

public class SpreadingOfFireGrid extends Grid {
  /**
   * Constructor for this class.
   *
   * @param data InputStream whose CSV file is read to initialize Grid
   */
  public SpreadingOfFireGrid(InputStream data) {
    super(data);
  }

  @Override
  public String setGridType() {
    return "SPREADING_OF_FIRE";
  }

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
}
