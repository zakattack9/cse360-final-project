package FinalProject.Controllers;

import FinalProject.Components.DataTable;
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

/**
 * Handles actions and functionality when clicking "Add Attendance".
 */
public class AttendanceController implements ActionListener {
  JFrame frame;
  String filePathInputLabel;
  String dateInputLabel;

  /**
   * Constructor initializing the modal textfield labels.
   *
   * @param frame used to position and attach JDialogs and JOptionPanes.
   */
  public AttendanceController(JFrame frame) {
    this.frame = frame;
    filePathInputLabel = "Attendance File Path";
    dateInputLabel = "Date";
  }

  /**
   * Called when "Add Attendance" is clicked in the JMenu and
   * shows the modal and passes in the inputs from the modal to the CSV parser;
   * if the roster database is empty, an error popup is shown.
   *
   * @param e event passed in from the ActionListener.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    InputModal addAttendanceModal = createInputModal();
    Map<String, String> inputs = addAttendanceModal.showModal();

    if (!inputs.isEmpty()) {
      if (RosterDatabase.getInstance().isEmpty()) showNoRosterPopup();
      else parseCSVFile(inputs.get(filePathInputLabel), inputs.get(dateInputLabel));
    }
  }

  /**
   * Parses the attendance CSV file and shows the appropriate popup upon successful or unsuccessful parsing;
   * a call to update the displayed data table is made if the parsing was successful.
   *
   * @param filePath of the attendance CSV file to be parsed.
   * @param date that the attendance data corresponds to.
   */
  private void parseCSVFile(String filePath, String date) {
    AttendanceParser attendanceParser = new AttendanceParser(filePath, date);
    boolean success = attendanceParser.runParser();
    if (success) {
      showSuccessPopup();
      DataTable.getInstance().updateTable();
    } else showInvalidCSVPopup();
  }

  /**
   * Uses the InputModal class to build a modal with a file path and date input fields.
   *
   * @return JDialog created through the InputModal class.
   */
  private InputModal createInputModal() {
    InputModal inputModal = new InputModal(frame, "Add Attendance");

    FilePathInput filePathInput = new FilePathInput(frame);
    inputModal.addInput(filePathInputLabel, filePathInput, filePathInput.getErrorMessage());

    DateInput dateInput = new DateInput();
    inputModal.addInput(dateInputLabel, dateInput, dateInput.getErrorMessage());
    return inputModal;
  }

  /**
   * Popup shown if the parsing of the attendance CSV was successful.
   */
  private void showSuccessPopup() {
    String message = buildSuccessMessage();
    JOptionPane.showMessageDialog(frame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Popup shown if the parsing of the attendance CSV was unsuccessful.
   */
  private void showInvalidCSVPopup() {
    String message = "Please load a valid attendance CSV";
    JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Popup shown if a roster CSV has not been loaded yet.
   */
  private void showNoRosterPopup() {
    String message = "Please load a roster first";
    JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.WARNING_MESSAGE);
  }

  /**
   * Builds the successful popup message which includes the amount of Asurites loaded from the roster and
   * a list of additional attendees that were found in the attendance CSV but not in the roster.
   *
   * @return String containing the entire successful parsing message.
   */
  private String buildSuccessMessage() {
    AttendanceDatabase attendanceDatabase = AttendanceDatabase.getInstance();
    LinkedHashMap<String, Integer> additionalAsurites = attendanceDatabase.getAdditionalAsurites();
    int numLoadedAsurites = attendanceDatabase.size();
    int numAdditionalAsurites = additionalAsurites.size();

    StringBuilder message = new StringBuilder("Data loaded for ");
    message.append(numLoadedAsurites).append(" Asurites in the roster\n\n");
    message.append(numAdditionalAsurites).append(" additional attendee(s) found:\n\n");
    additionalAsurites.forEach((asurite, time) -> message.append(asurite).append(", connected for ").append(time).append(" minute(s)\n"));
    return message.toString();
  }
}
