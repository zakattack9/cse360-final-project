package FinalProject.Models;

import java.util.LinkedHashMap;

/**
 * A Database is represented by a nested LinkedHashMap where
 * the parent hashmap has keys that can be considered as primary keys
 * and the nested hashmap has keys representing a "column" and
 * values representing the value in a "column" for a "primary key".
 */
public abstract class Database extends LinkedHashMap<String, LinkedHashMap<String, String>> {
  /**
   * Converts an integer type to a string type.
   *
   * @param integer to be converted to a String.
   * @return String of converted integer.
   */
  protected String parseToString(Integer integer) { return Integer.toString(integer); }

  /**
   * Converts a string type to an integer type.
   *
   * @param intString to be converted to an Integer.
   * @return Integer of converted string where 0 is returned if the string is not convertible.
   */
  protected Integer parseToInt(String intString) {
    if (intString == null) return 0;
    try { return Integer.parseInt(intString.trim()); }
    catch (NumberFormatException e) { return 0; }
  }

  /**
   * Clears the values in the database where implementation of this method for each database will vary.
   */
  public abstract void clearDatabase();
}
