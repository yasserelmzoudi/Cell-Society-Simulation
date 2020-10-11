package model.cell;

import java.util.List;

public class PercolationCell extends Cell {
  private int state;

  public PercolationCell(int row, int column, int state) {
    super(row, column, state);
    this.state = state;
  }

  public PercolationCell(Cell copyCell) {
    super(copyCell);
  }


  @Override
  public void update(List<Cell> neighbors, List<Cell> newNeighbors, boolean[][] isUpdated) {
    boolean changeState = false;
    for (Cell neighbor : neighbors) {
      if (neighbor.getState().name().equals("FULL_OPEN")) {
        changeState = true;
      }
    }

    if ((getState().name().equals("EMPTY_OPEN")) && changeState) {
      setCellType(CellType.FULL_OPEN);
    }
  }
}
