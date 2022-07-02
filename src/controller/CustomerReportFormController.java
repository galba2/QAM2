package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerReportFormController implements Initializable {

    @FXML
    private Button customerReportBackButton;

    @FXML
    private TableColumn<?, ?> customerReportMonthColumn;

    @FXML
    private TableColumn<?, ?> customerReportMonthNumColumn;

    @FXML
    private TableView<?> customerReportMonthTableView;

    @FXML
    private TableColumn<?, ?> customerReportTypeColumn;

    @FXML
    private TableColumn<?, ?> customerReportTypeNumColumn;

    @FXML
    private TableView<?> customerReportTypeTableView;

    @FXML
    void onActionBackButton(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
