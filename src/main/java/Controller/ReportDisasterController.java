package Controller;

import Enum.DisastersCategories;
import Enum.DisasterStatus;
import drs.App;
import drs.AuthenticationSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import Utils.WriteToFile;
import Utils.ShowAlert;

/**
 * FXML Controller class
 *
 * @author PJ
 */
public class ReportDisasterController {

    @FXML
    private MenuItem menuHome;
    @FXML
    private MenuItem menuLogout;
    @FXML
    private MenuItem menuDisasterReport;
    @FXML
    private MenuItem menuDisasterReported;

    @FXML
    private TextField name;
    @FXML
    private TextField area;
    @FXML
    private TextField damage;
    @FXML
    private TextField risk;
    @FXML
    private DatePicker date;
    @FXML
    private Button reportDisaster;
    @FXML
    private ComboBox<String> category;
    @FXML
    private TextField description;
    @FXML
    private Label errorLabel;
    private final String fileName = "disasters.csv";
    private final String[] fileHeader = {"name", "description", "location", "damage", "category", "risk", "date", "reportedBy", "status", "Priority", "Associated Department", "Response to disaster"};

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
        category.getItems().addAll(
                DisastersCategories.CHEMICAL_SPILL.getDisplayName(),
                DisastersCategories.NUCLEAR_ACCIDENT.getDisplayName(),
                DisastersCategories.OIL_SPILL.getDisplayName(),
                DisastersCategories.WAR.getDisplayName(),
                DisastersCategories.TERRORISM.getDisplayName(),
                DisastersCategories.FIRE.getDisplayName(),
                DisastersCategories.EARTHQUAKE.getDisplayName(),
                DisastersCategories.FLOOD.getDisplayName(),
                DisastersCategories.HURRICANE.getDisplayName(),
                DisastersCategories.VOLCANIC_ERUPTION.getDisplayName(),
                DisastersCategories.TORNADO.getDisplayName(),
                DisastersCategories.TSUNAMI.getDisplayName());
    }

    @FXML
    private void onMenuHomeAction(ActionEvent event) throws IOException {
        App.setRoot("userDashboard");
    }

    @FXML
    private void onMenuDisasterReportedAction(ActionEvent event) throws IOException {
        App.setRoot("viewReportedDisaster");
    }

    @FXML
    private void onMenuLogoutAction(ActionEvent event) throws IOException {
        MenucController.logout();
    }

    @FXML
    private void onMenuReportDisasterAction(ActionEvent event) throws IOException {
        App.setRoot("reportDisaster");
    }

    @FXML
    private void onReportDisasterAction(MouseEvent event) throws IOException {
        String errorString = "";
        List<String> errors = new ArrayList<>();
        errorLabel.setText(" ");
        
        
        if (name.getText().isEmpty() || name.getText().isBlank() || name.getText().length() < 5) {
            errors.add("Make sure that the name is not empty and has more than 5 characters");
        }

        if (area.getText().isEmpty() || area.getText().isBlank() || area.getText().length() < 5) {
            errors.add("Make sure that the area is not empty and has more than 5 characters");
        }
        if (damage.getText().isEmpty() || damage.getText().isBlank() || damage.getText().length() < 5) {
            errors.add("Make sure that the damage is not empty and has more than 5 characters");
        }

        if (risk.getText().isEmpty() || risk.getText().isBlank() || risk.getText().length() < 5) {
            errors.add("Make sure that the risk is not empty and has more than 5 characters");
        }
        if (date.getValue() == null) {
            errors.add("Make sure that the date is selected");
        }
        if (category.getValue() == null) {
            errors.add("Make sure that you select the category");
        }
        if (description.getText().isEmpty() || description.getText().isBlank() || description.getText().length() < 20) {
            errors.add("Make sure that the description is not empty and has more than 20 characters");
        }
        if (errors.isEmpty()) {
            String[] disaster = {name.getText(), description.getText(), area.getText(), damage.getText(), category.getValue(), risk.getText(), date.getValue().toString(), AuthenticationSession.getInstance().getEmail(), DisasterStatus.PENDING.getDisplayName()};

            WriteToFile dataWriter = new WriteToFile(fileName, fileHeader, disaster);
            boolean isDataWritten = dataWriter.writeData();
            if(isDataWritten) {
                ShowAlert showAlert = new ShowAlert(AlertType.INFORMATION, "success", "Disaster report", "Disaster reproted sucessfully.");
                
                App.setRoot("reportDisaster");
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
