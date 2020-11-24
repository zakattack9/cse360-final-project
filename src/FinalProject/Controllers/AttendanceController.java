package FinalProject.Controllers;

import FinalProject.Components.InputModal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttendanceController implements ActionListener {
  InputModal addAttendanceModal;

  public AttendanceController(JFrame frame) {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("CLICKED");
  }
}
