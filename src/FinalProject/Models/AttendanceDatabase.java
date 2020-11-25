package FinalProject.Models;

import java.util.*;

public class AttendanceDatabase {
  HashMap<String, Integer> asuriteRow;
  LinkedHashMap<String, Integer> dateColumn;
  List<List<String>> data;

  private static AttendanceDatabase attendanceDatabase;

  private AttendanceDatabase() {
    data = new ArrayList<>();
    asuriteRow = new HashMap<>();
    dateColumn = new LinkedHashMap<>();
  }

  public void addEntry(String asurite, String date, Integer time) {
    addAsuriteRow(asurite);
    addDateColumn(date);

    List<String> row = data.get(asuriteRow.get(asurite));
    if (row != null) {
      int currTime = parseToInt(row.get(dateColumn.get(date)));
      row.set(dateColumn.get(date), parseToString(currTime + time));
    } else {
      row.set(dateColumn.get(date), parseToString(time));
    }
  }

  // ensure thread-safety
  public static synchronized AttendanceDatabase getInstance() {
    if (attendanceDatabase == null) attendanceDatabase = new AttendanceDatabase();
    return attendanceDatabase;
  }

  public List<List<String>> getData() {
    return data;
  }

  public List<String> getDates() {
    return new ArrayList<>(dateColumn.keySet());
  }

  private void addAsuriteRow(String asurite) {
    if (asuriteRow.get(asurite) == null) {
      String row[] = new String[dateColumn.size()];
      Arrays.fill(row, "0");
      data.add(Arrays.asList(row));
      asuriteRow.put(asurite, asuriteRow.size());
    }
  }

  private void addDateColumn(String date) {
    if (dateColumn.get(date) == null) {
      data.forEach(list -> list.add("0"));
      dateColumn.put(date, dateColumn.size());
    }
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
    return Integer.toString(integer);
  }
}
