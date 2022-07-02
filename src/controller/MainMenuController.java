package controller;

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
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    private Stage stage;
    private Parent scene;

    @FXML
    private Button mainAppAddButton;
    @FXML
    private Button mainAppDeleteButton;
    @FXML
    private Button mainAppUpdateButton;
    @FXML
    private Button mainCusAddButton;
    @FXML
    private Button mainCusDeleteButton;
    @FXML
    private Button mainCusUpdateButton;
    @FXML
    private Label mainMonth1Label;
    @FXML
    private Label mainMonth2Label;
    @FXML
    private GridPane mainMonthGridpane1;
    @FXML
    private GridPane mainMonthGridpane2;
    @FXML
    private Button mainReport1Button;
    @FXML
    private Button mainReport2Button;
    @FXML
    private Button mainReport3Button;

    @FXML
    void onActionMainAppAdd(ActionEvent event) {

    }

    @FXML
    void onActionMainAppDelete(ActionEvent event) {

    }

    @FXML
    void onActionMainAppUpdate(ActionEvent event) {

    }

    /**
     * This method is activated from the Add Customer button and switches the scene to the AddCustomerForm.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionMainCusAdd(ActionEvent event) throws IOException {

        switchScene("/view/AddCustomerForm.fxml",event);

    }



    @FXML
    void onActionMainCusDelete(ActionEvent event) {

    }

    @FXML
    void onActionMainCusUpdate(ActionEvent event) {

    }

    @FXML
    void onActionMainReport1(ActionEvent event) {

    }

    @FXML
    void onActionMainReport2(ActionEvent event) {

    }

    @FXML
    void onActionMainReport3(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Sup from Controller");
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
