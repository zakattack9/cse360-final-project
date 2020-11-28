package FinalProject.Models.Utilities;

import FinalProject.Models.AttendanceDatabase;
import FinalProject.Models.RosterDatabase;

import java.util.LinkedHashMap;

// merges AttendanceDatabase and RosterDatabase into one object
public class DatabaseMerger {
  LinkedHashMap<String, LinkedHashMap<String, String>> mergedDatabase;

  public DatabaseMerger() {
    mergedDatabase = new LinkedHashMap<>();
  }

  public LinkedHashMap<String, LinkedHashMap<String, String>> getMergedDBs() {
    LinkedHashMap<String, LinkedHashMap<String, String>> rosterData = RosterDatabase.getInstance().getData();
    LinkedHashMap<String, LinkedHashMap<String, String>> attendanceData = AttendanceDatabase.getInstance().getData();
    rosterData.forEach((asurite, dataMap) -> dataMap.putAll(attendanceData.get(asurite)));
    return rosterData;
  }
}
