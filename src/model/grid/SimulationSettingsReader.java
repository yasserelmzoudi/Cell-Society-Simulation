package model.grid;

import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import model.exceptions.InvalidSimulationSettingsFileException;
import model.exceptions.UnableToSaveFileException;

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
    if (!simType.isEmpty()) {
      return simType;
    }
    throw new InvalidSimulationSettingsFileException(errorMessageSource.getString("InvalidSettingsFile"));
  }

  public String getSimulationTitle() {
    if (!simTitle.isEmpty()) {
      return simTitle;
    }
    throw new InvalidSimulationSettingsFileException(errorMessageSource.getString("InvalidSettingsFile"));
  }

  public String getSimulationAuthor() {
    return simAuthor;
  }

  public String getSimulationDescription() {
    return simDescription;
  }

  public String getSimulationRandomization() {
    if (!simRandomization.isEmpty()) {
      return simRandomization;
    }
    throw new InvalidSimulationSettingsFileException(errorMessageSource.getString("InvalidSettingsFile"));

  }

  public String getSimulationDataSourceCSV() {
    if (!simDataSourceCSV.isEmpty()) {
      return simDataSourceCSV;
    }
    throw new InvalidSimulationSettingsFileException(errorMessageSource.getString("InvalidSettingsFile"));

  }

  public String getSimulationEdgePolicy() {
    if (!simEdgePolicy.isEmpty()) {
      return simEdgePolicy;
    }
    throw new InvalidSimulationSettingsFileException(errorMessageSource.getString("InvalidSettingsFile"));

  }

  public String getSimulationNeighborhoodPolicy() {
    if (!simNeighborhoodPolicy.isEmpty()) {
      return simNeighborhoodPolicy;
    }
    throw new InvalidSimulationSettingsFileException(errorMessageSource.getString("InvalidSettingsFile"));

  }
}
