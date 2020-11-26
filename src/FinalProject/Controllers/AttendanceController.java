package FinalProject.Controllers;

import FinalProject.Components.InputModal;
import FinalProject.Inputs.DateInput;
import FinalProject.Inputs.FilePathInput;
import FinalProject.Models.AttendanceDatabase;
import FinalProject.Models.RosterDatabase;
import FinalProject.Parsers.AttendanceParser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class AttendanceController implements ActionListener {
  JFrame frame;
  String filePathInputLabel;
  String dateInputLabel;

  public AttendanceController(JFrame frame) {
    this.frame = frame;
    filePathInputLabel = "Attendance File Path";
    dateInputLabel = "Date";
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    InputModal addAttendanceModal = createInputModal();
    Map<String, String> inputs = addAttendanceModal.showModal();

    if (!inputs.isEmpty()) {
      if (RosterDatabase.getInstance().isEmpty()) showNoRosterPopup();
      else parseCSVFile(inputs.get(filePathInputLabel), inputs.get(dateInputLabel));
    }
  }

  private void parseCSVFile(String filePath, String date) {
    AttendanceParser attendanceParser = new AttendanceParser(filePath, date);
    boolean success = attendanceParser.runParser();
    if (success) showSuccessPopup();
    else showInvalidCSVPopup();
  }

  private InputModal createInputModal() {
    InputModal inputModal = new InputModal(frame, "Add Attendance");

    FilePathInput filePathInput = new FilePathInput(frame);
    inputModal.addInput(filePathInputLabel, filePathInput, filePathInput.getErrorMessage());

    DateInput dateInput = new DateInput();
    inputModal.addInput(dateInputLabel, dateInput, dateInput.getErrorMessage());
    return inputModal;
  }

  private void showSuccessPopup() {
    String message = buildSuccessMessage();
    JOptionPane.showMessageDialog(frame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
  }

  private void showInvalidCSVPopup() {
    String message = "Please load a valid attendance CSV";
    JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  private void showNoRosterPopup() {
    String message = "Please load a roster first";
    JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.WARNING_MESSAGE);
  }

  private String buildSuccessMessage() {
    AttendanceDatabase attendanceDatabase = AttendanceDatabase.getInstance();
    LinkedHashMap<String, Integer> additionalAsurites = attendanceDatabase.getAdditionalAsurites();
    int numLoadedAsurites = attendanceDatabase.getData().size();
    int numAdditionalAsurites = additionalAsurites.size();

    StringBuilder message = new StringBuilder("Data loaded for ");
    message.append(numLoadedAsurites).append(" Asurites in the roster\n\n");
    message.append(numAdditionalAsurites).append(" additional attendee(s) found:\n");
    additionalAsurites.forEach((asurite, time) -> message.append(asurite).append(", connected for ").append(time).append(" minute(s)\n"));
    return message.toString();
  }
}
