package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button cCancelButton;
    @FXML
    private TextField cCityTextBox;
    @FXML
    private ComboBox<?> cCountryComboBox;
    @FXML
    private TextField cIDTextbox;
    @FXML
    private TextField cNameTextBox;
    @FXML
    private TextField cPhoneTextbox;
    @FXML
    private TextField cPostalTextBox;
    @FXML
    private Button cSaveButton;
    @FXML
    private ComboBox<?> cStateComboBox;
    @FXML
    private TextField cStreetTextBox;
    @FXML
    private Label cTitleLabel;

    @FXML
    void onActionCusCancelButton(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenuForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionCusSaveButton(ActionEvent event) {

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
