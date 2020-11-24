package FinalProject.Controllers;

import FinalProject.Components.InputModal;
import FinalProject.Inputs.FilePathInput;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class RosterController implements ActionListener {
  JFrame frame;

  public RosterController(JFrame frame) {
    this.frame = frame;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    InputModal loadRosterModal = createInputModal(frame);
    Map<String, String> inputs = loadRosterModal.showModal();
    System.out.println("CLICKED LOAD ROSTER");
  }

  private InputModal createInputModal(JFrame frame) {
    InputModal inputModal = new InputModal(frame, "Load a Roster");

    FilePathInput filePathInput = new FilePathInput(frame);
    inputModal.addInput("Roster File Path", filePathInput, filePathInput.getErrorMessage());
    return inputModal;
  }
}
