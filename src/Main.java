import javax.swing.*;

public class Main extends JFrame {
  public Main() {
    MenuBar menuBar = new MenuBar();
    this.setJMenuBar(menuBar.getMenuBar());

    Modal addAttendanceModal = new Modal(this, "Add Attendance");
    Modal loadRosterModal = new Modal(this, "Load a Roster");

    // configure JFrame window properties
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1300, 800);
    this.setVisible(true);
  }

  public static void main(String[] args) {
    new Main();
  }
}
