package model.grid;

import java.io.InputStream;
import java.util.List;
import model.cell.CellType;
import model.cell.GameOfLifeCell;

public class GameOfLifeGrid extends Grid {

  public GameOfLifeGrid(InputStream data) {
    super(data);
  }

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
}
