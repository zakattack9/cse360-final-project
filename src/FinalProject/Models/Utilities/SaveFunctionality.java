package FinalProject.Models.Utilities;


import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFunctionality {



    DatabaseConverter con = new DatabaseConverter();
    String[][] data = con.getArrayModel();
    FileWriter csv = new FileWriter(new File("save.csv"));

    for(String[] row : data) {
        String joinedString = String.join(",", row) + "\n";
        csv.write(joinedString);
    }

    public SaveFunctionality() throws IOException {

    }
}

