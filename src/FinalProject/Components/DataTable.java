package FinalProject.Components;

import FinalProject.Models.Utilities.DatabaseConverter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DataTable extends JTable {
  private static DataTable dataTable;

  private DatabaseConverter databaseConverter;
  private JScrollPane scrollPane;

  private DataTable(){
    databaseConverter = new DatabaseConverter();
    scrollPane = new JScrollPane(this);
    setSize(MainWindow.WINDOW_WIDTH, MainWindow.WINDOW_HEIGHT);
    setVisible(true);
  }

  public static synchronized DataTable getInstance(){
    if (dataTable == null) dataTable = new DataTable();
    return dataTable;
  }

  public JScrollPane getScrollPane() {
    JScrollBar horizontalBar = new JScrollBar(JScrollBar.HORIZONTAL);
    JScrollBar verticalBar = new JScrollBar(JScrollBar.VERTICAL);
    scrollPane.add(this);
    scrollPane.add(horizontalBar);
    scrollPane.add(verticalBar);
    scrollPane.setVisible(true);
    scrollPane.setViewportView(this);
    return scrollPane;
  }

  public void updateTable(){
    DefaultTableModel tableModel = databaseConverter.getCurrentTableModel();
    setModel(tableModel);
  }
}
