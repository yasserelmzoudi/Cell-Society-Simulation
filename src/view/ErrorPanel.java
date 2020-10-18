package view;

import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorPanel {

  private ResourceBundle errorResource;
  private static final String ERROR_RESOURCE = "resources.exceptionMessages";
  private Alert alert;


  public ErrorPanel() {
    errorResource = ResourceBundle.getBundle(ERROR_RESOURCE);
    alert = new Alert(AlertType.ERROR);
    alert.setContentText(errorResource.getString("Error"));
    alert.showAndWait();
  }

}
