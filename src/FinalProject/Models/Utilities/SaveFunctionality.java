package FinalProject.Models.Utilities;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/***
 * Saves all the data currently in the table.
 */
public class SaveFunctionality {


    /***
     * This function is called in the SaveController and implements the
     * functionality of saving the data currently in the table.
     */
    public void saveCSV()
    {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Specify a file to save");
        int value = fileChooser.showSaveDialog(null);

        if(value == JFileChooser.APPROVE_OPTION)
        {
            try{

                File file = fileChooser.getSelectedFile();
                DatabaseConverter con = new DatabaseConverter();
                String[][] data = con.getArrayModel();
                FileWriter csv = new FileWriter(file);

                // adds headers
                String headers = String.join(",", con.getDBKeys()) + "\n";
                csv.write(headers);

                //adds data
                for(String[] row : data) {
                    String joinedString = String.join(",", row) + "\n";
                    csv.write(joinedString);
                }

                csv.close();

            }
            // handles IOException
            catch(IOException ex){
                ex.printStackTrace();
            }

        }


    }

}

