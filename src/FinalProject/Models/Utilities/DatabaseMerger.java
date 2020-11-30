package FinalProject.Models.Utilities;

import FinalProject.Models.Database;
import FinalProject.Models.AttendanceDatabase;
import FinalProject.Models.RosterDatabase;

/**
 * Used to merge the roster and attendance databases into one Database.
 */
public class DatabaseMerger {
  /**
   * Puts all keys from the attendance database into a cloned version of the roster database;
   * the key order of both the roster and attendance databases are maintained.
   *
   * @return Database that includes key value pairs from both the roster and attendance database.
   */
  public Database getMergedDBs() {
    Database rosterData = (Database) RosterDatabase.getInstance().clone();
    Database attendanceData = AttendanceDatabase.getInstance();
    rosterData.forEach((asurite, dataMap) -> dataMap.putAll(attendanceData.get(asurite)));
    return rosterData;
  }
}
