package FinalProject.Models;

import java.util.LinkedHashMap;

public class AttendanceDatabase {
  LinkedHashMap<String, LinkedHashMap<String, Integer>> data; // key is asurite
  LinkedHashMap<String, Integer> dates;

  private static AttendanceDatabase attendanceDatabase;

  private AttendanceDatabase() {
    data = new LinkedHashMap<>();
    dates = new LinkedHashMap<>();
  }

  public void addEntry(String asurite, String date, Integer time) {
    updateDates(date);
    if (data.get(asurite) != null) {
      int currTime = data.get(asurite).get(date);
      data.get(asurite).put(date, currTime + time);
    } else {
      LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
      map.put(date, time);
      data.put(asurite, map);
    }
  }

  // ensure thread-safety
  public static synchronized AttendanceDatabase getInstance() {
    if (attendanceDatabase == null) {
      System.out.println("creating new");
      attendanceDatabase = new AttendanceDatabase();
    }
    return attendanceDatabase;
  }

  public LinkedHashMap<String, LinkedHashMap<String, Integer>> getData() {
    return data;
  }

  // adds all inputted dates to each asurite map
  private void updateDates(String date) {
    dates.put(date, 0);
    data.forEach((key, value) -> data.put(key, mergeDates(value)));
  }

  // maintains order of dates added
  private LinkedHashMap<String, Integer> mergeDates(LinkedHashMap<String, Integer> dateMap) {
    LinkedHashMap<String, Integer> cloneDates = (LinkedHashMap<String, Integer>) dates.clone();
    dateMap.forEach((key, value) -> {
      cloneDates.merge(key, value, (oldDate, newDate) ->
        newDate > 0 ? newDate : oldDate);
    });
    return cloneDates;
  }
}
