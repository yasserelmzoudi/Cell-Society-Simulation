package model.grid;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ResourceBundle;
import org.junit.jupiter.api.Test;

public class FileReaderTest {

  @Test
  public void testGridRead_simpleGrid() {
    ResourceBundle resources = ResourceBundle.getBundle("resources.data");
    FileReader gridReader = new FileReader();
    List<String[]> expectedOutput = List.of(new String[]{"3","3"}, new String[]{"1","0","1"},
        new String[]{"0", "1", "1"}, new String[]{"1", "1", "1"});
    List<String[]> actualOutput = gridReader.readAll(FileReader.class.getClassLoader().getResourceAsStream(resources.getString("DataSource")));
    for (int row = 0; row < expectedOutput.size(); row++) {
      assertArrayEquals(expectedOutput.get(row), actualOutput.get(row));
    }
  }
}