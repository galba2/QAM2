package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static void addAppointment(){

    }

    public static void getAppointment(){

    }
}
