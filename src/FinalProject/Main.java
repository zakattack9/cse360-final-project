package FinalProject;

import FinalProject.Components.InputModal;
import FinalProject.Components.MenuBar;
import FinalProject.Controllers.AttendanceController;
import FinalProject.Controllers.PlotController;
import FinalProject.Controllers.RosterController;
import FinalProject.Controllers.SaveController;
import FinalProject.Inputs.DateInput;
import FinalProject.Models.AttendanceDatabase;

import javax.swing.*;
import java.util.Map;

public class Main extends JFrame {
  public static final int WINDOW_WIDTH = 1300;
  public static final int WINDOW_HEIGHT = 800;

  public Main() {
    MenuBar menuBar = new MenuBar();
    this.setJMenuBar(menuBar);

    AttendanceController attendanceController = new AttendanceController(this);
    menuBar.addAttendanceController(attendanceController);

    AttendanceDatabase db = new AttendanceDatabase();

    db.addEntry("1", "Nov 10", 10);
    db.addEntry("2", "Nov 11", 10);
    db.addEntry("3", "Nov 12", 10);
    db.addEntry("4", "Nov 13", 10);
    db.addEntry("1", "Nov 10", 10);
    db.addEntry("2", "Nov 10", 10);

    System.out.println(db.getData());

    RosterController rosterController = new RosterController(this);
    menuBar.addRosterController(rosterController);

    SaveController saveController = new SaveController();
    menuBar.addSaveController(saveController);

    PlotController plotController = new PlotController();
    menuBar.addPlotController(plotController);


    // configure JFrame window properties
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    this.setVisible(true);
  }

  public static void main(String[] args) {
    new Main();
  }
}
