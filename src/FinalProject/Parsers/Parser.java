package FinalProject.Parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Parser {
  protected String filePath;

  public Parser(String filePath) {
    this.filePath = filePath;
  }

  public abstract void runParser();
  protected abstract boolean parseLine(Scanner line);

  protected Scanner initializeParser() {
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
