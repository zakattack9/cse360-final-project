package FinalProject.Components;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Used to display team member's name for this project
 */
public class AboutWindow extends JPanel {
    /**
     * Constructor that instantiates the team information
     * also adds the the team information to the about window
     */
    public AboutWindow(){
        JOptionPane teamInfo = new JOptionPane("Team Members: "
                + "Zak Sakata, " + "John Woods, " + "Stefan Woods, " + "William Liu");
        add(teamInfo);
    }
    public void addActionListener(ActionListener actionListener) {
    }
}
