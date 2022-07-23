package DBAccess;

import Database.DBConnection;
import controller.ContactReportFormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ContactQuery {

    public static ObservableList<String> getAllContactsList() throws SQLException {

        ObservableList<String > conRptLis = FXCollections.observableArrayList();

        DBConnection.makePreparedStatement("SELECT Contact_ID, Contact_Name FROM contacts ORDER BY Contact_ID ASC",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ResultSet rs = ps.executeQuery();


        while(rs.next()){

            conRptLis.add(rs.getString("Contact_Name") + " (" + rs.getInt("Contact_ID") + ")");
        }


        return conRptLis;


    }

    public static void addContact(String contactName, String email) throws SQLException {

        DBConnection.makePreparedStatement("INSERT INTO contacts (Contact_Name,Email) VALUE (?,?)",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setString(1,contactName);
        ps.setString(2,email);
        ps.executeUpdate();

    }

    public static String getContactByContactID(int contactID) throws SQLException {

        String contact = "";

        DBConnection.makePreparedStatement("SELECT Contact_Name FROM contacts WHERE Contact_ID = ?", DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setInt(1, contactID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            contact = rs.getString("Contact_Name");
        }

        return contact;

    }

}
