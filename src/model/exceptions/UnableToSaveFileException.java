package model.exceptions;

public class UnableToSaveFileException extends RuntimeException {

  public UnableToSaveFileException(String message) {
    super(message);
  }

  public UnableToSaveFileException(String message, Throwable cause) {
    super(message, cause);
  }
}
