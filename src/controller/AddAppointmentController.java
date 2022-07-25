package controller;

import DBAccess.AppointmentQuery;
import DBAccess.ContactQuery;
import DBAccess.CustomerQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
    private TextField descriptionTextfield;
    @FXML
    private TextField locationTextfield;
    @FXML
    private TextField titleTextfield;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private ComboBox<String> contactComboBox;
    @FXML
    private ComboBox<Integer> customerIDComboBox;
    @FXML
    private javafx.scene.control.DatePicker DatePicker;
    @FXML
    private ComboBox<Integer> startMinuteComboBox;
    @FXML
    private ComboBox<Integer> startHourComboBox;
    @FXML
    private ComboBox<Integer> endMinuteComboBox;
    @FXML
    private ComboBox<Integer> endHourComboBox;

    @FXML
    void onActionAppCancel(ActionEvent event) throws IOException {

        clearFields();
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentsForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionAppSave(ActionEvent event) throws SQLException {

        if(AppointmentsFormController.getIsLabelAdd()){
            AppointmentQuery.addAppointment(titleTextfield.getText(),descriptionTextfield.getText(),locationTextfield.getText(),
                                            typeComboBox.getSelectionModel().getSelectedItem(), Date.valueOf(LocalDate.now()),
                                                Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now()),"U",
                                                    Timestamp.valueOf(LocalDateTime.now()),"U",customerIDComboBox.getSelectionModel().getSelectedItem(),
                                                1, getIDOnly(contactComboBox.getSelectionModel().getSelectedItem()));
        }

    }

    @FXML
    void onActionDatePicker(ActionEvent event) {

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCusLabel();
        try {
            setComboBoxes();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(!AppointmentsFormController.getIsLabelAdd()){
            try {
                setUpTextfields(AppointmentsFormController.getUpdateAppt());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

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

    private void setComboBoxes() throws SQLException {

        typeComboBox.setItems(getTypesList());
        contactComboBox.setItems(ContactQuery.getAllContactsList());
        customerIDComboBox.setItems(CustomerQuery.getAllCustomerIDs());
        startMinuteComboBox.setItems(getMinute());
        startHourComboBox.setItems(getHour());
        endMinuteComboBox.setItems(getMinute());
        endHourComboBox.setItems(getHour());

    }

    private int getIDOnly(String rawString) {

        int idInt;
        String idString;

        idString = rawString.substring(rawString.indexOf("(") +1, rawString.indexOf(")"));//id as String from String
        idInt = Integer.parseInt(idString);//turn string id into int id

        return idInt;
    }


    private void clearFields() {

        descriptionTextfield.clear();
        locationTextfield.clear();
        idTextfield.clear();
        titleTextfield.clear();
    }

    private void setUpTextfields(Appointment updateAppt) throws SQLException {

        idTextfield.setText("" + updateAppt.getApptID());
        titleTextfield.setText(updateAppt.getTitle());
        descriptionTextfield.setText(updateAppt.getDescription());
        locationTextfield.setText(updateAppt.getLocation());
        typeComboBox.getSelectionModel().select(updateAppt.getType());
        contactComboBox.getSelectionModel().select(ContactQuery.getContactIndexFromContact(updateAppt.getContact()));
        customerIDComboBox.getSelectionModel().select(updateAppt.getCustomerID());
    }

    private ObservableList<String> getTypesList() {
        ObservableList<String > types = FXCollections.observableArrayList();
        types.addAll("Planning Session","De-Briefing","Briefing","Brainstorm Session","Appointment Making","Introduction","Other");
        return types;
    }

    private ObservableList<Integer> getMinute() {
        ObservableList<Integer > m = FXCollections.observableArrayList();
        m.addAll(00,15,30,45);
        return m;
    }

    private ObservableList<Integer> getHour() {
        ObservableList<Integer > h = FXCollections.observableArrayList();
        h.addAll(8,9,10,11,12,13,14,15,16,17,18,19,20,21);
        return h;
    }

}
