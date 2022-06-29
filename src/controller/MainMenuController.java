package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    private Button mainAppAddButton;
    @FXML
    private Button mainAppDeleteButton;
    @FXML
    private Button mainAppUdateButton;
    @FXML
    private Button mainCusAddButton;
    @FXML
    private Button mainCusDeleteButton;
    @FXML
    private Button mainCusUpdateButton;
    @FXML
    private Label mainMonth1Label;
    @FXML
    private Label mainMonth2Label;
    @FXML
    private GridPane mainMonthGridpane1;
    @FXML
    private GridPane mainMonthGridpane2;
    @FXML
    private Button mainReport1Button;
    @FXML
    private Button mainReport2Button;
    @FXML
    private Button mainReport3Button;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Sup from Controller");
    }
}
