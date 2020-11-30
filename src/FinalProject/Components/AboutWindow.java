package FinalProject.Components;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Used to display team member's name for this project
 */
public class AboutWindow extends JOptionPane {
    MainWindow testing;
    /**
     * Constructor that instantiates the team information
     * also adds the the team information to the about window
     */
    public AboutWindow(){
        String teamInfo = "Team Members \nJohn Woods \nStefan Woods \nWilliam Liu \nZak Sakata";

        showMessageDialog(testing,teamInfo);
//      JOptionPane teamInfo1 = new JOptionPane(teamInfo);
        //add(teamInfo);

    }

    public void addActionListener(ActionListener actionListener) {
    }
}
