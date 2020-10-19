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
  private static final String INVALID_READING_PATH = "invalid_path";

  private static final int VALID_READING = 0;
  private static final int ERROR_READING = 1;
  private static final int INVALID_READING = 2;


  private static SimulationSettingsReader simulationReaderValid;
  private static SimulationSettingsReader simulationReaderError;

  private List<String> simulationReaderPaths;


  @BeforeAll
  static void setUp() {
    simulationReaderValid = new SimulationSettingsReader(VALID_READING_PATH);
    simulationReaderError = new SimulationSettingsReader(ERROR_READING_PATH);
  }

  @Test
  void testValidSimulation() {
    assertDoesNotThrow(() -> new SimulationSettingsReader(VALID_READING_PATH));
  }

  @Test
  void testInvalidSimulation() {
    assertThrows(InvalidSimulationSettingsFileException.class, () -> new SimulationSettingsReader(INVALID_READING_PATH));
  }

  @Test
  void getSimulationTypeNotPresent() {
    assertThrows(InvalidSimulationSettingsFileException.class, () -> simulationReaderError.getSimulationType());
  }

  @Test
  void getSimulationTitleNotPresent() {
    assertThrows(InvalidSimulationSettingsFileException.class, () -> simulationReaderError.getSimulationTitle());
  }

  @Test
  void getSimulationAuthorNotPresent() {
    assertThrows(InvalidSimulationSettingsFileException.class, () -> simulationReaderError.getSimulationAuthor());
  }

  @Test
  void getSimulationDescriptionNotPresent() {
    assertThrows(InvalidSimulationSettingsFileException.class, () -> simulationReaderError.getSimulationDescription());
  }

  @Test
  void getSimulationRandomizationNotPresent() {
    assertThrows(InvalidSimulationSettingsFileException.class, () -> simulationReaderError.getSimulationRandomization());
  }

  @Test
  void getSimulationDataSourceCSVNotPresent() {
    assertThrows(InvalidSimulationSettingsFileException.class, () -> simulationReaderError.getSimulationDataSourceCSV());
  }

  @Test
  void getSimulationEdgePolicyNotPresent() {
    assertThrows(InvalidSimulationSettingsFileException.class, () -> simulationReaderError.getSimulationEdgePolicy());
  }

  @Test
  void getSimulationNeighborhoodPolicyNotPresent() {
    assertThrows(InvalidSimulationSettingsFileException.class, () -> simulationReaderError.getSimulationNeighborhoodPolicy());
  }

  @Test
  void getSimulationTypePresent() {
    String expectedSimulationType = "a";
    String actualSimulationType = simulationReaderValid.getSimulationType();
    assertEquals(expectedSimulationType, actualSimulationType);
  }

  @Test
  void getSimulationTitlePresent() {
    String expectedSimulationTitle = "b";
    String actualSimulationTitle = simulationReaderValid.getSimulationTitle();
    assertEquals(expectedSimulationTitle, actualSimulationTitle);
  }

  @Test
  void getSimulationAuthorPresent() {
    String expectedSimulationAuthor = "c";
    String actualSimulationAuthor = simulationReaderValid.getSimulationAuthor();
    assertEquals(expectedSimulationAuthor, actualSimulationAuthor);  }

  @Test
  void getSimulationDescriptionPresent() {
    String expectedSimulationDescription = "d";
    String actualSimulationDescription = simulationReaderValid.getSimulationDescription();
    assertEquals(expectedSimulationDescription, actualSimulationDescription);  }

  @Test
  void getSimulationDataSourceCSVPresent() {
    String expectedSimulationCSV = "e";
    String actualSimulationCSV = simulationReaderValid.getSimulationDataSourceCSV();
    assertEquals(expectedSimulationCSV, actualSimulationCSV);  }

  @Test
  void getSimulationNeighborhoodPolicyPresent() {
    String expectedSimulationNeighborhoodPolicy = "f";
    String actualSimulationNeighborhoodPolicy = simulationReaderValid.getSimulationNeighborhoodPolicy();
    assertEquals(expectedSimulationNeighborhoodPolicy, actualSimulationNeighborhoodPolicy);  }

  @Test
  void getSimulationEdgePolicyPresent() {
    String expectedSimulationEdgePolicy = "g";
    String actualSimulationEdgePolicy = simulationReaderValid.getSimulationEdgePolicy();
    assertEquals(expectedSimulationEdgePolicy, actualSimulationEdgePolicy);  }

  @Test
  void getSimulationRandomizationPresent() {
    String expectedSimulationRandomization = "h";
    String actualSimulationRandomization = simulationReaderValid.getSimulationRandomization();
    assertEquals(expectedSimulationRandomization, actualSimulationRandomization);  }

}