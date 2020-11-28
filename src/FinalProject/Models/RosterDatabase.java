package FinalProject.Models;

import java.util.LinkedHashMap;

public class RosterDatabase extends Database {
  private static RosterDatabase rosterDatabase;

  private RosterDatabase() {
    super();
  }

  public void addEntry(String id, String firstName, String lastName, String programPlan, String academicLevel, String asurite) {
    if (get(asurite) == null) {
      LinkedHashMap<String, String> map = new LinkedHashMap<>();
      put(asurite, map);
    }
    get(asurite).put("ID", id);
    get(asurite).put("First Name", firstName);
    get(asurite).put("Last Name", lastName);
    get(asurite).put("Program and Plan", programPlan);
    get(asurite).put("Academic Level", academicLevel);
    get(asurite).put("ASURITE", asurite);

    AttendanceDatabase.getInstance().addAsurite(asurite);
  }

  // ensure thread-safety
  public static synchronized RosterDatabase getInstance() {
    if (rosterDatabase == null) rosterDatabase = new RosterDatabase();
    return rosterDatabase;
  }

  @Override
  public void clearDatabase() { clear(); }
}
