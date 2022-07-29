package DBAccess;

import Database.DBConnection;
import controller.AppointmentsFormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.Appointment;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                                            Timestamp lastUpdate,String lastUpdatedBy,int customerID, int userID,int contactID) throws SQLException {

        DBConnection.makePreparedStatement("INSERT INTO appointments (Title,Description,Location," +
                "Type,Start,End,Last_Update,Last_Updated_By,Customer_ID,User_ID,Contact_ID)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?)",DBConnection.getConnection());
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

        while(rs.next()){

            rsDate = rs.getTimestamp("Start").toLocalDateTime().toLocalDate();
            System.out.println("LocalDate-"+ date.getMonthValue());
            System.out.println("QueryDate-"+ rsDate.getMonthValue());
            if(AppointmentsFormController.getaRMonthlyRadio().isSelected()){//is monthly radiobutton selected
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
            }else if(AppointmentsFormController.getaRWeeklyRadio().isSelected()){//is weekly radiobutton selected
                    DateTimeFormatter weekNumFormatter = DateTimeFormatter.ofPattern("w");

                    if(rsDate.format(weekNumFormatter) == date.format(weekNumFormatter)) {//does week match
                        Appointment ap = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"),
                                rs.getString("Location"), rs.getString("Type"),
                                rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(),
                                rs.getTimestamp("Create_Date").toLocalDateTime(), rs.getString("Created_By"),
                                rs.getTimestamp("Last_Update"), rs.getString("Last_Update"),
                                rs.getInt("Customer_ID"), rs.getInt("User_ID"),
                                rs.getInt("Contact_ID"));

                        apps.add(ap);
                    }
            }else{

                    apps.addAll(getAllAppointments());
                }

        }

        return apps;

    }

    public static void getAppointment(){

    }
}
