package controller;

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
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * This class controls the javafx customersform html class.
 */
public class CustomersFormController implements Initializable {

    private Stage stage;
    private Parent scene;
    private static boolean isLabelAdd = true;
    private static Customer updateCustomer;
    private String currentCustomerRadio;

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
    private RadioButton customerSearchCountIDRadio;
    @FXML
    private RadioButton customerSearchCusIDRadio;
    @FXML
    private RadioButton customerSearchNameRadio;
    @FXML
    private ToggleGroup customerSearchToggle;
    @FXML
    private TextField customerSearchTextField;
    @FXML
    private Button customerSearchButton;



    /** This method switches screen to the add customer form.
     * @param event This event triggers the method.
     * @throws IOException Throws IOException.
     */
    @FXML
    void onActionCusAddButton(ActionEvent event) throws IOException {

        isLabelAdd = true;
        switchScene("/view/AddCustomerForm.fxml",event);

    }

    /** This method switches screen to the main menu form.
     * @param event This event triggers the method.
     * @throws IOException Throws IOException.
     */
    @FXML
    void onActionCusBackButton(ActionEvent event) throws IOException {

        switchScene("/view/MainMenuForm.fxml",event);

    }

    /** This method triggers when the delete button is pressed, checks if there is a selection, and then deletes the selected customer.
     * @param event This event triggers the method.
     * @throws SQLException Throws SQLException.
     * @throws IOException Throws IOException.
     */
    @FXML
    void onActionCusDeleteButton(ActionEvent event) throws SQLException, IOException {

        if(customerTableView.getSelectionModel().isEmpty()){//check if customer selection is empty

            PopUpFormController.setUpPopUp("ERROR!", "No customer selected.", "/view/CustomersForm.fxml");
            switchScene("/view/PopUpForm.fxml", event);
        }else{//customer is selected

            PopUpFormController.setUpPopUp("WARNING!",
                    "Customer and customer's appointment(s) will be deleted.\nAre you sure you want to delete this customer?",
                    "/view/CustomersForm.fxml",
                    customerTableView.getSelectionModel().getSelectedItem());
            switchScene("/view/PopUpForm.fxml", event);
        }
    }

    /** This method triggers when the update button is pressed, checks for a selection, and switches to the add customer form.
     * @param event This event triggers the method.
     * @throws IOException Throws IOException.
     */
    @FXML
    void onActionCusUpdateButton(ActionEvent event) throws IOException {

        if(customerTableView.getSelectionModel().isEmpty()){//check if customer is empty

            PopUpFormController.setUpPopUp("ERROR!", "No customer selected.", "/view/CustomersForm.fxml");
            switchScene("/view/PopUpForm.fxml", event);
        }else{

            setUpdateCustomer(customerTableView.getSelectionModel().getSelectedItem());
            isLabelAdd = false;
            switchScene("/view/AddCustomerForm.fxml",event);
        }
    }

    @FXML
    void onActionCustomerNameRadio(ActionEvent event) {
        currentCustomerRadio = "Name";

    }

    @FXML
    void onActionCustomerCountIDRadio(ActionEvent event) {
        currentCustomerRadio = "Country ID";
    }

    @FXML
    void onActioncustomerCusIDRadio(ActionEvent event) {
        currentCustomerRadio = "Customer ID";

    }

    @FXML
    void onActionCustomerSearchButton(ActionEvent event) throws SQLException {

        ArrayList<Customer> allCustomers = new ArrayList<>();
        ArrayList<Customer> vettedCustomers = new ArrayList<>();
        vettedCustomers.clear();
        allCustomers.clear();
        allCustomers.addAll(CustomerQuery.getAllCustomers());

        if(currentCustomerRadio.compareTo("Name") == 0){
            for(Customer c: allCustomers){
                if(c.getCustomerName().contains(customerSearchTextField.getText())){
                    vettedCustomers.add(c);
                }
            }
        }else if(currentCustomerRadio.compareTo("Country ID") == 0){
            for(Customer c: allCustomers){
                if(c.getCountryID() == Integer.valueOf(customerSearchTextField.getText())){
                    vettedCustomers.add(c);
                }
            }
        }else{
            for(Customer c: allCustomers){
                if(c.getCusID() == Integer.valueOf(customerSearchTextField.getText())){
                    vettedCustomers.add(c);
                }
            }
        }

        for(Customer c: vettedCustomers){
            System.out.println("Name: " + c.getCustomerName() + "~~~CountryID: " + c.getCountryID()
                                    + "~~~CustomerID: " + c.getCusID());
        }
    }






    /** This method initializes when the form is called and sets up form.
     * @param url This is the url.
     * @param resourceBundle This is the resource bundle.
     */
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


    /**
     * This method sets up the columns for the customers form table view.
     */
    private void setColumns(){

        customerIDTableColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("cusID"));
        customerNameTableColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        customerPhoneTableColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        customerAddressTableColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        customerDivIDTableColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("divisionID"));
        customerCountryIDTableColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("countryID"));
        customerPostalTableColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("postalCode"));

    }

    /** This method switches the screen to the new form.
     * @param newFXML This is the path to the new screen.
     * @param event This event triggers the method.
     * @throws IOException Throws IOException.
     */
    private void switchScene(String newFXML, ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(newFXML));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method gets if adding or updating customer.
     * @return Returns true if adding customer.
     */
    public static Boolean getIsLabelAdd(){

        if(isLabelAdd){
            return true;
        }else{
            return false;
        }
    }

    public static Customer getUpdateCustomer() {
        return updateCustomer;
    }

    public void setUpdateCustomer(Customer updateCustomer) {
        this.updateCustomer = updateCustomer;
    }

}