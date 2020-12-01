package FinalProject.Controllers;

import FinalProject.Components.ScatterPlot;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlotController implements ActionListener {
  public PlotController() {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    ScatterPlot plot = new ScatterPlot("Scatter Plot of Attendance");
    plot.buildPlot();
    plot.displayPlot(plot);
  }
}
