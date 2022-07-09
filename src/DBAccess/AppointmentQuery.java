package DBAccess;

import Database.DBConnection;

import java.sql.PreparedStatement;
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

    public static void addAppointment(){

    }

    public static void getAppointment(){

    }
}
