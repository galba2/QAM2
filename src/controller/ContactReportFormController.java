package controller;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContactReportFormController implements Initializable {

    private Stage stage;
    private Parent scene;

    @FXML
    private ComboBox<?> contactFormComboBox;
    @FXML
    private TableColumn<?, ?> contactFormCusIDColumn;
    @FXML
    private TableColumn<?, ?> contactFormDescriptionColumn;
    @FXML
    private TableColumn<?, ?> contactFormEndColumn;
    @FXML
    private TableColumn<?, ?> contactFormIDColumn;
    @FXML
    private TableColumn<?, ?> contactFormStartColumn;
    @FXML
    private TableColumn<?, ?> contactFormTitleColumn;
    @FXML
    private TableColumn<?, ?> contactFormTypeColumn;
    @FXML
    private TableView<?> contactFormTableView;
    @FXML
    private Button contactFormBackButton;

    @FXML
    void onActionBackButton(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenuForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
