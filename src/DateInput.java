import javax.swing.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateInput extends JFormattedTextField {
  JFormattedTextField dateInput;

  public DateInput() {
//    Format dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
//    dateInput = new JFormattedTextField(dateFormat);
//    dateInput.setValue(new Date());

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    dateInput = new JFormattedTextField(df);

  }

  public String getText() {
    String val = (String) dateInput.getValue();
    System.out.println("DATE INPUT VAL: " + val);
    return val;
  }

  public JFormattedTextField getDateInput() {
    return dateInput;
  }
}
