package model.cell;

public abstract class PPCellFeatures extends Cell {

  private int reproduce;
  private int energy;

  public PPCellFeatures(int row, int column, int state, int reproduce, int energy) {
    super(row, column, state);
    this.reproduce = reproduce;
    this.energy = energy;
  }

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
