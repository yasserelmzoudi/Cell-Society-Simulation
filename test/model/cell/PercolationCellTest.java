package model.cell;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PercolationCellTest {

  private PercolationCell fullOpen;
  private PercolationCell emptyOpen;
  private PercolationCell copyCell;

  private PercolationCell neighbor1;
  private PercolationCell neighbor2;
  private PercolationCell neighbor3;
  private PercolationCell neighbor4;


  @BeforeEach
  public void setup() {
    emptyOpen = new PercolationCell(2, 2, CellType.EMPTY_OPEN.ordinal());
    fullOpen = new PercolationCell(2,2,CellType.FULL_OPEN.ordinal());
    copyCell = new PercolationCell(emptyOpen);

    neighbor1 = new PercolationCell(1,2,CellType.EMPTY_OPEN.ordinal()) ;
    neighbor2 = new PercolationCell(2,3,CellType.EMPTY_OPEN.ordinal());
    neighbor3 = new PercolationCell(3,2,CellType.EMPTY_OPEN.ordinal());
    neighbor4 = new PercolationCell(2,1,CellType.EMPTY_OPEN.ordinal());
  }

  @Test
  public void neighborIsFull() {
    neighbor1.setCellType(CellType.FULL_OPEN);

    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    emptyOpen.update(neighbors,newNeighbors,null);
    assertEquals(CellType.FULL_OPEN,emptyOpen.getState());
  }

  @Test
  public void neighborIsNotFull() {

    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    emptyOpen.update(neighbors,newNeighbors,null);
    assertEquals(CellType.EMPTY_OPEN,emptyOpen.getState());
  }

  @Test
  public void ensureCopyCellIsWorking() {
    assertEquals(CellType.EMPTY_OPEN,copyCell.getState());
  }

}