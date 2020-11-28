package FinalProject.Components;

import FinalProject.Models.AttendanceDatabase;
import FinalProject.Models.Utilities.DatabaseConverter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DataTable extends JTable {
    private static DataTable dataTable;

    private DatabaseConverter dataConvert;
    private DefaultTableModel tableModel;
    private JFrame frame;
    private JScrollPane scrollPane;
    private DefaultTableModel currentData;
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
        return scrollPane;
    }
    public void updateTable(){
        tableModel = new DefaultTableModel();
        DefaultTableModel tableModel = dataConvert.getCurrentTableModel();
        setModel(tableModel);
    }
}
