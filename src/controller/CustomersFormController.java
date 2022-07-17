package controller;

import DBAccess.CountryReportQuery;
import DBAccess.CustomerQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static DBAccess.CustomerQuery.*;

public class CustomersFormController implements Initializable {

    private Stage stage;
    private Parent scene;
    private static boolean isLabelAdd = true;

    @FXML
    private Button customerAddButton;
    @FXML
    private Button customerBackButton;
    @FXML
    private Button customerDeleteButton;
    @FXML
    private Button customerUpdateButton;
    @FXML
    private TableColumn<Customer, Integer> customerCountryIDTableColumn;
    @FXML
    private TableColumn<Customer, Integer> customerIDTableColumn;
    @FXML
    private TableColumn<Customer, String> customerNameTableColumn;
    @FXML
    private TableColumn<Customer, String> customerPhoneTableColumn;
    @FXML
    private TableColumn<Customer, String> customerPostalTableColumn;
    @FXML
    private TableColumn<Customer, Integer> customerDivIDTableColumn;
    @FXML
    private TableColumn<Customer, String> customerAddressTableColumn;
    @FXML
    private TableView<Customer> customerTableView;



    @FXML
    void onActionCusAddButton(ActionEvent event) throws IOException {

        isLabelAdd = true;
        switchScene("/view/AddCustomerForm.fxml",event);

    }

    @FXML
    void onActionCusBackButton(ActionEvent event) throws IOException {

        switchScene("/view/MainMenuForm.fxml",event);

    }

    @FXML
    void onActionCusDeleteButton(ActionEvent event) throws SQLException {

        deleteCustomer(7);

    }

    @FXML
    void onActionCusUpdateButton(ActionEvent event) throws IOException {

        isLabelAdd = false;
        switchScene("/view/AddCustomerForm.fxml",event);
        getCustomer();

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setColumns();

        try {
            customerTableView.setItems(CustomerQuery.getAllCustomers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }




    //METHODS
    private void setColumns(){

        customerIDTableColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("cusID"));
        customerNameTableColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        customerPhoneTableColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        customerAddressTableColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        customerDivIDTableColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("divisionID"));
        customerCountryIDTableColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("countryID"));
        customerPostalTableColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("postalCode"));

    }

    private void switchScene(String newFXML, ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(newFXML));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public static Boolean getIsLabelAdd(){

        if(isLabelAdd){
            return true;
        }else{
            return false;
        }
    }
}
