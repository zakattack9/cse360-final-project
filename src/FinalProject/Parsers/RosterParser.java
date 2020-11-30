package FinalProject.Parsers;

import FinalProject.Models.RosterDatabase;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Used to parse roster CSV files that come in the format of
 * id, first name, last name, program plan, academic level, asurite
 */
public class RosterParser extends CSVParser {
  /**
   * Constructor that calls its parent constructor to set the file path of teh CSV to be parsed.
   *
   * @param filePath of the CSV file to be parsed.
   */
  public RosterParser(String filePath) {
    super(filePath);
  }

  /**
   * Parses a single line of the roster CSV and
   * adds the parsed data to the roster database.
   *
   * @param line that is an already configured Scanner that parses input up to the next comma.
   * @return boolean representing whether the parsing of the line was successful.
   */
  @Override
  protected boolean parseLine(Scanner line) {
    try {
      String id = line.next().trim();
      String firstName = line.next().trim();
      String lastName = line.next().trim();
      String programPlan = line.next().trim();
      String academicLevel = line.next().trim();
      String asurite = line.next().trim();
      addToDatabase(id, firstName, lastName, programPlan, academicLevel, asurite);
    } catch (NoSuchElementException e) {
      return false;
    }
    return true;
  }

  /**
   * Adds to the roster database a new entry with
   * the student's id, first name, last name, program plan, academic level, and Asurite.
   *
   * @param id of the student being added.
   * @param firstName of the student being added.
   * @param lastName of the student being added.
   * @param programPlan of the student being added.
   * @param academicLevel of the student being added.
   * @param asurite of the student being added.
   */
  private void addToDatabase(String id, String firstName, String lastName, String programPlan, String academicLevel, String asurite) {
    RosterDatabase.getInstance().addEntry(id, firstName, lastName, programPlan, academicLevel, asurite);
  }
}
