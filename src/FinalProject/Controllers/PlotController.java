package FinalProject.Controllers;

import FinalProject.Components.MainWindow;
import FinalProject.Components.ScatterPlot;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PlotController displays the plot during the action event
 * @param frame used to center the scatter plot in the window
 */
public class PlotController implements ActionListener {
  JFrame frame;

  /**
   *Takes in a JFrame
   * @param frame used to center the scatter plot in the window
   */
  public PlotController(JFrame frame) {
    this.frame = frame;
  }

  /**
   * actionPerformed creates the scatter plot when the
   * plot button is pressed and displays it
   * @param e waits for plot to be pressed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    ScatterPlot plot = new ScatterPlot();
    ChartPanel chartPanel = plot.getPlot();
    displayChart(chartPanel);
  }

  /**
   * displayChart displays the scatter plot to be seen in the
   * main window
   * @param chartPanel contains the scatter plot
   */
  private void displayChart(ChartPanel chartPanel) {
    JDialog modal = new JDialog(frame, "Attendance Plot", true);
//    modal.setSize((int) MainWindow.WINDOW_WIDTH * 0.8, (int) MainWindow.WINDOW_HEIGHT * 0.8);
    modal.add(chartPanel);
    modal.setLocationRelativeTo(frame);
    modal.setVisible(true);
  }
}
