package model.grid;

import java.io.InputStream;
import java.util.List;
import model.cell.CellType;
import model.cell.GameOfLifeCell;
import model.cell.PercolationCell;

public class PercolationGrid extends Grid{
  public PercolationGrid(InputStream data) {
    super(data);
  }

  @Override
  public String setGridType() {
    return "PERCOLATION";
  }

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
}
