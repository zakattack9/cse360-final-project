package FinalProject.Components;

import FinalProject.Models.AttendanceDatabase;
import FinalProject.Models.Database;
import FinalProject.Models.Utilities.DatabaseConverter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DataTable extends JTable {
    private static DataTable dataTable;

    private DatabaseConverter dataConvert;
    private JScrollPane scrollPane;
    private JScrollBar horizontalBar;
    private JScrollBar verticalBar;

    private DataTable(){
        dataConvert = new DatabaseConverter();
        setSize(MainWindow.WINDOW_WIDTH,MainWindow.WINDOW_HEIGHT);
        setVisible(true);
    }
    public static synchronized DataTable getInstance(){
        if (dataTable == null) dataTable = new DataTable();
        return dataTable;
    }

    public JScrollPane getScrollPane() {
        scrollPane = new JScrollPane(this);
        horizontalBar = new JScrollBar(JScrollBar.HORIZONTAL);
        verticalBar = new JScrollBar(JScrollBar.VERTICAL);
        scrollPane.add(this);
        scrollPane.add(horizontalBar);
        scrollPane.add(verticalBar);
        scrollPane.setVisible(true);
        scrollPane.setViewportView(this);
        return scrollPane;
    }

    public void updateTable(){
        DatabaseConverter databaseConverter = new DatabaseConverter();
        DefaultTableModel tableModel = databaseConverter.getCurrentTableModel();
        setModel(tableModel);
    }
}
