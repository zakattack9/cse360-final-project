package FinalProject.Inputs;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

public class FilePathInput extends JTextField {
  JFileChooser fileChooser;
  JFrame frame;

  public FilePathInput(JFrame frame) {
    FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("CSV File","csv");
    File workingDirectory = new File(System.getProperty("user.dir"));
    fileChooser = new JFileChooser();
    fileChooser.addChoosableFileFilter(fileFilter);
    fileChooser.setCurrentDirectory(workingDirectory);
    fileChooser.setAcceptAllFileFilterUsed(false);
    this.frame = frame;
    addListener();
  }

  @Override
  public String getText() {
    String input = super.getText();
    File file = new File(input);
    return file.exists() ? input : "";
  }

  public String getErrorMessage() {
    return "Please enter a valid file path (e.g. ./file.txt, /Desktop/file.txt)";
  }

  private void addListener() {
    this.addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent e) { chooseFile(); }

      @Override
      public void focusLost(FocusEvent e) { enableFocus(); }
    });
  }

  private void chooseFile() {
    this.setFocusable(false);
    fileChooser.showOpenDialog(frame);
    if (fileChooser.getSelectedFile() != null) {
      this.setText(fileChooser.getSelectedFile().toString());
    } else {
      this.setFocusable(true);
    }
  }

  private void enableFocus() {
    this.setFocusable(true);
  }
}