package FinalProject.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import FinalProject.Models.Utilities.SaveFunctionality;

/**
 * Handles actions and functionality when clicking "Save".
 */
public class SaveController implements ActionListener {
  /***
   * Called when "save" is selected by the user. Opens the save file dialog
   * box which allows the user to specify a file name and directory then saves the data.
   *
   * @param e event passed in from the ActionListener.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    SaveFunctionality s = new SaveFunctionality();
    s.saveCSV();
  }
}
