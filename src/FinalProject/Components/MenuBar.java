package FinalProject.Components;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Menu bar located at the top of the application.
 */
public class MenuBar extends JMenuBar {
  JMenuItem loadARoster;
  JMenuItem addAttendance;
  JMenuItem save;
  JMenuItem plotData;
  AboutWindow aboutWindow;


  /**
   * Adds File and About menu to the JMenuBar with their respective sub menus.
   */
  public MenuBar() {
    JMenu file = new JMenu("File");
    JMenu about = new JMenu("About");

    loadARoster = new JMenuItem("Load a Roster");
    addAttendance = new JMenuItem("Add Attendance");
    save = new JMenuItem("Save");
    plotData = new JMenuItem("Plot Data");

    file.add(loadARoster);
    file.add(addAttendance);
    file.add(save);
    file.add(plotData);

    aboutWindow = new AboutWindow();

    about.add(aboutWindow);

    add(file);
    add(about);
  }

  /**
   * Adds a controller that is called upon click to the "Load a Roster" menu option.
   */
  public void addRosterController(ActionListener actionListener) { loadARoster.addActionListener(actionListener); }

  /**
   * Adds a controller that is called upon click to the "Add Attendance" menu option.
   */
  public void addAttendanceController(ActionListener actionListener) { addAttendance.addActionListener(actionListener); }

  /**
   * Adds a controller that is called upon click to the "Save" menu option.
   */
  public void addSaveController(ActionListener actionListener) { save.addActionListener(actionListener); }

  /**
   * Adds a controller that is called upon click to the "Plot Data" menu option.
   */
  public void addPlotController(ActionListener actionListener) { plotData.addActionListener(actionListener); }

  /**
   * Adds a controller that is called upon click to the "About" option.
   */
  public void addAboutController(ActionListener actionListener){ aboutWindow.addActionListener(actionListener);
  }
}
