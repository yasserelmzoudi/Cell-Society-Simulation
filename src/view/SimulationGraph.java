package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.sound.sampled.Line;
import model.grid.Grid;
import view.GamePaneShapes.GamePane;

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


  public SimulationGraph(Grid grid, String simulationType) {
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

  public void updateSimulationGraph(int frameCount, Map<String, Integer> cellTypeCounts) {
    for (String cellTypeName: cellTypeData.keySet()) {
      Data graphData = new Data(frameCount, cellTypeCounts.get(cellTypeName));
      cellTypeData.get(cellTypeName).getData().add(graphData);
    }
  }

  public void closeGraphWindow() {
    stage.close();
  }

  public void showGraph() {
    graphRoot.setCenter(simulationGraph);
    stage.setTitle(simulationType);
    stage.setScene(scene);
    stage.show();
  }

}
