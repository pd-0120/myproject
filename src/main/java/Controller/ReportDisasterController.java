package Controller;

import drs.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author PJ
 */
public class ReportDisasterController implements Initializable {

    @FXML
    private MenuItem menuHome;
    @FXML
    private MenuItem menuLogout;
    @FXML
    private MenuItem menuDisasterReport;
    @FXML
    private MenuItem menuDisasterReported;

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
}
