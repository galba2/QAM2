package controller;

import DBAccess.AppointmentQuery;
import DBAccess.ContactQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContactReportFormController implements Initializable {

    private Stage stage;
    private Parent scene;


    @FXML
    private ComboBox<String> contactFormComboBox;
    @FXML
    private TableColumn<Appointment, Integer> contactFormCusIDColumn;
    @FXML
    private TableColumn<Appointment, String> contactFormDescriptionColumn;
    @FXML
    private TableColumn<Appointment, Date> contactFormEndColumn;
    @FXML
    private TableColumn<Appointment, Integer> contactFormIDColumn;
    @FXML
    private TableColumn<Appointment, Date> contactFormStartColumn;
    @FXML
    private TableColumn<Appointment, String> contactFormTitleColumn;
    @FXML
    private TableColumn<Appointment, String> contactFormTypeColumn;
    @FXML
    private TableView<Appointment> contactFormTableView;
    @FXML
    private Button contactFormBackButton;


    @FXML
    void onActionBackButton(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenuForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionContactReportComboBox(ActionEvent event) {

        String selected = contactFormComboBox.getSelectionModel().getSelectedItem();

        if(selected.equals("All")){

            try {
                contactFormTableView.setItems(AppointmentQuery.getAllContactFormAppointments());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{


        }
        int contactID = getIDOnly();



    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            contactFormComboBox.setItems(ContactQuery.getAllContactsList());
            contactFormComboBox.getItems().add(0,"All");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        setColumns();

        try {
            contactFormTableView.setItems(AppointmentQuery.getAllContactFormAppointments());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }



    //METHODS
    private void setColumns(){

        contactFormIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("apptID"));
        contactFormTitleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        contactFormTypeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        contactFormDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        contactFormCusIDColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerID"));
        contactFormStartColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Date>("start"));
        contactFormEndColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Date>("end"));

    }

    private int getIDOnly(String rawString) {

        int idInt;
        String idString;

        idString = rawString.substring(rawString.indexOf("(") +1, rawString.indexOf(")"));//id as String from String
        idInt = Integer.parseInt(idString);//turn string id into int id

        return idInt;
    }


}
