package FinalProject.Components;

import FinalProject.Controllers.AttendanceController;
import FinalProject.Controllers.PlotController;
import FinalProject.Controllers.RosterController;
import FinalProject.Controllers.SaveController;

import javax.swing.*;

/**
 * Topmost level of the application containing the menu bar, controllers, data table, and
 * configuration of the window's properties.
 */
public class MainWindow extends JFrame {
  public static final int WINDOW_WIDTH = 1300;
  public static final int WINDOW_HEIGHT = 800;

  /**
   * Constructor that instantiates the menu bar and adds the controllers to it;
   * also adds the data table to the main window.
   */
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

    AboutWindow aboutWindow = new AboutWindow(this);
    menuBar.addAboutMenuListener(aboutWindow);

    DataTable dataTable = DataTable.getInstance();
    add(dataTable.getScrollPane());
    dataTable.setDefaultEditor(Object.class,null);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setLocationRelativeTo(null);
    setVisible(true);
  }
}
