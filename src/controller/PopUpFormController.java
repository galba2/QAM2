package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PopUpFormController implements Initializable {

    private static String viewPath;
    private Stage stage;
    private Parent scene;
    private static String alertText;
    private static String descriptionText;

    @FXML
    private Label popUpAlertLabel;
    @FXML
    private Label popUpDescriptionLabel;
    @FXML
    private Button popUpOkButton;
    @FXML
    private Button popUpCancelButton;



    @FXML
    void onActionPopUpCancelButton(ActionEvent event) throws IOException {
        switchScene(viewPath,event);

    }

    @FXML
    void onActionPopUpOkButton(ActionEvent event) throws IOException {
        switchScene(viewPath,event);

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        popUpAlertLabel.setText(alertText);
        popUpDescriptionLabel.setText(descriptionText);
    }





    //METHODS
    public static void setUpPopUp(String typeOfAlert, String description, String parentViewPath) throws IOException {

        alertText = typeOfAlert;
        descriptionText = description;
        viewPath = parentViewPath;
    }

    private void switchScene(String newFXML, ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(newFXML));
        stage.setScene(new Scene(scene));
        stage.show();
    }


}
