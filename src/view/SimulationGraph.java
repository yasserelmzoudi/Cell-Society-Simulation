package view;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.grid.Grid;

public class SimulationGraph {

  private static final int GRAPH_WINDOW_WIDTH = 400;
  private static final int GRAPH_WINDOW_HEIGHT = 400;
  private static final int TRANSLATE_FACTOR = 800;
  private static final String GRAPH_RESOURCE = "resources.graphLabels";

  private  ResourceBundle graphLabelSource;

  private Grid grid;
  private NumberAxis xAxis;
  private NumberAxis yAxis;
  private Map<String, Series> cellTypeData;

  private LineChart<Number, Number> simulationGraph;

  private String simulationType;

  private BorderPane graphRoot;
  private Scene scene;
  private Stage stage;
  private boolean graphShowing;

  /**
   * Constructor of class
   * @param grid: the grid that the graph should keep track of
   * @param simulationType: the type of simulation to display as a title
   *
   */
  public SimulationGraph(Grid grid, String simulationType) {
    graphShowing =false;
    this.grid = grid;
    this.simulationType = simulationType;
    graphLabelSource = ResourceBundle.getBundle(GRAPH_RESOURCE);

    graphRoot = new BorderPane();
    scene = new Scene(graphRoot, GRAPH_WINDOW_WIDTH, GRAPH_WINDOW_HEIGHT);
    stage = new Stage();
    stage.setX(TRANSLATE_FACTOR);

    cellTypeData = new HashMap<>();

    xAxis = new NumberAxis();
    yAxis = new NumberAxis();

    simulationGraph = createSimulationGraph();

  }

  /**
   * Creates the initial graph set up
   * @return the corresponding line chart
   *
   */
  public LineChart createSimulationGraph() {
    LineChart<Number, Number> simulationGraph = new LineChart<>(xAxis, yAxis);
    simulationGraph.setCreateSymbols(false);
    xAxis.setLabel(graphLabelSource.getString("xAxis"));
    yAxis.setLabel(graphLabelSource.getString("yAxis"));
    simulationGraph.setTitle(simulationType);

    for (String cellTypeName: grid.getAllTypes()) {
      Series graphData = new Series();
      graphData.setName(cellTypeName);
      cellTypeData.put(cellTypeName, graphData);
      simulationGraph.getData().add(graphData);
    }

    return simulationGraph;
  }

  /**
   * Updates the simulation graph based on the current fram
   * @param frameCount: the current frame
   * @param cellTypeCounts: a map of that contains the total number of cells of a certain type of cell
   *
   */
  public void updateSimulationGraph(int frameCount, Map<String, Integer> cellTypeCounts) {
    for (String cellTypeName: cellTypeData.keySet()) {
      Data graphData = new Data(frameCount, cellTypeCounts.get(cellTypeName));
      cellTypeData.get(cellTypeName).getData().add(graphData);
    }
  }

  /**
   * Closes the graph Window
   *
   */
  public void closeGraphWindow() {
    stage.close();
  }

  /**
   * Adds the graph to the stage and shows the window
   *
   */
  public void showGraph() {
    graphRoot.setCenter(simulationGraph);
    stage.setTitle(simulationType);
    stage.setScene(scene);
    stage.show();
    graphShowing=true;
  }

  /**
   * Boolean that remembers whether the graph is open or closed
   * Used when reseting the current simulation so that the user doesn't have to reopen a graph is they already had it open
   *
   */
  public boolean graphIsShowing() {
    return graphShowing;
  }


}
