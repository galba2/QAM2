package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CountryReportFormController implements Initializable {

    @FXML
    private Button countryFormBackButton;

    @FXML
    private TableColumn<?, ?> countryFormCountryColumn;

    @FXML
    private TableColumn<?, ?> countryFormNumberColumn;

    @FXML
    private TableView<?> countryFormTableView;

    @FXML
    void onActionBackButton(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
