package FinalProject.Controllers;

import FinalProject.Components.InputModal;
import FinalProject.Inputs.DateInput;
import FinalProject.Inputs.FilePathInput;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class AttendanceController implements ActionListener {
  JFrame frame;

  public AttendanceController(JFrame frame) {
    this.frame = frame;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    InputModal addAttendanceModal = createInputModal(frame);
    Map<String, String> inputs = addAttendanceModal.showModal();
    System.out.println("CLICKED ADD ATTENDANCE");
  }

  private InputModal createInputModal(JFrame frame) {
    InputModal inputModal = new InputModal(frame, "Add Attendance");

    FilePathInput filePathInput = new FilePathInput(frame);
    inputModal.addInput("Attendance File Path", filePathInput, filePathInput.getErrorMessage());

    DateInput dateInput = new DateInput();
    inputModal.addInput("Date", dateInput, dateInput.getErrorMessage());
    return inputModal;
  }
}
