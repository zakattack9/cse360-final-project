package FinalProject.Parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class CSVParser {
  protected String filePath;

  public CSVParser(String filePath) {
    this.filePath = filePath;
  }

  protected abstract boolean parseLine(Scanner line);

  public boolean runParser() {
    Scanner parser = initializeParser();
    while(parser != null && parser.hasNextLine()) {
      Scanner line = new Scanner(parser.nextLine());
      line.useDelimiter(",");
      boolean parseSuccessful = parseLine(line);
      if (!parseSuccessful) return false;
    }
    return true;
  }

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
