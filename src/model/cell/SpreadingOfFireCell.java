package model.cell;

import java.util.List;
import java.util.Random;


public class SpreadingOfFireCell extends Cell{
  private int state;
  private static final double PROB_CATCH = 0.55;

  public SpreadingOfFireCell(int row, int column, int state) {
    super(row, column, state);
    this.state = state;
  }

  public SpreadingOfFireCell(Cell copyCell) {
    super(copyCell);
  }

  @Override
  public void update(List<Cell> neighbors, List<Cell> newNeighbors, boolean[][] isUpdated) {
/*
    int[] indexes = {7,5,2,0};
    for (int index : indexes) {
      neighbors.remove(index);
    }
*/
    for (int i = neighbors.size()-1; i >= 0; i--) {
      Cell neighbor = neighbors.get(i);
      if ((neighbor.getRow() == this.getRow() - 1 && neighbor.getColumn() == this.getColumn() - 1) ||
          (neighbor.getRow() == this.getRow() - 1 && neighbor.getColumn() == this.getColumn() + 1) ||
          (neighbor.getRow() == this.getRow() + 1 && neighbor.getColumn() == this.getColumn() - 1) ||
          (neighbor.getRow() == this.getRow() + 1 && neighbor.getColumn() == this.getColumn() + 1)) {
        neighbors.remove(i);
      }
    }

    if ((getState().name().equals("EMPTY")) || (getState().name().equals("BURNING")) ) {
      setCellType(CellType.EMPTY);
    }

    boolean neighboringFire = false;
    for (Cell neighbor : neighbors) {
      if (neighbor.getState().name().equals("BURNING")) {
        neighboringFire = true;
      }
    }

    if ((neighboringFire) && (getState().name().equals("TREE"))) {
      Random random = new Random();
      int roll = random.nextInt(99);
      if(roll < (PROB_CATCH * 100)){
        setCellType(CellType.BURNING);
      }
      else {
        setCellType(CellType.TREE);
      }
    }

  }

}
