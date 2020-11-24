import javax.swing.*;
import java.util.Map;

public class Main extends JFrame {
  public static final int WINDOW_WIDTH = 1300;
  public static final int WINDOW_HEIGHT = 800;

  public Main() {
    MenuBar menuBar = new MenuBar();
    this.setJMenuBar(menuBar);

    // BEGIN DELETE FROM HERE (leaving for reference/testing)
    InputModal addAttendanceInputModal = new InputModal(this, "Add Attendance");
    addAttendanceInputModal.addInput("File Path");

    DateInput dateInput = new DateInput();
    addAttendanceInputModal.addInput("Date", dateInput, dateInput.getErrorMessage());
    Map<String, String> input = addAttendanceInputModal.showModal();
    System.out.println(input);

    InputModal loadRosterInputModal = new InputModal(this, "Load a Roster");
    // END DELETE FROM HERE

    // configure JFrame window properties
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    this.setVisible(true);
  }

  public static void main(String[] args) {
    new Main();
  }
}
