package Utils;

import javafx.scene.control.Alert;

/**
 *
 * @author PJ
 */
public class ShowAlert {

    public Alert.AlertType alertType;
    public String title;
    public String header;
    public String context;

    public ShowAlert(Alert.AlertType alertType, String title, String header, String context) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);

        // Show the alert and wait for the user to close it
        alert.showAndWait();
    }
}
