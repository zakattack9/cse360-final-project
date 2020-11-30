package FinalProject.Models;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A nested hashmap where the key is an Asurite and the value is another hashmap
 * whose key is an inputted date and value is the total time of attendance for that date.
 */
public class AttendanceDatabase extends Database {
  private static AttendanceDatabase attendanceDatabase;

  LinkedHashMap<String, String> dates;
  LinkedHashMap<String, Integer> additionalAsurites;

  /**
   * Instantiates a dates hashmap used to keep track of all inputted dates and the order of which they were added;
   * an additional hashmap is created to store Asurites that were in the attendance CSV but not in the roster.
   */
  private AttendanceDatabase() {
    dates = new LinkedHashMap<>();
    additionalAsurites = new LinkedHashMap<>();
  }

  /**
   * Adds a new entry into the attendance database and increments the total time of attendance for a student on a given date
   * if the student is found in the attendance CSV multiple times.
   *
   * @param asurite of the student being added to the attendance database.
   * @param date that the student attended class.
   * @param time that the student spent in class for the passed in date.
   */
  public void addEntry(String asurite, String date, Integer time) {
    updateDates(date);
    if (get(asurite) != null) {
      int currTime = parseToInt(get(asurite).get(date));
      get(asurite).put(date, parseToString(currTime + time));
    } else {
      additionalAsurites.merge(asurite, time, Integer::sum);
    }
  }

  /**
   * Retrieves the only instance of the AttendanceDatabase class,
   * uses synchronized to prevent race conditions, enabling thread-safe instantiation.
   *
   * @return AttendanceDatabase if it has already been instantiated, otherwise instantiate its only instance and return it.
   */
  public static synchronized AttendanceDatabase getInstance() {
    if (attendanceDatabase == null) attendanceDatabase = new AttendanceDatabase();
    return attendanceDatabase;
  }

  /**
   * Gets the list of additional Asurites found in a parsed attendance CSV.
   *
   * @return LinkedHashMap where the key is an Asurite not in the roster and the value is the total time that Asurite attended class.
   */
  public LinkedHashMap<String, Integer> getAdditionalAsurites() {
    return additionalAsurites;
  }

  /**
   * Adds a new Asurite to the attendance database and assigns it an empty LinkedHashMap;
   * this method is called by RosterDatabase to keep the attendance database in-sync with the Asurites added to the roster database;
   * furthermore, this helps identify which Asurites are found in the attendance CSV but not in the roster
   * since Asurites not in the roster database will not be found as keys in the attendance database.
   *
   * @param asurite being added as a key to the attendance database.
   */
  public void addAsurite(String asurite) {
    put(asurite, new LinkedHashMap<>());
  }

  /**
   * Clears the attendance database, the map of currently inputted dates, and the map of additional Asurites found.
   */
  @Override
  public void clearDatabase() {
    clear();
    dates.clear();
    additionalAsurites.clear();
  }

  /**
   * Ensures that each Asurite already in the hashmap includes all previously and newly added dates such that
   * Asurites that did not attend class on a given date will have key value pairs
   * where the key is the date they did not attend class and the value is the total time attended which is 0;
   * the additionalAsurites map is cleared if a new date is being added so that
   * it only contains Asurites not found in the roster for that date.
   *
   * @param date that is currently being added to the attendance database.
   */
  private void updateDates(String date) {
    if (dates.get(date) == null) additionalAsurites.clear();
    dates.put(date, "0");
    forEach((asurite, dateMap) -> put(asurite, mergeDates(dateMap)));
  }

  /**
   * Returns all inputted dates in an array
   *
   * @return String[] including all the dates of the LinkedHashMap.
   */
  public String[] getDateKeys() {
    return !dates.isEmpty() ? dates.keySet().toArray(String[]::new) : null;
  }

  public LinkedHashMap<String, String> getDate() {
    return !dates.isEmpty() ? dates : null;
  }

  /**
   * Returns all inputted dates in an array
   *
   * @return String[] including all the dates of the LinkedHashMap.
   */
  public String[] getTimeKeys() {
    return !additionalAsurites.isEmpty() ? additionalAsurites.keySet().toArray(String[]::new) : null;
  }


  /**
   * Used by updateDates() and merges the dates the from the dates map with the dates for a given Asurite in the attendance database;
   * because the dates map maintains the order of which new dates were inputted, when merged with dates for an Asurite,
   * the Asurite inherits the same date ordering, but will replace the total time attended for each date with the correct calculated value.
   *
   * @param dateMap representing the dates and total time of attendance for a given Asurite.
   * @return LinkedHashMap with the correct ordering of dates and their calculated total time values for a given Asurite.
   */
  private LinkedHashMap<String, String> mergeDates(LinkedHashMap<String, String> dateMap) {
    LinkedHashMap<String, String> clonedDates = (LinkedHashMap<String, String>) dates.clone();
    dateMap.forEach((date, time) ->
      clonedDates.merge(date, time, (oldDate, newDate) ->
        parseToInt(newDate) > 0 ? newDate : oldDate));
    return clonedDates;
  }
}
