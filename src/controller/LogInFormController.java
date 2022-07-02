package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInFormController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
