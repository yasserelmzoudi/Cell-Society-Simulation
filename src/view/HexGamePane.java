package view;

import javafx.scene.shape.Shape;
import model.grid.Grid;


public class HexGamePane extends GamePane {
    private Shape[][] allShapes;
    int gridWidth;

    public HexGamePane(Grid grid, int width, int height){
        super(grid, width, height);
    }


    private double determinePolyLength(Grid myGrid) {
        double maxHeight = getGridWidth()/ (myGrid.gridRows()*1.7);

        return maxHeight;
    }


    @Override
    public void makeArray(Grid myGrid) {
        allShapes = new Hexagon[myGrid.gridRows()][myGrid.gridColumns()];
        for (int r = 0; r < myGrid.gridRows(); r++) {
            for (int c = 0; c < myGrid.gridColumns(); c++) {
                Hexagon mynewPixel = new Hexagon(r, c, determinePolyLength(myGrid), 180, 20);
                //TODO change this to not be magic values
                allShapes[r][c] = mynewPixel;
            }
        }
    }

    @Override
    public Shape[][] getInitialArray() {
        return allShapes;
    }

}
