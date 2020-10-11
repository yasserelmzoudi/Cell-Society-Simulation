package model.cell;

import java.util.List;

public class RockPaperScissorsCell extends Cell {
  private int state;
  private static final int THRESHOLD = 4;

  public RockPaperScissorsCell(int row, int column, int state) {
    super(row, column, state);
    this.state = state;
  }

  public RockPaperScissorsCell(Cell copyCell) {
    super(copyCell);
  }

  @Override
  public void update(List<Cell> neighbors, List<Cell> newNeighbors, boolean[][] isUpdated) {
    int rock = 0;
    int paper = 0;
    int scissors = 0;

    for (Cell neighbor : neighbors) {
      switch (neighbor.getState().name()) {
        case "ROCK" -> rock++;
        case "PAPER" -> paper++;
        case "SCISSORS" -> scissors++;
      }
    }

    changeCellType(rock, paper, scissors);

  }

  private void changeCellType(int rock, int paper, int scissors) {
    if ((getState().name().equals("ROCK")) && (paper > THRESHOLD)) {
      setCellType(CellType.PAPER);
    } else if ((getState().name().equals("PAPER")) && (scissors > THRESHOLD)) {
      setCellType(CellType.SCISSORS);
    } else if ((getState().name().equals("SCISSORS")) && (rock > THRESHOLD)) {
      setCellType(CellType.ROCK);
    }
  }

}
