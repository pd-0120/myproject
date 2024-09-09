package Controller;

import static Controller.RegisterController.isValidEmail;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import drs.App;
import Enum.Role;
import drs.AuthenticationSession;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author PJ
 */
public class LoginController implements Initializable {

    @FXML
    private Button registerBtn;
    @FXML
    private Button submitBtn;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorLabel;
    public String userFirstName;
    public String userLastName;
    public String userRole;
    public String userEmail;
    public String userPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onRegisterBtnClick(MouseEvent event) throws IOException {
        App.setRoot("register");
    }

    @FXML
    private void submitBtnClick(MouseEvent event) throws CsvValidationException, IOException {

        userEmail = email.getText();
        userPassword = password.getText();

        String csvFile = "users.csv";
        boolean isValidUser = true;
        String errorString = "";
        List<String> errors = new ArrayList<>();
        errorLabel.setText("");
        File file = new File(csvFile);

        if (email.getText().isEmpty()) {
            isValidUser = false;
            errors.add("Email can not be left empty");
        } else if (!isValidEmail(email.getText())) {
            isValidUser = false;
            errors.add("Email is not valid");
        }

        if (password.getText().isEmpty()) {
            isValidUser = false;
            errors.add("Password can not be left empty");
        } else if (password.getText().length() < 5) {
            isValidUser = false;
            errors.add("Password must be atleast 5 characters");
        }

        if (!checkValidUser()) {
            isValidUser = false;
            errors.add("User id password does not match.");
        }

        if (!errors.isEmpty()) {
            for (String error : errors) {
                System.out.println(errorString);
                errorString = errorString + "- " + error + "\n";
            }
            errorLabel.setText(errorString);
        } else {
            AuthenticationSession as = new AuthenticationSession(userEmail, userRole, userFirstName, userLastName);

            if (userRole.equals(Role.ADMIN.getDisplayName())) {
                App.setRoot("dashboard");
            } else if (userRole.equals(Role.USER.getDisplayName()))  {
                App.setRoot("userDashboard");
            }
        }
    }

    public boolean checkValidUser() throws CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader("users.csv"))) {
            // Skip the header row
            reader.readNext();

            String[] nextLine;

            // Read the file row by row
            while ((nextLine = reader.readNext()) != null) {
                String email = nextLine[2];      // Email is at index 2
                String password = nextLine[5];   // Password is at index 5
                userFirstName = nextLine[0];
                userLastName = nextLine[1];
                userRole = nextLine[3];
                // Compare the email and password with the provided input
                if (email.equals(userEmail) && password.equals(userPassword)) {
                    return true;  // Match found
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
