package controller;

import DBAccess.ContactQuery;
import Database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    private Stage stage;
    private Parent scene;


    @FXML
    private Label mainMonth1Label;
    @FXML
    private Label mainMonth2Label;
    @FXML
    private GridPane mainMonthGridpane1;
    @FXML
    private GridPane mainMonthGridpane2;
    @FXML
    private Button customerReportButton;
    @FXML
    private Button contactReportButton;
    @FXML
    private Button countryReportButton;
    @FXML
    private Button mainAppButton;
    @FXML
    private Button mainCusButton;
    @FXML
    private Button mainExitButton;





    @FXML
    void onActionCustomerReportButton(ActionEvent event) throws IOException {

        switchScene("/view/CustomerReportForm.fxml",event);

    }

    @FXML
    void onActionContactReportButton(ActionEvent event) throws IOException {

        switchScene("/view/ContactReportForm.fxml",event);

    }

    @FXML
    void onActionCountryReportButton(ActionEvent event) throws IOException {

        switchScene("/view/CountryReportForm.fxml",event);

    }

    @FXML
    void onActionMainAppButton(ActionEvent event) throws IOException {

        switchScene("/view/AppointmentsForm.fxml",event);

    }

    @FXML
    void onActionMainCusButton(ActionEvent event) throws IOException {

        switchScene("/view/CustomersForm.fxml",event);

    }

    @FXML
    void onActionMainExitButton(ActionEvent event) {

        DBConnection.closeConnection();
        System.exit(0);

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }




    //METHODS

    /**
     * This method switches the scene that is shown to the user.
     * @param newFXML is the path of the fxml file that is being switched to
     * @param event is the ActionEvent that activates this method
     * @throws IOException
     */
    private void switchScene(String newFXML, ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(newFXML));
        stage.setScene(new Scene(scene));
        stage.show();

    }






}
