package FinalProject.Models;

import java.util.LinkedHashMap;

public abstract class Database extends LinkedHashMap<String, LinkedHashMap<String, String>> {
  public boolean isEmpty() { return this.size() == 0; }

  protected String parseToString(Integer integer) { return Integer.toString(integer); }

  protected Integer parseToInt(String intString) {
    if (intString == null) return 0;
    try { return Integer.parseInt(intString.trim()); }
    catch (NumberFormatException e) { return 0; }
  }

  public abstract void clearDatabase();
}
