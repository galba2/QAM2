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

/**
 * This class controls the javafx mainmenuform html class.
 */
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


    /**This method clears the text fields and goes back to the appointments form.
     * @param event This event triggers this method.
     * @throws IOException This throws an IOException.
     */
    @FXML
    void onActionAppCancel(ActionEvent event) throws IOException {

        clearFields();
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentsForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**This method checks for errors and adds or updates an appointment. Then goes back to the appointments form.
     * @param event This event triggers this method.
     * @throws SQLException This throws an SQLException.
     * @throws IOException This throws an IOException.
     */
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

    /** This method checks if datepicker is not selected and if a past day is selected. Triggers a popup if date is in the past.
     * @param event This event triggers this method.
     * @throws IOException This throws an IOException.
     */
    @FXML
    void onActionDatePicker(ActionEvent event) throws IOException {

        if(datePicker.getValue() == null){//check if datepicker is null
            //do nothing
        }else if(datePicker.getValue().isBefore(LocalDate.now())){//check if date is before current date
            PopUpFormController.setUpPopUp("ERROR!", "Please choose a date that is " + LocalDate.now().toString() + " or later.");
            Stage popUpStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/PopUpForm.fxml"));
            popUpStage.setScene(new Scene(root, 400, 300));
            popUpStage.show();
            datePicker.setValue(null);
        }
    }

    /**This method checks to see if the last hour is selected on the start hour combo box and then loads the end minute combo box with only the
     * value of "00" if it is. If it is not, it loads it with the regular minute list.
     * @param event This event triggers this method.
     */
    @FXML
    void onActionEndHourComboBox(ActionEvent event) {

        if(endHourComboBox.getSelectionModel().getSelectedItem().compareTo(String.valueOf(adjustedLocalZDT.plusHours(14).getHour())) == 0){//
            ObservableList<String> m = FXCollections.observableArrayList();
            m.add("00");
            endMinuteComboBox.setItems(m);
        }else{
            endMinuteComboBox.setItems(getMinuteList());
        }
    }


    /**This method initializes the form.
     * @param url This is the url.
     * @param resourceBundle This is the resource bundle.
     */
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

    /**
     * This method sets the label of the form based on where it was called from.
     */
    @FXML
    private void setCusLabel(){

        if(AppointmentsFormController.getIsLabelAdd()){
            this.appointmentTitleLabel.setText("Add Appointment");
            this.idTextfield.setText("AUTO GENERATED");
        }else{
            this.appointmentTitleLabel.setText("Update Appointment");
        }
    }

    /** This method sets up all the form combo boxes.
     * @throws SQLException Throws a SQLException.
     */
    private void setComboBoxes() throws SQLException {

        typeComboBox.setItems(getTypesList());
        contactComboBox.setItems(ContactQuery.getAllContactsList());
        customerIDComboBox.setItems(CustomerQuery.getAllCustomerIDs());
        startMinuteComboBox.setItems(getMinuteList());
        startHourComboBox.setItems(getHourList(true));
        endMinuteComboBox.setItems(getMinuteList());
        endHourComboBox.setItems(getHourList(false));
    }

    /** This method grabs only the ID number of the string
     * @param rawString This is the string with the ID number.
     * @return Returns the ID number as an int.
     */
    private int getIDOnly(String rawString) {

        int idInt;
        String idString;

        idString = rawString.substring(rawString.indexOf("(") +1, rawString.indexOf(")"));//id as String from String
        idInt = Integer.parseInt(idString);//turn string id into int id

        return idInt;
    }

    /**
     * This method clears all textfields.
     */
    private void clearFields() {

        descriptionTextfield.clear();
        locationTextfield.clear();
        idTextfield.clear();
        titleTextfield.clear();
    }

    /** This method sets up all the form text fields with update information.
     * @param updateAppt This is the appointment to be updated.
     * @throws SQLException Throws SQLException.
     */
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

    /** This method gets a static list of different types.
     * @return Returns an observable list of types.
     */
    private ObservableList<String> getTypesList() {
        ObservableList<String > types = FXCollections.observableArrayList();
        types.addAll("Planning Session","De-Briefing","Briefing","Brainstorm Session","Appointment Making","Introduction","Other");
        return types;
    }

    /** This method gets a static list of minutes.
     * @return Returns an observable list of minutes.
     */
    private ObservableList<String> getMinuteList() {
        ObservableList<String > m = FXCollections.observableArrayList();
        m.addAll("00","15","30","45");
        return m;
    }

    /**This method returns the adjusted hour list for the start combo box or end combo box.
     * @param isStart This notifies if it returns the hours for the start combo box or end combo box.
     * @return Returns an observable list of hours for the start combo box or end combo box.
     */
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


    /** This method gets the start localdatetime from the datepicker, start hour combo box, and start minute combo box.
     * @return Returns the localdatetime.
     */
    private LocalDateTime getStartLocalDateTime() {

        return LocalDateTime.of(datePicker.getValue(),LocalTime.parse(startHourComboBox.getSelectionModel().getSelectedItem() + ":"
                                                                        + startMinuteComboBox.getSelectionModel().getSelectedItem()));
    }


    /** This method gets the end localdatetime from the datepicker, end hour combo box, and end minute combo box.
     * @return Returns the localdatetime.
     */
    private LocalDateTime getEndLocalDateTime() {

        return LocalDateTime.of(datePicker.getValue(),LocalTime.parse(endHourComboBox.getSelectionModel().getSelectedItem() + ":"
                                                                        + endMinuteComboBox.getSelectionModel().getSelectedItem()));
    }

    /** This method checks for empty text fields and triggers a popup form if it finds one while activating an error label.
     * @return Returns true if errors found.
     * @throws SQLException Throws SQLException.
     * @throws IOException Throws IOException.
     */
    private boolean checkInput() throws SQLException, IOException {

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
            timeComboBoxesSelected();//still check if time combo boxes are selected
            errors = true;
        }else{//not empty
            errorDayLabel.setVisible(false);
            if(timeComboBoxesSelected() && !customerIDComboBox.getSelectionModel().isEmpty()){//check if time combo boxes and customerid combo box have been selected

                if(getStartLocalDateTime().isAfter(getEndLocalDateTime()) || getStartLocalDateTime().isEqual(getEndLocalDateTime())){//check if start time is after end time or both times are equal
                    //show popup
                    PopUpFormController.setUpPopUp("ERROR!", "Please choose an end time that is after the start time.");
                    Stage popUpStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/view/PopUpForm.fxml"));
                    popUpStage.setScene(new Scene(root, 400, 300));
                    popUpStage.show();
                    errors = true;
                }else if(checkAppointmentOverlap()){//check for any appointment overlaps
                    errors = true;
                }

            }
        }

        return errors;
    }

    /** This method checks for empty combo boxes.
     * @return Returns true if all time combo boxes are selected.
     */
    private boolean timeComboBoxesSelected() {

        boolean selected = true;

        if(startHourComboBox.getSelectionModel().getSelectedItem() == null){//check if start hour combobox is empty
            errorStartHourLabel.setVisible(true);
            selected = false;
        }else{//not empty
            errorStartHourLabel.setVisible(false);
        }
        if(startMinuteComboBox.getSelectionModel().getSelectedItem() == null){//check if start minute combobox is empty
            errorStartMinuteLabel.setVisible(true);
            selected = false;
        }else{//not empty
            errorStartMinuteLabel.setVisible(false);
        }
        if(endHourComboBox.getSelectionModel().getSelectedItem() == null){//check if end hour combobox is empty
            errorEndHourLabel.setVisible(true);
            selected = false;
        }else{//not empty
            errorEndHourLabel.setVisible(false);
        }
        if(endMinuteComboBox.getSelectionModel().getSelectedItem() == null){//check if end minute combobox is empty
            errorEndMinuteLabel.setVisible(true);
            selected = false;
        }else{//not empty
            errorEndMinuteLabel.setVisible(false);
        }

        return selected;
    }

    /** This method checks if there is an appointment time overlap with another appointment for the same customer.
     * The lambda expression is used by foreach methods in order to go through the make the code easier to read.
     * @return Returns true if there is an error of an appointment overlap.
     * @throws SQLException Throws SQLException.
     */
    private boolean checkAppointmentOverlap() throws SQLException {

        AtomicBoolean err = new AtomicBoolean(false);
        ObservableList<Appointment> allAppts = FXCollections.observableArrayList();
        ObservableList<Appointment> relevantAppts = FXCollections.observableArrayList();
        LocalDateTime s = getStartLocalDateTime();
        LocalDateTime e = getEndLocalDateTime();

        allAppts.addAll(AppointmentQuery.getAllContactFormAppointments());
        allAppts.forEach(a ->{//add appointments to relevantAppts pertaining to the day of the new appointment

            if(idTextfield.getText().compareTo("AUTO GENERATED") == 0){//check if adding new appointment

                if(a.getCustomerID() == Integer.parseInt(customerIDComboBox.getSelectionModel().getSelectedItem()) && //check if the customerId matches
                        LocalDate.from(a.getStart()).isEqual(datePicker.getValue())){ //check if date matches

                    relevantAppts.add(a);
                }
            }else{//updating appointment

                if(a.getApptID() != Integer.parseInt(idTextfield.getText()) && //check if appointment is not the same appointment
                        a.getCustomerID() == Integer.parseInt(customerIDComboBox.getSelectionModel().getSelectedItem()) && //check if the customerId matches
                        LocalDate.from(a.getStart()).isEqual(datePicker.getValue())){ //check if date matches

                    relevantAppts.add(a);
                }
            }

        });

        relevantAppts.forEach(a -> {//check for overlaps on each relevant appointment

            if((s.isBefore(a.getStart()) || s.isEqual(a.getStart()))  &&  (e.isAfter(a.getEnd()) || e.isEqual(a.getEnd()))){//check if new datetime swallows old datetime

                err.set(true);
                //show popup error
                try {
                    PopUpFormController.setUpPopUp("ERROR!", "Appointment overlaps with other appointment.\n" +
                                                                                    "Date: " + LocalDate.from(a.getStart())  + "\n" +
                                                                                        "Start: " + a.getStart().getHour() + ":" + a.getStart().getMinute()  + "\n" +
                                                                                            "End: " + a.getEnd().getHour() + ":" + a.getEnd().getMinute());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Stage popUpStage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/view/PopUpForm.fxml"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                popUpStage.setScene(new Scene(root, 400, 300));
                popUpStage.show();
            }else if((s.isBefore(a.getStart()) || s.isEqual(a.getStart()))  &&  (e.isAfter(a.getStart()))){//check if new datetime starts before and ends before old datetime

                err.set(true);
                //show popup error
                try {
                    PopUpFormController.setUpPopUp("ERROR!", "Appointment overlaps with other appointment.\n" +
                                                                                    "Date: " + LocalDate.from(a.getStart())  + "\n" +
                                                                                        "Start: " + a.getStart().getHour() + ":" + a.getStart().getMinute()  + "\n" +
                                                                                            "End: " + a.getEnd().getHour() + ":" + a.getEnd().getMinute());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Stage popUpStage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/view/PopUpForm.fxml"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                popUpStage.setScene(new Scene(root, 400, 300));
                popUpStage.show();
            }else if(((s.isAfter(a.getStart()) || s.isEqual(a.getStart())) && s.isBefore(a.getEnd()))){//check if new datetime starts during old datetime

                err.set(true);
                //show popup error
                try {
                    PopUpFormController.setUpPopUp("ERROR!", "Appointment overlaps with other appointment.\n" +
                                                                                    "Date: " + LocalDate.from(a.getStart())  + "\n" +
                                                                                        "Start: " + a.getStart().getHour() + ":" + a.getStart().getMinute() + "\n" +
                                                                                            "End: " + a.getEnd().getHour() + ":" + a.getEnd().getMinute());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Stage popUpStage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/view/PopUpForm.fxml"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                popUpStage.setScene(new Scene(root, 400, 300));
                popUpStage.show();
            }
        });

        return err.get();
    }
}
