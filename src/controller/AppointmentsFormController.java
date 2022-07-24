package controller;

import DBAccess.AppointmentQuery;
import DBAccess.CustomerQuery;
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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static DBAccess.AppointmentQuery.*;

public class AppointmentsFormController implements Initializable {

    private Stage stage;
    private Parent scene;
    private static boolean isLabelAdd = true;

    @FXML
    private RadioButton aRAllRadio;
    @FXML
    private RadioButton aRMonthlyRadio;
    @FXML
    private RadioButton aRWeeklyRadio;
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

        isLabelAdd = false;
        switchScene("/view/AddAppointment.fxml", event);

    }

    @FXML
    void onActionAppDeleteButton(ActionEvent event) throws SQLException {

        deleteAppointment(aRTableView.getSelectionModel().getSelectedItem().getApptID());

    }


    @FXML
    void onActionBackButton(ActionEvent event) {

    }

    @FXML
    void onActionForwardButton(ActionEvent event) {

    }

    @FXML
    void onActionAllRadio(ActionEvent event) {
        setLabel();
    }

    @FXML
    void onActionMonthlyRadio(ActionEvent event) {
        setLabel();
    }

    @FXML
    void onActionWeeklyRadio(ActionEvent event) {
        setLabel();
    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setLabel();
        setColumns();


        try {
            aRTableView.setItems(AppointmentQuery.getAllAppointments());
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
        if(aRAllRadio.isSelected()){
            aRMonthWeekLabel.setText("--------");

        }else if(aRMonthlyRadio.isSelected()){
            Date nowDate = Date.valueOf(LocalDate.now());
            DateFormat formatter = new SimpleDateFormat("MMMM");
            aRMonthWeekLabel.setText(formatter.format(nowDate));

        }else if(aRWeeklyRadio.isSelected()){
            aRMonthWeekLabel.setText("Week");
        }
    }
}