package FinalProject.Parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * CSV file parser class that includes functionality to parse a CSV line by line.
 */
public abstract class CSVParser {
  protected String filePath;

  /**
   * Constructor that sets the file path of the CSV to be parsed.
   *
   * @param filePath of the CSV to be parsed.
   */
  public CSVParser(String filePath) {
    this.filePath = filePath;
  }

  /**
   * A method that can be called by runParser() to parse a single line from a CSV file;
   * this method should return a boolean indicating whether the parsing for the passed in line was successful or not.
   *
   * @param line from the CSV to be parsed.
   * @return boolean representing a successful or unsuccessful parsing of the line.
   */
  protected abstract boolean parseLine(Scanner line);

  /**
   * Starts line by line parsing of the CSV file and passes an already configured Scanner to the parseLine() method;
   * if the parsing of any line was unsuccessful, the method immediately exits and returns an appropriate boolean value.
   *
   * @return boolean representing whether the CSV file was successfully parsed or not.
   */
  public boolean runParser() {
    Scanner parser = initializeParser();
    if (parser == null) return false;
    while(parser.hasNextLine()) {
      Scanner line = new Scanner(parser.nextLine());
      line.useDelimiter(",");
      boolean parseSuccessful = parseLine(line);
      if (!parseSuccessful) return false;
    }
    return true;
  }

  /**
   * Initializes a new Scanner that reads input from the CSV file specified by the filepath passed into the constructor;
   * if the filepath is not valid, the exception is caught and no Scanner is instantiated.
   *
   * @return null if the filepath is not valid, otherwise return a new Scanner that reads from a specified CSV file.
   */
  protected Scanner initializeParser() {
    Scanner scanner;
    try {
      File file = new File(filePath);
      scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      scanner = null;
    }
    return scanner;
  }
}
