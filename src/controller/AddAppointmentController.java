package controller;

import DBAccess.AppointmentQuery;
import DBAccess.ContactQuery;
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
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private TextField idTextfield;
    @FXML
    private TextField customerIDTextfield;
    @FXML
    private TextField descriptionTextfield;
    @FXML
    private TextField locationTextfield;
    @FXML
    private TextField titleTextfield;
    @FXML
    private TextField userIDTextfield;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private ComboBox<String> contactComboBox;
    @FXML
    private ComboBox<?> startDayComboBox;
    @FXML
    private ComboBox<?> startMonthComboBox;
    @FXML
    private ComboBox<?> startYearComboBox;
    @FXML
    private ComboBox<?> startMinuteComboBox;
    @FXML
    private ComboBox<?> startHourComboBox;
    @FXML
    private ComboBox<?> endDayComboBox;
    @FXML
    private ComboBox<?> endMonthComboBox;
    @FXML
    private ComboBox<?> endYearComboBox;
    @FXML
    private ComboBox<?> endMinuteComboBox;
    @FXML
    private ComboBox<?> endHourComboBox;

    @FXML
    void onActionAppCancel(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentsForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionAppSave(ActionEvent event) {

        if(AppointmentsFormController.getIsLabelAdd()){
            AppointmentQuery.addAppointment(titleTextfield.getText(),descriptionTextfield.getText(),locationTextfield.getText(),
                                            typeComboBox.getSelectionModel().getSelectedItem(), Date.valueOf(LocalDate.now()),
                                                Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now()),"U",
                                                    Timestamp.valueOf(LocalDateTime.now()),"U",Integer.parseInt(customerIDTextfield.getText()),
                                                777, ContactQuery.getContactIDByContact(contactComboBox.getSelectionModel().getSelectedItem()));
        }

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCusLabel();

    }



    //METHODS

    @FXML
    private void setCusLabel(){

        if(AppointmentsFormController.getIsLabelAdd()){
            this.appointmentTitleLabel.setText("Add Appointment");
            this.idTextfield.setText("AUTO GENERATED");
        }else{
            this.appointmentTitleLabel.setText("Update Appointment");
        }

    }



}
