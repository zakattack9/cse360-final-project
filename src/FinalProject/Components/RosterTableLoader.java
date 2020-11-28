package FinalProject.Components;

import FinalProject.Models.RosterDatabase;
import FinalProject.Models.Utilities.DatabaseConverter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RosterTableLoader {

    private DatabaseConverter dataConvert;
    private DefaultTableModel tableModel;
    protected JTable table;
    private JFrame frame;
    private JScrollPane scrollPane;
    private DefaultTableModel currentData;
    private JScrollBar horizontalBar;
    private JScrollBar verticalBar;

    public RosterTableLoader(JFrame jTable){

        this.frame = jTable;
        String[] columnTitles = {"ID", "First Name","Last Name", "Program", "Level", "ASURITE"};
//        dataConvert = new DatabaseConverter();
//        currentData = dataConvert.getCurrentTableModel();
//
//        tableModel = new DefaultTableModel(columnTitles,0);

        table = new JTable(tableModel);
        table.setBounds(30,40,200,300);

        scrollPane = new JScrollPane(table);
        horizontalBar = new JScrollBar(JScrollBar.HORIZONTAL);
        verticalBar = new JScrollBar(JScrollBar.VERTICAL);
        //frame.add(table);
        frame.add(scrollPane);
        frame.add(horizontalBar);
        frame.add(verticalBar);
        frame.setSize(300,400);
        frame.setVisible(true);
    }

}

