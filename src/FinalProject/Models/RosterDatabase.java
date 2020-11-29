package FinalProject.Models;

import java.util.LinkedHashMap;

/**
 * A nested hashmap where the key is an Asurite and the value is another hashmap
 * whose key is a property of a student in the roster (e.g. first name, id, etc.)
 * and value is the value of its respective property.
 */
public class RosterDatabase extends Database {
  private static RosterDatabase rosterDatabase;

  /**
   * Adds a new entry into the roster database while keeping the attendance database in-sync with students in the roster.
   *
   * @param id of the student being added.
   * @param firstName of the student being added.
   * @param lastName of the student being added.
   * @param programPlan of the student being added.
   * @param academicLevel of the student being added.
   * @param asurite of the student being added.
   */
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

  /**
   * Retrieves the only instance of the RosterDatabase class,
   * uses synchronized to prevent race conditions, enabling thread-safe instantiation.
   *
   * @return RosterDatabase if it has already been instantiated, otherwise instantiate its only instance and return it.
   */
  public static synchronized RosterDatabase getInstance() {
    if (rosterDatabase == null) rosterDatabase = new RosterDatabase();
    return rosterDatabase;
  }

  /**
   * Clears the roster database.
   */
  @Override
  public void clearDatabase() { clear(); }
}
