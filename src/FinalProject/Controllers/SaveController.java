package FinalProject.Controllers;

import FinalProject.Models.AttendanceDatabase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SaveController implements ActionListener {
  public SaveController() {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    AttendanceDatabase attendanceDatabase = AttendanceDatabase.getInstance();
    List<List<String>> data = attendanceDatabase.getData();
  }
}
