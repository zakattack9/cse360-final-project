package FinalProject.Models.Utilities;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedHashMap;
import java.util.Map;

// used to convert Database models to DefaultTableModel
public class DatabaseConverter {
  String[][] tableArr;
  LinkedHashMap<String, LinkedHashMap<String, String>> database;
  DatabaseMerger databaseMerger;

  public DatabaseConverter() {
    databaseMerger = new DatabaseMerger();
  }

  // returns table model of passed in database
  public DefaultTableModel getTableModel(LinkedHashMap<String, LinkedHashMap<String, String>> database) {
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
      DefaultTableModel defaultTableModel = new DefaultTableModel();
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
    return database.values().stream().map(dataMap -> buildRow(dataMap)).toArray(String[][]::new);
  }

  private String[] getDBKeys() {
    Map<String, String> firstMap = getFirstNestedMap(database);
    return firstMap.keySet().toArray(String[]::new);
  }

  private String[] buildRow(LinkedHashMap<String, String> dataMap) {
    return dataMap.values().toArray(new String[0]);
  }

  private Map<String, String> getFirstNestedMap(LinkedHashMap<String, LinkedHashMap<String, String>> database) {
    if (isDatabaseEmpty()) return null;
    return (Map<String, String>) database.values().toArray()[0];
  }

  private boolean isDatabaseEmpty() {
    return database == null || database.isEmpty();
  }
}
