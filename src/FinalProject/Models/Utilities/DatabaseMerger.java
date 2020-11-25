package FinalProject.Models.Utilities;

import FinalProject.Models.AttendanceDatabase;
import FinalProject.Models.RosterDatabase;

import java.util.LinkedHashMap;

// merges AttendanceDatabase and RosterDatabase into one object
public class DatabaseMerger {
  LinkedHashMap<String, LinkedHashMap<String, String>> mergedDatabase;
  AttendanceDatabase attendanceDatabase;
  RosterDatabase rosterDatabase;

  public DatabaseMerger() {
    attendanceDatabase = AttendanceDatabase.getInstance();
    rosterDatabase = RosterDatabase.getInstance();
    mergedDatabase = new LinkedHashMap<>();
    mergeDatabases();
  }

  public LinkedHashMap<String, LinkedHashMap<String, String>> getMergedDBs() {
    return mergedDatabase;
  }

  private void mergeDatabases() {
    LinkedHashMap<String, LinkedHashMap<String, String>> attendanceData = attendanceDatabase.getData();
    LinkedHashMap<String, LinkedHashMap<String, String>> rosterData = rosterDatabase.getData();
    rosterData.forEach((asurite, dataMap) -> dataMap.putAll(attendanceData.get(asurite)));
    mergedDatabase = rosterData;
  }
}
