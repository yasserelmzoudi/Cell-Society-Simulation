package view.Shapes;

import javafx.scene.shape.Polygon;

public class Triangle extends Polygon {
    private double myRow;
    private double myColumn;
    private Double[] pointArray;
    private double myWidth;
    private double myHeight;

    public Triangle(int row, int column, double width, double length) {
        myRow = row;
        myColumn = column;
        myWidth = width;
        myHeight = length;
        makeInitialShape();

    }

    private void makeInitialShape() {


        if (myRow % 2 != 0 && myColumn % 2 != 0) {
            uprightOddRow();
        } else if (myRow % 2 == 0 && myColumn %2 == 0) {
            uprightEvenRow();
        } else if (myRow % 2 == 0 && myColumn%2 != 0) {
            invertedEvenRow();
        } else if (myRow % 2 != 0 &&  myColumn % 2 == 0) {
            invertedOddRow();
        }
        this.getPoints().addAll(pointArray);
    }


    private void uprightEvenRow() {

        // triangle is in even row and points upwards
        pointArray = new Double[]{
                (myColumn) * myWidth/2.0, myRow*myHeight,
                (myColumn) * myWidth/2.0+myWidth/2.0, (myRow+1) * myHeight,
                (myColumn) * myWidth/2.0 -myWidth/2.0, (myRow +1) * myHeight
        };
    }

    private void invertedEvenRow() {
        // triangle is in even row and points downwards
        pointArray = new Double[]{
                (myColumn*myWidth/2.0) - myWidth/2.0, myRow*myHeight,
                (myColumn*myWidth/2.0) + myWidth/2.0, myRow* myHeight,
                myColumn*myWidth/2.0 , (myRow + 1)*myHeight
        };
    }

    private void invertedOddRow() {
        pointArray = new Double[]{
                (myColumn) * myWidth/2.0, (myRow+1)*myHeight,
                (myColumn) * myWidth/2.0+myWidth/2.0, myRow * myHeight,
                (myColumn) * myWidth/2.0 -myWidth/2.0, myRow * myHeight
        };
    }

    private void uprightOddRow() {
        pointArray = new Double[]{
                (myColumn* myWidth/2.0), myRow*myHeight,
                (myColumn* myWidth/2.0) - myWidth/2.0, (myRow+1)*myHeight,
                (myColumn* myWidth/2.0) + myWidth/2.0, (myRow+1)*myHeight
        };
    }

    }