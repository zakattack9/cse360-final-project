package FinalProject.Controllers;

import FinalProject.Models.AttendanceDatabase;
import FinalProject.Models.RosterDatabase;
import FinalProject.Models.Utilities.DatabaseMerger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

public class SaveController implements ActionListener {
  public SaveController() {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    DatabaseMerger databaseMerger = new DatabaseMerger();
    LinkedHashMap<String, LinkedHashMap<String, String>> mergedDBs = databaseMerger.getMergedDBs();
    LinkedHashMap<String, Integer> additionalAsurites = AttendanceDatabase.getInstance().getAdditionalAsurites();
  }
}
