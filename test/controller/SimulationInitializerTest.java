package controller;

import static org.junit.jupiter.api.Assertions.*;

import model.exceptions.InvalidSimulationSettingsFileException;
import model.exceptions.InvalidSimulationTypeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SimulationInitializerTest {
  private static SimulationInitializer simulationController;
  private static final String INITIAL_PATH = "/resources/initialSimulationSettings.properties";
  private static final String INVALID_PATH = "/resources/invalidSimulationSettings.properties";


  @BeforeAll
  static void setUp() throws ReflectiveOperationException {
    assertDoesNotThrow(() -> new SimulationInitializer(INITIAL_PATH));
    simulationController = new SimulationInitializer(INITIAL_PATH);
  }

  @Test
  void getGridTest() {
    int expectedGridHeight = 20;
    int actualGridHeight = simulationController.getGrid().getGridHeight();
    assertEquals(expectedGridHeight, actualGridHeight);
  }

  @Test
  void getSimulationSettingsReader() {
    String expectedSimulationSettingsReader = "Spreading of Fire";
    String actualSimulationSettingsReader = simulationController.getSimulationSettingsReader().getSimulationTitle();
    assertEquals(expectedSimulationSettingsReader, actualSimulationSettingsReader);
  }

  @Test
  void testInvalidSimulationType() {
    assertThrows(InvalidSimulationTypeException.class, () -> new SimulationInitializer(INVALID_PATH));
  }

}