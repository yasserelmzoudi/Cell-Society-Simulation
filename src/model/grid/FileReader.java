package model.grid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that reads all the lines from a given file, and returns every line from that file.
 * The file can be in a csv file.
 */

public class FileReader {

  private FileReader() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Read all lines from a file to a list.
   *
   * @param filePath Path to read file from.
   * @return A list of strings.
   * @throws IOException Returns an empty list.
   */
  public static List<String> getAllLinesInFile(String filePath) {
    Path path = Paths.get(filePath);
    List<String> allLines = new ArrayList<>();
    try {
      allLines.addAll(Files.readAllLines(path));
    } catch (IOException e) {
      System.err.println(e.getMessage());
      return new ArrayList<>();
    }
    return allLines;
  }
}