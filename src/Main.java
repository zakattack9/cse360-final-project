import javax.swing.*;

public class Main extends JFrame {
  public static final int WINDOW_WIDTH = 1300;
  public static final int WINDOW_HEIGHT = 800;

  public Main() {
    MenuBar menuBar = new MenuBar();
    this.setJMenuBar(menuBar.getMenuBar());

    ModalBuilder addAttendanceModalBuilder = new ModalBuilder(this, "Add Attendance");
    addAttendanceModalBuilder.addInput("Testing Plz Work");
    addAttendanceModalBuilder.addInput("Date", new DateInput());
    addAttendanceModalBuilder.getModal().setVisible(true);

    ModalBuilder loadRosterModalBuilder = new ModalBuilder(this, "Load a Roster");

    this.add(new DateInput());

    // configure JFrame window properties
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    this.setVisible(true);
  }

  public static void main(String[] args) {
    new Main();
  }
}
