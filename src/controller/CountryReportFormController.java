package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.AreaCount;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CountryReportFormController implements Initializable {

    private Stage stage;
    private Parent scene;

    @FXML
    private Button countryFormBackButton;

    @FXML
    private TableColumn<AreaCount, String> countryFormCountryColumn;

    @FXML
    private TableColumn<AreaCount, Integer> countryFormNumberColumn;

    @FXML
    private TableView<AreaCount> countryFormTableView;

    @FXML
    void onActionBackButton(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenuForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setColumns();



    }

    //METHODS
    public void setColumns(){

        countryFormCountryColumn.setCellValueFactory(new PropertyValueFactory<AreaCount, String>("apptID"));
        countryFormNumberColumn.setCellValueFactory(new PropertyValueFactory<AreaCount, Integer>("title"));


    }
}
