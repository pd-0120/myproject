package Controller;

import drs.App;
import Enum.Role;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.opencsv.CSVWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author PJ
 */
public class RegisterController extends App implements Initializable {

    private static final String EMAIL_PATTERN
            = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
            + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

    @FXML
    private Button loginBtn;
    @FXML
    private TextField lastName;
    @FXML
    private TextField firstName;
    @FXML
    private TextField email;
    @FXML
    private TextField phoneNumber;
    @FXML
    private PasswordField password;
    @FXML
    private Button registerBtn;
    @FXML
    private Label errorLabel;
    @FXML
    private Label successMessasgelabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onLoginBtnClick(MouseEvent event) throws IOException {
        System.out.println(event);
        App.setRoot("login");
    }

    @FXML
    private void onRegisterBtnClick(MouseEvent event) throws IOException, InterruptedException, CsvValidationException {
        String csvFile = "users.csv";
        boolean isValidUser = true;
        String errorString = "";
        List<String> errors = new ArrayList<>();
        errorLabel.setText("");
        File file = new File(csvFile);
        boolean isFileExists = file.exists();
        
        if (email.getText().isEmpty()) {
            isValidUser = false;
            errors.add("Email can not be left empty");
        } else if (!isValidEmail(email.getText())) {
            isValidUser = false;
            errors.add("Email is not valid");
        } else if (isFileExists && isEmailInCsv(csvFile, email.getText())) {
            isValidUser = false;
            errors.add("Email is exists. Please use different email.");
        }

        if (firstName.getText().isEmpty()) {
            isValidUser = false;
            errors.add("First Name can not be left empty");
        }

        if (lastName.getText().isEmpty()) {
            isValidUser = false;
            errors.add("Last Name can not be left empty");
        }
        if (phoneNumber.getText().isEmpty()) {
            isValidUser = false;
            errors.add("Phone number can not be left empty");
        }

        if (password.getText().isEmpty()) {
            isValidUser = false;
            errors.add("Password can not be left empty");
        } else if (password.getText().length() < 5) {
            isValidUser = false;
            errors.add("Password must be atleast 5 characters");
        }

        if (!errors.isEmpty()) {
            for (String error : errors) {
                System.out.println(errorString);
                errorString = errorString + "- " + error + "\n";
            }
            errorLabel.setText(errorString);
        }

        // Data to be written to the CSV file
        String[] header = {"First Name", "Last Name", "Email", "Role", "Phone", "Password", "Department"};
        String[] user = {firstName.getText(), lastName.getText(), email.getText(), Role.USER.getDisplayName(), phoneNumber.getText(), password.getText()};

        if (isValidUser) {
            // Writing data to the CSV file
            try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile, true))) {
                // Write the header
                if (!isFileExists) {
                    writer.writeNext(header);
                }
                writer.writeNext(user);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText("User has been registered successfully.");
                alert.setContentText("Click to redirect on login page.");

                // Show the alert and wait for the user to close it
                alert.showAndWait();

                successMessasgelabel.setText("User has been registered successfully. Redirecting now.....");
                System.out.println("Data written successfully to " + csvFile);
                App.setRoot("login");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static boolean isValidEmail(String email) {

        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();  // Returns true if email matches the pattern
    }

    public static boolean isEmailInCsv(String filePath, String emailToCheck) throws CsvValidationException {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;

            // Read the header row (first line)
            String[] header = csvReader.readNext();
            if (header == null) {
                System.out.println("Empty CSV file.");
                return false;
            }

            // Find the index of the email column
            int emailColumnIndex = -1;
            for (int i = 0; i < header.length; i++) {
                if (header[i].equalsIgnoreCase("Email")) {
                    emailColumnIndex = i;
                    break;
                }
            }

            if (emailColumnIndex == -1) {
                System.out.println("Email column not found.");
                return false;
            }

            // Iterate through the CSV rows and check if the email exists
            while ((nextLine = csvReader.readNext()) != null) {
                if (nextLine.length > emailColumnIndex && nextLine[emailColumnIndex].equalsIgnoreCase(emailToCheck)) {
                    return true; // Email found
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Email not found
    }
}
