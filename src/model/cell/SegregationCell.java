package model.cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Subclass encapsulating logic to update cells in the Segregation simulation.
 * Checks to see how many neighbors are of the same state and if the percentage of neighbors that
 * have the same state is less than <code>THRESHOLD</code>, the agent moves to an empty cell.
 *
 * <code>needsPlacement</code> is a List that keeps track of the agents that need to be replaced
 * since they were dissatisfied.
 *
 *  @author Umika Paul
 */

public class SegregationCell extends Cell {

  private int state;
  private List<String> needsPlacement;
  private static final int THRESHOLD = 30;

  /**
   * Constructor for this class.
   *
   * @param row Row of cell.
   * @param column Column of cell.
   * @param state State cell is in.
   */
  public SegregationCell(int row, int column, int state) {
    super(row, column, state);
    this.state = state;
    this.needsPlacement = new ArrayList<String>();
  }

  /**
   * Copy constructor.
   *
   * @param copyCell Makes a copy of the cell to preserve original values.
   */
  public SegregationCell(Cell copyCell) {
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

    double XCount = 0;
    double OCount = 0;
    double totalCount = 0;
    for (Cell neighbor : neighbors) {
      if (neighbor.getState().name().equals("X")) {
        XCount++;
        totalCount++;
      }
      if (neighbor.getState().name().equals("O"))  {
        OCount++;
        totalCount++;
      }
    }

    double percentageX = (XCount / totalCount) * 100;
    double percentageO = (OCount / totalCount) * 100;

    moveDissatisfiedAgents(percentageX, percentageO);
  }

  /**
   * <p>If cell is X and the percentage of neighbors of race X are below the threshold, current
   * cell is set to empty and will be moved.</p>
   *
   * <p>If cell is O and the percentage of neighbors of race O are below the threshold, current
   * cell is set to empty and will be moved.</p>
   *
   * <p>If cell is NO_RACE, cells that need to be moved are moved.</p>
   *
   * @param percentageX Percentage of neighbors that are of X race.
   * @param percentageO Percentage of neighbors that are of O race.
   */
  private void moveDissatisfiedAgents(double percentageX, double percentageO) {
    if ((getState().name().equals("X")) && (percentageX < THRESHOLD)) {
      this.needsPlacement.add("X");
      setCellType(CellType.NO_RACE);
    }
    else if ((getState().name().equals("O")) && (percentageO < THRESHOLD)) {
      needsPlacement.add("O");
      setCellType(CellType.NO_RACE);
    }
    else if ((getState().name().equals("NO_RACE")) && ((needsPlacement.size())!=0)) {
      String agentType = needsPlacement.get(0);
        needsPlacement.remove(0);
        if (agentType.equals("X")) {
          setCellType(CellType.X);
        }
        else if(agentType.equals("O")) {
          setCellType(CellType.O);
        }
      }
  }

}