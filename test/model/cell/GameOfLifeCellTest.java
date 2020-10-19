package model.cell;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameOfLifeCellTest {
  private GameOfLifeCell aliveCell;
  private GameOfLifeCell deadCell;
  private GameOfLifeCell copyCell;

  private GameOfLifeCell neighbor1;
  private GameOfLifeCell neighbor2;
  private GameOfLifeCell neighbor3;
  private GameOfLifeCell neighbor4;


  @BeforeEach
  public void setup() {
    aliveCell = new GameOfLifeCell(2, 2, 1);
    deadCell = new GameOfLifeCell(2,2,0);
    copyCell = new GameOfLifeCell(aliveCell);

    neighbor1 = new GameOfLifeCell(1,2,0) ;
    neighbor2 = new GameOfLifeCell(2,3,0);
    neighbor3 = new GameOfLifeCell(3,2,0);
    neighbor4 = new GameOfLifeCell(2,1,0);


  }

  @Test
  public void liveCellWithFewerThanTwoLiveNeighbors() {
    neighbor1.setCellType(CellType.ALIVE);

    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    aliveCell.update(neighbors,newNeighbors,null);
    assertEquals(CellType.DEAD,aliveCell.getState());
  }

  @Test
  public void liveCellWithTwoAliveNeighbors() {

    neighbor1.setCellType(CellType.ALIVE);
    neighbor2.setCellType(CellType.ALIVE);
    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    aliveCell.update(neighbors,newNeighbors,null);
    assertEquals(CellType.ALIVE,aliveCell.getState());

  }

  @Test
  public void liveCellWithMoreThanThreeAliveNeighbors() {

    neighbor1.setCellType(CellType.ALIVE);
    neighbor2.setCellType(CellType.ALIVE);
    neighbor3.setCellType(CellType.ALIVE);
    neighbor4.setCellType(CellType.ALIVE);
    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    aliveCell.update(neighbors,newNeighbors,null);
    assertEquals(CellType.DEAD,aliveCell.getState());

  }

  @Test
  public void deadCellWithThreeAliveNeighbors() {

    neighbor1.setCellType(CellType.ALIVE);
    neighbor2.setCellType(CellType.ALIVE);
    neighbor3.setCellType(CellType.ALIVE);
    neighbor4.setCellType(CellType.DEAD);
    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    deadCell.update(neighbors,newNeighbors,null);
    assertEquals(CellType.ALIVE,deadCell.getState());

  }

  @Test
  public void ensureCopyCellIsWorking() {
    assertEquals(CellType.ALIVE,aliveCell.getState());
  }

}