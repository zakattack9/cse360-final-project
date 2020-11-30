package FinalProject.Inputs;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

/**
 * Input field that opens a JFileChooser to get the path of a selected file.
 */
public class FilePathInput extends JTextField {
  JFileChooser fileChooser;
  JFrame frame;

  /**
   * Constructor that instantiates and configures the JFileChooser.
   *
   * @param frame that the JFileChooser is attached and centered to.
   */
  public FilePathInput(JFrame frame) {
    FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("CSV File","csv");
    File workingDirectory = new File(System.getProperty("user.dir"));
    fileChooser = new JFileChooser();
    fileChooser.addChoosableFileFilter(fileFilter);
    fileChooser.setCurrentDirectory(workingDirectory);
    fileChooser.setAcceptAllFileFilterUsed(false);
    this.frame = frame;
    setColumns(20);
    addListener();
  }

  /**
   * Overridden getText() method used with the InputModal;
   * checks whether the selected file exists.
   *
   * @return "" if the file does not exists, otherwise the currently inputted file path is returned.
   */
  @Override
  public String getText() {
    String input = super.getText();
    File file = new File(input);
    return file.exists() ? input : "";
  }

  /**
   * Gets the error message that should be shown in the modal if the selected file does not exist.
   *
   * @return String containing an invalid file error message.
   */
  public String getErrorMessage() {
    return "Please enter a valid file path (e.g. ./file.txt, /Desktop/file.txt)";
  }

  /**
   * Adds a focus listener to the JTextField to show the JFileChooser upon clicking the textfield and
   * re-enables focus of the textfield upon losing focus to another element; this is done in both the listener and
   * chooseFile() method because if a file has already been previously selected and the JFileChooser is cancelled out of,
   * the textfield needs to be made re-focusable again due to the logic of chooseFile().
   */
  private void addListener() {
    addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent e) { chooseFile(); }

      @Override
      public void focusLost(FocusEvent e) { setFocusable(true); }
    });
  }

  /**
   * Disables the focusability of the textfield and then shows the JFileChooser popup;
   * if a file was selected, the JTextField is populated with the filepath;
   * if the JFileChooser was cancelled out of and no file has been previously selected,
   * the textfield is made re-focusable again.
   */
  private void chooseFile() {
    setFocusable(false);
    fileChooser.showOpenDialog(frame);
    if (fileChooser.getSelectedFile() != null) {
      setText(fileChooser.getSelectedFile().toString());
    } else {
      setFocusable(true);
    }
  }
}
