package controller;

import DBAccess.AppointmentQuery;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static DBAccess.AppointmentQuery.*;

public class AppointmentsFormController implements Initializable {

    private Stage stage;
    private Parent scene;
    private static boolean isLabelAdd = true;
    private static Appointment updateAppt;
    private LocalDate labelLocalDate;
    private static String currentRadioButton;

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
    void onActionAppAddButton(ActionEvent event) throws IOException {

        isLabelAdd = true;
        switchScene("/view/AddAppointment.fxml", event);

    }

    @FXML
    void onActionAppointmentsFormBackButton(ActionEvent event) throws IOException {

        switchScene("/view/MainMenuForm.fxml", event);

    }

    @FXML
    void onActionAppUpdateButton(ActionEvent event) throws IOException {

        if(aRTableView.getSelectionModel().isEmpty()){//check if customer is empty

            PopUpFormController.setUpPopUp("ERROR!", "No appointment selected.", "/view/AppointmentsForm.fxml");
            switchScene("/view/PopUpForm.fxml", event);
        }else{

            isLabelAdd = false;
            setUpdateAppt(aRTableView.getSelectionModel().getSelectedItem());
            switchScene("/view/AddAppointment.fxml", event);
        }
    }

    @FXML
    void onActionAppDeleteButton(ActionEvent event) throws SQLException, IOException {

        if(aRTableView.getSelectionModel().isEmpty()){//check if customer selection is empty

            PopUpFormController.setUpPopUp("ERROR!", "No appointment  selected.", "/view/AppointmentsForm.fxml");
            switchScene("/view/PopUpForm.fxml", event);
        }else{//customer is selected

            PopUpFormController.setUpPopUp("WARNING!",
                                            "Customer and customer's appointment(s) will be deleted.\nAre you sure you want to delete this customer?",
                                                "/view/CustomersForm.fxml",
                                                    aRTableView.getSelectionModel().getSelectedItem());
            switchScene("/view/PopUpForm.fxml", event);
        }

        deleteAppointment(aRTableView.getSelectionModel().getSelectedItem().getApptID());

        try {
            aRTableView.setItems(AppointmentQuery.getAllAppointments());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    @FXML
    void onActionBackButton(ActionEvent event) throws SQLException {

        if(currentRadioButton.compareTo("Monthly") == 0){
            labelLocalDate = labelLocalDate.minusMonths(1);
        }else if(currentRadioButton.compareTo("Weekly") == 0){
            labelLocalDate = labelLocalDate.minusWeeks(1);
        }

        setLabel();
        aRTableView.setItems(AppointmentQuery.populateTableItems(labelLocalDate));
    }

    @FXML
    void onActionForwardButton(ActionEvent event) throws SQLException {

        if(currentRadioButton.compareTo("Monthly") == 0){
            labelLocalDate = labelLocalDate.plusMonths(1);
        }else if(currentRadioButton.compareTo("Weekly") == 0){
            labelLocalDate = labelLocalDate.plusWeeks(1);
        }

        setLabel();
        aRTableView.setItems(AppointmentQuery.populateTableItems(labelLocalDate));
    }

    @FXML
    void onActionAllRadio(ActionEvent event) throws SQLException {
        currentRadioButton = "All";
        setLabel();
        aRTableView.setItems(AppointmentQuery.populateTableItems(labelLocalDate));
    }

    @FXML
    void onActionMonthlyRadio(ActionEvent event) throws SQLException {
        currentRadioButton = "Monthly";
        setLabel();
        aRTableView.setItems(AppointmentQuery.populateTableItems(labelLocalDate));
    }

    @FXML
    void onActionWeeklyRadio(ActionEvent event) throws SQLException {
        currentRadioButton = "Weekly";
        setLabel();
        aRTableView.setItems(AppointmentQuery.populateTableItems(labelLocalDate));
    }






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
    private void switchScene(String newFXML, ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(newFXML));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public static Boolean getIsLabelAdd() {

        if (isLabelAdd) {
            return true;
        } else {
            return false;
        }
    }

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

    public static Appointment getUpdateAppt() {
        return updateAppt;
    }

    public void setUpdateAppt(Appointment updateAppt) {
        this.updateAppt = updateAppt;
    }

    public static String getCurrentRadioButton() {
        return currentRadioButton;
    }

    public static void setCurrentRadioButton(String currentRadioButton) {
        AppointmentsFormController.currentRadioButton = currentRadioButton;
    }
}