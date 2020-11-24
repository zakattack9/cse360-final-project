import javax.swing.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateInput extends JFormattedTextField {
//  JFormattedTextField dateInput;
//  JTextField dateInput;


  public DateInput() {
//    dateFormat = new SimpleDateFormat("MM/dd/yy");
//    dateFormat.setLenient(false);
//    Format dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
//    dateInput = new JFormattedTextField(dateFormat);
//    super(new SimpleDateFormat("MM/dd/yy"));
//    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
//    dateInput = new JFormattedTextField(dateFormat);
//    dateInput.setValue(new Date());
  }

  @Override
  public String getText() {
    String input = super.getText();
//    Date getValue = (Date) super.getValue();
    String getValue = (String) getValue();
//    setText(input);
    System.out.println("INPUT " + input);
    System.out.println("GET VALUE " + getValue);

//    if (super.getValue() != null) {
    if (getValue() != "") {
      return input;
    }
    else {
      System.out.println("returning empty");
      return "";
//      return input;
    }
  }

  @Override
  public Object getValue() {
    String input = super.getText();
//    return test;
    String format = "MM/dd/yy";
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    sdf.setLenient(false);
    try {
      Date date = sdf.parse(input);
      if (!sdf.format(date).equals(input)) {
        System.out.println("NOT A VALID DATE");
        return "";
      }
    } catch (ParseException ex) {
      System.out.println("NOT A VALID DATE");
      return "";
    }

    System.out.println("VALID DATE");
    return input;
  }

//  @Override
//  public void setText(String text) {
//    System.out.println("SETTING TEXT\n");
//    if (!text.equals("")) super.setText(text);
//  }

  public String getErrorMessage() {
    return "Please enter a valid date in the format of MM/DD/YYYY";
  }

//  private boolean validateDate(String input) {
//    try {
//      Date date = dateFormat.parse(input);
//      return dateFormat.format(date).equals(input);
//    } catch (ParseException ex) {
//      return false;
//    }
//  }
}
