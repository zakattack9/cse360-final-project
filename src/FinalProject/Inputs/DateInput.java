package FinalProject.Inputs;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Input field that accepts dates only in the format of MMM dd (e.g. Nov 10, Jan 1).
 */
public class DateInput extends JTextField {
  SimpleDateFormat dateFormat1;
  SimpleDateFormat dateFormat2;

  /**
   * Constructor that instantiates the valid date formats accepted.
   */
  public DateInput() {
    dateFormat1 = new SimpleDateFormat("MMM dd");
    dateFormat2 = new SimpleDateFormat("MMM d");
    setColumns(20);
  }

  /**
   * Overridden getText() method used with the InputModal;
   * checks whether the current input is of the correct date format.
   *
   * @return "" if the date is not in the proper format, otherwise the currently inputted date is returned.
   */
  @Override
  public String getText() {
    String input = super.getText();
    try {
      Date date1 = dateFormat1.parse(input);
      Date date2 = dateFormat2.parse(input);
      if (!dateFormat1.format(date1).equals(input) &&
          !dateFormat2.format(date2).equals(input)) return "";
    } catch (ParseException ex) { return ""; }
    return input;
  }

  /**
   * Gets the error message that should be shown in the modal if the date input is invalid.
   *
   * @return String containing an invalid date error message.
   */
  public String getErrorMessage() {
    return "Please enter a valid date in the format of MMM dd (e.g. Nov 10, Jan 2)";
  }
}
