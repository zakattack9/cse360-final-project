package FinalProject.Models.Utilities;

import FinalProject.Models.AttendanceDatabase;
import FinalProject.Models.Database;
import FinalProject.Models.RosterDatabase;

import java.util.LinkedHashMap;

// merges AttendanceDatabase and RosterDatabase into one object
public class DatabaseMerger {
  LinkedHashMap<String, LinkedHashMap<String, String>> mergedDatabase;

  public DatabaseMerger() {
    mergedDatabase = new LinkedHashMap<>();
  }

  public Database getMergedDBs() {
    Database attendanceData = RosterDatabase.getInstance();
    Database rosterData = AttendanceDatabase.getInstance();
    rosterData.forEach((asurite, dataMap) -> dataMap.putAll(attendanceData.get(asurite)));
    return rosterData;
  }
}
