package FinalProject.Components;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * Used to display team member's name for this project
 */
public class AboutWindow extends JOptionPane implements MenuListener {
  private final String teamInfo = "Team Members:\nJohn Woods\nStefan Woods\nWilliam Liu\nZak Sakata";
  private JFrame frame;

  /**
   * Constructor that instantiates the team information
   * also adds the the team information to the about window
   */
  public AboutWindow(JFrame frame) {
    this.frame = frame;
  }

  @Override
  public void menuSelected(MenuEvent e) { showMessageDialog(frame, teamInfo); }

  @Override
  public void menuDeselected(MenuEvent e) { }

  @Override
  public void menuCanceled(MenuEvent e) { }
}
