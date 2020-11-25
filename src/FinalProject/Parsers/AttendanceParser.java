package FinalProject.Parsers;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class AttendanceParser extends CSVParser {
  String date;

  public AttendanceParser(String filePath, String date) {
    super(filePath);
    this.date = date;
  }

  @Override
  protected boolean parseLine(Scanner line) {
    try {
      String asurite = line.next().trim();
      int time = Integer.parseInt(line.next().trim());
      addToDatabase(asurite, date, time);
    } catch (NoSuchElementException | NumberFormatException e) {
      return false;
    }
    return true;
  }

  private void addToDatabase(String asurite, String date, int time) {
    // access database through main
  }
}
