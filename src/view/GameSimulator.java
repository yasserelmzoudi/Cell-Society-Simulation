package view;

import model.cell.Cell;
import model.grid.Grid;

import javax.swing.*;
import java.awt.*;

public class GameSimulator extends JPanel {
    private int gridRows, gridColumns;
    private static JFrame gridFrame = new JFrame();
    private Grid myGrid;
    private int appHeight;
    private int appWidth;
    private Cell[][] cellArray;
    private int cellHeight;
    private int cellWidth;
    private GameSimulator currentSimulator;

    public GameSimulator(Grid grid, int appheight, int appwidth) {
        myGrid = grid;
        appHeight = appheight;
        appWidth = appwidth;
        cellArray = grid.getAllCells();
        gridFrame.setTitle("Conways's Game of Life Simulator");
        gridColumns = cellArray[0].length;
        gridRows = cellArray.length;
        cellHeight = appheight / gridRows;
        cellWidth = appwidth / gridColumns;
        gridFrame.setSize(appwidth+15, appheight+30); //need to fix
        gridFrame.setDefaultCloseOperation(gridFrame.EXIT_ON_CLOSE);
        gridFrame.setVisible(true);
        gridFrame.add(this);
        this.setBackground(Color.black);
        currentSimulator = this;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int r = 0; r < gridRows; r++) {
            for (int c = 0; c < gridColumns; c++) {
                if (cellArray[r][c].isAlive()) {
                    g.setColor(new Color(0,102, 0));
                    g.fillRect((c * cellHeight),r * cellWidth, cellWidth, cellHeight);
                } else {
                    g.setColor(new Color(153, 0 , 0));
                    g.fillRect((c * cellHeight),r * cellWidth, cellWidth, cellHeight);
                }
              }
        }
    }

    public GameSimulator loadnewgrid(Grid grid) {
        currentSimulator = new GameSimulator(grid, appHeight, appWidth);
        return currentSimulator;
    }

}
