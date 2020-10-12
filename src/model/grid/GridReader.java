package model.grid;

import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;
import model.exceptions.InvalidSimulationSettingsFileException;

public class GridReader {
  private Properties prop;
  private InputStream initialSimulationSettings;
  private static final String EXCEPTION_RESOURCE = "exceptionMessages";
  private ResourceBundle errorMessageSource;

  public GridReader() {
    try {
      errorMessageSource = ResourceBundle.getBundle(EXCEPTION_RESOURCE);
      prop = new Properties();
      initialSimulationSettings = GridReader.class.getResourceAsStream("initialSimulationSettings.properties");
      prop.load(initialSimulationSettings);
    } catch (Exception e) {
      throw new InvalidSimulationSettingsFileException(errorMessageSource.getString("InvalidSettingsFile"));
    }
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

  public String getSimulationDataSourceCSV() {
    return prop.getProperty("DataSourceCSV");
  }
}
