package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsFormController implements Initializable {

    private Stage stage;
    private Parent scene;

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
    @FXML
    private Button appointmentsFormBackButton;

    @FXML
    void onActionAppointmentsFormBackButton(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenuForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
