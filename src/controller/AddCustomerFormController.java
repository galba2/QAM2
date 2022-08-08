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
    private boolean errors;


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
    private Label errorCountryLabel;
    @FXML
    private Label errorNameLabel;
    @FXML
    private Label errorPhoneLabel;
    @FXML
    private Label errorPostalLabel;
    @FXML
    private Label errorStateLabel;
    @FXML
    private Label errorStreetLabel;



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

        if(checkInput()){//check for errors thru method
            //do nothing else
        }else{//no errors
            if(CustomersFormController.getIsLabelAdd()){//check for add customer

                CustomerQuery.addCustomer(cNameTextBox.getText(),cStreetTextBox.getText(),
                        cPostalTextBox.getText(),cPhoneTextbox.getText(), Timestamp.valueOf(LocalDateTime.now()),
                        LogInFormController.getUser().getUserName(),Timestamp.valueOf(LocalDateTime.now()),
                        LogInFormController.getUser().getUserName(),
                        CustomerQuery.getDivIDByDiv(cStateComboBox.getSelectionModel().getSelectedItem()));
            }else{//is update customer
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
            cCountryComboBox.getSelectionModel().clearSelection();
            cStateComboBox.getSelectionModel().clearSelection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(!CustomersFormController.getIsLabelAdd()){//Check if is update
            try {
                setUpdateTextFields(CustomersFormController.getUpdateCustomer());//setup textboxes with information from customer to be updated
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

    private boolean checkInput() {

        errors = false;

        if(cNameTextBox.getText().isEmpty()){//check if name textbox is empty
            errorNameLabel.setVisible(true);
            errors = true;
        }else{//not empty
            errorNameLabel.setVisible(false);
        }
        if(cStreetTextBox.getText().isEmpty()){//check if street textbox is empty
            errorStreetLabel.setVisible(true);
            errors = true;
        }else{//not empty
            errorStreetLabel.setVisible(false);
        }
        if(cPostalTextBox.getText().isEmpty()){//check if postal textbox is empty
            errorPostalLabel.setVisible(true);
            errors = true;
        }else{//not empty
            errorPostalLabel.setVisible(false);
        }
        if(cPhoneTextbox.getText().isEmpty()){//check if phone textbox is empty
            errorPhoneLabel.setVisible(true);
            errors = true;
        }else{//not empty
            errorPhoneLabel.setVisible(false);
        }
        if(cStateComboBox.getSelectionModel().isEmpty()){//check if state combobox is empty
            errorStateLabel.setVisible(true);
            errors = true;
        }else{//not empty
            errorStateLabel.setVisible(false);
        }
        if(cCountryComboBox.getSelectionModel().isEmpty()){//check if country combobox is empty
            errorCountryLabel.setVisible(true);
            errors = true;
        }else{//not empty
            errorCountryLabel.setVisible(false);
        }

        return errors;
    }
}
