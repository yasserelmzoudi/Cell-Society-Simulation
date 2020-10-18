package model.grid;

import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;
import model.exceptions.InvalidSimulationSettingsFileException;

/**
 * Class that reads the simulation settings.
 */


public class SimulationSettingsReader {
  private Properties prop;
  private InputStream initialSimulationSettings;
  private static final String EXCEPTION_RESOURCE = "resources.exceptionMessages";
  private ResourceBundle errorMessageSource;
  private String path;

  /**
   * Constructor for this class.
   *
   * @param path Path of the file.
   */
  public SimulationSettingsReader(String path) {
    try {
      errorMessageSource = ResourceBundle.getBundle(EXCEPTION_RESOURCE);
      prop = new Properties();
      initialSimulationSettings = SimulationSettingsReader.class.getResourceAsStream(path);
      prop.load(initialSimulationSettings);
    } catch (Exception e) {
      throw new InvalidSimulationSettingsFileException(errorMessageSource.getString("InvalidSettingsFile"));
    }
  }

  /**
   * Returns source of error message.
   *
   * @return Error message.
   */
  public ResourceBundle getErrorMessageSource() {
    return errorMessageSource;
  }

  /**
   * @return Simulation type.
   */
  public String getSimulationType() {
    return prop.getProperty("SimulationType");
  }

  /**
   * @return Title of simulation.
   */
  public String getSimulationTitle() {
    return prop.getProperty("Title");
  }

  /**
   * @return Author of simulation.
   */
  public String getSimulationAuthor() {
    return prop.getProperty("Author");
  }

  /**
   * @return Description of simulation.
   */
  public String getSimulationDescription() {
    return prop.getProperty("Description");
  }

  /**
   * @return Type of randomization, if any.
   */
  public String getSimulationRandomization() {
    return prop.getProperty("Random");
  }

  /**
   * @return Source of simulation.
   */
  public String getSimulationDataSourceCSV() {
    return prop.getProperty("DataSourceCSV");
  }

  /**
   * @return Type of edge policy - finite, klein bottle, or torodial.
   */
  public String getSimulationEdgePolicy() {
    return prop.getProperty("EdgePolicy");
  }

  /**
   * @return Type of neighborhood policy - cardinal, complete, and diagonal.
   */
  public String getSimulationNeighborhoodPolicy() {
    return prop.getProperty("NeighborhoodPolicy");
  }
}
