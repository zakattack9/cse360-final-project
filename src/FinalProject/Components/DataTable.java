package FinalProject.Components;

import FinalProject.Models.Utilities.DatabaseConverter;

import javax.swing.*;
import java.awt.*;

public class DataTable extends JTable {
  private static DataTable dataTable;

  private DatabaseConverter databaseConverter;
  private JScrollPane scrollPane;

  private DataTable(){
    databaseConverter = new DatabaseConverter();
    scrollPane = new JScrollPane(this);

    Dimension dimension = new Dimension(MainWindow.WINDOW_WIDTH, MainWindow.WINDOW_HEIGHT);
    setPreferredSize(dimension);
    setPreferredScrollableViewportSize(dimension);
    setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    setFillsViewportHeight(true);
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
    setModel(databaseConverter.getCurrentTableModel());
  }
}
