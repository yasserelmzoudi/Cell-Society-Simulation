package model.grid;

import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;
import model.exceptions.InvalidSimulationSettingsFileException;

public class SimulationSettingsReader {
  private Properties prop;
  private InputStream initialSimulationSettings;
  private static final String EXCEPTION_RESOURCE = "resources.exceptionMessages";
  private ResourceBundle errorMessageSource;
  private String path;


  public SimulationSettingsReader(String path) {
    readSimulationSettings(path);
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

  public ResourceBundle getErrorMessageSource() {
    return errorMessageSource;
  }

  public String getSimulationType() {
    return prop.getProperty("SimulationType");
  }

  public String getSimulationTitle() {
    return prop.getProperty("Title");
  }

  public String getSimulationAuthor() {
    return prop.getProperty("Author");
  }

  public String getSimulationDescription() {
    return prop.getProperty("Description");
  }

  public String getSimulationRandomization() {
    return prop.getProperty("Random");
  }

  public String getSimulationDataSourceCSV() {
    return prop.getProperty("DataSourceCSV");
  }

  public String getSimulationEdgePolicy() {
    return prop.getProperty("EdgePolicy");
  }

  public String getSimulationNeighborhoodPolicy() {
    return prop.getProperty("NeighborhoodPolicy");
  }
}
