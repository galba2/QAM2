package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

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
    private TableColumn<?, ?> customerCountryTableColumn;
    @FXML
    private Button customerDeleteButton;
    @FXML
    private TableColumn<?, ?> customerIDTableColumn;
    @FXML
    private TableColumn<?, ?> customerNameTableColumn;
    @FXML
    private TableColumn<?, ?> customerPhoneTableColumn;
    @FXML
    private TableColumn<?, ?> customerPostalTableColumn;
    @FXML
    private TableColumn<?, ?> customerStateTableColumn;
    @FXML
    private TableColumn<?, ?> customerStreetTableColumn;
    @FXML
    private TableView<?> customerTableView;
    @FXML
    private Button customerUpdateButton;


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

    }



    //METHODS
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
