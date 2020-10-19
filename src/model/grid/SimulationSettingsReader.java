package model.grid;

import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import model.exceptions.InvalidSimulationSettingsFileException;
import model.exceptions.UnableToSaveFileException;

/**
 * Class that reads the simulation settings.
 */


public class SimulationSettingsReader {
  private Properties prop;
  private InputStream initialSimulationSettings;
  private static final String EXCEPTION_RESOURCE = "resources.exceptionMessages";
  private ResourceBundle errorMessageSource;
  private String path;

  private String simType;
  private String simTitle;
  private String simAuthor;
  private String simDescription;
  private String simRandomization;
  private String simDataSourceCSV;
  private String simEdgePolicy;
  private String simNeighborhoodPolicy;





  public SimulationSettingsReader(String path) {
    readSimulationSettings(path);
    simType = prop.getProperty("SimulationType");
    simTitle = prop.getProperty("Title");
    simAuthor = prop.getProperty("Author");
    simDescription = prop.getProperty("Description");
    simRandomization = prop.getProperty("Random");
    simDataSourceCSV = prop.getProperty("DataSourceCSV");
    simEdgePolicy = prop.getProperty("EdgePolicy");
    simNeighborhoodPolicy = prop.getProperty("NeighborhoodPolicy");
  }

  private void readSimulationSettings(String path) {
    try {
      errorMessageSource = ResourceBundle.getBundle(EXCEPTION_RESOURCE);
      prop = new Properties();
      initialSimulationSettings = SimulationSettingsReader.class.getResourceAsStream(path);
      prop.load(initialSimulationSettings);
    } catch (Exception e) {
      throw new InvalidSimulationSettingsFileException(errorMessageSource.getString("InvalidSettingsFile"));
    }
  }

  public String getSimulationType() {
    return checkIfProvided(simType);
  }

  /**
   * @return Title of simulation.
   */
  public String getSimulationTitle() {
    return checkIfProvided(simTitle);
  }

  /**
   * @return Author of simulation.
   */
  public String getSimulationAuthor() {
    return checkIfProvided(simAuthor);
  }

  /**
   * @return Description of simulation.
   */
  public String getSimulationDescription() {
    return checkIfProvided(simDescription);
  }

  /**
   * @return Type of randomization, if any.
   */
  public String getSimulationRandomization() {
    return checkIfProvided(simRandomization);
  }

  /**
   * @return Source of simulation.
   */
  public String getSimulationDataSourceCSV() {
    return checkIfProvided(simDataSourceCSV);
  }

  /**
   * @return Type of edge policy - finite, klein bottle, or torodial.
   */
  public String getSimulationEdgePolicy() {
    return checkIfProvided(simEdgePolicy);
  }



  /**
   * @return Type of neighborhood policy - cardinal, complete, and diagonal.
   */
  public String getSimulationNeighborhoodPolicy() {
    return checkIfProvided(simNeighborhoodPolicy);
  }

  private String checkIfProvided(String simulationValue) {
    if (!simulationValue.isEmpty()) {
      return simulationValue;
    }
    throw new InvalidSimulationSettingsFileException(errorMessageSource.getString("InvalidSettingsFile"));

  }
}
