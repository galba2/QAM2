package controller;

import DBAccess.CountryReportQuery;
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
import model.AreaCount;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This class controls the javafx countryreportform html class.
 */
public class CountryReportFormController implements Initializable {

    private Stage stage;
    private Parent scene;

    @FXML
    private Button countryFormBackButton;

    @FXML
    private TableColumn<AreaCount, String> countryFormCountryColumn;

    @FXML
    private TableColumn<AreaCount, Integer> countryFormNumberColumn;

    @FXML
    private TableView<AreaCount> countryFormTableView;

    /** This method triggers when the back button is pressed.
     * @param event This is the event that triggers the method.
     * @throws IOException Throws IOException.
     */
    @FXML
    void onActionBackButton(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenuForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }



    /** This method initializes when the form is called sets up the form.
     * @param url This is the url.
     * @param resourceBundle This is the resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setColumns();

        try {
            countryFormTableView.setItems(CountryReportQuery.getCountryReport());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //METHODS


    /**
     * This method sets up the columns for the table view.
     */
    public void setColumns(){

        countryFormCountryColumn.setCellValueFactory(new PropertyValueFactory<AreaCount, String>("area"));
        countryFormNumberColumn.setCellValueFactory(new PropertyValueFactory<AreaCount, Integer>("areaCount"));


    }
}
