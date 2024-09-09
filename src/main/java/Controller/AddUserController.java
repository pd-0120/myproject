package Controller;

import static Controller.RegisterController.isEmailInCsv;
import static Controller.RegisterController.isValidEmail;
import Enum.Role;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.exceptions.CsvException;
import drs.App;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author PJ
 */
public class AddUserController {

    @FXML
    private TextField phoneNumber;
    @FXML
    private MenuItem menuHome;
    @FXML
    private MenuItem menuUser;
    @FXML
    private MenuItem menuDisaster;
    @FXML
    private MenuItem menuFundRaising;
    @FXML
    private MenuItem menuLogout;
    @FXML
    private Button addUserSubmitBtn;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<String> role;
    @FXML
    private Label errorLabel;
    @FXML
    private Label successMessasgelabel;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        role.getItems().addAll(Role.ADMIN.getDisplayName(), Role.GUEST.getDisplayName(), Role.SUPPORT.getDisplayName(), Role.USER.getDisplayName());
    }

    @FXML
    private void onMenuHomeAction(ActionEvent event) throws IOException {
        MenucController.dashboardPage();
    }

    @FXML
    private void onMenuUserAcion() throws IOException {
        MenucController.userListingPage();
    }

    @FXML
    private void onMenuDisasterAction(ActionEvent event) throws IOException {
        MenucController.disasterListingPage();
    }

    @FXML
    private void onFundRaisingAction(ActionEvent event) throws IOException {
        MenucController.fundingListingPage();
    }

    @FXML
    private void onMenuLogoutAction(ActionEvent event) throws IOException {
        MenucController.logout();
    }

    @FXML
    private void onAddUserSubmitBtn(MouseEvent event) throws CsvValidationException, CsvException {
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
        } else if (file.exists() && isEmailInCsv(csvFile, email.getText())) {
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
        String[] header = {"First Name", "Last Name", "Email", "Role", "Phone", "Password"};
        String[] user = {firstName.getText(), lastName.getText(), email.getText(), role.getValue(), phoneNumber.getText(), password.getText()};

        if (isValidUser) {
            // Writing data to the CSV file
            try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile, true))) {
                // Write the header
                if (!file.exists()) {
                    writer.writeNext(header);
                }
                writer.writeNext(user);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText("User has been added successfully.");
 
                // Show the alert and wait for the user to close it
                alert.showAndWait();

                App.setRoot("users");
                onMenuUserAcion();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

}
