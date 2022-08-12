package controller;

import DBAccess.AppointmentQuery;
import DBAccess.CustomerQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class controls the javafx popupform html class.
 */
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
    private static ResourceBundle rb = ResourceBundle.getBundle("Lang", Locale.getDefault());

    @FXML
    private Label popUpAlertLabel;
    @FXML
    private Label popUpDescriptionLabel;
    @FXML
    private Button popUpOkButton;
    @FXML
    private Button popUpCancelButton;


    /** This method triggers when the cancel button is pressed and switches or closes the screen.
     * @param event This event triggers the method.
     * @throws IOException Throws IOException.
     */
    @FXML
    void onActionPopUpCancelButton(ActionEvent event) throws IOException {
        if(justClose){//check if just closing stage
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }else{
            switchScene(viewPath,event);
        }
    }

    /** This method triggers when the ok button is pressed, checks if it is a customer,appointment,just close or switch screen situation and
     *  deals with it accordingly.
     * @param event This event triggers the method.
     * @throws IOException Throws IOException.
     * @throws SQLException Throws SQLException.
     */
    @FXML
    void onActionPopUpOkButton(ActionEvent event) throws IOException, SQLException {

        if(customerSetting){//check if deleting customer
            ObservableList<Appointment> allAppts = FXCollections.observableArrayList();
            allAppts.addAll(AppointmentQuery.getAllAppointments());//add all appointments to allappts

            allAppts.forEach(a -> {//delete all appointments that has the customer's to be deleted id

                if(a.getCustomerID() == customerToBeDeleted.getCusID()){//check if appointment's customerid has the customer's to be deleted id
                    try {
                        AppointmentQuery.deleteAppointment(a.getApptID());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });

            CustomerQuery.deleteCustomer(customerToBeDeleted.getCusID());
            switchScene(viewPath,event);
        }else if(appointmentSetting){//check if deleting appointment

            AppointmentQuery.deleteAppointment(appointmentToBeDeleted.getApptID());
            switchScene(viewPath,event);
        }else if(justClose){//check if just closing stage

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }else{//not deleting customer or appointment or justclose

            switchScene(viewPath,event);
        }
    }


    /** This method initializes when form is called and sets up form based on language default.
     * @param url This is the url.
     * @param resourceBundle This is the resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")){//check computer system's language

            popUpOkButton.setText(rb.getString("OK"));
            popUpCancelButton.setText(rb.getString("Cancel"));
        }

        popUpAlertLabel.setText(alertText);
        popUpDescriptionLabel.setText(descriptionText);
    }





    //METHODS


    /** This method overload setting up the form to be an alert type.
     * @param typeOfAlert This is the alert entered onto the title label.
     * @param description This is the message entered onto the body of the screen.
     * @throws IOException Throws IOException.
     */
    public static void setUpPopUp(String typeOfAlert, String description) throws IOException {

        justClose = true;
        customerSetting = false;
        appointmentSetting = false;
        alertText = typeOfAlert;
        descriptionText = description;
    }

    /** This method overload setting up the form to be a switch type and checks the language default.
     * @param typeOfAlert This is the alert entered onto the title label.
     * @param description This is the message entered onto the body of the screen.
     * @param parentViewPath This is the path to go back to the old screen.
     * @throws IOException Throws IOException.
     */
    public static void setUpPopUp(String typeOfAlert, String description, String parentViewPath) throws IOException {

        justClose = false;
        customerSetting = false;
        appointmentSetting = false;
        viewPath = parentViewPath;
        
        if(Locale.getDefault().getLanguage().equals("fr") && description.contains("account")){//check if computer system's language is set to french and if description is from login screen
            alertText = rb.getString(typeOfAlert);
            descriptionText = rb.getString("account");
        }else{
            alertText = typeOfAlert;
            descriptionText = description;
        }

    }

    /** This method overload setting up the form to be a customer type.
     * @param typeOfAlert This is the alert entered onto the title label.
     * @param description This is the message entered onto the body of the screen.
     * @param parentViewPath This is the path to go back to the old screen.
     * @param deleteCustomer This is the customer to be deleted.
     * @throws IOException Throws IOException.
     */
    public static void setUpPopUp(String typeOfAlert, String description, String parentViewPath, Customer deleteCustomer) throws IOException {

        justClose = false;
        customerSetting = true;
        appointmentSetting = false;
        alertText = typeOfAlert;
        descriptionText = description;
        viewPath = parentViewPath;
        customerToBeDeleted = deleteCustomer;
    }

    /** This method overload setting up the form to be an appointment type.
     * @param typeOfAlert This is the alert entered onto the title label.
     * @param description This is the message entered onto the body of the screen.
     * @param parentViewPath This is the path to go back to the old screen.
     * @param deleteAppointment This is the appointment to be deleted.
     * @throws IOException Throws IOException.
     */
    public static void setUpPopUp(String typeOfAlert, String description, String parentViewPath, Appointment deleteAppointment) throws IOException {

        justClose = false;
        customerSetting = false;
        appointmentSetting = true;
        alertText = typeOfAlert;
        descriptionText = description;
        viewPath = parentViewPath;
        appointmentToBeDeleted = deleteAppointment;
    }

    /** This method overload setting up the form to be a switch screen type and checks default language.
     * @param typeOfAlert This is the alert entered onto the title label.
     * @param description This is the message entered onto the body of the screen.
     * @param parentViewPath This is the path to go back to the old screen.
     * @throws IOException Throws IOException.
     */
    public static void setUpPopUp(String typeOfAlert, ObservableList<String> description, String parentViewPath) throws IOException {

        justClose = false;
        customerSetting = false;
        appointmentSetting = false;

        ObservableList<String> d = FXCollections.observableArrayList();//create observable list to store description parameter
        d.addAll(description);
        String descrip = "";

        if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")){//check computer system's language
            alertText = rb.getString(typeOfAlert);//get alert in the computer system's language
            for(String m : d){//assign message for popup description
                descrip += rb.getString(m) + "\n";
            }
        }

        descriptionText = descrip;
        viewPath = parentViewPath;
    }

    /** This method switches the screen to the new screen.
     * @param newFXML This the path to the new form.
     * @param event This event triggers the method.
     * @throws IOException Throws IOException.
     */
    private void switchScene(String newFXML, ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(newFXML));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}