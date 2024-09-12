package Controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import drs.App;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author PJ
 */
public class DashboardController extends App {
    
    @FXML
    private MenuItem menuHome;
    @FXML
    private MenuItem menuUser;
    @FXML
    private MenuItem menuDisaster;
    @FXML
    private MenuItem menuFundRaising;
    @FXML
    private Button userBtn;
    @FXML
    private Button disasterReportBtn;
    @FXML
    private Button organisationBtn;
    @FXML
    private Button activeDisasterBtn;
    @FXML
    private Button fundBtn;
    @FXML
    private MenuItem menuLogout;

    /**
     * Initializes the controller class.
     */
    @FXML    
    public void initialize() throws FileNotFoundException, IOException, CsvException {
        String userFileName = "users.csv";
        int userRowCount = 0;
        File userFile = new File(userFileName);
        if (userFile.exists()) {
            try (CSVReader reader = new CSVReader(new FileReader(userFileName))) {
                userRowCount = reader.readAll().size() - 1;
            }
        }
        userBtn.setText("Total Users : " + userRowCount);
        
        String disasterFileName = "disasters.csv";
        int disasterRowCount = 0;
        File disasterFile = new File(disasterFileName);
        if (disasterFile.exists()) {
            try (CSVReader reader = new CSVReader(new FileReader(disasterFileName))) {
                disasterRowCount = reader.readAll().size() - 1;
            }
        }
        disasterReportBtn.setText("Total Disasters reproted : " + disasterRowCount);
        
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
    private void onUserBtnClick(MouseEvent event) throws IOException {
        MenucController.userListingPage();
    }
    
    @FXML
    private void onDisasterReportClick(MouseEvent event) {
    }
    
    @FXML
    private void onOrganisationBtnClick(MouseEvent event) {
    }
    
    @FXML
    private void onActiveDisasterBtnClick(MouseEvent event) {
    }
    
    @FXML
    private void onFundBtnClick(MouseEvent event) {
    }
}
