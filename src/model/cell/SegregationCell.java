package model.cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SegregationCell extends Cell {
  private int state;
  private List<String> needsPlacement;
  private static final int THRESHOLD = 30;

  public SegregationCell(int row, int column, int state) {
    super(row, column, state);
    this.state = state;
    this.needsPlacement = new ArrayList<String>();
  }

  public SegregationCell(Cell copyCell) {
    super(copyCell);
  }

  @Override
  public void update(List<Cell> neighbors, List<Cell> newNeighbors, boolean[][] isUpdated) {

    double XCount = 0;
    double OCount = 0;
    double totalCount = 0;
    for (Cell neighbor : neighbors) {
      if (neighbor.getState().name().equals("X")) {
        XCount++;
        totalCount++;
      }
      if (neighbor.getState().name().equals("O"))  {
        OCount++;
        totalCount++;
      }
    }

    double percentageX = (XCount / totalCount) * 100;
    double percentageO = (OCount / totalCount) * 100;


    if ((getState().name().equals("X")) && (percentageX < THRESHOLD)) {
      this.needsPlacement.add("X");
      setCellType(CellType.NO_RACE);
    }
    else if ((getState().name().equals("O")) && (percentageO < THRESHOLD)) {
      needsPlacement.add("O");
      setCellType(CellType.NO_RACE);
    }
    else if ((getState().name().equals("NO_RACE")) && ((needsPlacement.size())!=0)) {
      String agentType = needsPlacement.get(0);
        needsPlacement.remove(0);
        if (agentType.equals("X")) {
          setCellType(CellType.X);
        }
        else if(agentType.equals("O")) {
          setCellType(CellType.O);
        }
      }
    }

  }