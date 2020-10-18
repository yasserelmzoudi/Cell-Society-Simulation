package model.grid;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.exceptions.InvalidCSVFileException;
import model.exceptions.InvalidSimulationSettingsFileException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SimulationSettingsReaderTest {
  private static final String VALID_READING_PATH = "/validSimulationReading.properties";
  private static final String ERROR_READING_PATH = "/errorSimulationReading.properties";
  private static final String INVALID_READING_PATH = "/testdata/invalidSimulationReading.properties";

  private static final int VALID_READING = 0;
  private static final int ERROR_READING = 1;
  private static final int INVALID_READING = 2;


  private SimulationSettingsReader simulationReader;
  private List<String> simulationReaderPaths;

  /*@BeforeAll
  void setUp() {
    simulationReaderPaths = new ArrayList<>(Arrays.asList(VALID_READING_PATH, ERROR_READING_PATH, INVALID_READING_PATH));
  }*/

  @Test
  void testValidSimulation() {
    assertDoesNotThrow(() -> new SimulationSettingsReader(VALID_READING_PATH));
  }

  @Test
  void testInvalidCSVSimulation() {
    assertThrows(InvalidCSVFileException.class, () -> new SimulationSettingsReader(ERROR_READING_PATH));
  }


}