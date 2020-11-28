package FinalProject.Models.Utilities;

import FinalProject.Models.Database;
import FinalProject.Models.AttendanceDatabase;
import FinalProject.Models.RosterDatabase;

// merges AttendanceDatabase and RosterDatabase into one Database
public class DatabaseMerger {
  public Database getMergedDBs() {
    Database rosterData = RosterDatabase.getInstance();
    Database attendanceData = AttendanceDatabase.getInstance();
    rosterData.forEach((asurite, dataMap) -> dataMap.putAll(attendanceData.get(asurite)));
    return rosterData;
  }
}
