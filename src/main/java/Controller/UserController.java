package Controller;

import Models.User;
import drs.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * FXML Controller class
 *
 * @author PJ
 */
public class UserController {

    @FXML
    private MenuItem menuHome;
    @FXML
    private MenuItem menuUser;
    @FXML
    private MenuItem menuDisaster;
    @FXML
    private MenuItem menuFundRaising;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> firstNameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    @FXML
    private TableColumn<User, String> phoneColumn;
    @FXML
    private MenuItem menuLogout;
    @FXML
    private Button addUserBtn;
    private ObservableList<User> userData;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() throws CsvValidationException, CsvException {
        userData = FXCollections.observableArrayList();

        listUsers();
    }

    @FXML
    private void onMenuHomeAction(ActionEvent event) throws IOException {
        MenucController.dashboardPage();
    }

    @FXML
    private void onMenuUserAcion(ActionEvent event) throws IOException {
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
    private void onAddUserBtnClick(MouseEvent event) throws IOException {
        App.setRoot("addUser");
    }

    public void listUsers() throws CsvValidationException, CsvException {
        String csvFile = "users.csv";
        File file = new File(csvFile);

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        if (file.exists()) {
            try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
                // Skip the header row
                reader.readNext();

                List<String[]> rows = reader.readAll();

                // Process the data
                for (String[] row : rows) {
                    User user = new User(row[0], row[1], row[2], row[3], row[4]);
                    userData.add(user);
                }
                userTable.setItems(userData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
