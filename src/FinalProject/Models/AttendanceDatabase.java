package FinalProject.Models;

import java.util.LinkedHashMap;

public class AttendanceDatabase {
  LinkedHashMap<String, LinkedHashMap<String, Integer>> data;
  LinkedHashMap<String, Integer> dates;

  public AttendanceDatabase() {
    data = new LinkedHashMap<>();
    dates = new LinkedHashMap<>();
  }

  public void addEntry(String asurite, String date, int time) {
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

  public LinkedHashMap<String, LinkedHashMap<String, Integer>> getData() {
    return data;
  }

  // ensures every asurite in data includes all previously added dates
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
