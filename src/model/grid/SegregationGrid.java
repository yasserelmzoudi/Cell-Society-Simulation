package model.grid;

import java.io.InputStream;
import java.util.List;
import model.cell.CellType;
import model.cell.RockPaperScissorsCell;
import model.cell.SegregationCell;

public class SegregationGrid extends Grid{

  /**
   * Constructor for this class.
   *
   * @param data InputStream whose CSV file is read to initialize Grid
   */
  public SegregationGrid(InputStream data) {
    super(data);
  }

  @Override
  public String setGridType() {
    return "SEGREGATION";
  }

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
