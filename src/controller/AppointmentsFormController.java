package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsFormController implements Initializable {

    @FXML
    private RadioButton aRAllRadio;

    @FXML
    private TableColumn<?, ?> aRContactColumn;

    @FXML
    private TableColumn<?, ?> aRCusIDColumn;

    @FXML
    private TableColumn<?, ?> aRDescriptionColumn;

    @FXML
    private TableColumn<?, ?> aREndDateColumn;

    @FXML
    private TableColumn<?, ?> aREndTimeColumn;

    @FXML
    private TableColumn<?, ?> aRIDColumn;

    @FXML
    private TableColumn<?, ?> aRLocationColumn;

    @FXML
    private RadioButton aRMonthlyRadio;

    @FXML
    private TableColumn<?, ?> aRStartDateColumn;

    @FXML
    private TableColumn<?, ?> aRStartTimeColumn;

    @FXML
    private TableView<?> aRTableView;

    @FXML
    private TableColumn<?, ?> aRTitleColumn;

    @FXML
    private TableColumn<?, ?> aRTypeColumn;

    @FXML
    private TableColumn<?, ?> aRUserIDColumn;

    @FXML
    private RadioButton aRWeeklyRadio;

    @FXML
    private ToggleGroup appViewToggle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
