package Utils;

import com.opencsv.CSVWriter;
import drs.App;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.Alert;

/**
 *
 * @author PJ
 */
public class WriteToFile {

    private final String fileName;
    private final String[] fileHeader;
    private final String[] writeData;

    public WriteToFile(String fileName, String[] fileHeader, String[] writeData) {
        this.fileHeader = fileHeader;
        this.fileName = fileName;
        this.writeData = writeData;
    }

    public boolean writeData() {
        File file = new File(fileName);
        // Writing data to the CSV file
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName, true))) {
            // Write the header
            if (!file.exists()) {
                writer.writeNext(fileHeader);
            }
            writer.writeNext(writeData);
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }
}
