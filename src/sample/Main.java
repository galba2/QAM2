package sample;

import DBAccess.ContactQuery;
import Database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void init(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/LogInForm.fxml"));
        primaryStage.setTitle("Client-Tracker");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    @Override
    public void stop(){
        System.out.println("stop");
    }


    public static void main(String[] args) throws SQLException {
        DBConnection.makeConnection();

            System.out.println("Before");
            System.out.println("After");



        launch(args);

        DBConnection.closeConnection();
    }
}
