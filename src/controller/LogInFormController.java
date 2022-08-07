package controller;

import DBAccess.UserAttemptQuery;
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
import model.UserAttempt;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class LogInFormController implements Initializable {

    private Stage stage;
    private Parent scene;
    private static UserAttempt user;
    private  static ZoneId localZoneID;


    @FXML
    private Label loginZoneIDLabel;
    @FXML
    private Label loginLocationLabel;
    @FXML
    private Label loginPasswordLabel;
    @FXML
    private Label loginUserLabel;
    @FXML
    private TextField loginUserTextBox;
    @FXML
    private TextField loginPasswordTextBox;
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
    void onActionLoginButton(ActionEvent event) throws IOException, SQLException {

        checkInput(event);//checks textboxes for errors
        user = new UserAttempt(loginUserTextBox.getText());


        if(!UserAttemptQuery.isPasswordCorrect(user, loginPasswordTextBox.getText())){
            loginUserTextBox.clear();
            loginPasswordTextBox.clear();
            user.setIsUserAttemptSuccessful(false);
            saveLoginAttempt();
            PopUpFormController.setUpPopUp("ERROR!",
                                        "No account found for that user or password.","/view/LogInForm.fxml");
            switchScene("/view/PopUpForm.fxml",event);

        }else{

            user.setIsUserAttemptSuccessful(true);
            saveLoginAttempt();
            switchScene("/view/MainMenuForm.fxml", event);

        }



    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ResourceBundle rb = ResourceBundle.getBundle("Lang", Locale.getDefault());

        if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("es") || Locale.getDefault().getLanguage().equals("en")){

            loginExitButton.setText(rb.getString("Exit"));
            loginLogInButton.setText(rb.getString("Login"));
            loginPasswordLabel.setText(rb.getString("PASSWORD"));
            loginUserLabel.setText(rb.getString("USER"));
        }

        localZoneID = ZoneId.of(TimeZone.getDefault().getID());
        Locale l = Locale.getDefault();
        loginLocationLabel.setText(l.getDisplayCountry());
        loginZoneIDLabel.setText(localZoneID.getId());
    }



    //METHODS

    public static UserAttempt getUser() {
        return user;
    }

    public void setUser(UserAttempt user) {
        this.user = user;
    }

    private void switchScene(String newFXML, ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(newFXML));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    private void saveLoginAttempt() throws IOException {
        String fileName = "src/File/login_activity.txt", userAttemptLine;

        FileWriter fw = new FileWriter(fileName, true);
        PrintWriter pw = new PrintWriter(fw);
        userAttemptLine = user.getUserName() + " " + Timestamp.valueOf(user.getUserLogInLocalDateTime()) + " " + user.getIsUserAttemptSuccessful();
        pw.println(userAttemptLine);
        pw.close();
    }

    private void checkInput(ActionEvent event) throws IOException {

        //ArrayList<String> message = new ArrayList<String>();
        String message = "";

        if(loginUserTextBox.getText().isEmpty()){//checks if login textbox is empty
            message += "Please enter a user.\n";
        }
        if(loginPasswordTextBox.getText().isEmpty()){//checks if password textbox is empty
            message += "Please enter a password.\n";
        }

        if(!message.isEmpty()){//checks if there are errors
            PopUpFormController.setUpPopUp("ERROR!", message,"/view/LogInForm.fxml");
            switchScene("/view/PopUpForm.fxml",event);
        }
    }
}
