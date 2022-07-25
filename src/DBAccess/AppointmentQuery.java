package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.*;

public class AppointmentQuery {

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
            ap.setStart(rs.getDate("Start"));
            ap.setEnd(rs.getDate("End"));

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
            ap.setStart(rs.getDate("Start"));
            ap.setEnd(rs.getDate("End"));

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
                                                rs.getString("Location"),rs.getString("Type"),rs.getDate("Start"),
                                                    rs.getDate("End"),rs.getDate("Create_Date"),rs.getString("Created_By"),
                                                        rs.getTimestamp("Last_Update"),rs.getString("Last_Update"),rs.getInt("Customer_ID"),
                                                            rs.getInt("User_ID"),rs.getInt("Contact_ID"));

            apps.add(ap);
        }

        return apps;
    }


    public static void addAppointment(String title,String description,String location,String type,Date start,Date end,
                                      Date createDate,String createdBy,Timestamp lastUpdate,String lastUpdatedBy,
                                        int customerID, int userID,int contactID) throws SQLException {

        DBConnection.makePreparedStatement("INSERT INTO appointments (Title,Description,Location," +
                "Type,Start,End,Create_Date,Created_By,Last_Update,Last_Updated_By,Customer_ID,User_ID,Contact_ID)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setString(1,title);
        ps.setString(2,description);
        ps.setString(3,location);
        ps.setString(4,type);
        ps.setDate(5,start);
        ps.setDate(6,end);
        ps.setDate(7,createDate);
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

    public static void updateAppointment(String title,String description,String location,String type,Date start,Date end,
                                            Timestamp lastUpdate,String lastUpdatedBy,int customerID, int userID,int contactID) throws SQLException {

        DBConnection.makePreparedStatement("INSERT INTO appointments (Title,Description,Location," +
                "Type,Start,End,Last_Update,Last_Updated_By,Customer_ID,User_ID,Contact_ID)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?)",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setString(1,title);
        ps.setString(2,description);
        ps.setString(3,location);
        ps.setString(4,type);
        ps.setDate(5,start);
        ps.setDate(6,end);
        ps.setTimestamp(7,lastUpdate);
        ps.setString(8,lastUpdatedBy);
        ps.setInt(9,customerID);
        ps.setInt(10,userID);
        ps.setInt(11,contactID);

        int rows = ps.executeUpdate();
        if(rows > 0){
            System.out.println("Update successful");
        }else{
            System.out.println("Update failed");
        }
    }

    public static void getAppointment(){

    }
}
