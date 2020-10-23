package controller;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import model.exceptions.InvalidSimulationTypeException;
import model.grid.Grid;


public class SimulationInitializer {
  private String edgePolicy;
  private String neighborhoodPolicy;
  private SimulationSettingsReader simulationSettingsReader;
  private InputStream simulationData;
  private Class<?> gridType;
  private Grid grid;
  private ResourceBundle errorMessageSource;

  private static final String EXCEPTION_RESOURCE = "resources.exceptionMessages";



  public SimulationInitializer(String path) throws ReflectiveOperationException {
    setUpSimulation(path);
  }

  public void setUpSimulation(String path) throws ReflectiveOperationException {
    errorMessageSource = ResourceBundle.getBundle(EXCEPTION_RESOURCE);
    simulationSettingsReader = new SimulationSettingsReader(path);
    simulationData = Grid.class.getClassLoader()
        .getResourceAsStream(simulationSettingsReader.getSimulationDataSourceCSV());
    edgePolicy = simulationSettingsReader.getSimulationEdgePolicy();
    neighborhoodPolicy = simulationSettingsReader.getSimulationNeighborhoodPolicy();

    try {
      gridType = Class.forName("model.grid." + simulationSettingsReader.getSimulationType() + "Grid");
      Object gridInstance = gridType.getDeclaredConstructor(
          new Class[]{InputStream.class, String.class, String.class}).newInstance(simulationData, edgePolicy, neighborhoodPolicy);
      grid = (Grid) gridInstance;
    } catch (Exception e){
      throw new InvalidSimulationTypeException(errorMessageSource.getString("InvalidSimulation"));
    }

    try {
      Method randomizeType = Grid.class.getMethod(simulationSettingsReader.getSimulationRandomization());
      randomizeType.invoke(grid);
    } catch(Exception e) {
      throw new InvalidSimulationTypeException(errorMessageSource.getString("InvalidSimulation"));
    }
  }

  public Grid getGrid() {
    return grid;
  }

  public SimulationSettingsReader getSimulationSettingsReader() {
    return simulationSettingsReader;
  }
}
