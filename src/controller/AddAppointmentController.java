package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    private Stage stage;
    private Parent scene;

    @FXML
    private Button appointmentCancelButton;
    @FXML
    private Button appointmentSaveButton;
    @FXML
    private Label appointmentTitleLabel;
    @FXML
    private ComboBox<?> contactComboBox;
    @FXML
    private TextField customerIDTextfield;
    @FXML
    private TextField descriptionTextfield;
    @FXML
    private ComboBox<?> endDayComboBox;
    @FXML
    private ComboBox<?> endHourComboBox;
    @FXML
    private ComboBox<?> endMinuteComboBox;
    @FXML
    private ComboBox<?> endMonthComboBox;
    @FXML
    private ComboBox<?> endYearComboBox;
    @FXML
    private TextField idTextfield;
    @FXML
    private TextField locationTextfield;
    @FXML
    private ComboBox<?> startDayComboBox;
    @FXML
    private ComboBox<?> startHourComboBox;
    @FXML
    private ComboBox<?> startMinuteComboBox;
    @FXML
    private ComboBox<?> startMonthComboBox;
    @FXML
    private ComboBox<?> startYearComboBox;
    @FXML
    private TextField titleTextfield;
    @FXML
    private TextField typeTextfield;
    @FXML
    private TextField userIDTextfield;


    @FXML
    void onActionAppCancel(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenuForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionAppSave(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
