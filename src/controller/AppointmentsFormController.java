package controller;

import DBAccess.AppointmentQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * This class controls the javafx appointmentsform html class.
 */
public class AppointmentsFormController implements Initializable {

    private Stage stage;
    private Parent scene;
    private static boolean isLabelAdd = true;
    private static Appointment updateAppt;
    private LocalDate labelLocalDate;
    private static String currentRadioButton;
    private static String currentSearchRadioButton = null;

    @FXML
    private  static RadioButton aRAllRadio;
    @FXML
    private static RadioButton aRMonthlyRadio;
    @FXML
    private static RadioButton aRWeeklyRadio;
    @FXML
    private ToggleGroup appViewToggle;
    @FXML
    private TableView<Appointment> aRTableView;
    @FXML
    private TableColumn<Appointment, String> aRContactColumn;
    @FXML
    private TableColumn<Appointment, Integer> aRCusIDColumn;
    @FXML
    private TableColumn<Appointment, String> aRDescriptionColumn;
    @FXML
    private TableColumn<Appointment, Date> aRStartDateTimeColumn;
    @FXML
    private TableColumn<Appointment, Date> aREndDateTimeColumn;
    @FXML
    private TableColumn<Appointment, Integer> aRIDColumn;
    @FXML
    private TableColumn<Appointment, String> aRLocationColumn;
    @FXML
    private TableColumn<Appointment, String> aRTitleColumn;
    @FXML
    private TableColumn<Appointment, String> aRTypeColumn;
    @FXML
    private TableColumn<Appointment, Integer> aRUserIDColumn;
    @FXML
    private Button aRForwardButton;
    @FXML
    private Label aRMonthWeekLabel;
    @FXML
    private Button aRBackButton;
    @FXML
    private Button appAddButton;
    @FXML
    private Button appUpdateButton;
    @FXML
    private Button appointmentsFormBackButton;
    @FXML
    private Button appDeleteButton;
    @FXML
    private RadioButton appApptIDRadio;
    @FXML
    private RadioButton appContactRadio;
    @FXML
    private RadioButton appCusIDRadio;
    @FXML
    private ToggleGroup appSearchToggle;
    @FXML
    private Button appSearchButton;
    @FXML
    private TextField appSearchTextfield;
    @FXML
    private Label appTypeLabel;
    @FXML
    private Label appErrorLabel;
    @FXML
    private Button appClearButton;



    /** This method triggers when the add button is pressed and switches to the add appointment form.
     * @param event This is the event that triggers the method.
     * @throws IOException Throws IOException.
     */
    @FXML
    void onActionAppAddButton(ActionEvent event) throws IOException {

        isLabelAdd = true;
        switchScene("/view/AddAppointment.fxml", event);

    }

    /** This method triggers when the back button is pressed and switches to the main menu form.
     * @param event This is the event that triggers the method.
     * @throws IOException Throws IOException.
     */
    @FXML
    void onActionAppointmentsFormBackButton(ActionEvent event) throws IOException {

        switchScene("/view/MainMenuForm.fxml", event);

    }

    /** This method triggers when the update button is pressed and switches to the add apointment form only if something is selected.
     * @param event This is the event that triggers the method.
     * @throws IOException Throws IOException.
     */
    @FXML
    void onActionAppUpdateButton(ActionEvent event) throws IOException {

        if(aRTableView.getSelectionModel().isEmpty()){//check if customer is empty

            PopUpFormController.setUpPopUp("ERROR!", "No appointment selected.", "/view/AppointmentsForm.fxml");
            switchScene("/view/PopUpForm.fxml", event);
        }else{//customer selected

            isLabelAdd = false;
            setUpdateAppt(aRTableView.getSelectionModel().getSelectedItem());
            switchScene("/view/AddAppointment.fxml", event);
        }
    }

    /** This method triggers when the delete button is pressed, checks if there is a selection, and triggers proper popup forms. Sets up aRtable view.
     * @param event This is the event that triggers the method.
     * @throws SQLException Throws SQLException.
     * @throws IOException Throws IOException.
     */
    @FXML
    void onActionAppDeleteButton(ActionEvent event) throws SQLException, IOException {

        if(aRTableView.getSelectionModel().isEmpty()){//check if customer selection is empty

            PopUpFormController.setUpPopUp("ERROR!", "No appointment  selected.", "/view/AppointmentsForm.fxml");
            switchScene("/view/PopUpForm.fxml", event);
        }else{//customer is selected

            PopUpFormController.setUpPopUp("WARNING!",
                                            "Are you sure you want to delete this appointment?",
                                                "/view/AppointmentsForm.fxml",
                                                    aRTableView.getSelectionModel().getSelectedItem());
            switchScene("/view/PopUpForm.fxml", event);
        }

        try {
            aRTableView.setItems(AppointmentQuery.getAllAppointments());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    /** This method moves the month or week level back one. Refreshes aRTable view and label.
     * @param event This is the event that triggers the method.
     * @throws SQLException Throws SQLException.
     */
    @FXML
    void onActionBackButton(ActionEvent event) throws SQLException {

        if(currentRadioButton.compareTo("Monthly") == 0){
            labelLocalDate = labelLocalDate.minusMonths(1);
        }else if(currentRadioButton.compareTo("Weekly") == 0){
            labelLocalDate = labelLocalDate.minusWeeks(1);
        }

        setLabel();
        onActionAppClearButton(event);
        //aRTableView.setItems(AppointmentQuery.populateTableItems(labelLocalDate));
    }

    /** This method moves the month or week level forward one. Refreshes aRTable view and label.
     * @param event This is the event that triggers the method.
     * @throws SQLException Throws SQLException.
     */
    @FXML
    void onActionForwardButton(ActionEvent event) throws SQLException {

        if(currentRadioButton.compareTo("Monthly") == 0){
            labelLocalDate = labelLocalDate.plusMonths(1);
        }else if(currentRadioButton.compareTo("Weekly") == 0){
            labelLocalDate = labelLocalDate.plusWeeks(1);
        }

        setLabel();
        onActionAppClearButton(event);
        //aRTableView.setItems(AppointmentQuery.populateTableItems(labelLocalDate));
    }

    /** This method refreshes aRTable view with all appointments and sets label.
     * @param event This is the event that triggers the method.
     * @throws SQLException Throws SQLException.
     */
    @FXML
    void onActionAllRadio(ActionEvent event) throws SQLException {
        currentRadioButton = "All";
        setLabel();
        onActionAppClearButton(event);
        //aRTableView.setItems(AppointmentQuery.populateTableItems(labelLocalDate));
    }

    /** This method refreshes aRTable view with all monthly appointments and sets label.
     * @param event This is the event that triggers the method.
     * @throws SQLException Throws SQLException.
     */
    @FXML
    void onActionMonthlyRadio(ActionEvent event) throws SQLException {
        currentRadioButton = "Monthly";
        setLabel();
        onActionAppClearButton(event);
        //aRTableView.setItems(AppointmentQuery.populateTableItems(labelLocalDate));
    }

    /** This method refreshes aRTable view with all weekly appointments and sets label.
     * @param event This is the event that triggers the method.
     * @throws SQLException Throws SQLException.
     */
    @FXML
    void onActionWeeklyRadio(ActionEvent event) throws SQLException {
        currentRadioButton = "Weekly";
        setLabel();
        onActionAppClearButton(event);
        //aRTableView.setItems(AppointmentQuery.populateTableItems(labelLocalDate));
    }

    @FXML
    void onActionCusIDRadio(ActionEvent event) {
        currentSearchRadioButton = "Customer ID";
    }

    @FXML
    void onActionContactRadio(ActionEvent event) {
        currentSearchRadioButton = "Contact ID";
    }

    @FXML
    void onActionApptIDRadio(ActionEvent event) {
        currentSearchRadioButton = "Appointment ID";
    }

    @FXML
    void onActionSearchButton(ActionEvent event) {

        aRTableView.getItems().clear();
        appErrorLabel.setVisible(false);
        appTypeLabel.setVisible(false);

        if(isSearchError()){
            System.out.println("Inside if(isSearchError)");
            return;
        }

        ArrayList<Appointment> shownAppts = new ArrayList<Appointment>();
        ObservableList<Appointment> vettedAppts = FXCollections.observableArrayList();
        shownAppts.clear();
        vettedAppts.clear();

        try {
            shownAppts.addAll(AppointmentQuery.populateTableItems(labelLocalDate));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        if(currentSearchRadioButton.compareTo("Customer ID") == 0){
            for(Appointment a: shownAppts){
                if(a.getCustomerID() == Integer.valueOf(appSearchTextfield.getText())){
                    vettedAppts.add(a);
                }
            }
        }else if(currentSearchRadioButton.compareTo("Contact ID") == 0){
            for(Appointment a: shownAppts){
                if(a.getContactID() == Integer.valueOf(appSearchTextfield.getText())){
                    vettedAppts.add(a);
                }
            }
        }else {
            for(Appointment a: shownAppts){
                if(a.getApptID() == Integer.valueOf(appSearchTextfield.getText())){
                    vettedAppts.add(a);
                }
            }
        }

        aRTableView.setItems(vettedAppts);

    }

    @FXML
    void onActionAppClearButton(ActionEvent event) {
        appSearchTextfield.clear();
        appErrorLabel.setVisible(false);
        appTypeLabel.setVisible(false);
        appCusIDRadio.setSelected(false);
        appContactRadio.setSelected(false);
        appApptIDRadio.setSelected(false);
        currentSearchRadioButton = null;
        aRTableView.getItems().clear();


        //labelLocalDate = LocalDate.now();
        //setLabel();
        //setColumns();

        try {
            aRTableView.setItems(AppointmentQuery.populateTableItems(labelLocalDate));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }



    /** This is method initializes when the form is called and sets up the form.
     * @param url This the url.
     * @param resourceBundle This is the resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        currentRadioButton = "All";
        labelLocalDate = LocalDate.now();
        setLabel();
        setColumns();

        try {
            aRTableView.setItems(AppointmentQuery.populateTableItems(labelLocalDate));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    //METHODS


    /** This method switches to a scene.
     * @param newFXML This is the path of the new form.
     * @param event This is the event that triggers the method.
     * @throws IOException Throws IOException.
     */
    private void switchScene(String newFXML, ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(newFXML));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method gets whether it is an add or update appointment.
     * @return Returns true if adding appointment.
     */
    public static Boolean getIsLabelAdd() {

        if (isLabelAdd) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This methods sets up the columns to the aRTable view.
     */
    private void setColumns() {

        aRContactColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("contact"));
        aRDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        aRStartDateTimeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Date>("start"));
        aRCusIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerID"));
        aREndDateTimeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Date>("end"));
        aRIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("apptID"));
        aRLocationColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        aRTitleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        aRTypeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        aRUserIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userID"));
    }

    /**
     * This method sets the option label.
     */
    private void setLabel() {
        if(currentRadioButton.compareTo("All") == 0){
            aRMonthWeekLabel.setText("--------");

        }else if(currentRadioButton.compareTo("Monthly") == 0){
            DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM");
            aRMonthWeekLabel.setText(labelLocalDate.format(monthFormatter));

        }else if(currentRadioButton.compareTo("Weekly") == 0){
            DateTimeFormatter weekNumFormatter = DateTimeFormatter.ofPattern("w");
            aRMonthWeekLabel.setText("Week " + labelLocalDate.format(weekNumFormatter));
        }
    }

    private boolean isSearchError(){
        boolean isWrong = true;

        if(currentSearchRadioButton == null){
            appTypeLabel.setVisible(true);
        }else if(currentSearchRadioButton.compareTo("Customer ID") == 0 || currentSearchRadioButton.compareTo("Appointment ID") == 0){

            try{
                Integer.valueOf(appSearchTextfield.getText());
                isWrong = false;
            }catch(NumberFormatException e){
                appErrorLabel.setVisible(true);
            }
        }else{
            isWrong = false;
        }

        return isWrong;
    }

    /** This method gets the appointment to be updated.
     * @return Returns update appointment.
     */
    public static Appointment getUpdateAppt() {
        return updateAppt;
    }

    /** This method sets the appointment to be updated.
     * @param updateAppt Appointment to be updated.
     */
    public void setUpdateAppt(Appointment updateAppt) {
        this.updateAppt = updateAppt;
    }

    /** This method gets the current radio button selected.
     * @return Returns current selected radio button.
     */
    public static String getCurrentRadioButton() {
        return currentRadioButton;
    }

    /** This method sets the selected radio button.
     * @param currentRadioButton This is the selected radio button.
     */
    public static void setCurrentRadioButton(String currentRadioButton) {
        AppointmentsFormController.currentRadioButton = currentRadioButton;
    }
}