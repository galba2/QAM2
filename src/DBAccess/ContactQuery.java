package DBAccess;

import Database.DBConnection;
import controller.ContactReportFormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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



}
