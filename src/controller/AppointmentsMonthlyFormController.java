package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsMonthlyFormController implements Initializable {

    @FXML
    private RadioButton aRMAllRadio;

    @FXML
    private TableColumn<?, ?> aRMContactCol;

    @FXML
    private TableColumn<?, ?> aRMCusIDCol;

    @FXML
    private TableColumn<?, ?> aRMDescriptionCol;

    @FXML
    private TableColumn<?, ?> aRMEndDateCol;

    @FXML
    private TableColumn<?, ?> aRMEndTimeCol;

    @FXML
    private TableColumn<?, ?> aRMIDCol;

    @FXML
    private TableColumn<?, ?> aRMLocationCol;

    @FXML
    private ComboBox<?> aRMMonthComboBox;

    @FXML
    private RadioButton aRMMonthlyRadio;

    @FXML
    private TableColumn<?, ?> aRMStartDateCol;

    @FXML
    private TableColumn<?, ?> aRMStartTimeCol;

    @FXML
    private TableView<?> aRMTableView;

    @FXML
    private TableColumn<?, ?> aRMTitleCol;

    @FXML
    private TableColumn<?, ?> aRMTypeCol;

    @FXML
    private TableColumn<?, ?> aRMUserIDCol;

    @FXML
    private RadioButton aRMWeeklyRadio;

    @FXML
    private ComboBox<?> aRMYearComboBox;

    @FXML
    private ToggleGroup appViewToggle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
