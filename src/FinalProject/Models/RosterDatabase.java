package FinalProject.Models;

import java.util.LinkedHashMap;

public class RosterDatabase {
  LinkedHashMap<String, LinkedHashMap<String, String>> data; // key is asurite

  private static RosterDatabase rosterDatabase;

  private RosterDatabase() {
    data = new LinkedHashMap<>();
  }

  public void addEntry(String id, String firstName, String lastName, String programPlan, String academicLevel, String asurite) {
    if (data.get(asurite) == null) {
      LinkedHashMap<String, String> map = new LinkedHashMap<>();
      data.put(asurite, map);
    }
    data.get(asurite).put("ID", id);
    data.get(asurite).put("First Name", firstName);
    data.get(asurite).put("Last Name", lastName);
    data.get(asurite).put("Program and Plan", programPlan);
    data.get(asurite).put("Academic Level", academicLevel);
    data.get(asurite).put("ASURITE", asurite);

    AttendanceDatabase attendanceDatabase = AttendanceDatabase.getInstance();
    attendanceDatabase.addAsurite(asurite);
  }

  // ensure thread-safety
  public static synchronized RosterDatabase getInstance() {
    if (rosterDatabase == null) rosterDatabase = new RosterDatabase();
    return rosterDatabase;
  }

  public LinkedHashMap<String, LinkedHashMap<String, String>> getData() { return data; }

  public boolean isEmpty() { return data == null || data.size() == 0; }
}
