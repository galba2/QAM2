package controller;

import DBAccess.AppointmentQuery;
import DBAccess.UserAttemptQuery;
import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Appointment;
import model.UserAttempt;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * This class controls the javafx loginform html class.
 */
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

    /** This method exits the application.
     * @param event This event triggers the method.
     */
    @FXML
    void onActionExitButton(ActionEvent event) {

        DBConnection.closeConnection();
        System.exit(0);

    }

    /** This method triggers when the login button is pressed, checks for empty text fields, checks user and password log in,
     *  saves user attempt, switches screen to main menu form if successful, popup notifing if appointment is within 15 minutes.
     * @param event This event triggers the method.
     * @throws IOException Throws IOException.
     * @throws SQLException Throws SQLException.
     */
    @FXML
    void onActionLoginButton(ActionEvent event) throws IOException, SQLException {

        if(checkInput(event)){//checks textboxes for errors
            //do nothing
        }else{

            user = new UserAttempt(loginUserTextBox.getText());

            if(!UserAttemptQuery.isPasswordCorrect(user, loginPasswordTextBox.getText())){//check if user and password entered match

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

                String message = checkApptsForFifteen();//sets message for any appointments within 15 minutes
                PopUpFormController.setUpPopUp("ALERT!",
                        message,"/view/MainMenuForm.fxml");
                switchScene("/view/PopUpForm.fxml",event);
            }
        }
    }


    /** This method initializes when form is called and sets up form including language default.
     * @param url This is the url.
     * @param resourceBundle This is the resource bundle.
     */
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

    /** This method switches screen to the new screen.
     * @param newFXML This is the path to the new screen.
     * @param event This event triggers the method.
     * @throws IOException Throws IOException.
     */
    private void switchScene(String newFXML, ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(newFXML));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method saves the user log in attempt to an external file.
     * @throws IOException Throws IOException.
     */
    private void saveLoginAttempt() throws IOException {
        String fileName = "src/File/login_activity.txt", userAttemptLine;

        FileWriter fw = new FileWriter(fileName, true);
        PrintWriter pw = new PrintWriter(fw);
        userAttemptLine = user.getUserName() + " " + Timestamp.valueOf(user.getUserLogInLocalDateTime()) + " " + user.getIsUserAttemptSuccessful();
        pw.println(userAttemptLine);
        pw.close();
    }

    /** This method checks for empty text fields.
     * @param event This event triggers the method.
     * @return Returns true if errors are found.
     * @throws IOException Throws IOException.
     */
    private boolean checkInput(ActionEvent event) throws IOException {

        boolean errors = false;
        ObservableList<String> message = FXCollections.observableArrayList();

        if(loginUserTextBox.getText().isEmpty()){//checks if login textbox is empty
            message.add("user");

        }
        if(loginPasswordTextBox.getText().isEmpty()){//checks if password textbox is empty
            message.add("password");
        }

        if(!message.isEmpty()){//checks if there are errors
            PopUpFormController.setUpPopUp("ERROR!", message,"/view/LogInForm.fxml");
            switchScene("/view/PopUpForm.fxml",event);
            errors = true;
        }

        return errors;
    }

    /** This method checks if the user has an appointment within 15 minutes. Lambdas are used in foreach because I can easily iterate through the list and
     * because what i wanted to do with the code was not very complicated.
     * @return Returns message to input into the popup screen.
     * @throws SQLException Throws SQLException.
     */
    private String checkApptsForFifteen() throws SQLException {

        String mFifteen = "";
        ObservableList<Appointment> allAppts = FXCollections.observableArrayList();
        ObservableList<Appointment> relevantAppts = FXCollections.observableArrayList();
        ObservableList<Appointment> fifteenAppts = FXCollections.observableArrayList();
        allAppts.addAll(AppointmentQuery.getAllAppointments());//add all appointments to allappts

        allAppts.forEach(a -> {//add user's appointments to relevantappts
            if(a.getUserID() == user.getUserID()){//check if the appointment userid is the same as the user's id
                relevantAppts.add(a);
            }
        });

        fifteenAppts.addAll(relevantAppts);//add all relevantAppts to fifteenAppts
        relevantAppts.forEach(a -> {//remove appointments that are not within 15 minutes of now

            if(a.getStart().isAfter(LocalDateTime.now().plusMinutes(15))   || //check if appointment start is after 15 minutes
                      a.getStart().isBefore(LocalDateTime.now())){//check if appointment start is before now

                fifteenAppts.remove(a);
            }
        });

        if(fifteenAppts.isEmpty()){//check if relevantappts is empty
            mFifteen = "No appointments within 15 minutes.";
        }else{//relevantappts is not empty

            mFifteen = "You have (an) appointment(s) within 15 minutes.\n\n";

            for(Appointment ap : fifteenAppts){
                mFifteen += "Appointment ID: " + ap.getApptID() + ", DateTime: " + ap.getStart() + "\n";
            }
        }

        return mFifteen;
    }
}
