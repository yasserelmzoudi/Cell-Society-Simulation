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
  private RockPaperScissorsCell copyCell;

  private RockPaperScissorsCell neighbor1;
  private RockPaperScissorsCell neighbor2;
  private RockPaperScissorsCell neighbor3;
  private RockPaperScissorsCell neighbor4;
  private int THRESHOLD;


  @BeforeEach
  public void setup() {
    rock = new RockPaperScissorsCell(2, 2, CellType.ROCK.ordinal());
    paper = new RockPaperScissorsCell(2,2,CellType.PAPER.ordinal());
    scissors = new RockPaperScissorsCell(2,2,CellType.SCISSORS.ordinal());
    copyCell = new RockPaperScissorsCell(rock);

    neighbor1 = new RockPaperScissorsCell(1,2,CellType.ROCK.ordinal()) ;
    neighbor2 = new RockPaperScissorsCell(2,3,CellType.ROCK.ordinal());
    neighbor3 = new RockPaperScissorsCell(3,2,CellType.ROCK.ordinal());
    neighbor4 = new RockPaperScissorsCell(2,1,CellType.ROCK.ordinal());
    THRESHOLD = RockPaperScissorsCell.getTHRESHOLD();
  }

  @Test
  public void rockBeatsScissors() {

    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    scissors.update(neighbors,newNeighbors,null);
    assertEquals(CellType.ROCK,scissors.getState());
  }


  @Test
  public void paperBeatsRock() {
    neighbor1.setCellType(CellType.PAPER);
    neighbor2.setCellType(CellType.PAPER);
    neighbor3.setCellType(CellType.PAPER);


    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    rock.update(neighbors,newNeighbors,null);
    assertEquals(CellType.PAPER,rock.getState());
  }


  @Test
  public void scissorsBeatsPaper() {
    neighbor1.setCellType(CellType.SCISSORS);
    neighbor2.setCellType(CellType.SCISSORS);
    neighbor3.setCellType(CellType.SCISSORS);

    List<Cell> neighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);
    List<Cell> newNeighbors = Arrays.asList(neighbor1,neighbor2,neighbor3,neighbor4);

    paper.update(neighbors,newNeighbors,null);
    assertEquals(CellType.SCISSORS,paper.getState());
  }


  @Test
  public void ensureCopyCellIsWorking() {
    assertEquals(CellType.ROCK,copyCell.getState());
  }


}