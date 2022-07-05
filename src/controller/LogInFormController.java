package controller;

import Database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInFormController implements Initializable {

    private Stage stage;
    private Parent scene;

    @FXML
    private RadioButton loginEnglishRadio;
    @FXML
    private RadioButton loginFrenchRadio;
    @FXML
    private ToggleGroup loginLanguageSelect;
    @FXML
    private Label loginLocationLabel;
    @FXML
    private TextField loginPasswordTextBox;
    @FXML
    private Label loginTimeLabel;
    @FXML
    private TextField loginUserTextBox;
    @FXML
    private Button loginExitButton;
    @FXML
    private Button loginLogInButton;

    @FXML
    void onActionExitButton(ActionEvent event) {

        DBConnection.closeConnection();
        System.exit(0);

    }

    @FXML
    void onActionLoginButton(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenuForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
