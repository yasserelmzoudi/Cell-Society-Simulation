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
 * @author Umika Paul
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


  /**
   * Updates cell based on the state of neighboring cells.
   *
   * @param neighbors List of neighboring cells
   * @param newNeighbors List of neighboring cells that may have been updated.
   * @param isUpdated Whether the cell has already been updated or not. If it has been updated,
   *                  skip over the cell.
   */
  @Override
  public void update(List<Cell> neighbors, List<Cell> newNeighbors, boolean[][] isUpdated) {
    getFourNeighbors(neighbors);

    updateBurningTree();
    boolean neighboringFire = isNeighboringFire(neighbors);

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

  /**
   * Get North, East, South, West neighbors.
   *
   * @param neighbors List of neighbors.
   */
  private void getFourNeighbors(List<Cell> neighbors) {
    for (int i = neighbors.size()-1; i >= 0; i--) {
      Cell neighbor = neighbors.get(i);
      if ((neighbor.getRow() == this.getRow() - 1 && neighbor.getColumn() == this.getColumn() - 1) ||
          (neighbor.getRow() == this.getRow() - 1 && neighbor.getColumn() == this.getColumn() + 1) ||
          (neighbor.getRow() == this.getRow() + 1 && neighbor.getColumn() == this.getColumn() - 1) ||
          (neighbor.getRow() == this.getRow() + 1 && neighbor.getColumn() == this.getColumn() + 1)) {
        neighbors.remove(i);
      }
    }
  }

  /**
   * Checks if any neighboring cells have a fire.
   *
   * @param neighbors List of neighbors.
   * @return If neighboring fire is true.
   */
  private boolean isNeighboringFire(List<Cell> neighbors) {
    boolean neighboringFire = false;
    for (Cell neighbor : neighbors) {
      if (neighbor.getState().name().equals("BURNING")) {
        neighboringFire = true;
      }
    }
    return neighboringFire;
  }

  /**
   * If a tree is burning, sets cell type to empty.
   */
  private void updateBurningTree() {
    if ((getState().name().equals("EMPTY")) || (getState().name().equals("BURNING")) ) {
      setCellType(CellType.EMPTY);
    }
  }

}
