package DBAccess;

import Database.DBConnection;
import controller.AppointmentsFormController;
import controller.LogInFormController;
import controller.PopUpFormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Appointment;


import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class AppointmentQuery {

    private Stage stage;
    private Parent scene;

    public static void deleteAppointment(int id) throws SQLException {

        DBConnection.makePreparedStatement("DELETE FROM appointments WHERE Appointment_ID = ?",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setInt(1,id);
        int rows = ps.executeUpdate();
        if(rows > 0){
            System.out.println("Delete successful");
        }else{
            System.out.println("Delete failed");
        }

        //show confirmation popup
        try {
            PopUpFormController.setUpPopUp("ALERT!", "Appointment deleted.");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Stage popUpStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(AppointmentQuery.class.getResource("/view/PopUpForm.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        popUpStage.setScene(new Scene(root, 400, 300));
        popUpStage.show();
    }

    public static ObservableList getAllContactFormAppointments() throws SQLException {

        ObservableList<Appointment> apps = FXCollections.observableArrayList();

        DBConnection.makePreparedStatement("SELECT * FROM appointments ",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            Appointment ap = new Appointment();
            ap.setApptID(rs.getInt("Appointment_ID"));
            ap.setTitle(rs.getString("Title"));
            ap.setType(rs.getString("Type"));
            ap.setDescription(rs.getString("Description"));
            ap.setCustomerID(rs.getInt("Customer_ID"));
            ap.setStart(rs.getTimestamp("Start").toLocalDateTime());
            ap.setEnd(rs.getTimestamp("End").toLocalDateTime());
            apps.add(ap);
        }

        return apps;
    }

    public static ObservableList getAllAppointmentsOfContactID(int contactID) throws SQLException {

        ObservableList<Appointment> apps = FXCollections.observableArrayList();

        DBConnection.makePreparedStatement("SELECT * FROM appointments WHERE Contact_ID = ?", DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setInt(1, contactID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Appointment ap = new Appointment();
            ap.setApptID(rs.getInt("Appointment_ID"));
            ap.setTitle(rs.getString("Title"));
            ap.setType(rs.getString("Type"));
            ap.setDescription(rs.getString("Description"));
            ap.setCustomerID(rs.getInt("Customer_ID"));
            ap.setStart(rs.getTimestamp("Start").toLocalDateTime());
            ap.setEnd(rs.getTimestamp("End").toLocalDateTime());

            apps.add(ap);
        }

        return apps;

    }

    public static ObservableList getAllAppointments() throws SQLException {

        ObservableList<Appointment> apps = FXCollections.observableArrayList();

        DBConnection.makePreparedStatement("SELECT * FROM appointments ",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            Appointment ap = new Appointment(rs.getInt("Appointment_ID"),rs.getString("Title"),rs.getString("Description"),
                                                rs.getString("Location"),rs.getString("Type"),
                                                    rs.getTimestamp("Start").toLocalDateTime(),rs.getTimestamp("End").toLocalDateTime(),
                                                        rs.getTimestamp("Create_Date").toLocalDateTime(),rs.getString("Created_By"),
                                                            rs.getTimestamp("Last_Update"),rs.getString("Last_Update"),
                                                                rs.getInt("Customer_ID"),rs.getInt("User_ID"),
                                                                    rs.getInt("Contact_ID"));

            apps.add(ap);
        }

        return apps;
    }


    public static void addAppointment(String title, String description, String location, String type, Timestamp start, Timestamp end,
                                      Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy,
                                      int customerID, int userID, int contactID) throws SQLException {

        DBConnection.makePreparedStatement("INSERT INTO appointments (Title,Description,Location," +
                "Type,Start,End,Create_Date,Created_By,Last_Update,Last_Updated_By,Customer_ID,User_ID,Contact_ID)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setString(1,title);
        ps.setString(2,description);
        ps.setString(3,location);
        ps.setString(4,type);
        ps.setTimestamp(5,start);
        ps.setTimestamp(6,end);
        ps.setTimestamp(7,createDate);
        ps.setString(8,createdBy);
        ps.setTimestamp(9,lastUpdate);
        ps.setString(10,lastUpdatedBy);
        ps.setInt(11,customerID);
        ps.setInt(12,userID);
        ps.setInt(13,contactID);

        int rows = ps.executeUpdate();
        if(rows > 0){
            System.out.println("Add successful");
        }else{
            System.out.println("Add failed");
        }
    }

    public static void updateAppointment(String title,String description,String location,String type,Timestamp start,Timestamp end,
                                            Timestamp lastUpdate,String lastUpdatedBy,int customerID, int userID,int contactID, int apptID) throws SQLException {

        DBConnection.makePreparedStatement("UPDATE appointments SET Title = ?, Description = ?, Location = ?," +
                "Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ?" +
                " WHERE Appointment_ID = ?",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setString(1,title);
        ps.setString(2,description);
        ps.setString(3,location);
        ps.setString(4,type);
        ps.setTimestamp(5,start);
        ps.setTimestamp(6,end);
        ps.setTimestamp(7,lastUpdate);
        ps.setString(8,lastUpdatedBy);
        ps.setInt(9,customerID);
        ps.setInt(10,userID);
        ps.setInt(11,contactID);
        ps.setInt(12,apptID);

        int rows = ps.executeUpdate();
        if(rows > 0){
            System.out.println("Update successful");
        }else{
            System.out.println("Update failed");
        }
    }

    public static ObservableList<Appointment> populateTableItems(LocalDate date) throws SQLException {

        ObservableList<Appointment> apps = FXCollections.observableArrayList();
        LocalDate rsDate;

        DBConnection.makePreparedStatement("SELECT * FROM appointments ",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        if(AppointmentsFormController.getCurrentRadioButton().compareTo("Monthly") == 0){//is monthly radiobutton selected

            while (rs.next()){

                rsDate = rs.getTimestamp("Start").toLocalDateTime().toLocalDate();
                if(rsDate.getMonthValue() == date.getMonthValue()) {//does month match
                    Appointment ap = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"),
                            rs.getString("Location"), rs.getString("Type"),
                            rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(),
                            rs.getTimestamp("Create_Date").toLocalDateTime(), rs.getString("Created_By"),
                            rs.getTimestamp("Last_Update"), rs.getString("Last_Update"),
                            rs.getInt("Customer_ID"), rs.getInt("User_ID"),
                            rs.getInt("Contact_ID"));

                    apps.add(ap);
                }
            }
        }else if(AppointmentsFormController.getCurrentRadioButton().compareTo("Weekly") == 0){//is weekly radiobutton selected

            DateTimeFormatter weekNumFormatter = DateTimeFormatter.ofPattern("w");
            while(rs.next()){

                rsDate = rs.getTimestamp("Start").toLocalDateTime().toLocalDate();
                if(rsDate.format(weekNumFormatter).compareTo(date.format(weekNumFormatter)) == 0) {//does week match
                    Appointment ap = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"),
                            rs.getString("Location"), rs.getString("Type"),
                            rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(),
                            rs.getTimestamp("Create_Date").toLocalDateTime(), rs.getString("Created_By"),
                            rs.getTimestamp("Last_Update"), rs.getString("Last_Update"),
                            rs.getInt("Customer_ID"), rs.getInt("User_ID"),
                            rs.getInt("Contact_ID"));

                    apps.add(ap);
                }

            }
        }else{

            apps.addAll(getAllAppointments());
        }

        return apps;

    }


}
