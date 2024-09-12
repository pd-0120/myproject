package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import drs.App;
import drs.AuthenticationSession;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author PJ
 */
public class UserDashboardController{

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
    @FXML
    public void initialize() {
        System.out.println(AuthenticationSession.isLoggedIn());
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
    private void onDisasterReportClick(MouseEvent event) throws IOException {
        App.setRoot("reportDisaster");
    }

    @FXML
    private void onMenuReportDisasterAction(ActionEvent event) throws IOException {
        App.setRoot("reportDisaster");
    }
}
