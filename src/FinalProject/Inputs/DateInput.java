package FinalProject.Inputs;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateInput extends JFormattedTextField {
  SimpleDateFormat dateFormat1;
  SimpleDateFormat dateFormat2;

  public DateInput() {
    dateFormat1 = new SimpleDateFormat("MMM dd");
    dateFormat2 = new SimpleDateFormat("MMM d");
  }

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

  public String getErrorMessage() {
    return "Please enter a valid date in the format of MMM dd (e.g. Nov 10, Jan 2)";
  }
}
