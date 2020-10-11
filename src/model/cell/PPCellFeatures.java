package model.cell;

/**
 * Abstract class encapsulating logic for Predator Prey simulation.
 *
 * <p>Reproduce represents number of cycles a fish or shark can live before reproducing, and
 * being reset back to 0.</p>
 *
 * <p>Energy represents the units of energy a shark has left. Fish always have energy 0.</p>
 */

public abstract class PPCellFeatures extends Cell {

  private int reproduce;
  private int energy;

  /**
   * Additional features of Predator Prey Cell.
   *
   * @param row Row in grid.
   * @param column Column in grid.
   * @param state State of cell.
   * @param reproduce Number of cycles lived before reset to 0 after reproducing.
   * @param energy Units of energy a shark has left.
   */
  public PPCellFeatures(int row, int column, int state, int reproduce, int energy) {
    super(row, column, state);
    this.reproduce = reproduce;
    this.energy = energy;
  }

  /**
   * Copy constructor.
   *
   * @param copyCell Copy of cell.
   */
  public PPCellFeatures(Cell copyCell) {
    super(copyCell);
  }

  public int getCellReproduction() {
    return this.reproduce;
  }

  public int getCellEnergy() {
    return this.energy;
  }

  public void setCellReproduction(int newReproduce) {
    this.reproduce = newReproduce;
  }

  public void setCellEnergy(int newEnergy) {
    this.energy = newEnergy;
  }

}
