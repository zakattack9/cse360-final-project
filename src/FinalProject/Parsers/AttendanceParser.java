package FinalProject.Parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AttendanceParser {
  String filePath;
  String date;

  public AttendanceParser(String filePath, String date) {
    this.filePath = filePath;
    this.date = date;
  }

  public void runParser() {
    Scanner parser = initializeParser();
    while(parser.hasNextLine()) {
      Scanner line = new Scanner(parser.nextLine());
      line.useDelimiter(",");

      String asurite = line.next();
      int time = parseToInteger(line.next());
      addToDatabase(asurite, date, time);
    }
  }

  private void addToDatabase(String asurite, String date, int time) {
    // access database through main
  }

  private int parseToInteger(String stringInt) {
    int integer = 0;
    try {
      integer = Integer.parseInt(stringInt.trim());
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return integer;
  }

  private Scanner initializeParser() {
    Scanner scanner = null;
    try {
      File file = new File(filePath);
      scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return scanner;
  }
}
