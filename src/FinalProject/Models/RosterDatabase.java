package FinalProject.Models;

import java.util.LinkedHashMap;

public class RosterDatabase extends Database {
  private static RosterDatabase rosterDatabase;

  private RosterDatabase() {
    super();
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

    AttendanceDatabase.getInstance().addAsurite(asurite);
  }

  // ensure thread-safety
  public static synchronized RosterDatabase getInstance() {
    if (rosterDatabase == null) rosterDatabase = new RosterDatabase();
    return rosterDatabase;
  }

  @Override
  public void clearDatabase() { data.clear(); }
}
