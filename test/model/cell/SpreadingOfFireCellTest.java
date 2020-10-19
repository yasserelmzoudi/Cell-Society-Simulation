package model.cell;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpreadingOfFireCellTest {

  private SpreadingOfFireCell tree;
  private SpreadingOfFireCell burning;
  private SpreadingOfFireCell empty;
  private SpreadingOfFireCell copyCell;

  private SpreadingOfFireCell neighbor1;
  private SpreadingOfFireCell neighbor2;
  private SpreadingOfFireCell neighbor3;
  private SpreadingOfFireCell neighbor4;


  @BeforeEach
  public void setup() {
    tree = new SpreadingOfFireCell(2, 2, CellType.TREE.ordinal());
    burning = new SpreadingOfFireCell(2,2,CellType.BURNING.ordinal());
    empty = new SpreadingOfFireCell(2,2,CellType.EMPTY.ordinal());
    copyCell = new SpreadingOfFireCell(tree);

    neighbor1 = new SpreadingOfFireCell(1,2,CellType.EMPTY.ordinal()) ;
    neighbor2 = new SpreadingOfFireCell(2,3,CellType.EMPTY.ordinal());
    neighbor3 = new SpreadingOfFireCell(3,2,CellType.EMPTY.ordinal());
    neighbor4 = new SpreadingOfFireCell(2,1,CellType.EMPTY.ordinal());
  }

  @Test
  public void neighborIsFull() {
    neighbor1.setCellType(CellType.BURNING);
    SpreadingOfFireCell.setProbCatch(1);

    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    tree.update(neighbors,newNeighbors,null);
    assertEquals(CellType.BURNING,tree.getState());
  }

}