package FinalProject.Controllers;

import FinalProject.Components.InputModal;
import FinalProject.Inputs.FilePathInput;
import FinalProject.Parsers.RosterParser;

import javax.swing.*;
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
    if (!inputs.isEmpty()) parseCSVFile(inputs.get(filePathInputLabel));
  }

  private void parseCSVFile(String filePath) {
    RosterParser rosterParser = new RosterParser(filePath);
    rosterParser.runParser();
  }

  private InputModal createInputModal() {
    InputModal inputModal = new InputModal(frame, "Load a Roster");

    FilePathInput filePathInput = new FilePathInput(frame);
    inputModal.addInput(filePathInputLabel, filePathInput, filePathInput.getErrorMessage());
    return inputModal;
  }
}
