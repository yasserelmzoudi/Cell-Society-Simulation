package model.cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Subclass encapsulating logic to update cells in the Predator Prey simulation.
 */

public class PredatorPreyCell extends PPCellFeatures {

  private int state;
  private int reproduce;
  private int energy;
  private static final int REPRODUCTION_TIME = 5;
  private static final int ENERGY_FROM_FISH = 3;
  private static final int UNIT_ENERGY = 1;
  private static final int UNIT_REPRODUCE = 1;

  /**
   * Additional features of Predator Prey Cell.
   *
   * @param row       Row in grid.
   * @param column    Column in grid.
   * @param state     State of cell.
   * @param reproduce Number of cycles lived before reset to 0 after reproducing.
   * @param energy    Units of energy a shark has left.
   */
  public PredatorPreyCell(int row, int column, int state, int reproduce, int energy) {
    super(row, column, state, reproduce, energy);
    this.state = state;
    this.reproduce = reproduce;
    this.energy = energy;
  }

  /**
   * Copy constructor.
   *
   * @param copyCell Copy of cell.
   */
  public PredatorPreyCell(Cell copyCell) {
    super(copyCell);
  }

  /**
   * Updates cell based on the state of neighboring cells.
   *
   * @param neighbors    List of neighboring cells
   * @param newNeighbors List of neighboring cells that may have been updated.
   * @param isUpdated    Whether the cell has already been updated or not. If it has been updated,
   *                     skip over the cell.
   */
  @Override
  public void update(List<Cell> neighbors, List<Cell> newNeighbors, boolean[][] isUpdated) {

    getFourNeighbors(neighbors, newNeighbors);

    List<PPCellFeatures> localFish = new ArrayList<>();
    List<PPCellFeatures> localWater = new ArrayList<>();
    List<PPCellFeatures> localSharks = new ArrayList<>();

    getLocalOrganisms(neighbors, newNeighbors, localFish, localWater, localSharks);

    if ((getState().name().equals("FISH"))) {
      if (!localSharks.isEmpty()) {
        manageFishEatenBySharks(isUpdated, localSharks);
      } else if (!localWater.isEmpty()) {
        manageFishMoving(isUpdated, localWater);
      } else {
        setCellType(CellType.FISH);
        this.setCellReproduction(this.getCellReproduction() + UNIT_REPRODUCE);
      }
    } else if ((getState().name().equals("SHARK"))) {

      if (this.getCellEnergy() == 0) {
        setCellType(CellType.WATER);
        this.setCellReproduction(0);
      } else if (!localFish.isEmpty()) {
        manageSharkEatingFish(isUpdated, localFish);
      } else if (!localWater.isEmpty()) {
        manageSharkMoving(isUpdated, localWater);
      } else {
        this.setCellReproduction(this.getCellReproduction() + UNIT_REPRODUCE);
        this.setCellEnergy(this.getCellEnergy() - UNIT_ENERGY);
        this.setCellType(CellType.SHARK);
      }
    }
  }

  /**
   * Manages when shark moves into surrounding water cell.
   *
   * @param isUpdated  If cell has been updated, boolean for the cell is set to true.
   * @param localWater Surrounding water cells.
   */
  private void manageSharkMoving(boolean[][] isUpdated, List<PPCellFeatures> localWater) {
    int randomWaterIndex = getRandomIndex(localWater.size());
    PPCellFeatures newSharkCell = localWater.get(randomWaterIndex);
    newSharkCell.setCellType(CellType.SHARK);
    newSharkCell.setCellReproduction(this.getCellReproduction() + UNIT_REPRODUCE);
    newSharkCell.setCellEnergy(this.getCellEnergy() - UNIT_ENERGY);
    if (newSharkCell.getCellReproduction() >= REPRODUCTION_TIME) {
      setCellType(CellType.SHARK);
      this.setCellReproduction(0);
      newSharkCell.setCellReproduction(0);
      this.setCellEnergy(2);
    } else {
      setCellType(CellType.WATER);
      this.setCellReproduction(0);
      this.setCellEnergy(0);
    }
    isUpdated[newSharkCell.getRow()][newSharkCell.getColumn()] = true;
  }

  /**
   * Manages shark eating fish.
   *
   * @param isUpdated If cell has been updated, boolean for the cell is set to true.
   * @param localFish Surrounding fish cells.
   */
  private void manageSharkEatingFish(boolean[][] isUpdated, List<PPCellFeatures> localFish) {
    int randomFishIndex = getRandomIndex(localFish.size());
    PPCellFeatures newSharkCell = localFish.get(randomFishIndex);
    newSharkCell.setCellType(CellType.SHARK);
    newSharkCell.setCellReproduction(this.getCellReproduction() + UNIT_REPRODUCE);
    newSharkCell.setCellEnergy(this.getCellEnergy() + ENERGY_FROM_FISH - UNIT_ENERGY);
    if (newSharkCell.getCellReproduction() >= REPRODUCTION_TIME) {
      setCellType(CellType.SHARK);
      this.setCellReproduction(0);
      this.setCellEnergy(2);
      newSharkCell.setCellReproduction(0);
    } else {
      setCellType(CellType.WATER);
      this.setCellReproduction(0);
      this.setCellEnergy(0);
    }
    isUpdated[newSharkCell.getRow()][newSharkCell.getColumn()] = true;
  }

  /**
   * Manages when fish moves into surrounding water cell.
   *
   * @param isUpdated  If cell has been updated, boolean for the cell is set to true.
   * @param localWater Surrounding water cells.
   */
  private void manageFishMoving(boolean[][] isUpdated, List<PPCellFeatures> localWater) {
    int randomWaterIndex = getRandomIndex(localWater.size());
    PPCellFeatures newFishCell = localWater.get(randomWaterIndex);
    newFishCell.setCellType(CellType.FISH);
    newFishCell.setCellReproduction(this.getCellReproduction() + UNIT_REPRODUCE);
    this.setCellReproduction(0);
    if (newFishCell.getCellReproduction() >= REPRODUCTION_TIME) {
      setCellType(CellType.FISH);
      newFishCell.setCellReproduction(0);
    } else {
      setCellType(CellType.WATER);
    }
    isUpdated[newFishCell.getRow()][newFishCell.getColumn()] = true;
  }

  /**
   * Manages fish eaten by sharks.
   *
   * @param isUpdated   If cell has been updated, boolean for the cell is set to true.
   * @param localSharks Surrounding shark cells.
   */
  private void manageFishEatenBySharks(boolean[][] isUpdated, List<PPCellFeatures> localSharks) {
    int randomSharkIndex = getRandomIndex(localSharks.size());
    PPCellFeatures sharkCell = localSharks.get(randomSharkIndex);
    setCellType(CellType.SHARK);
    setCellReproduction(sharkCell.getCellReproduction() + UNIT_REPRODUCE);
    setCellEnergy(sharkCell.getCellEnergy() + ENERGY_FROM_FISH - UNIT_ENERGY);
    if (sharkCell.getCellReproduction() >= REPRODUCTION_TIME) {
      setCellReproduction(0);
      sharkCell.setCellReproduction(0);
      sharkCell.setCellType(CellType.SHARK);
      sharkCell.setCellEnergy(2);
    } else {
      sharkCell.setCellType(CellType.WATER);
      sharkCell.setCellReproduction(0);
      sharkCell.setCellEnergy(0);

    }
    isUpdated[sharkCell.getRow()][sharkCell.getColumn()] = true;
  }

  /**
   * Gets lists of the surrounding cells.
   *
   * @param neighbors    Cells from previous cycle.
   * @param newNeighbors Cells that have been updated this cycle.
   * @param localFish    Surrounding fish cells.
   * @param localWater   Surrounding water cells.
   * @param localSharks  Surrounding shark cells.
   */
  private void getLocalOrganisms(List<Cell> neighbors, List<Cell> newNeighbors,
      List<PPCellFeatures> localFish, List<PPCellFeatures> localWater,
      List<PPCellFeatures> localSharks) {
    for (int i = 0; i < neighbors.size(); i++) {
      Cell neighbor = neighbors.get(i);
      Cell newNeighbor = newNeighbors.get(i);
      if (neighbor.getState().name().equals("FISH") && newNeighbor.getState().name()
          .equals("FISH")) {
        localFish.add((PPCellFeatures) newNeighbor);
      }
      if (neighbor.getState().name().equals("SHARK") && newNeighbor.getState().name()
          .equals("SHARK")
          && ((PPCellFeatures) neighbor).getCellReproduction() != 0) {
        localSharks.add((PPCellFeatures) newNeighbor);
      }
      if (neighbor.getState().name().equals("WATER") && newNeighbor.getState().name()
          .equals("WATER")) {
        localWater.add((PPCellFeatures) newNeighbor);
      }
    }
  }

  /**
   * Get North, East, South, West neighbors.
   *
   * @param neighbors List of neighbors.
   */
  private void getFourNeighbors(List<Cell> neighbors, List<Cell> newNeighbors) {
    for (int i = neighbors.size() - 1; i >= 0; i--) {
      Cell neighbor = neighbors.get(i);
      Cell newNeighbor = newNeighbors.get(i);
      if ((neighbor.getRow() == this.getRow() - 1 && neighbor.getColumn() == this.getColumn() - 1)
          ||
          (neighbor.getRow() == this.getRow() - 1 && neighbor.getColumn() == this.getColumn() + 1)
          ||
          (neighbor.getRow() == this.getRow() + 1 && neighbor.getColumn() == this.getColumn() - 1)
          ||
          (neighbor.getRow() == this.getRow() + 1
              && neighbor.getColumn() == this.getColumn() + 1)) {
        neighbors.remove(i);
        newNeighbors.remove(i);
      }
    }
  }

  /**
   * Generate random index.
   *
   * @param rangeSize Range from which to generate random index.
   * @return Random index.
   */
  private int getRandomIndex(int rangeSize) {
    return (int) (Math.random() * rangeSize);
  }
}

