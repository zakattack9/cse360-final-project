package FinalProject.Parsers;

import FinalProject.Models.RosterDatabase;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class RosterParser extends CSVParser {
  public RosterParser(String filePath) {
    super(filePath);
  }

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

  private void addToDatabase(String id, String firstName, String lastName, String programPlan, String academicLevel, String asurite) {
    RosterDatabase.getInstance().addEntry(id, firstName, lastName, programPlan, academicLevel, asurite);
  }
}
