package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsWeeklyFormController implements Initializable {

    private Stage stage;
    private Parent scene;

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
    @FXML
    private Button appointmentsWeeklyFormBackButton;

    @FXML
    void onActionAppointmentsWeeklyFormBackButton(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenuForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
