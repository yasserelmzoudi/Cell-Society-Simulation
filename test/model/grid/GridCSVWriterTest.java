package model.grid;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.InputStream;
import java.util.ResourceBundle;
import model.exceptions.InvalidSimulationSettingsFileException;
import model.exceptions.UnableToSaveFileException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GridCSVWriterTest {
  private static GridCSVWriter validGridWriter;
  private static SimulationSettingsReader previousSettings;
  private static PercolationGrid grid;
  private static ResourceBundle resources;
  private static InputStream data;
  private static final String VALID_READING_PATH = "/GameOfLife_Blinker.properties";

  @BeforeAll
  static void setUp() {
    resources = ResourceBundle.getBundle("GameOfLife_Blinker");
    data = Grid.class.getClassLoader()
        .getResourceAsStream(resources.getString("DataSourceCSV"));
    grid = new PercolationGrid(data, "Finite", "Complete");
    grid.performNextStep();
    previousSettings = new SimulationSettingsReader(VALID_READING_PATH);
    validGridWriter = new GridCSVWriter(grid, previousSettings.getSimulationTitle(), previousSettings.getSimulationAuthor(), previousSettings
        .getSimulationDescription(), previousSettings);
  }

  @Test
  void checkIfMandatoryFieldsProvided_ValidTest() {
    assertDoesNotThrow(() -> validGridWriter.checkIfMandatoryFieldsProvided());
  }

  @Test
  void checkIfMandatoryFieldsProvided_InvalidTest() {
    assertThrows(UnableToSaveFileException.class, () -> new GridCSVWriter(grid, "", "","", previousSettings));
  }

  @Test
  void saveFile_Valid() {
    assertDoesNotThrow(() -> validGridWriter.saveFile());
  }

  @Test
  void setUpSaveFile_Invalid() {
    assertThrows(UnableToSaveFileException.class, () -> new GridCSVWriter(grid, "", "","", previousSettings).setUpSaveFile());
  }

  @Test
  void saveFile_Invalid() {
    assertThrows(UnableToSaveFileException.class, () -> new GridCSVWriter(grid, "", "","", previousSettings).saveFile());
  }

  @Test
  void writeFile() {
  }
}