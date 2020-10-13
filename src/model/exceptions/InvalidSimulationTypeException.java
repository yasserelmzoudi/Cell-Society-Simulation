package model.exceptions;

public class InvalidSimulationTypeException extends RuntimeException{

  public InvalidSimulationTypeException(String message) {
    super(message);
  }

  public InvalidSimulationTypeException(String message, Throwable cause) {
    super(message, cause);
  }
}
