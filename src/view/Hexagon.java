package view;

import javafx.scene.shape.Polygon;
import model.grid.Grid;

public class Hexagon extends Polygon{
    private static final int GRID_PADDING_LR =200;
    private int myRow;
    private int myColumn;
    private Double[] pointArray;
    private int upperLeftX;
    private int upperLeftY;
    public Hexagon (int row, int column, double hexLength, int upperX, int upperY) {
        myRow = row;
        myColumn =column;
        upperLeftX = upperX;
        upperLeftY = upperY;
        makeInitialShape(myRow, myColumn, hexLength);

    }

    public void makeInitialShape(double row, double col, double sideLength) {
        if (row % 2 == 1) {
            col = col + 0.5;
            row = row + row / 2;
        } else if (row != 0 && row % 2 == 0) {
            row = row + row / 2;
        }
        row = row + 0.5;
        pointArray = new Double[]{
                getUpperLeftX() + col * sideLength, getUpperLeftY() + row * sideLength,
                getUpperLeftX() + (col + 0.5) * sideLength, getUpperLeftY() + (row - 0.5) * sideLength,
                getUpperLeftX() + (col + 1) * sideLength, getUpperLeftY() + row * sideLength,
                getUpperLeftX() + (col + 1) * sideLength, getUpperLeftY() + (row + 1) * sideLength,
                getUpperLeftX() + (col + 0.5) * sideLength, getUpperLeftY() + (row + 1.5) * sideLength,
                getUpperLeftX() + col * sideLength, getUpperLeftY() + (row + 1) * sideLength
        };
        this.getPoints().addAll(pointArray);
    }


    private int getUpperLeftX () {
        return upperLeftX;
    }

    private int getUpperLeftY() {
        return upperLeftY;
    }

}
