package view.GamePaneShapes;


import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.grid.Grid;


public class RectangleGamePane extends GamePane{

    private Shape[][] allShapes;

    public RectangleGamePane(Grid grid, int width, int height){
        super(grid, width, height);
    }


    @Override
    public void makeArray(Grid myGrid) {
        allShapes = new Shape[myGrid.gridRows()][myGrid.gridColumns()];
        for (int r = 0; r < myGrid.gridRows(); r++) {
            for (int c = 0; c < myGrid.gridColumns(); c++) {
                Rectangle mynewPixel = new Rectangle( c*cellWidth(),r*cellHeight(), cellWidth(),cellHeight());
                allShapes[r][c] = mynewPixel;
            }
        }
    }

    @Override
    public Shape[][] getInitialArray() {
        return allShapes;
    }

}
