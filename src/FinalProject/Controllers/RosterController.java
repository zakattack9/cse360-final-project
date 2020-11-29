package FinalProject.Controllers;

import FinalProject.Components.DataTable;
import FinalProject.Components.InputModal;
import FinalProject.Inputs.FilePathInput;
import FinalProject.Models.AttendanceDatabase;
import FinalProject.Models.RosterDatabase;
import FinalProject.Parsers.RosterParser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Handles actions and functionality when clicking "Load a Roster".
 */
public class RosterController implements ActionListener {
  JFrame frame;
  String filePathInputLabel;

  /**
   * Constructor initializing the modal textfield label.
   *
   * @param frame used to position and attach JDialogs and JOptionPanes.
   */
  public RosterController(JFrame frame) {
    this.frame = frame;
    filePathInputLabel = "Roster File Path";
  }

  /**
   * Called when "Load a Roster" is clicked in the JMenu and
   * shows the modal and passes in the inputs from the modal to the CSV parser;
   * both the attendance and roster databases are cleared before parsing the roster CSV.
   *
   * @param e event passed in from the ActionListener.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    InputModal loadRosterModal = createInputModal();
    Map<String, String> inputs = loadRosterModal.showModal();

    if (!inputs.isEmpty()) {
      clearDatabases();
      parseCSVFile(inputs.get(filePathInputLabel));
    }
  }

  /**
   * Parses the roster CSV file and shows a popup if the parsing was unsuccessful;
   * a call to update the displayed data table is made if the parsing was successful.
   *
   * @param filePath of the roster CSV file to be parsed.
   */
  private void parseCSVFile(String filePath) {
    RosterParser rosterParser = new RosterParser(filePath);
    boolean success = rosterParser.runParser();
    if (success) DataTable.getInstance().updateTable();
    else showInvalidCSVPopup();
  }

  /**
   * Use the InputModal class to build a modal with a file path input field.
   *
   * @return JDialog created through the InputModal class.
   */
  private InputModal createInputModal() {
    InputModal inputModal = new InputModal(frame, "Load a Roster");

    FilePathInput filePathInput = new FilePathInput(frame);
    inputModal.addInput(filePathInputLabel, filePathInput, filePathInput.getErrorMessage());
    return inputModal;
  }

  /**
   * Popup shown if the parsing of the roster CSV was unsuccessful.
   */
  private void showInvalidCSVPopup() {
    String message = "Please load a valid roster CSV";
    JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Resets both the attendance and roster databases.
   */
  private void clearDatabases() {
    AttendanceDatabase.getInstance().clearDatabase();
    RosterDatabase.getInstance().clearDatabase();
  }
}
