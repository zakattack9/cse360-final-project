package FinalProject.Components;

import java.awt.Color;
import java.util.LinkedHashMap;

import FinalProject.Models.AttendanceDatabase;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Class ScatterPlot creates a scatter plot that displays
 * the attendance of students
 */
public class ScatterPlot {
  JFreeChart chart;

  /**
   * Constructor that creates the dataset and initializes the scatter plot
   */
  public ScatterPlot() {
    XYDataset dataset = createDataset();

    chart = ChartFactory.createScatterPlot("Percentage of Time Students are in Class", "Percentage", "Count", dataset);

    XYPlot plot = (XYPlot) chart.getPlot();
    plot.setBackgroundPaint(new Color(255, 228, 196));
  }

  /**
   * Gets a scatter plot with all the data points from the database
   *
   * @return returns a panel with the scatter plot
   */
  public ChartPanel getPlot() {
    return new ChartPanel(chart);
  }

  /**
   * Gets all the inputted dates and attendance times
   * of the students and adds them to the dataset
   *
   * @return XYDataset with data plots from database
   */
  private XYDataset createDataset() {
    XYSeriesCollection dataset = new XYSeriesCollection();

    AttendanceDatabase attendanceDatabase = AttendanceDatabase.getInstance();
    String[] dates = attendanceDatabase.getDates();

    int hundred = 0;
    int ninety = 0;
    int eighty = 0;
    int seventy = 0;
    int sixty = 0;
    int fifty = 0;
    int forty = 0;
    int thirty = 0;
    int twenty = 0;
    int ten = 0;
    int zero = 0;

    for (String date : dates) {
      for (LinkedHashMap<String, String> map : attendanceDatabase.values()) {
        if (parseToInt(map.get(date)) >= 75) {
          hundred++;
        } else if (parseToInt(map.get(date)) < 75 && parseToInt(map.get(date)) >= 67.5) {
          ninety++;
        } else if (parseToInt(map.get(date)) < 67.5 && parseToInt(map.get(date)) >= 60) {
          eighty++;
        } else if (parseToInt(map.get(date)) < 60 && parseToInt(map.get(date)) >= 52.5) {
          seventy++;
        } else if (parseToInt(map.get(date)) < 52.5 && parseToInt(map.get(date)) >= 45) {
          sixty++;
        } else if (parseToInt(map.get(date)) < 45 && parseToInt(map.get(date)) >= 37.5) {
          fifty++;
        } else if (parseToInt(map.get(date)) < 37.5 && parseToInt(map.get(date)) >= 30) {
          forty++;
        } else if (parseToInt(map.get(date)) < 30 && parseToInt(map.get(date)) >= 22.5) {
          thirty++;
        } else if (parseToInt(map.get(date)) < 22.5 && parseToInt(map.get(date)) >= 15) {
          twenty++;
        } else if (parseToInt(map.get(date)) < 15 && parseToInt(map.get(date)) >= 7.5) {
          ten++;
        } else if (parseToInt(map.get(date)) < 7.5) {
          zero++;
        } else {
          System.out.println("invaid attendance");
        }
      }

      XYSeries series = new XYSeries(date);
      series.add(100, hundred);
      series.add(90, ninety);
      series.add(80, eighty);
      series.add(70, seventy);
      series.add(60, sixty);
      series.add(50, fifty);
      series.add(40, forty);
      series.add(30, thirty);
      series.add(20, twenty);
      series.add(10, ten);
      series.add(0, zero);
      hundred = 0;
      ninety = 0;
      eighty = 0;
      seventy = 0;
      sixty = 0;
      fifty = 0;
      forty = 0;
      thirty = 0;
      twenty = 0;
      ten = 0;
      zero = 0;
      dataset.addSeries(series);
    }
    return dataset;
  }

  /**
   * Converts a string type to an integer type.
   *
   * @param intString to be converted to an Integer.
   * @return Integer of converted string where 0 is returned if the string is not convertible.
   */
  private Integer parseToInt(String intString) {
    if (intString == null) return 0;
    try {
      return Integer.parseInt(intString.trim());
    } catch (NumberFormatException e) {
      return 0;
    }
  }
}
