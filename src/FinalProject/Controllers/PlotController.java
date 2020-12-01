package FinalProject.Controllers;

import FinalProject.Components.MainWindow;
import FinalProject.Components.ScatterPlot;
import FinalProject.Models.AttendanceDatabase;
import FinalProject.Models.RosterDatabase;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlotController implements ActionListener {
  private final int CHART_WIDTH = (int) (MainWindow.WINDOW_WIDTH * 0.8);
  private final int CHART_HEIGHT = (int) (MainWindow.WINDOW_HEIGHT * 0.8);
  private JFrame frame;

  public PlotController(JFrame frame) {
    this.frame = frame;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (RosterDatabase.getInstance().isEmpty()) showNoRosterPopup();
    else if (AttendanceDatabase.getInstance().getDates() == null) showNoAttendancePopup();
    else {
      ScatterPlot plot = new ScatterPlot();
      ChartPanel chartPanel = plot.getPlot();
      displayChart(chartPanel);
    }
  }

  private void displayChart(ChartPanel chartPanel) {
    JDialog modal = new JDialog(frame, "Attendance Plot", true);
    modal.add(chartPanel);
    modal.setSize(CHART_WIDTH, CHART_HEIGHT);
    modal.setLocationRelativeTo(frame);
    modal.setVisible(true);
  }

  /**
   * Popup shown if a roster CSV has not been loaded yet.
   */
  private void showNoRosterPopup() {
    String message = "Please load a roster first";
    JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.WARNING_MESSAGE);
  }

  /**
   * Popup shown if a attendance CSV has not been loaded yet.
   */
  private void showNoAttendancePopup() {
    String message = "Please add an attendance CSV first";
    JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.WARNING_MESSAGE);
  }
}
