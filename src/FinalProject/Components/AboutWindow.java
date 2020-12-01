package FinalProject.Components;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Used to display team member's name for this project
 */
public class AboutWindow extends JOptionPane{
    private String teamInfo = "Team Members \nJohn Woods \nStefan Woods \nWilliam Liu \nZak Sakata";
    private JOptionPane message;
    /**
     * Constructor that instantiates the team information
     * also adds the the team information to the about window
     */
    public AboutWindow(){
        //message = new JOptionPane();
        showMessageDialog(null,teamInfo);
        //JOptionPane teamInfo1 = new JOptionPane(teamInfo);
        //teamInfo1.showMessageDialog(null,teamInfo);
        //add(teamInfo);
    }
    public void addActionListener(ActionListener actionListener) {

    }

}
