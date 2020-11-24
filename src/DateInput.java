import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateInput extends JFormattedTextField {
  SimpleDateFormat dateFormat;

  public DateInput(String format) {
    dateFormat = new SimpleDateFormat(format);
    dateFormat.setLenient(false);
  }

  @Override
  public String getText() {
    String input = super.getText();
    return getValue().equals("") ? "" : input;
  }

  @Override
  public Object getValue() {
    String input = super.getText();
    try {
      Date date = dateFormat.parse(input);
      if (!dateFormat.format(date).equals(input)) return "";
    } catch (ParseException ex) { return ""; }
    return input;
  }

  public String getErrorMessage() {
    return "Please enter a valid date in the format of MM/DD/YYYY";
  }
}
