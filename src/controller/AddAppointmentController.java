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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private ComboBox<String> customerIDComboBox;
    @FXML
    private javafx.scene.control.DatePicker datePicker;
    @FXML
    private ComboBox<String> startMinuteComboBox;
    @FXML
    private ComboBox<String> startHourComboBox;
    @FXML
    private ComboBox<String> endMinuteComboBox;
    @FXML
    private ComboBox<String> endHourComboBox;

    @FXML
    void onActionAppCancel(ActionEvent event) throws IOException {

        clearFields();
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentsForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionAppSave(ActionEvent event) throws SQLException, IOException {

        if(AppointmentsFormController.getIsLabelAdd()){
            AppointmentQuery.addAppointment(titleTextfield.getText(),descriptionTextfield.getText(),locationTextfield.getText(),
                                            typeComboBox.getSelectionModel().getSelectedItem(),Timestamp.valueOf(getStartLocalDateTime()),
                                                Timestamp.valueOf(getEndLocalDateTime()),Timestamp.valueOf(LocalDateTime.now()),LogInFormController.getUser().getUserName(),
                                                    Timestamp.valueOf(LocalDateTime.now()),LogInFormController.getUser().getUserName(),
                                                        Integer.parseInt(customerIDComboBox.getSelectionModel().getSelectedItem()),LogInFormController.getUser().getUserID(),
                                                            getIDOnly(contactComboBox.getSelectionModel().getSelectedItem()));
        }else{
            AppointmentQuery.updateAppointment(titleTextfield.getText(),descriptionTextfield.getText(),locationTextfield.getText(),
                    typeComboBox.getSelectionModel().getSelectedItem(), Timestamp.valueOf(getStartLocalDateTime()),
                    Timestamp.valueOf(getEndLocalDateTime()),Timestamp.valueOf(LocalDateTime.now()),LogInFormController.getUser().getUserName(),
                    Integer.parseInt(customerIDComboBox.getSelectionModel().getSelectedItem()),LogInFormController.getUser().getUserID(),
                                getIDOnly(contactComboBox.getSelectionModel().getSelectedItem()));
        }

        clearFields();
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentsForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

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
        startMinuteComboBox.setItems(getMinuteList());
        startHourComboBox.setItems(getHourList());
        endMinuteComboBox.setItems(getMinuteList());
        endHourComboBox.setItems(getHourList());

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
        customerIDComboBox.getSelectionModel().select(String.valueOf(updateAppt.getCustomerID()));
        datePicker.setValue(updateAppt.getStart().toLocalDate());
        startHourComboBox.getSelectionModel().select(String.valueOf(updateAppt.getStart().toLocalTime().getHour()));
        startMinuteComboBox.getSelectionModel().select(String.valueOf(updateAppt.getStart().toLocalTime().getMinute()));
        endHourComboBox.getSelectionModel().select(String.valueOf(updateAppt.getEnd().toLocalTime().getHour()));
        endMinuteComboBox.getSelectionModel().select(String.valueOf(updateAppt.getEnd().toLocalTime().getMinute()));
        System.out.println(updateAppt.getCustomerID());
    }

    private ObservableList<String> getTypesList() {
        ObservableList<String > types = FXCollections.observableArrayList();
        types.addAll("Planning Session","De-Briefing","Briefing","Brainstorm Session","Appointment Making","Introduction","Other");
        return types;
    }

    private ObservableList<String> getMinuteList() {
        ObservableList<String > m = FXCollections.observableArrayList();
        m.addAll("00","15","30","45");
        return m;
    }

    private ObservableList<String> getHourList() {
        ObservableList<String > h = FXCollections.observableArrayList();
        h.addAll("08","09","10","11","12","13","14","15","16","17","18","19","20,21");
        return h;
    }


    private LocalDateTime getStartLocalDateTime() {

        return LocalDateTime.of(datePicker.getValue(),LocalTime.parse(startHourComboBox.getSelectionModel().getSelectedItem() + ":"
                                                                        + startMinuteComboBox.getSelectionModel().getSelectedItem()));
    }

    private LocalDateTime getEndLocalDateTime() {

        return LocalDateTime.of(datePicker.getValue(),LocalTime.parse(endHourComboBox.getSelectionModel().getSelectedItem() + ":"
                                                                        + endMinuteComboBox.getSelectionModel().getSelectedItem()));
    }
}
