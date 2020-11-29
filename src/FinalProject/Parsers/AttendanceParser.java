package FinalProject.Parsers;

import FinalProject.Models.AttendanceDatabase;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Used to parse attendance CSV files that come in the format of
 * asurite, time
 */
public class AttendanceParser extends CSVParser {
  String date;

  /**
   * Constructor that calls its parent constructor to set the file path of the CSV to be parsed;
   * also sets the date to the date inputted by the user from the modal.
   *
   * @param filePath of the CSV file to be parsed.
   * @param date corresponding to all attendance entries in the CSV.
   */
  public AttendanceParser(String filePath, String date) {
    super(filePath);
    this.date = date;
  }

  /**
   * Parses a single line of the attendance CSV while converting the parsed time to an integer and
   * adds the parsed data to the attendance database.
   *
   * @param line that is an already configured Scanner that parses input up to the next comma.
   * @return boolean representing whether the parsing of the line was successful or not.
   */
  @Override
  protected boolean parseLine(Scanner line) {
    try {
      String asurite = line.next().trim();
      Integer time = Integer.parseInt(line.next().trim());
      addToDatabase(asurite, date, time);
    } catch (NoSuchElementException | NumberFormatException e) {
      return false;
    }
    return true;
  }

  /**
   * Adds to the attendance database a new entry with the Asurite, date, and time of attendance.
   *
   * @param asurite of the student being added.
   * @param date that the student attended class.
   * @param time student spent attending class for a given date,
   */
  private void addToDatabase(String asurite, String date, Integer time) {
    AttendanceDatabase.getInstance().addEntry(asurite, date, time);
  }
}
