package model.cell;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RockPaperScissorsCellTest {

  private RockPaperScissorsCell rock;
  private RockPaperScissorsCell paper;
  private RockPaperScissorsCell scissors;
  private RockPaperScissorsCell copycell;

  private RockPaperScissorsCell neighbor1;
  private RockPaperScissorsCell neighbor2;
  private RockPaperScissorsCell neighbor3;
  private RockPaperScissorsCell neighbor4;

/*
  @BeforeEach
  public void setup() {
    rock = new RockPaperScissorsCell(2, 2, CellType.ROCK.ordinal());
    paper = new RockPaperScissorsCell(2,2,CellType.PAPER.ordinal());
    scissors = new RockPaperScissorsCell(2,2,CellType.SCISSORS.ordinal());
    copyCell = new RockPaperScissorsCell(emptyOpen);

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

*/
}