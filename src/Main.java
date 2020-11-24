import javax.swing.*;
import java.util.Map;

public class Main extends JFrame {
  public static final int WINDOW_WIDTH = 1300;
  public static final int WINDOW_HEIGHT = 800;

  public Main() {
    MenuBar menuBar = new MenuBar();
    this.setJMenuBar(menuBar);

    InputModal addAttendanceInputModal = new InputModal(this, "Add Attendance");
    addAttendanceInputModal.addInput("Testing");

    DateInput dateInput = new DateInput("MM/dd/yy");
    addAttendanceInputModal.addInput("Date", dateInput, dateInput.getErrorMessage());
    Map<String, String> test = addAttendanceInputModal.showModal();
    System.out.println(test);

    InputModal loadRosterInputModal = new InputModal(this, "Load a Roster");

    // configure JFrame window properties
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    this.setVisible(true);
  }

  public static void main(String[] args) {
    new Main();
  }
}
