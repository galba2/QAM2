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
import java.time.*;
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
                                getIDOnly(contactComboBox.getSelectionModel().getSelectedItem()),Integer.valueOf(idTextfield.getText()));
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
        startHourComboBox.setItems(getHourList(true));
        endMinuteComboBox.setItems(getMinuteList());
        endHourComboBox.setItems(getHourList(false));

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

        String startMinuteString;
        String endMinuteString;
        String startHourString;
        String endHourString;

        if(String.valueOf(updateAppt.getStart().toLocalTime().getMinute()).compareTo("0") == 0){//check if start minute is single digit
            startMinuteString = "00";
        }else{
            startMinuteString = String.valueOf(updateAppt.getStart().toLocalTime().getMinute());
        }

        if(String.valueOf(updateAppt.getEnd().toLocalTime().getMinute()).compareTo("0") == 0){//check if end minute is single digit
            endMinuteString = "00";
        }else{
            endMinuteString = String.valueOf(updateAppt.getEnd().toLocalTime().getMinute());
        }

        if(String.valueOf(updateAppt.getStart().toLocalTime().getHour()).length() == 1){//check if start hour is single digit
            startHourString = "0" + String.valueOf(updateAppt.getStart().toLocalTime().getHour());
        }else{
            startHourString = String.valueOf(updateAppt.getStart().toLocalTime().getHour());
        }

        if(String.valueOf(updateAppt.getEnd().toLocalTime().getHour()).length() == 1){//check if end hour is single digit
            endHourString = "0" + String.valueOf(updateAppt.getEnd().toLocalTime().getHour());
        }else{
            endHourString = String.valueOf(updateAppt.getEnd().toLocalTime().getHour());
        }

        idTextfield.setText("" + updateAppt.getApptID());
        titleTextfield.setText(updateAppt.getTitle());
        descriptionTextfield.setText(updateAppt.getDescription());
        locationTextfield.setText(updateAppt.getLocation());
        typeComboBox.getSelectionModel().select(updateAppt.getType());
        contactComboBox.getSelectionModel().select(ContactQuery.getContactIndexFromContact(updateAppt.getContact()));
        customerIDComboBox.getSelectionModel().select("" + updateAppt.getCustomerID());
        datePicker.setValue(updateAppt.getStart().toLocalDate());
        startHourComboBox.getSelectionModel().select(startHourString);
        startMinuteComboBox.getSelectionModel().select(startMinuteString);
        endHourComboBox.getSelectionModel().select(endHourString);
        endMinuteComboBox.getSelectionModel().select(endMinuteString);
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

    private ObservableList<String> getHourList(Boolean isStart) {
        ObservableList<String > h = FXCollections.observableArrayList();

        LocalDate businessLocationDate = LocalDate.now();
        LocalTime businessLocationTime = LocalTime.of(8,00);
        ZoneId businessLocationZoneID = ZoneId.of("America/New_York");
        ZonedDateTime estZDT = ZonedDateTime.of(businessLocationDate,businessLocationTime,businessLocationZoneID);

        ZoneId localZoneID = ZoneId.systemDefault();

        ZonedDateTime adjustedLocalZDT = estZDT.withZoneSameInstant(localZoneID);
        System.out.println(adjustedLocalZDT);
        System.out.println(estZDT);

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
