package model.exceptions;

public class InvalidSimulationSettingsFileException extends RuntimeException{

  public InvalidSimulationSettingsFileException(String message) {
    super(message);
  }

  public InvalidSimulationSettingsFileException(String message, Throwable cause) {
    super(message, cause);
  }
}
