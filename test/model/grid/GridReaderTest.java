package model.grid;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.InputStream;
import java.util.List;
import java.util.ResourceBundle;
import org.junit.jupiter.api.Test;

public class GridReaderTest {

  @Test
  public void testReadAll_simpleGrid() {
    ResourceBundle resources = ResourceBundle.getBundle("resources.data");
    InputStream data = Grid.class.getClassLoader().getResourceAsStream(resources.getString("TestSource"));
    Grid grid = new GameOfLifeGrid(data);
    List<String[]> expectedReading = List.of(new String[]{"3", "3"}, new String[]{"1", "0", "1"},
        new String[]{"0", "1", "1"}, new String[]{"1", "1", "1"});
    List<String[]> actualReading = grid.readAll();

    for (int row = 0; row < expectedReading.size(); row++) {
      assertArrayEquals(expectedReading.get(row), actualReading.get(row));
    }
  }
}