package controller;

import DBAccess.AppointmentQuery;
import DBAccess.ContactQuery;
import Database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Appointment;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    private Stage stage;
    private Parent scene;
    private LocalDate labelLocalDate;


    @FXML
    private Label mainMonth1Label;
    @FXML
    private Label mainMonth2Label;
    @FXML
    private TableView<Appointment> mainMonthOneTableView;
    @FXML
    private TableView<Appointment> mainMonthTwoTableView;
    @FXML
    private TableColumn<Appointment, Integer> mainOneCustomerColumn;
    @FXML
    private TableColumn<Appointment, Date> mainOneEndColumn;
    @FXML
    private TableColumn<Appointment, Date> mainOneStartColumn;
    @FXML
    private TableColumn<Appointment, String> mainOneTitleColumn;
    @FXML
    private TableColumn<Appointment, Integer> mainTwoCustomerColumn;
    @FXML
    private TableColumn<Appointment, Date> mainTwoEndColumn;
    @FXML
    private TableColumn<Appointment, Date> mainTwoStartColumn;
    @FXML
    private TableColumn<Appointment, String> mainTwoTitleColumn;
    @FXML
    private Button customerReportButton;
    @FXML
    private Button contactReportButton;
    @FXML
    private Button countryReportButton;
    @FXML
    private Button mainAppButton;
    @FXML
    private Button mainCusButton;
    @FXML
    private Button mainExitButton;





    @FXML
    void onActionCustomerReportButton(ActionEvent event) throws IOException {

        switchScene("/view/CustomerReportForm.fxml",event);

    }

    @FXML
    void onActionContactReportButton(ActionEvent event) throws IOException {

        switchScene("/view/ContactReportForm.fxml",event);

    }

    @FXML
    void onActionCountryReportButton(ActionEvent event) throws IOException {

        switchScene("/view/CountryReportForm.fxml",event);

    }

    @FXML
    void onActionMainAppButton(ActionEvent event) throws IOException {

        switchScene("/view/AppointmentsForm.fxml",event);

    }

    @FXML
    void onActionMainCusButton(ActionEvent event) throws IOException {

        switchScene("/view/CustomersForm.fxml",event);

    }

    @FXML
    void onActionMainExitButton(ActionEvent event) {

        DBConnection.closeConnection();
        System.exit(0);

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        labelLocalDate = LocalDate.now();
        setLabels();
        setMonthOneColumns();
        setMonthTwoColumns();
        AppointmentsFormController.setCurrentRadioButton("Monthly");

        try {
            mainMonthOneTableView.setItems(AppointmentQuery.populateTableItems(labelLocalDate));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            mainMonthTwoTableView.setItems(AppointmentQuery.populateTableItems(labelLocalDate.plusMonths(1)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




    //METHODS

    /**
     * This method switches the scene that is shown to the user.
     * @param newFXML is the path of the fxml file that is being switched to
     * @param event is the ActionEvent that activates this method
     * @throws IOException
     */
    private void switchScene(String newFXML, ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(newFXML));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    private void setMonthOneColumns(){

        mainOneCustomerColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerID"));
        mainOneStartColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Date>("start"));
        mainOneEndColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Date>("end"));
        mainOneTitleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));

    }

    private void setMonthTwoColumns(){

        mainTwoCustomerColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerID"));
        mainTwoStartColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Date>("start"));
        mainTwoEndColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Date>("end"));
        mainTwoTitleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
    }

    private void setLabels() {
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM");
        mainMonth1Label.setText(labelLocalDate.format(monthFormatter));
        mainMonth2Label.setText(labelLocalDate.plusMonths(1).format(monthFormatter));

    }


}
