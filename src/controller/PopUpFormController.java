package controller;

import DBAccess.CustomerQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class PopUpFormController implements Initializable {

    private static String viewPath;
    private Stage stage;
    private Parent scene;
    private static boolean customerSetting = false;
    private static boolean appointmentSetting = false;
    private static boolean justClose = false;
    private static Customer customerToBeDeleted;
    private static Appointment appointmentToBeDeleted;
    private static String alertText;
    private static String descriptionText;

    @FXML
    private Label popUpAlertLabel;
    @FXML
    private Label popUpDescriptionLabel;
    @FXML
    private Button popUpOkButton;
    @FXML
    private Button popUpCancelButton;



    @FXML
    void onActionPopUpCancelButton(ActionEvent event) throws IOException {
        if(justClose){//check if just closing stage
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }else{
            switchScene(viewPath,event);
        }
    }

    @FXML
    void onActionPopUpOkButton(ActionEvent event) throws IOException, SQLException {

        if(customerSetting){//check if deleting customer

            CustomerQuery.deleteCustomer(customerToBeDeleted.getCusID());
            switchScene(viewPath,event);
        }else if(appointmentSetting){//check if deleting appointment





        }else if(justClose){//check if just closing stage

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }else{//not deleting customer or appointment

            switchScene(viewPath,event);
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        popUpAlertLabel.setText(alertText);
        popUpDescriptionLabel.setText(descriptionText);
    }





    //METHODS
    public static void setUpPopUp(String typeOfAlert, String description) throws IOException {

        justClose = true;
        customerSetting = false;
        appointmentSetting = false;
        alertText = typeOfAlert;
        descriptionText = description;
    }

    public static void setUpPopUp(String typeOfAlert, String description, String parentViewPath) throws IOException {

        justClose = false;
        customerSetting = false;
        appointmentSetting = false;
        alertText = typeOfAlert;
        descriptionText = description;
        viewPath = parentViewPath;
    }

    public static void setUpPopUp(String typeOfAlert, String description, String parentViewPath, Customer deleteCustomer) throws IOException {

        justClose = false;
        customerSetting = true;
        appointmentSetting = false;
        alertText = typeOfAlert;
        descriptionText = description;
        viewPath = parentViewPath;
        customerToBeDeleted = deleteCustomer;
    }

    public static void setUpPopUp(String typeOfAlert, String description, String parentViewPath, Appointment deleteAppointment) throws IOException {

        justClose = false;
        customerSetting = false;
        appointmentSetting = true;
        alertText = typeOfAlert;
        descriptionText = description;
        viewPath = parentViewPath;
        appointmentToBeDeleted = deleteAppointment;
    }

    private void switchScene(String newFXML, ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(newFXML));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}

