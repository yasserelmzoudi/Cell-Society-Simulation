package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.cell.Cell;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CellSocietyTest extends ApplicationTest {

    private int gridHeight =700;
    private int gridWidth = 700;


    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
    }

    @Test
    void made() {
        assertTrue(new CellSociety() !=null);
    }
}