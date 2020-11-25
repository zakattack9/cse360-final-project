package FinalProject.Models;

import java.util.LinkedHashMap;

public class AttendanceDatabase {
  LinkedHashMap<String, LinkedHashMap<String, String>> data; // key is asurite
  LinkedHashMap<String, String> dates;

  private static AttendanceDatabase attendanceDatabase;

  private AttendanceDatabase() {
    data = new LinkedHashMap<>();
    dates = new LinkedHashMap<>();
  }

  public void addEntry(String asurite, String date, Integer time) {
    updateDates(date);
    if (data.get(asurite) != null) {
      int currTime = parseToInt(data.get(asurite).get(date));
      data.get(asurite).put(date, parseToString(currTime + time));
    } else {
      LinkedHashMap<String, String> map = new LinkedHashMap<>();
      map.put(date, parseToString(time));
      data.put(asurite, map);
    }
  }

  // ensure thread-safety
  public static synchronized AttendanceDatabase getInstance() {
    if (attendanceDatabase == null) attendanceDatabase = new AttendanceDatabase();
    return attendanceDatabase;
  }

  public LinkedHashMap<String, LinkedHashMap<String, String>> getData() {
    return data;
  }

  // adds all inputted dates to each asurite map
  private void updateDates(String date) {
    dates.put(date, "0");
    data.forEach((asurite, dateMap) -> data.put(asurite, mergeDates(dateMap)));
  }

  // maintains order of dates added
  private LinkedHashMap<String, String> mergeDates(LinkedHashMap<String, String> dateMap) {
    LinkedHashMap<String, String> cloneDates = (LinkedHashMap<String, String>) dates.clone();
    dateMap.forEach((date, time) -> {
      cloneDates.merge(date, time, (oldDate, newDate) ->
        parseToInt(newDate) > 0 ? newDate : oldDate);
    });
    return cloneDates;
  }

  private Integer parseToInt(String intString) {
    int parsedInt;
    try {
      parsedInt = Integer.parseInt(intString.trim());
    } catch (NumberFormatException e) {
      parsedInt = 0;
    }
    return parsedInt;
  }

  private String parseToString(Integer integer) {
    return integer + "";
  }
}
