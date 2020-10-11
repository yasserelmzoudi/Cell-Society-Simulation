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
  //private static List<String> needsPlacement = new ArrayList<String>();
  private static List<Cell> needsPlacement = new ArrayList<Cell>();
  private static List<Cell> emptyCells = new ArrayList<Cell>();
  private static final int THRESHOLD = 50;
  private static final int NUM_EMPTY_CELLS = 9;

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

    moveDissatisfiedAgents(percentageX, percentageO, isUpdated);
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
  private void moveDissatisfiedAgents(double percentageX, double percentageO, boolean[][] isUpdated) {

    //count is number of cells that have been added to needsPlacement list
    int count = 0;
    for (int row = 0; row < isUpdated.length; row++) {
      for (int column = 0; column < isUpdated[0].length; column++) {
        if (isUpdated[row][column]) {
          count++;
        }
      }
    }

    if ((getState().name().equals("X")) && (percentageX < THRESHOLD)) {
      isUpdated[getRow()][getColumn()] = true;
      needsPlacement.add(this);
    }
    else if ((getState().name().equals("O")) && (percentageO < THRESHOLD)) {
      isUpdated[getRow()][getColumn()] = true;
      needsPlacement.add(this);
    }
    else if ((getState().name().equals("NO_RACE"))) {
      emptyCells.add(this);
      }

    if (this.getRow() == isUpdated.length-1 && this.getColumn() == isUpdated[0].length-1) {
      int numEmptyCells = emptyCells.size();
      int numReplacements = Math.min(numEmptyCells, needsPlacement.size());
      for (int agent = 0; agent<numReplacements; agent++) {
        Cell agentType = needsPlacement.get(agent);
        int randomCell = getRandomIndex(emptyCells.size());
        if (agentType.getState().name().equals("X")) {
          agentType.setCellType(CellType.NO_RACE);
          emptyCells.get(randomCell).setCellType(CellType.X);
        }
        else if(agentType.getState().name().equals("O")) {
          agentType.setCellType(CellType.NO_RACE);
          emptyCells.get(randomCell).setCellType(CellType.O);
        }
        emptyCells.remove(randomCell);
      }
      emptyCells.clear();
      needsPlacement.clear();
    }
  }

  private int getRandomIndex(int rangeSize) {
    return (int) (Math.random() * rangeSize);
  }


}