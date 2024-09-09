package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author PJ
 */
public class LoginController implements Initializable {


    @FXML
    private Button registerBtn;
    @FXML
    private Button submitBtn;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void onRegisterBtnClick(MouseEvent event) {
    }

    @FXML
    private void submitBtnClick(MouseEvent event) {
    }

}
