package model.cell;

import java.util.List;
import java.util.Random;

/**
 * Subclass encapsulating logic to update cells in the Spreading of Fire simulation.
 *
 * Checks to see if a neighbor burning and if this is true, there is a probability <code>PROB_CATCH</code>
 * for the current tree to catch fire.
 *
 * If the state is <code>BURNING</code>, in the next cycle the cell will by <code>EMPTY</code>
 *
 */

public class SpreadingOfFireCell extends Cell{
  private int state;
  private static final double PROB_CATCH = 0.55;

  /**
   * Constructor for this class.
   *
   * @param row Row of cell.
   * @param column Column of cell.
   * @param state State cell is in.
   */
  public SpreadingOfFireCell(int row, int column, int state) {
    super(row, column, state);
    this.state = state;
  }

  /**
   * Copy constructor.
   *
   * @param copyCell Makes a copy of the cell to preserve original values.
   */
  public SpreadingOfFireCell(Cell copyCell) {
    super(copyCell);
  }


  @Override
  public void update(List<Cell> neighbors, List<Cell> newNeighbors, boolean[][] isUpdated) {
    for (int i = neighbors.size()-1; i >= 0; i--) {
      Cell neighbor = neighbors.get(i);
      if ((neighbor.getRow() == this.getRow() - 1 && neighbor.getColumn() == this.getColumn() - 1) ||
          (neighbor.getRow() == this.getRow() - 1 && neighbor.getColumn() == this.getColumn() + 1) ||
          (neighbor.getRow() == this.getRow() + 1 && neighbor.getColumn() == this.getColumn() - 1) ||
          (neighbor.getRow() == this.getRow() + 1 && neighbor.getColumn() == this.getColumn() + 1)) {
        neighbors.remove(i);
      }
    }

    updateBurningTree();

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

  private void updateBurningTree() {
    if ((getState().name().equals("EMPTY")) || (getState().name().equals("BURNING")) ) {
      setCellType(CellType.EMPTY);
    }
  }

}
