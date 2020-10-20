package model.exceptions;

public class InvalidCSVFileException extends RuntimeException {

  public InvalidCSVFileException(String message) {
    super(message);
  }
}
