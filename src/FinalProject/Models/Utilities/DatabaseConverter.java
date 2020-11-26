package FinalProject.Models.Utilities;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedHashMap;
import java.util.Map;

// used to convert Database models to DefaultTableModel
public class DatabaseConverter {
  String[][] tableArr;
  LinkedHashMap<String, LinkedHashMap<String, String>> database;

  public DatabaseConverter(LinkedHashMap<String, LinkedHashMap<String, String>> database) {
    this.database = database;
    if (!database.isEmpty()) {
      Map<String, String> firstMap = getFirstNestedMap(database);
      int rows = database.size();
      int columns = firstMap.size();
      tableArr = new String[rows][columns];
    }
  }

  public String[][] getArrayModel() {
    return database.values().stream().map(dataMap -> buildRow(dataMap)).toArray(String[][]::new);
  }

  public String[] getDBKeys() {
    Map<String, String> firstMap = getFirstNestedMap(database);
    return firstMap.keySet().toArray(String[]::new);
  }

  public DefaultTableModel getTableModel() {
    DefaultTableModel defaultTableModel = new DefaultTableModel();
    defaultTableModel.setDataVector(getArrayModel(), getDBKeys());
    return defaultTableModel;
  }

  private String[] buildRow(LinkedHashMap<String, String> dataMap) {
    return dataMap.values().toArray(new String[dataMap.size()]);
  }

  private Map<String, String> getFirstNestedMap(LinkedHashMap<String, LinkedHashMap<String, String>> database) {
    return (Map<String, String>) database.values().toArray()[0];
  }
}
