package FinalProject.Controllers;

import FinalProject.Components.InputModal;
import FinalProject.Inputs.DateInput;
import FinalProject.Inputs.FilePathInput;
import FinalProject.Parsers.AttendanceParser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class AttendanceController implements ActionListener {
  JFrame frame;
  String filePathInputLabel;
  String dateInputLabel;

  public AttendanceController(JFrame frame) {
    this.frame = frame;
    this.filePathInputLabel = "Attendance File Path";
    this.dateInputLabel = "Date";
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    InputModal addAttendanceModal = createInputModal(frame);
    Map<String, String> inputs = addAttendanceModal.showModal();
    if (inputs.size() > 0) parseCSVFile(inputs.get(filePathInputLabel), inputs.get(dateInputLabel));
  }

  private void parseCSVFile(String filePath, String date) {
    AttendanceParser attendanceParser = new AttendanceParser(filePath, date);
    attendanceParser.runParser();
  }

  private InputModal createInputModal(JFrame frame) {
    InputModal inputModal = new InputModal(frame, "Add Attendance");

    FilePathInput filePathInput = new FilePathInput(frame);
    inputModal.addInput(this.filePathInputLabel, filePathInput, filePathInput.getErrorMessage());

    DateInput dateInput = new DateInput();
    inputModal.addInput(this.dateInputLabel, dateInput, dateInput.getErrorMessage());
    return inputModal;
  }
}
