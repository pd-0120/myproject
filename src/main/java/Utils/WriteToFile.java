package Utils;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        File inputFile = new File(fileName);
        boolean isFileExists = inputFile.exists();
        // Writing data to the CSV file
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName, true))) {
            // Write the header
            System.out.println("File Status\n");
            System.out.println(inputFile.exists());
            System.out.println(inputFile.isFile());
            if (!isFileExists) {
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
