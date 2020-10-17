package view.GamePaneShapes;

import javafx.scene.shape.Shape;
import model.grid.Grid;
import view.Shapes.Triangle;


public class TriangleGamePane extends GamePane {
    private Shape[][] allShapes;

    public TriangleGamePane(Grid grid, int width, int height){
        super(grid, width, height);
    }

    private double triangleBase() {
        return cellWidth()/1.5;
    }

    private double traingleHeight() {
        return cellHeight();
    }


    @Override
    public void makeArray(Grid myGrid) {
        allShapes = new Triangle[myGrid.gridRows()][myGrid.gridColumns()];
        for (int r = 0; r < myGrid.gridRows(); r++) {
            for (int c = 0; c < myGrid.gridColumns(); c++) {
                Triangle mynewPixel = new Triangle(r, c, triangleBase(), traingleHeight());
                allShapes[r][c] = mynewPixel;
            }
        }
    }

    @Override
    public Shape[][] getInitialArray() {
        return allShapes;
    }

}
