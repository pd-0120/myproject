package Controller;

import Models.Disaster;
import Enum.DisasterStatus;
import Enum.DisasterPriority;
import Enum.DisasterDepartment;
import Utils.ShowAlert;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author PJ
 */
public class DisasterController {

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
    private TableColumn<Disaster, String> nameColumn;
    @FXML
    private TableColumn<Disaster, String> descriptionColumn;
    @FXML
    private TableColumn<Disaster, String> locationColumn;
    @FXML
    private TableColumn<Disaster, String> damageColumn;
    @FXML
    private TableColumn<Disaster, String> categoryColumn;
    @FXML
    private TableColumn<Disaster, String> riskColumn;
    @FXML
    private TableColumn<Disaster, String> dateColumn;
    @FXML
    private TableColumn<Disaster, String> statusColumn;
    private ObservableList<Disaster> disasterData;
    @FXML
    private TableView<Disaster> disasterTable;
    @FXML
    private GridPane updateFieldPanel;
    @FXML
    private ComboBox<String> statusField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private Button updateBtn;

    private Disaster selectedDisaster;
    @FXML
    private Label errorLabel;
    private int selectedIndexValue;
    @FXML
    private TextField responseToDisaster;
    @FXML
    private ComboBox<String> priority;
    @FXML
    private ComboBox<String> department;

    /**
     * Initializes the controller class.
     */
    public void initialize() throws CsvValidationException, CsvException {
        statusField.getItems().addAll(
                DisasterStatus.APPROVED.getDisplayName(),
                DisasterStatus.PENDING.getDisplayName(),
                DisasterStatus.REJECTED.getDisplayName());

        priority.getItems().addAll(
                DisasterPriority.HIGH.getDisplayName(),
                DisasterPriority.MEDIUM.getDisplayName(),
                DisasterPriority.LOW.getDisplayName());

        department.getItems().addAll(
                DisasterDepartment.CYBER.getDisplayName(),
                DisasterDepartment.FINANCE.getDisplayName(),
                DisasterDepartment.FIRE.getDisplayName(),
                DisasterDepartment.FORENSIC.getDisplayName(),
                DisasterDepartment.MEDICAL.getDisplayName(),
                DisasterDepartment.POLICE.getDisplayName());

        disasterData = FXCollections.observableArrayList();
        listDisasters();
        updateFieldPanel.setVisible(false);
        disasterTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, disaster) -> {
            if (disaster != null) {
                selectedIndexValue = disasterTable.getSelectionModel().getSelectedIndex() + 1;
                updateFieldPanel.setVisible(true);
                selectedDisaster = disaster;
                
                nameField.setText(selectedDisaster.getName());
                descriptionField.setText(selectedDisaster.getDescription());
                statusField.setValue(selectedDisaster.getStatus());
                responseToDisaster.setText(selectedDisaster.getResponseToDisaster());
                priority.setValue(selectedDisaster.getPriority());
                department.setValue(selectedDisaster.getAssociatedDepartment());
                
            }
        });

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

    public void listDisasters() throws CsvValidationException, CsvException {
        String csvFile = "disasters.csv";
        File file = new File(csvFile);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        damageColumn.setCellValueFactory(new PropertyValueFactory<>("damage"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        riskColumn.setCellValueFactory(new PropertyValueFactory<>("risk"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        if (file.exists()) {
            try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
                // Skip the header row
                reader.readNext();

                List<String[]> rows = reader.readAll();

                // Process the data
                for (String[] row : rows) {
                    Disaster disaster = new Disaster(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[8]);
                    disasterData.add(disaster);
                }
                disasterTable.setItems(disasterData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onUpdateBtnClick(MouseEvent event) throws IOException, CsvException {
        String name = nameField.getText();
        String description = descriptionField.getText();

        String errorString = "";
        List<String> errors = new ArrayList<>();
        errorLabel.setText(" ");

        if (name.isEmpty() || name.isBlank() || name.length() < 5) {
            errors.add("Make sure that the name is not empty and has more than 5 characters");
        }
        if (description.isEmpty() || description.isBlank() || description.length() < 20) {
            errors.add("Make sure that the description is not empty and has more than 20 characters");
        }

        selectedDisaster.setName(name);
        selectedDisaster.setDescription(description);
        selectedDisaster.setStatus(statusField.getValue());
        selectedDisaster.setResponseToDisaster(responseToDisaster.getText());
        selectedDisaster.setPriority(priority.getValue());
        selectedDisaster.setAssociatedDepartment(department.getValue());

        if (errors.isEmpty()) {
            String filePath = "disasters.csv";
            try {
                // Read the entire CSV file
                CSVReader reader = new CSVReader(new FileReader(filePath));
                List<String[]> csvData = reader.readAll(); // Get all rows in the CSV
                reader.close();

                // Modify a specific row at index, for example row 2
                int rowIndex = selectedIndexValue; // Remember index is 0-based
                if (rowIndex < csvData.size()) {
                    String[] row = csvData.get(rowIndex); // Get the row at the specified index
                    System.err.println(row.length);
                    row[0] = selectedDisaster.getName();
                    row[1] = selectedDisaster.getDescription();
                    row[8] = selectedDisaster.getStatus();
                    row[9] = selectedDisaster.getPriority();
                    row[10] = selectedDisaster.getAssociatedDepartment();
                    row[11] = selectedDisaster.getResponseToDisaster();

                    csvData.set(rowIndex, row); // Update the row in the list
                } else {
                    System.out.println("Row index out of bounds.");
                }

                // Write the updated data back to the CSV file
                CSVWriter writer = new CSVWriter(new FileWriter(filePath));
                writer.writeAll(csvData); // Write the entire CSV back to the file
                writer.close();

                ShowAlert showAlert = new ShowAlert(Alert.AlertType.INFORMATION, "success", "Disaster report", "Disaster updated sucessfully.");
                disasterTable.refresh();

                updateFieldPanel.setVisible(false);
                disasterTable.getSelectionModel().clearSelection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            for (String error : errors) {
                System.out.println(errorString);
                errorString = errorString + "- " + error + "\n";
            }
            errorLabel.setText(errorString);
        }
    }
}
