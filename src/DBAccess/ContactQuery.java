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


    public static int getContactIDByContact(String contact) throws SQLException {

        int contactID = 0;

        DBConnection.makePreparedStatement("SELECT Contact_ID FROM contacts WHERE Contact_Name = ?", DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setString (1, contact);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            contactID = rs.getInt("Contact_ID");
        }

        return contactID;

    }


    public static int getContactIndexFromContact(String contact) throws SQLException {

        ObservableList<String > contactList = getAllContactsList();
        String onlyName;
        int contactIndex = -1;

        for(int i = 0; i < contactList.size(); i++){
            onlyName = contactList.get(i).substring(0,contactList.get(i).indexOf('(') - 1);//get substring before the '(' character
            if(contact.compareTo(onlyName) == 0){
                contactIndex = i;
                return contactIndex;
            }
        }

        return contactIndex;

    }

}
