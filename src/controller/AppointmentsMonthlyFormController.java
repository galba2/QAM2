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

public class AppointmentsMonthlyFormController implements Initializable {

    private Stage stage;
    private Parent scene;

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
    @FXML
    private Button appointmentsMonthlyFormBackButton;

    @FXML
    void onActionAppointmentsMonthlyFormBackButton(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenuForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
