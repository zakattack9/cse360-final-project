package FinalProject.Models;

import java.util.LinkedHashMap;

public class AttendanceDatabase extends Database {
  private static AttendanceDatabase attendanceDatabase;

  LinkedHashMap<String, String> dates;
  LinkedHashMap<String, Integer> additionalAsurites;

  private AttendanceDatabase() {
    super();
    dates = new LinkedHashMap<>();
    additionalAsurites = new LinkedHashMap<>();
  }

  public void addEntry(String asurite, String date, Integer time) {
    updateDates(date);
    if (data.get(asurite) != null) {
      int currTime = parseToInt(data.get(asurite).get(date));
      data.get(asurite).put(date, parseToString(currTime + time));
    } else {
      additionalAsurites.merge(asurite, time, Integer::sum);
    }
  }

  // ensure thread-safety
  public static synchronized AttendanceDatabase getInstance() {
    if (attendanceDatabase == null) attendanceDatabase = new AttendanceDatabase();
    return attendanceDatabase;
  }

  public LinkedHashMap<String, Integer> getAdditionalAsurites() {
    return additionalAsurites;
  }

  // add asurites from roster to attendance
  public void addAsurite(String asurite) {
    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    data.put(asurite, map);
  }

  @Override
  public void clearDatabase() {
    data.clear();
    dates.clear();
    additionalAsurites.clear();
  }

  // ensures each asurite hashmap includes all previously/newly added dates
  private void updateDates(String date) {
    if (dates.get(date) == null) additionalAsurites.clear();
    dates.put(date, "0");
    data.forEach((asurite, dateMap) -> data.put(asurite, mergeDates(dateMap)));
  }

  // maintains order of dates added
  private LinkedHashMap<String, String> mergeDates(LinkedHashMap<String, String> dateMap) {
    LinkedHashMap<String, String> clonedDates = (LinkedHashMap<String, String>) dates.clone();
    dateMap.forEach((date, time) ->
      clonedDates.merge(date, time, (oldDate, newDate) ->
        parseToInt(newDate) > 0 ? newDate : oldDate));
    return clonedDates;
  }
}
