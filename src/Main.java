import javax.swing.*;

public class Main extends JFrame {
  public static final int WINDOW_WIDTH = 1300;
  public static final int WINDOW_HEIGHT = 800;

  public Main() {
    MenuBar menuBar = new MenuBar();
    this.setJMenuBar(menuBar);

    Modal addAttendanceModal = new Modal(this, "Add Attendance");
    addAttendanceModal.addInput("Testing");
    addAttendanceModal.addInput("Date", new DateInput());
    addAttendanceModal.showModal();

    Modal loadRosterModal = new Modal(this, "Load a Roster");

    // configure JFrame window properties
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    this.setVisible(true);
  }

  public static void main(String[] args) {
    new Main();
  }
}
