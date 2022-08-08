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
import java.util.concurrent.atomic.AtomicBoolean;

public class AddAppointmentController implements Initializable {

    private Stage stage;
    private Parent scene;
    private ZonedDateTime adjustedLocalZDT;

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
    private Label errorContactLabel;
    @FXML
    private Label errorCustomerIDLabel;
    @FXML
    private Label errorDayLabel;
    @FXML
    private Label errorDescriptionLabel;
    @FXML
    private Label errorEndHourLabel;
    @FXML
    private Label errorEndMinuteLabel;
    @FXML
    private Label errorLocationLabel;
    @FXML
    private Label errorStartHourLabel;
    @FXML
    private Label errorStartMinuteLabel;
    @FXML
    private Label errorTitleLabel;
    @FXML
    private Label errorTypeLabel;

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

        if(checkInput()){//check for errors thru method
            //do nothing else
        }else{//no errors

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
    }

    @FXML
    void onActionDatePicker(ActionEvent event) throws IOException {

        if(datePicker.getValue().isBefore(LocalDate.now())){

            PopUpFormController.setUpPopUp("ERROR!", "Please choose a date that is " + LocalDate.now().toString() + " or later.");
            Stage popUpStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/PopUpForm.fxml"));
            popUpStage.setScene(new Scene(root, 400, 300));
            popUpStage.show();
        }
    }

    @FXML
    void onActionEndHourComboBox(ActionEvent event) {
        if(endHourComboBox.getSelectionModel().getSelectedItem().compareTo(String.valueOf(adjustedLocalZDT.plusHours(14).getHour())) == 0){
            ObservableList<String> m = FXCollections.observableArrayList();
            m.add("00");
            endMinuteComboBox.setItems(m);
        }else{
            endMinuteComboBox.setItems(getMinuteList());
        }
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCusLabel();
        try {
            setComboBoxes();
            startMinuteComboBox.getSelectionModel().clearSelection();
            startHourComboBox.getSelectionModel().clearSelection();
            endMinuteComboBox.getSelectionModel().clearSelection();
            endHourComboBox.getSelectionModel().clearSelection();
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

        adjustedLocalZDT = estZDT.withZoneSameInstant(localZoneID);
        if(isStart){//check if start combobox
            for(int i = 0; i < 14; i++){

                if(String.valueOf(adjustedLocalZDT.plusHours(i).getHour()).length() == 1){//check if end hour is single digit
                    h.add("0" + String.valueOf(adjustedLocalZDT.plusHours(i).getHour()));
                }else{
                    h.add(String.valueOf(adjustedLocalZDT.plusHours(i).getHour()));
                }
            }
        }else{//combobox is end
            for(int i = 1; i < 15; i++){

                if(String.valueOf(adjustedLocalZDT.plusHours(i).getHour()).length() == 1){//check if end hour is single digit
                    h.add("0" + String.valueOf(adjustedLocalZDT.plusHours(i).getHour()));
                }else{
                    h.add(String.valueOf(adjustedLocalZDT.plusHours(i).getHour()));
                }
            }
        }

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

    private boolean checkInput() throws SQLException {

        boolean errors = false;

        if(titleTextfield.getText().isEmpty()){//check if title textfield is empty
            errorTitleLabel.setVisible(true);
            errors = true;
        }else{//not empty
            errorTitleLabel.setVisible(false);
        }
        if(descriptionTextfield.getText().isEmpty()){//check if description textfield is empty
            errorDescriptionLabel.setVisible(true);
            errors = true;
        }else{//not empty
            errorDescriptionLabel.setVisible(false);
        }
        if(locationTextfield.getText().isEmpty()){//check if location textfield is empty
            errorLocationLabel.setVisible(true);
            errors = true;
        }else{//not empty
            errorLocationLabel.setVisible(false);
        }
        if(typeComboBox.getSelectionModel().isEmpty()){//check if type combobox is empty
            errorTypeLabel.setVisible(true);
            errors = true;
        }else{//not empty
            errorTypeLabel.setVisible(false);
        }
        if(contactComboBox.getSelectionModel().isEmpty()){//check if contact combobox is empty
            errorContactLabel.setVisible(true);
            errors = true;
        }else{//not empty
            errorContactLabel.setVisible(false);
        }
        if(customerIDComboBox.getSelectionModel().isEmpty()){//check if customerid combobox is empty
            errorCustomerIDLabel.setVisible(true);
            errors = true;
        }else{//not empty
            errorCustomerIDLabel.setVisible(false);
        }
        if(datePicker.getValue() == null){//check if day is empty
            errorDayLabel.setVisible(true);
            errors = true;
        }else{//not empty
            errorDayLabel.setVisible(false);
        }
        if(startHourComboBox.getSelectionModel().getSelectedItem() == null){//check if start hour combobox is empty
            errorStartHourLabel.setVisible(true);
            errors = true;
        }else{//not empty
            errorStartHourLabel.setVisible(false);
        }
        if(startMinuteComboBox.getSelectionModel().getSelectedItem() == null){//check if start minute combobox is empty
            errorStartMinuteLabel.setVisible(true);
            errors = true;
        }else{//not empty
            errorStartMinuteLabel.setVisible(false);
        }
        if(endHourComboBox.getSelectionModel().getSelectedItem() == null){//check if end hour combobox is empty
            errorEndHourLabel.setVisible(true);
            errors = true;
        }else{//not empty
            errorEndHourLabel.setVisible(false);
        }
        if(endMinuteComboBox.getSelectionModel().getSelectedItem() == null){//check if end minute combobox is empty
            errorEndMinuteLabel.setVisible(true);
            errors = true;
        }else{//not empty
            errorEndMinuteLabel.setVisible(false);
        }
        if(checkAppointmentOverlap()){
            errors = true;
        }

        return errors;

    }

    private boolean checkAppointmentOverlap() throws SQLException {

        AtomicBoolean err = new AtomicBoolean(false);
        ObservableList<Appointment> allAppts = FXCollections.observableArrayList();
        ObservableList<Appointment> relevantAppts = FXCollections.observableArrayList();
        LocalDateTime s = getStartLocalDateTime();
        LocalDateTime e = getEndLocalDateTime();

        allAppts.addAll(AppointmentQuery.getAllContactFormAppointments());
        allAppts.forEach(a ->{
            if(a.getApptID() != Integer.parseInt(idTextfield.getText()) || //check if appointment is not the same appointment
                    a.getCustomerID() == Integer.parseInt(customerIDComboBox.getSelectionModel().getSelectedItem()) || //check if the customerId matches
                        LocalDate.from(a.getStart()).isEqual(datePicker.getValue())){ //check if date matches

                relevantAppts.add(a);
            }
        });

        relevantAppts.forEach(a -> {

            if((s.isBefore(a.getStart()) || s.isEqual(a.getStart()))  &&  (e.isAfter(a.getEnd()) || e.isEqual(a.getEnd()))){//check if new datetime swallows old datetime
                err.set(true);
            }
            if((s.isBefore(a.getStart()) || s.isEqual(a.getStart()))  &&  (e.isAfter(a.getStart()) || e.isEqual(a.getStart()))){//check if new datetime starts before and ends before new datetime
                err.set(true);
            }
        });

        return err.get();
    }
}
