package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import drs.App;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author PJ
 */
public class UserDashboardController implements Initializable {


    @FXML
    private MenuItem menuHome;
    @FXML
    private MenuItem menuDisasterReport;
    @FXML
    private MenuItem menuDisasterReported;
    @FXML
    private MenuItem menuLogout;
    @FXML
    private Button disasterReportBtn;
    @FXML
    private Button disasterReportApprovedBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void onMenuHomeAction(ActionEvent event) throws IOException {
        App.setRoot("userDashboard");
    }

    @FXML
    private void onMenuDisasterAction(ActionEvent event) {
    }

    @FXML
    private void onMenuDisasterReportedAction(ActionEvent event) throws IOException {
        App.setRoot("reportDisaster");
    }

    @FXML
    private void onMenuLogoutAction(ActionEvent event) throws IOException {
        MenucController.logout();
    }

    @FXML
    private void onDisasterReportClick(MouseEvent event) throws IOException {
        App.setRoot("reportDisaster");
    }

}
