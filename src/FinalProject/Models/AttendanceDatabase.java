package FinalProject.Models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

public class AttendanceDatabase implements ActionListener {
  LinkedHashMap<String, LinkedHashMap<String, String>> data; // key is asurite
  LinkedHashMap<String, String> dates;
  LinkedHashMap<String, Integer> additionalAsurites;

  private static AttendanceDatabase attendanceDatabase;

  private AttendanceDatabase() {
    data = new LinkedHashMap<>();
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

  public LinkedHashMap<String, LinkedHashMap<String, String>> getData() {
    return data;
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
  public void actionPerformed(ActionEvent e) {
    // double check if action event is when new roster is loaded
    clearDatabase();
  }

  private void clearDatabase() {
    data.clear();
    dates.clear();
  }

  // adds all inputted dates to each asurite map
  private void updateDates(String date) {
    dates.put(date, "0");
    data.forEach((asurite, dateMap) -> data.put(asurite, mergeDates(dateMap)));
  }

  // maintains order of dates added
  private LinkedHashMap<String, String> mergeDates(LinkedHashMap<String, String> dateMap) {
    LinkedHashMap<String, String> cloneDates = (LinkedHashMap<String, String>) dates.clone();
    dateMap.forEach((date, time) ->
      cloneDates.merge(date, time, (oldDate, newDate) ->
        parseToInt(newDate) > 0 ? newDate : oldDate));
    return cloneDates;
  }

  private Integer parseToInt(String intString) {
    if (intString == null) return 0;
    int parsedInt;
    try {
      parsedInt = Integer.parseInt(intString.trim());
    } catch (NumberFormatException e) {
      parsedInt = 0;
    }
    return parsedInt;
  }

  private String parseToString(Integer integer) {
    return Integer.toString(integer);
  }
}
