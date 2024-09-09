/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
public class FundingController implements Initializable {


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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

}
