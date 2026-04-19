package edu.ntnu.idatt2003.gruppe50.view.components;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.math.BigDecimal;
import java.util.List;

public class LineChartView {
  private final LineChart<Number, Number> chart;
  private final XYChart.Series<Number, Number> series;

  public LineChartView(String xLabel, String yLabel){
    NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();

    xAxis.setLabel(xLabel);
    yAxis.setLabel(yLabel);
    series = new XYChart.Series<>();
    chart = new LineChart<>(xAxis, yAxis);
    chart.getData().add(series);
    chart.setAnimated(false);
  }

  public void display(String title, List<BigDecimal> history) {
    chart.setTitle(title);
    series.setName(title);
    series.getData().clear();
    for (int i = 0; i < history.size(); i++) {
      series.getData().add(new XYChart.Data<>(i, history.get(i)));
    }
  }

  public void appendPoint(int index, BigDecimal value) {
    series.getData().add(new XYChart.Data<>(index, value));
  }

  public LineChart<Number, Number> getChart() {
    return chart;
  }
}
