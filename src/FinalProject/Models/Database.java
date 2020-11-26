package FinalProject.Models;

import java.util.LinkedHashMap;

public abstract class Database {
  LinkedHashMap<String, LinkedHashMap<String, String>> data; // key is asurite

  protected Database() {
    data = new LinkedHashMap<>();
  }

  public LinkedHashMap<String, LinkedHashMap<String, String>> getData() {
    return data;
  }

  public boolean isEmpty() { return data == null || data.size() == 0; }

  protected Integer parseToInt(String intString) {
    if (intString == null) return 0;
    int parsedInt;
    try {
      parsedInt = Integer.parseInt(intString.trim());
    } catch (NumberFormatException e) {
      parsedInt = 0;
    }
    return parsedInt;
  }

  protected String parseToString(Integer integer) {
    return Integer.toString(integer);
  }

  public abstract void clearDatabase();
}
