package controller;

import DBAccess.CustomerQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Customer;


import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    private Stage stage;
    private Parent scene;


    @FXML
    private TextField cIDTextbox;
    @FXML
    private TextField cNameTextBox;
    @FXML
    private TextField cPhoneTextbox;
    @FXML
    private TextField cPostalTextBox;
    @FXML
    private TextField cStreetTextBox;
    @FXML
    private Button cCancelButton;
    @FXML
    private Button cSaveButton;
    @FXML
    private ComboBox<String> cCountryComboBox;
    @FXML
    private ComboBox<String> cStateComboBox;
    @FXML
    private Label cTitleLabel;



    @FXML
    void onActionCusCancelButton(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomersForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        clearFields();

    }


    @FXML
    void onActionCusSaveButton(ActionEvent event) throws SQLException, IOException {

        if(CustomersFormController.getIsLabelAdd()){

            CustomerQuery.addCustomer(cNameTextBox.getText(),cStreetTextBox.getText(),
                                        cPostalTextBox.getText(),cPhoneTextbox.getText(), Timestamp.valueOf(LocalDateTime.now()),
                                            LogInFormController.getUser().getUserName(),Timestamp.valueOf(LocalDateTime.now()),
                                                LogInFormController.getUser().getUserName(),
                                                    CustomerQuery.getDivIDByDiv(cStateComboBox.getSelectionModel().getSelectedItem()));
        }else{
            CustomerQuery.updateCustomer(cNameTextBox.getText(),cStreetTextBox.getText(),
                                            cPostalTextBox.getText(),cPhoneTextbox.getText(),
                                                Timestamp.valueOf(LocalDateTime.now()),LogInFormController.getUser().getUserName(),
                                                CustomerQuery.getDivIDByDiv(cStateComboBox.getSelectionModel().getSelectedItem()),
                                                    Integer.parseInt(cIDTextbox.getText()));
        }

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomersForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        clearFields();


    }

    @FXML
    void onActionAddCusCountryCombo(ActionEvent event) throws SQLException {

        cStateComboBox.setItems(CustomerQuery.getCustomerDivByCounIDList(getCounIDByCoun(cCountryComboBox.getSelectionModel().getSelectedItem())));

    }

    @FXML
    void onActionAddCusStateCombo(ActionEvent event) {

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCusLabel();

        try {
            setComboBoxes();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(!CustomersFormController.getIsLabelAdd()){//Check if is update
            try {
                setUpdateTextFields(CustomersFormController.getUpdateCustomer());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }



    //METHODS

    @FXML
    public void setCusLabel(){

        if(CustomersFormController.getIsLabelAdd()){
            this.cTitleLabel.setText("Add Customer");
            cIDTextbox.setText("AUTO GENERATED");
        }else{
            this.cTitleLabel.setText("Update Customer");
        }

    }

    private void clearFields() {

        cIDTextbox.clear();
        cNameTextBox.clear();
        cPhoneTextbox.clear();
        cPostalTextBox.clear();
        cStreetTextBox.clear();
    }

    private void setComboBoxes() throws SQLException {

        cCountryComboBox.setItems(CustomerQuery.getCustomerCountryList());
        cStateComboBox.setItems(CustomerQuery.getCustomerDivByCounIDList(getCounIDByCoun(cCountryComboBox.getSelectionModel().getSelectedItem())));
    }

    private int getCounIDByCoun(String country) throws SQLException {

        return CustomerQuery.getCounIDByCoun(country);
    }

    private void setUpdateTextFields(Customer c) throws SQLException {

        cIDTextbox.setText(Integer.toString(c.getCusID()));
        cNameTextBox.setText(c.getCustomerName());
        cStreetTextBox.setText(c.getAddress());
        cPostalTextBox.setText(c.getPostalCode());
        cPhoneTextbox.setText(c.getPhone());
        cCountryComboBox.getSelectionModel().select(CustomerQuery.getCounByDivID(c.getDivisionID()));
        cStateComboBox.getSelectionModel().select(CustomerQuery.getDivByDivID(c.getDivisionID()));



    }

}
