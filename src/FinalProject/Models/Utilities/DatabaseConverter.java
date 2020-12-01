package FinalProject.Models.Utilities;
import FinalProject.Models.Database;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Used to convert Database models to DefaultTableModels;
 * it is important to note that a Database used a nested LinkedHashMap data structure.
 */
public class DatabaseConverter {
  String[][] tableArr;
  Database database;
  DatabaseMerger databaseMerger;
  DefaultTableModel defaultTableModel;

  /**
   * Constructor that instantiates a DatabaseMerger and DefaultTableModel needed by other methods.
   */
  public DatabaseConverter() {
    databaseMerger = new DatabaseMerger();
    defaultTableModel = new DefaultTableModel();
    database = databaseMerger.getMergedDBs();
  }

  /**
   * Creates a DefaultTableModel based off the passed in database.
   *
   * @param database that will be converted to a DefaultTableModel.
   * @return DefaultTableModel including the content of the passed in database.
   */
  public DefaultTableModel getTableModel(Database database) {
    this.database = database;
    return createTableModel();
  }

  /**
   * Creates a DefaultTableModel based off the current data in both the roster and attendance databases.
   *
   * @return DefaultTableModel representing the combined data from the roster and attendance databases.
   */
  public DefaultTableModel getCurrentTableModel() {
    database = databaseMerger.getMergedDBs();
    return createTableModel();
  }

  /**
   * Loops through the values (LinkedHashMap<String, String>) of the internal database and converts them to a 2D array
   * where each row includes data from one nested LinkedHashMap in the database and
   * each value in the row is a value from the nested LinkedHashMap.
   *
   * @return String[][] including the values of database in a 2D array representation.
   */
  public String[][] getArrayModel() {
    if (isDatabaseEmpty()) return null;
    return database.values().stream().map(dataMap ->
            dataMap.values().toArray(new String[0])).toArray(String[][]::new);
  }

  /**
   * Gets all the keys of the nested LinkedHashMap which can be used as the column header names of the DefaultTableModel.
   *
   * @return String[] including all the keys of the nested LinkedHashMap.
   */
  public String[] getDBKeys() {
    if (isDatabaseEmpty()) return null;
    Map<String, String> firstMap = getFirstNestedMap(database);
    return firstMap.keySet().toArray(String[]::new);
  }

  /**
   * If the internal database used in this class is not empty,
   * a new 2D string array is created which is later converted to a DefaultTableModel.
   *
   * @return DefaultTableModel containing the data of either a passed in Database or the merged data from
   * both the roster and attendance databases.
   */
  private DefaultTableModel createTableModel() {
    if (!isDatabaseEmpty()) {
      initializeTableArr();
      defaultTableModel.setDataVector(getArrayModel(), getDBKeys());
      return defaultTableModel;
    }
    return null;
  }

  /**
   * Allocates memory for a 2D string array that fits the size of the current internal database.
   */
  private void initializeTableArr() {
    Map<String, String> firstMap = getFirstNestedMap(database);
    int rows = database.size();
    int columns = firstMap.size();
    tableArr = new String[rows][columns];
  }

  /**
   * Gets the first nested map in a passed in database that can be used to retrieve various values from.
   *
   * @param database that the first nested map will be extracted from.
   * @return an empty LinkedHashMap if the passed in database is empty, otherwise the first nested LinkedHashMap is returned.
   */
  private Map<String, String> getFirstNestedMap(Database database) {
    if (isDatabaseEmpty()) return new LinkedHashMap<>();
    return (Map<String, String>) database.values().toArray()[0];
  }

  /**
   * Checks whether the current internal database is null or empty.
   *
   * @return boolean representing if the database is empty.
   */
  private boolean isDatabaseEmpty() { return database == null || database.isEmpty(); }
}
