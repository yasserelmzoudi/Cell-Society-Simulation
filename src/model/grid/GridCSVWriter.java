package model.grid;

import com.opencsv.CSVWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import model.exceptions.UnableToSaveFileException;

/**
 * Class encapsulating logic that writes to the CSV.
 */

public class GridCSVWriter {
  private Grid grid;
  private String title;
  private String author;
  private String description;

  private int gridHeight;
  private int gridWidth;
  private ResourceBundle errorMessageSource;
  private static final String EXCEPTION_RESOURCE = "resources.exceptionMessages";


  /**
   * Constructor for this class.
   *
   * @param grid The given grid.
   * @param title Title of the simulation.
   * @param author Author of the simulation.
   * @param description Description of the simulation.
   */
  public GridCSVWriter(Grid grid, String title, String author, String description) {
    this.grid = grid;
    this.title = title;
    this.author = author;
    this.description = description;
    gridHeight = grid.getGridHeight();
    gridWidth = grid.getGridWidth();
    errorMessageSource = ResourceBundle.getBundle(EXCEPTION_RESOURCE);

    checkIfTitleProvided();

    setUpSaveFile();
  }

  /**
   * Throws error if the no title is given.
   */
  private void checkIfTitleProvided() {
    if (title.length() == 0) {
      throw new UnableToSaveFileException(errorMessageSource.getString("UnableToSave"));
    }
  }

  private void setUpSaveFile() {
    try (OutputStream saveFile = new FileOutputStream("src/resources/" + title + ".properties")) {
      Properties prop = new Properties();
      prop.setProperty("Title", title);
      prop.setProperty("Author", author);
      prop.setProperty("Description", description);
      prop.store(saveFile, null);
    } catch (Exception e) {
      throw new UnableToSaveFileException(errorMessageSource.getString("UnableToSave"));
    }
  }

  public void saveFile() {
    checkIfTitleProvided();
    try{
      Writer saveFile = new FileWriter("data/" + title + ".csv");
      CSVWriter writer = new CSVWriter(saveFile, ',', CSVWriter.NO_QUOTE_CHARACTER,
          CSVWriter.DEFAULT_ESCAPE_CHARACTER,
          CSVWriter.DEFAULT_LINE_END);
      List<String[]> data = new ArrayList<>();
      String[] rowData = {String.valueOf(gridHeight), String.valueOf(gridWidth)};
      data.add(rowData);

      for (int row = 0; row < gridHeight; row++) {
        rowData = new String[gridWidth];
        for (int column = 0; column < gridWidth; column++) {
          rowData[column] = String.valueOf(grid.getCellTypeState(row, column));
        }
        data.add(rowData);
      }
      writer.writeAll(data);
      writer.close();
    } catch (IOException e) {
      throw new UnableToSaveFileException(errorMessageSource.getString("UnableToSave"));
    }
  }

  public void writeFile(Grid cells) {

  }
}
