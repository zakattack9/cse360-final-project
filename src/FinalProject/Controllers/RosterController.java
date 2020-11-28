package FinalProject.Controllers;

import FinalProject.Components.DataTable;
import FinalProject.Components.InputModal;
import FinalProject.Inputs.FilePathInput;
import FinalProject.Models.AttendanceDatabase;
import FinalProject.Models.RosterDatabase;
import FinalProject.Parsers.RosterParser;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class RosterController implements ActionListener {
  JFrame frame;
  String filePathInputLabel;

  public RosterController(JFrame frame) {
    this.frame = frame;
    filePathInputLabel = "Roster File Path";
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    InputModal loadRosterModal = createInputModal();
    Map<String, String> inputs = loadRosterModal.showModal();


    if (!inputs.isEmpty()) {
      clearDatabases();
      parseCSVFile(inputs.get(filePathInputLabel));
      DataTable dataTable = DataTable.getInstance();
      dataTable.updateTable();
    }
  }

  private void parseCSVFile(String filePath) {
    RosterParser rosterParser = new RosterParser(filePath);
    boolean success = rosterParser.runParser();
    if (!success) showInvalidCSVPopup();
  }

  private InputModal createInputModal() {
    InputModal inputModal = new InputModal(frame, "Load a Roster");

    FilePathInput filePathInput = new FilePathInput(frame);
    inputModal.addInput(filePathInputLabel, filePathInput, filePathInput.getErrorMessage());
    return inputModal;
  }

  private void showInvalidCSVPopup() {
    String message = "Please load a valid roster CSV";
    JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  private void clearDatabases() {
    AttendanceDatabase.getInstance().clearDatabase();
    RosterDatabase.getInstance().clearDatabase();
  }
}
