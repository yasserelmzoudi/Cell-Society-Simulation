package model.cell;

import java.util.List;

public class GameOfLifeCell extends Cell {
  private int state;

  public GameOfLifeCell(int row, int column, int state) {
    super(row, column, state);
    this.state = state;
  }

  public GameOfLifeCell(Cell copyCell) {
    super(copyCell);
  }

  @Override
  public void update(List<Cell> neighbors) {
    Integer numAliveCells = 0;
    for (Cell neighbor : neighbors) {
      if (neighbor.getState().name().equals("ALIVE")) {
        numAliveCells++;
      }
    }

    if (getState().name().equals("ALIVE")) {
      if (numAliveCells == 3 || numAliveCells == 2) {
        return;
      }
      setCellType(CellType.DEAD);
      return;
    }
    else if (numAliveCells == 3) {
      setCellType(CellType.ALIVE);
    }
  }
}
