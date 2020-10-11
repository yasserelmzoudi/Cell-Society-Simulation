package model.grid;

import java.io.InputStream;
import java.util.List;
import model.cell.CellType;
import model.cell.PercolationCell;
import model.cell.PredatorPreyCell;

public class PredatorPreyGrid extends Grid {
  public PredatorPreyGrid(InputStream data) {
    super(data);
  }

  @Override
  public String setGridType() {
    return "PREDATOR_PREY";
  }

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
}
