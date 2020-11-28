package FinalProject.Components;

import FinalProject.Controllers.AttendanceController;
import FinalProject.Controllers.PlotController;
import FinalProject.Controllers.RosterController;
import FinalProject.Controllers.SaveController;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
  public static final int WINDOW_WIDTH = 1300;
  public static final int WINDOW_HEIGHT = 800;

  public MainWindow() {
    MenuBar menuBar = new MenuBar();
    setJMenuBar(menuBar);

    AttendanceController attendanceController = new AttendanceController(this);
    menuBar.addAttendanceController(attendanceController);

    RosterController rosterController = new RosterController(this);
    menuBar.addRosterController(rosterController);

    SaveController saveController = new SaveController();
    menuBar.addSaveController(saveController);

    PlotController plotController = new PlotController();
    menuBar.addPlotController(plotController);

    DataTable dataTable = DataTable.getInstance();
    add(dataTable.getScrollPane());

    // configure JFrame window properties
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setLocationRelativeTo(null);
    setVisible(true);
  }
}
