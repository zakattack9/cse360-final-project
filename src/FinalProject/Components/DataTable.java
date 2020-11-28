package FinalProject.Components;

import FinalProject.Models.Utilities.DatabaseConverter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DataTable extends JTable {

    private DatabaseConverter dataConvert;
    private DefaultTableModel tableModel;
    protected JTable table;
    private JFrame frame;
    private JScrollPane scrollPane;
    private DefaultTableModel currentData;
    private JScrollBar horizontalBar;
    private JScrollBar verticalBar;

    public DataTable(){
        dataConvert = new DatabaseConverter();

    }

    public JScrollPane getScrollPane() {
       // currentData = dataConvert.getCurrentTableModel();

        tableModel = new DefaultTableModel();
        DefaultTableModel tableModel = dataConvert.getCurrentTableModel();

        table = new JTable(tableModel);
        table.setBounds(30,40,200,300);

        scrollPane = new JScrollPane(table);
        horizontalBar = new JScrollBar(JScrollBar.HORIZONTAL);
        verticalBar = new JScrollBar(JScrollBar.VERTICAL);
        table.add(horizontalBar);
        table.add(verticalBar);
        table.add(scrollPane);
        table.setSize(300,400);
        table.setVisible(true);
        return scrollPane;
    }
}
