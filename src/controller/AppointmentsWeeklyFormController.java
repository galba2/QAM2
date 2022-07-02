package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsWeeklyFormController implements Initializable {

    @FXML
    private RadioButton aRWAllRadio;

    @FXML
    private TableColumn<?, ?> aRWContactCol;

    @FXML
    private TableColumn<?, ?> aRWCusIDCol;

    @FXML
    private TableColumn<?, ?> aRWDescriptionCol;

    @FXML
    private TableColumn<?, ?> aRWEndDateCol;

    @FXML
    private TableColumn<?, ?> aRWEndTimeCol;

    @FXML
    private TableColumn<?, ?> aRWIDCol;

    @FXML
    private TableColumn<?, ?> aRWLocationCol;

    @FXML
    private RadioButton aRWMonthlyRadio;

    @FXML
    private TableColumn<?, ?> aRWStartDateCol;

    @FXML
    private TableColumn<?, ?> aRWStartTimeCol;

    @FXML
    private TableView<?> aRWTableView;

    @FXML
    private TableColumn<?, ?> aRWTitleCol;

    @FXML
    private TableColumn<?, ?> aRWTypeCol;

    @FXML
    private TableColumn<?, ?> aRWUserIDCol;

    @FXML
    private ComboBox<?> aRWWeekComboBox;

    @FXML
    private RadioButton aRWWeeklyRadio;

    @FXML
    private ComboBox<?> aRWYearComboBox;

    @FXML
    private ToggleGroup appViewToggle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
