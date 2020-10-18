package model.grid;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class GameOfLifeGridTest {

  @Test
  public void GameOfLifeGridTest() {
    ResourceBundle resources = ResourceBundle.getBundle("resources.data");
    InputStream data = Grid.class.getClassLoader()
        .getResourceAsStream(resources.getString("TestSource"));
    //Scanner s = new Scanner(data).useDelimiter("\\A");
    //System.out.println(s.hasNext() ? s.next() : "");
    Grid grid = new PredatorPreyGrid(data,"Finite","Complete");
    //System.out.println(Arrays.deepToString();
    /*List<String[]> expectedReading = List.of(new String[]{"3", "3"}, new String[]{"1", "0", "1"},
        new String[]{"0", "1", "1"}, new String[]{"1", "1", "1"});
    List<String[]> actualReading = grid.readAll();
    System.out.println(actualReading);
    for (int row = 0; row < expectedReading.size(); row++) {
      assertArrayEquals(expectedReading.get(row), actualReading.get(row));
    }*/

  }


}