package controller;


import DBAccess.AppointmentMonthQuery;
import DBAccess.AppointmentTypeQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.AppointmentMonth;
import model.AppointmentType;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerReportFormController implements Initializable {

    private Stage stage;
    private Parent scene;

    @FXML
    private Button customerReportBackButton;
    @FXML
    private TableColumn<AppointmentMonth, Date> customerReportMonthColumn;
    @FXML
    private TableColumn<AppointmentMonth, Integer> customerReportMonthNumColumn;
    @FXML
    private TableView<AppointmentMonth> customerReportMonthTableView;
    @FXML
    private TableColumn<AppointmentType, String> customerReportTypeColumn;
    @FXML
    private TableColumn<AppointmentType, Integer> customerReportTypeNumColumn;
    @FXML
    private TableView<AppointmentType> customerReportTypeTableView;

    @FXML
    void onActionBackButton(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenuForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setColumnsCustomerReportTypeTableView();
        setColumnsCustomerReportMonthTableView();

        try {
            customerReportTypeTableView.setItems(AppointmentTypeQuery.getAllCustomerReportType());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            customerReportMonthTableView.setItems(AppointmentMonthQuery.getAllCustomerReportMonth());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    //METHODS
    private void setColumnsCustomerReportTypeTableView() {

        customerReportTypeColumn.setCellValueFactory(new PropertyValueFactory<AppointmentType, String>("apptType"));
        customerReportTypeNumColumn.setCellValueFactory(new PropertyValueFactory<AppointmentType, Integer>("apptTypeCount"));
    }

    private void setColumnsCustomerReportMonthTableView() {

        customerReportMonthColumn.setCellValueFactory(new PropertyValueFactory<AppointmentMonth, Date>("apptMonth"));
        customerReportMonthNumColumn.setCellValueFactory(new PropertyValueFactory<AppointmentMonth, Integer>("apptMonthCount"));
    }


}
