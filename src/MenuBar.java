import javax.swing.*;

public class MenuBar {
  private JMenuBar menuBar;

  public MenuBar() {
    menuBar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu about = new JMenu("About");

    JMenuItem loadARoster = new JMenuItem("Load a Roster");
    JMenuItem addAttendance = new JMenuItem("Add Attendance");
    JMenuItem save = new JMenuItem("Save");
    JMenuItem plotData = new JMenuItem("Plot Data");

    file.add(loadARoster);
    file.add(addAttendance);
    file.add(save);
    file.add(plotData);

    menuBar.add(file);
    menuBar.add(about);
  }

  public JMenuBar getMenuBar() { return menuBar; }
}
