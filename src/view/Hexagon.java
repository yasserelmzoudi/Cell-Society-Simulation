package view;

import javafx.scene.shape.Polygon;
import model.grid.Grid;

public class Hexagon extends Polygon{
    private static final int GRID_PADDING_LR =200;
    private int myRow;
    private int myColumn;
    private Double[] pointArray;
    public Hexagon (int row, int column, double hexWidth, double hexLength) {
        myRow = row;
        myColumn =column;
        makeInitialShape(myRow, myColumn, hexWidth, hexLength);

    }

    public void makeInitialShape(double row, double col, double hexWidth, double hexLength) {
        if (row % 2 == 1) {
            col = col + 0.5;
            row = row + row / 2;
        } else if (row != 0 && row % 2 == 0) {
            row = row + row / 2;
        }
        row = row + 0.5;
        pointArray = new Double[]{
                 col * hexWidth, row * hexLength,
                (col + 0.5) * hexWidth, (row - 0.5) * hexLength,
                (col + 1) * hexWidth,  row * hexLength,
                (col + 1) * hexWidth,  (row + 1) * hexLength,
                 (col + 0.5) * hexWidth,  (row + 1.5) * hexLength,
                col * hexWidth,  (row + 1) * hexLength
        };
        this.getPoints().addAll(pointArray);
    }



}
