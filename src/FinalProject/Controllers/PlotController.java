package FinalProject.Controllers;

import FinalProject.Components.MainWindow;
import FinalProject.Components.ScatterPlot;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlotController implements ActionListener {
  JFrame frame;

  public PlotController(JFrame frame) {
    this.frame = frame;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    ScatterPlot plot = new ScatterPlot();
    ChartPanel chartPanel = plot.getPlot();
    displayChart(chartPanel);
  }

  private void displayChart(ChartPanel chartPanel) {
    JDialog modal = new JDialog(frame, "Attendance Plot", true);
//    modal.setSize((int) MainWindow.WINDOW_WIDTH * 0.8, (int) MainWindow.WINDOW_HEIGHT * 0.8);
    modal.add(chartPanel);
    modal.setLocationRelativeTo(frame);
    modal.setVisible(true);
  }
}
