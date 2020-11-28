package FinalProject.Models;

import java.util.LinkedHashMap;

public class RosterDatabase extends Database {
  private static RosterDatabase rosterDatabase;

  private RosterDatabase() {
    super();
  }

  public void addEntry(String id, String firstName, String lastName, String programPlan, String academicLevel, String asurite) {
    if (this.get(asurite) == null) {
      LinkedHashMap<String, String> map = new LinkedHashMap<>();
      this.put(asurite, map);
    }
    this.get(asurite).put("ID", id);
    this.get(asurite).put("First Name", firstName);
    this.get(asurite).put("Last Name", lastName);
    this.get(asurite).put("Program and Plan", programPlan);
    this.get(asurite).put("Academic Level", academicLevel);
    this.get(asurite).put("ASURITE", asurite);

    AttendanceDatabase.getInstance().addAsurite(asurite);
  }

  // ensure thread-safety
  public static synchronized RosterDatabase getInstance() {
    if (rosterDatabase == null) rosterDatabase = new RosterDatabase();
    return rosterDatabase;
  }

  @Override
  public void clearDatabase() { this.clear(); }
}
