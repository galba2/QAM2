package controller;


import DBAccess.CustomerReportQuery;
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
import model.CustomerReport;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerReportFormController implements Initializable {

    private Stage stage;
    private Parent scene;

    @FXML
    private TableView<CustomerReport> customerReportTableView;
    @FXML
    private TableColumn<CustomerReport, String> customerReportMonthColumn;//
    @FXML
    private TableColumn<CustomerReport, String> customerReportTypeColumn;
    @FXML
    private TableColumn<CustomerReport, Integer> customerReportNumColumn;
    @FXML
    private Button customerReportBackButton;



    @FXML
    void onActionBackButton(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenuForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setColumnsCustomerReportTableView();

        try {
            customerReportTableView.setItems(CustomerReportQuery.getAllCustomerReport());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    //METHODS
    private void setColumnsCustomerReportTableView() {

        customerReportMonthColumn.setCellValueFactory(new PropertyValueFactory<CustomerReport, String>("month"));
        customerReportTypeColumn.setCellValueFactory(new PropertyValueFactory<CustomerReport, String>("type"));
        customerReportNumColumn.setCellValueFactory(new PropertyValueFactory<CustomerReport, Integer>("count"));
    }




}
