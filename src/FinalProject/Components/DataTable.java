package FinalProject.Components;

import FinalProject.Models.Utilities.DatabaseConverter;

import javax.swing.*;
import java.awt.*;

/**
 * Displays roster and attendance data in a JTable,
 * it is instantiated as a Singleton that can be updated from any class.
 */
public class DataTable extends JTable {
  private static DataTable dataTable;

  private DatabaseConverter databaseConverter;
  private JScrollPane scrollPane;

  /**
   * Constructor that instantiates a DatabaseConverter to retrieve database table models and
   * configures the JTable with the proper display properties.
   */
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

  /**
   * Retrieves the only instance of the DataTable class,
   * uses synchronized to prevent race conditions, enabling thread-safe instantiation.
   *
   * @return DataTable if it has already been instantiated, otherwise instantiate its only instance.
   */
  public static synchronized DataTable getInstance(){
    if (dataTable == null) dataTable = new DataTable();
    return dataTable;
  }

  /**
   * Configures the instantiated JScrollPane to make the table scrollable.
   *
   * @return JScrollPane that wraps the JTable and its data.
   */
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

  /**
   * Updates the data table by retrieving the latest table model from the databases.
   */
  public void updateTable(){
    setModel(databaseConverter.getCurrentTableModel());
  }
}
