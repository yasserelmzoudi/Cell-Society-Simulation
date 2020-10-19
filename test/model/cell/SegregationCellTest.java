package model.cell;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SegregationCellTest {

  private SegregationCell O;
  private SegregationCell X;
  private SegregationCell empty;
  private SegregationCell copyCell;

  private SegregationCell neighbor1;
  private SegregationCell neighbor2;
  private SegregationCell neighbor3;
  private SegregationCell neighbor4;
  private boolean[][] isUpdated;

  @BeforeEach
  public void setup() {
    O = new SegregationCell(2, 2, CellType.O.ordinal());
    X = new SegregationCell(2,2,CellType.X.ordinal());
    empty = new SegregationCell(2,2,CellType.NO_RACE.ordinal());
    copyCell = new SegregationCell(O);

    neighbor1 = new SegregationCell(1,2,CellType.NO_RACE.ordinal()) ;
    neighbor2 = new SegregationCell(2,1,CellType.NO_RACE.ordinal());
    //neighbor3 = new SegregationCell(3,2,CellType.NO_RACE.ordinal());
    //neighbor4 = new SegregationCell(2,1,CellType.NO_RACE.ordinal());

    isUpdated = new boolean[3][3];
  }


  @Test
  public void OisDissatisfied() {
    neighbor1.setCellType(CellType.X);
    neighbor2.setCellType(CellType.X);

    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2);

    List<Cell> emptyCells = new ArrayList<>();
    emptyCells.add(new SegregationCell(0,0,CellType.NO_RACE.ordinal()));
    SegregationCell.setEmptyCells(emptyCells);

    O.update(neighbors,newNeighbors,isUpdated);
    assertEquals(CellType.NO_RACE,O.getState());
  }

  @Test
  public void XisDissatisfied() {
    neighbor1.setCellType(CellType.O);
    neighbor2.setCellType(CellType.O);

    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2);

    List<Cell> emptyCells = new ArrayList<>();
    emptyCells.add(new SegregationCell(0,0,CellType.NO_RACE.ordinal()));
    SegregationCell.setEmptyCells(emptyCells);

    X.update(neighbors,newNeighbors,isUpdated);
    assertEquals(CellType.NO_RACE,X.getState());
  }


  @Test
  public void checkIfEmptyCellsAreAddedToList() {
    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2);
    empty.update(neighbors,newNeighbors,isUpdated);
    assertTrue(SegregationCell.getEmptyCells().size()==0);
  }

  @Test
  public void ensureCopyCellIsWorking() {
    assertEquals(CellType.O,copyCell.getState());
  }

}