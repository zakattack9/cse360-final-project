package FinalProject.Components;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
  JMenuItem loadARoster;
  JMenuItem addAttendance;
  JMenuItem save;
  JMenuItem plotData;

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

    this.add(file);
    this.add(about);
  }

  public void addRosterController(ActionListener actionListener) {
    loadARoster.addActionListener(actionListener);
  }

  public void addAttendanceController(ActionListener actionListener) { addAttendance.addActionListener(actionListener); }

  public void addSaveController(ActionListener actionListener) {
    save.addActionListener(actionListener);
  }

  public void addPlotController(ActionListener actionListener) {
    plotData.addActionListener(actionListener);
  }
}
