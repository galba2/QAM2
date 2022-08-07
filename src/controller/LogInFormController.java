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

        user = new UserAttempt(loginUserTextBox.getText());


        if(!UserAttemptQuery.isPasswordCorrect(user, loginPasswordTextBox.getText())){
            loginUserTextBox.clear();
            loginPasswordTextBox.clear();
            user.setIsUserAttemptSuccessful(false);
            saveLoginAttempt();
        }else{

            user.setIsUserAttemptSuccessful(true);
            saveLoginAttempt();
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenuForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }



    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println(Locale.getDefault());
        System.out.println(Locale.getDefault().getLanguage());
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

    private void saveLoginAttempt() throws IOException {
        String fileName = "src/File/login_activity.txt", userAttemptLine;

        System.out.println(user.getUserLogInLocalDateTime());

        FileWriter fw = new FileWriter(fileName, true);
        PrintWriter pw = new PrintWriter(fw);
        userAttemptLine = user.getUserName() + " " + Timestamp.valueOf(user.getUserLogInLocalDateTime()) + " " + user.getIsUserAttemptSuccessful();
        pw.println(userAttemptLine);
        pw.close();


    }
}
