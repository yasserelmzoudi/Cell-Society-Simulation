package view;

import model.cell.Cell;
import model.grid.Grid;
import model.grid.GridReader;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class GamePanel extends JPanel {
    private Grid myGrid;
    private Dimension framesize;
    private int gridColumns;
    private int gridRows;

    public GamePanel(Grid grid, Dimension framedimensions) {
        myGrid = grid;
        framesize = framedimensions;
        gridColumns = myGrid.getAllCells()[0].length;
        gridRows = myGrid.getAllCells().length;
    }

    public void updategrid(Grid grid) {
        myGrid = grid;
        super.update(this.getGraphics());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellHeight = (int) myGrid.cellHeight(framesize);
        int cellWidth = (int) myGrid.cellWidth(framesize);
        //System.out.format("Cell Height: %d, Frame Height: %d, Number of Columns: %d \n", cellHeight, (int) framesize.getHeight(), gridRows);
        //System.out.format("Cell Width: %d, Frame Width: %d, Number of Rows: %d \n", cellWidth, (int) framesize.getWidth(), gridColumns);
        for (int r = 0; r < gridRows; r++) {
            for (int c = 0; c < gridColumns; c++) {
                if (myGrid.getAllCells()[r][c].isAlive()) {
                    g.setColor(new Color(0,102, 0));
                } else {
                    g.setColor(new Color(153, 0 , 0));
                }
                g.fillRect((c * cellWidth),r * cellHeight, cellWidth, cellHeight);
            }
        }
    }
}
