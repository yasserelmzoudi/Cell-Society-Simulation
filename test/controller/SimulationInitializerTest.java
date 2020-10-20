package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SimulationInitializerTest {
  private static SimulationInitializer simulationController;
  private static final String INITIAL_PATH = "/resources/initialSimulationSettings.properties";


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

}