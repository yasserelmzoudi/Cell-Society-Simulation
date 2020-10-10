package view;


import javafx.scene.Node;

import javafx.scene.layout.GridPane;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import model.grid.Grid;


public class GamePane extends GridPane {
    private Grid myGrid;
    private int windowHeight;
    private int windowWidth;




    public GamePane(Grid grid, int width, int height){
        windowHeight = width;
        windowWidth=height;
        myGrid = grid;
    }


    public void setUpPane(Grid grid) {
        //System.out.format("Cell Height: %d, Frame Height: %d, Number of Columns: %d \n", cellHeight, (int) framesize.getHeight(), gridRows);
        //System.out.format("Cell Width: %d, Frame Width: %d, Number of Rows: %d \n", cellWidth, (int) framesize.getWidth(), gridColumns);
        myGrid = grid;
        for (int r = 0; r < myGrid.gridRows(); r++) {
            for (int c = 0; c < myGrid.gridColumns(); c++) {
                Rectangle myPixel = getNodeFromGridPane(r,c);
                String state = "ALIVE".toLowerCase();
                if(myPixel==null) {
                    myPixel = new Rectangle(myGrid.cellWidth(windowWidth) , myGrid.cellHeight(windowHeight));
                }
                else{
                    this.getChildren().remove(myPixel);
                }
                myPixel.getStyleClass().add(state);
                //myPixel.setFill(status); //change to based on status
                this.setRowIndex(myPixel, r);
                this.setColumnIndex(myPixel, c);
                this.getChildren().add(myPixel);
            }
        }
    }

    private Rectangle getNodeFromGridPane(int row, int col) {
        for (Node node : this.getChildren()) {
            if (node instanceof Rectangle && GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return (Rectangle) node;
            }
        }
        return null;
    }

}
