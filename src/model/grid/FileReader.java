package model.grid;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that reads all the lines from a given file, and returns every line from that file. The file
 * can be in a csv file.
 */

public class FileReader {

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

  /**
   * Code adopted from Professor Duvall to read CSV files
   * @param data CSV file containing grid configuration to be read
   * @return List<String[]> representing all of the lines read from the CSV file
   * @author Robert C. Duvall
   */
  public List<String[]> readAll(InputStream data) {
    try (CSVReader csvReader = new CSVReader(new InputStreamReader(data))) {
      return csvReader.readAll();
    } catch (IOException | CsvException e) {
      e.printStackTrace();
      return Collections.emptyList();
      }
  }

}