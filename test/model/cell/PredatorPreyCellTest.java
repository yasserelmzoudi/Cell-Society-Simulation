package model.cell;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PredatorPreyCellTest {

  private PredatorPreyCell shark;
  private PredatorPreyCell fish;
  private PredatorPreyCell water;
  private PredatorPreyCell copyCell;

  private PredatorPreyCell neighbor1;
  private PredatorPreyCell neighbor2;
  private PredatorPreyCell neighbor3;
  private PredatorPreyCell neighbor4;
  private boolean[][] isUpdated;


  @BeforeEach
  public void setup() {
    shark = new PredatorPreyCell(2, 2, CellType.SHARK.ordinal(),2,2);
    fish = new PredatorPreyCell(2,2,CellType.FISH.ordinal(),2,2);
    water = new PredatorPreyCell(2,2,CellType.FISH.ordinal(),2,2);

    copyCell = new PredatorPreyCell(shark);

    neighbor1 = new PredatorPreyCell(1,2,CellType.WATER.ordinal(),2,2);
    neighbor2 = new PredatorPreyCell(2,3,CellType.WATER.ordinal(),2,2);
    neighbor3 = new PredatorPreyCell(3,2,CellType.WATER.ordinal(),2,2);
    neighbor4 = new PredatorPreyCell(2,1,CellType.WATER.ordinal(),2,2);

    isUpdated = new boolean[4][4];
  }

  @Test
  public void fishEatenByShark() {
    neighbor1.setCellType(CellType.SHARK);
    neighbor2.setCellType(CellType.SHARK);
    neighbor3.setCellType(CellType.SHARK);
    neighbor4.setCellType(CellType.SHARK);


    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    fish.update(neighbors,newNeighbors,isUpdated);
    assertEquals(CellType.SHARK,fish.getState());
  }

  @Test
  public void sharkEatsFish() {
    neighbor1.setCellType(CellType.FISH);
    neighbor2.setCellType(CellType.FISH);
    neighbor3.setCellType(CellType.FISH);
    neighbor4.setCellType(CellType.FISH);

    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    shark.update(neighbors,newNeighbors,isUpdated);
    assertEquals(CellType.WATER,shark.getState());

  }

  @Test
  public void sharkCannotMove() {
    neighbor1.setCellType(CellType.SHARK);
    neighbor2.setCellType(CellType.SHARK);
    neighbor3.setCellType(CellType.SHARK);
    neighbor4.setCellType(CellType.SHARK);

    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    shark.update(neighbors,newNeighbors,isUpdated);
    assertEquals(CellType.SHARK,shark.getState());

  }

  @Test
  public void fishCannotMove() {
    neighbor1.setCellType(CellType.FISH);
    neighbor2.setCellType(CellType.FISH);
    neighbor3.setCellType(CellType.FISH);
    neighbor4.setCellType(CellType.FISH);

    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    fish.update(neighbors,newNeighbors,isUpdated);
    assertEquals(CellType.FISH,fish.getState());

  }

  @Test
  public void fishMoving() {

    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    fish.update(neighbors,newNeighbors,isUpdated);
    assertEquals(CellType.WATER,fish.getState());

  }

  @Test
  public void sharkMoving() {

    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    shark.update(neighbors,newNeighbors,isUpdated);
    assertEquals(CellType.WATER,shark.getState());

  }

  @Test
  public void checkIfSharkReproduces() {
    shark.setCellReproduction(5);

    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    shark.update(neighbors,newNeighbors,isUpdated);
    assertEquals(CellType.SHARK,shark.getState());
  }

  @Test
  public void checkIfFishReproduces() {
    fish.setCellReproduction(5);

    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    fish.update(neighbors,newNeighbors,isUpdated);
    assertEquals(CellType.FISH,fish.getState());
  }


  @Test
  public void checkIfSharkReproducesWhenEatingFish() {
    neighbor1.setCellType(CellType.FISH);
    shark.setCellReproduction(5);

    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    shark.update(neighbors,newNeighbors,isUpdated);
    assertEquals(CellType.SHARK,shark.getState());
  }

  @Test
  public void checkIfSharkReproducesNeighboringFishIsEaten() {
    neighbor1.setCellType(CellType.SHARK);
    neighbor1.setCellReproduction(5);

    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    fish.update(neighbors,newNeighbors,isUpdated);
    assertEquals(CellType.SHARK,fish.getState());
  }

  @Test
  public void checkIfSharkDiesWhenEnergyIs0() {

    shark.setCellEnergy(0);

    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    shark.update(neighbors,newNeighbors,isUpdated);
    assertEquals(CellType.WATER,shark.getState());
  }

  @Test
  public void ensureCopyCellIsWorking() {
    assertEquals(CellType.SHARK,copyCell.getState());

  }

}