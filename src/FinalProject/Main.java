package FinalProject;

import FinalProject.Components.MainWindow;

import javax.swing.*;

/**
 * Entry point to application that creates a new window.
 * Uses invokeLater to ensure thread safe Swing object method calls.
 */
public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(MainWindow::new);
  }
}
