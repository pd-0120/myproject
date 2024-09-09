package Controller;

import drs.App;
import drs.AuthenticationSession;
import java.io.IOException;
import javafx.scene.control.Alert;

/**
 *
 * @author PJ
 */
public class MenucController {

    public static void logout() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setContentText("Do you really want to close the session and logout ?");

        // Show the alert and wait for the user to close it
        alert.showAndWait();
        if (alert.getResult().getText().equalsIgnoreCase("ok")) {
            AuthenticationSession.clearSession();
            App.setRoot("login");
        }
    }

    public static void dashboardPage() throws IOException {
        App.setRoot("dashboard");
    }

    public static void disasterListingPage() throws IOException {
        App.setRoot("disasters");
    }

    public static void fundingListingPage() throws IOException {
        App.setRoot("funding");
    }

    public static void userListingPage() throws IOException {
        App.setRoot("users");
    }
}
