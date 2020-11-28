package FinalProject.Models.Utilities;

import FinalProject.Models.Database;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedHashMap;
import java.util.Map;

// used to convert Database models to DefaultTableModel
public class DatabaseConverter {
  String[][] tableArr;
  Database database;
  DatabaseMerger databaseMerger;
  DefaultTableModel defaultTableModel;

  public DatabaseConverter() {
    databaseMerger = new DatabaseMerger();
    defaultTableModel = new DefaultTableModel();
  }

  // returns table model of passed in database
  public DefaultTableModel getTableModel(Database database) {
    this.database = database;
    return createTableModel();
  }

  // returns table model of latest data from databases
  public DefaultTableModel getCurrentTableModel() {
    database = databaseMerger.getMergedDBs();
    return createTableModel();
  }

  private DefaultTableModel createTableModel() {
    if (!isDatabaseEmpty()) {
      initializeTableArr();
      defaultTableModel.setDataVector(getArrayModel(), getDBKeys());
      return defaultTableModel;
    }
    return null;
  }

  private void initializeTableArr() {
    Map<String, String> firstMap = getFirstNestedMap(database);
    int rows = database.size();
    int columns = firstMap.size();
    tableArr = new String[rows][columns];
  }

  private String[][] getArrayModel() {
    if (isDatabaseEmpty()) return null;
    return database.values().stream().map(dataMap -> buildRow(dataMap)).toArray(String[][]::new);
  }

  private String[] getDBKeys() {
    if (isDatabaseEmpty()) return null;
    Map<String, String> firstMap = getFirstNestedMap(database);
    return firstMap.keySet().toArray(String[]::new);
  }

  private String[] buildRow(LinkedHashMap<String, String> dataMap) {
    if (isDatabaseEmpty(dataMap)) return null;
    return dataMap.values().toArray(new String[0]);
  }

  private Map<String, String> getFirstNestedMap(Database database) {
    if (isDatabaseEmpty()) return new LinkedHashMap<>();
    return (Map<String, String>) database.values().toArray()[0];
  }

  private boolean isDatabaseEmpty() { return database == null || database.isEmpty(); }
  private boolean isDatabaseEmpty(Map<String, String> dataMap) { return dataMap == null || dataMap.isEmpty(); }
}
