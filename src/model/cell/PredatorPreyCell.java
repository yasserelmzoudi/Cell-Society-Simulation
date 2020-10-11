package model.cell;

import java.util.ArrayList;
import java.util.List;

public class PredatorPreyCell extends PPCellFeatures {

  private int state;
  private int reproduce;
  private int energy;

  public PredatorPreyCell(int row, int column, int state, int reproduce, int energy) {
    super(row, column, state, reproduce, energy);
    this.state = state;
    this.reproduce = reproduce;
    this.energy = energy;
  }

  public PredatorPreyCell(Cell copyCell) {
    super(copyCell);
  }


  @Override
  public void update(List<Cell> neighbors, List<Cell> newNeighbors, boolean[][] isUpdated) {
/*
    int[] indexes = {7,5,2,0};
    for (int index : indexes) {
      neighbors.remove(index);
      newNeighbors.remove(index);
    }
*/
    for (int i = neighbors.size()-1; i >= 0; i--) {
      Cell neighbor = neighbors.get(i);
      Cell newNeighbor = newNeighbors.get(i);
      if ((neighbor.getRow() == this.getRow() - 1 && neighbor.getColumn() == this.getColumn() - 1) ||
      (neighbor.getRow() == this.getRow() - 1 && neighbor.getColumn() == this.getColumn() + 1) ||
      (neighbor.getRow() == this.getRow() + 1 && neighbor.getColumn() == this.getColumn() - 1) ||
      (neighbor.getRow() == this.getRow() + 1 && neighbor.getColumn() == this.getColumn() + 1)) {
        neighbors.remove(i);
        newNeighbors.remove(i);
      }
    }

    List<PPCellFeatures> localFish = new ArrayList<>();
    List<PPCellFeatures> localWater = new ArrayList<>();
    List<PPCellFeatures> localSharks = new ArrayList<>();

    for (int i = 0; i < neighbors.size(); i++) {
      Cell neighbor = neighbors.get(i);
      Cell newNeighbor = newNeighbors.get(i);
      if (neighbor.getState().name().equals("FISH") && newNeighbor.getState().name().equals("FISH")) {
        localFish.add((PPCellFeatures) newNeighbor);
      }
      if (neighbor.getState().name().equals("SHARK") && newNeighbor.getState().name().equals("SHARK")
      && ((PPCellFeatures) neighbor).getCellReproduction() != 0) {
        localSharks.add((PPCellFeatures) newNeighbor);
      }
      if (neighbor.getState().name().equals("WATER") && newNeighbor.getState().name().equals("WATER")) {
        localWater.add((PPCellFeatures) newNeighbor);
      }
    }


    if ((getState().name().equals("FISH"))) {

      if (!localSharks.isEmpty()) {
        int randomSharkIndex = getRandomIndex(localSharks.size());
        PPCellFeatures sharkCell = localSharks.get(randomSharkIndex);
        setCellType(CellType.SHARK);
        setCellReproduction(sharkCell.getCellReproduction()+1);
        setCellEnergy(sharkCell.getCellEnergy()+3-1);
        if (sharkCell.getCellReproduction()>=5) {
          setCellReproduction(0);
          sharkCell.setCellReproduction(0);
          sharkCell.setCellType(CellType.SHARK);
          sharkCell.setCellEnergy(2);
        }
        else {
          sharkCell.setCellType(CellType.WATER);
          sharkCell.setCellReproduction(0);
          sharkCell.setCellEnergy(0);

        }
        isUpdated[sharkCell.getRow()][sharkCell.getColumn()] = true;
      }

      else if (!localWater.isEmpty()) {
        int randomWaterIndex = getRandomIndex(localWater.size());
        PPCellFeatures newFishCell = localWater.get(randomWaterIndex);
        newFishCell.setCellType(CellType.FISH);
        newFishCell.setCellReproduction(this.getCellReproduction()+1);
        this.setCellReproduction(0);
        //if (this.reproduce >= 5) {
        if (newFishCell.getCellReproduction() >= 5) {
          setCellType(CellType.FISH);
          //this.setCellReproduction(0);
          newFishCell.setCellReproduction(0);
        }
        else {
          setCellType(CellType.WATER);
        //  this.setCellReproduction(0);
        }
        isUpdated[newFishCell.getRow()][newFishCell.getColumn()] = true;
      }
      else {
        setCellType(CellType.FISH);
        this.setCellReproduction(this.getCellReproduction() + 1);
      }

    }

    else if ((getState().name().equals("SHARK"))) {

      if (this.getCellEnergy() == 0) {
        setCellType(CellType.WATER);
        this.setCellReproduction(0);
      }

      else if(!localFish.isEmpty()) {
        int randomFishIndex = getRandomIndex(localFish.size());
        PPCellFeatures newSharkCell = localFish.get(randomFishIndex);
        newSharkCell.setCellType(CellType.SHARK);
        newSharkCell.setCellReproduction(this.getCellReproduction()+1);
        newSharkCell.setCellEnergy(this.getCellEnergy()+3-1);
        //if (this.reproduce >= 5) {
        if (newSharkCell.getCellReproduction() >= 5) {
          setCellType(CellType.SHARK);
          this.setCellReproduction(0);
          this.setCellEnergy(2);
          newSharkCell.setCellReproduction(0);
        }
        else {
          setCellType(CellType.WATER);
          this.setCellReproduction(0);
          this.setCellEnergy(0);
        }
        isUpdated[newSharkCell.getRow()][newSharkCell.getColumn()] = true;

      }

      else if (!localWater.isEmpty()) {
        int randomWaterIndex = getRandomIndex(localWater.size());
        PPCellFeatures newSharkCell = localWater.get(randomWaterIndex);
        newSharkCell.setCellType(CellType.SHARK);
        newSharkCell.setCellReproduction(this.getCellReproduction()+1);
        newSharkCell.setCellEnergy(this.getCellEnergy()-1);
        //if (this.reproduce >= 5) {
        if (newSharkCell.getCellReproduction() >= 5) {
          setCellType(CellType.SHARK);
          this.setCellReproduction(0);
          newSharkCell.setCellReproduction(0);
          this.setCellEnergy(2);
        }
        else {
          setCellType(CellType.WATER);
          this.setCellReproduction(0);
          this.setCellEnergy(0);
        }
        isUpdated[newSharkCell.getRow()][newSharkCell.getColumn()] = true;
      }

      else {
        this.setCellReproduction(this.getCellReproduction()+1);
        this.setCellEnergy(this.getCellEnergy()-1);
        this.setCellType(CellType.SHARK);
      }

    }
  }

  private int getRandomIndex(int rangeSize) {
    return (int)(Math.random() * rangeSize);
  }

  }

