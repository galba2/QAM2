package DBAccess;

import Database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ContactQuery {

    public static void getAllContacts() throws SQLException {

        String ret = "";

        DBConnection.makePreparedStatement("SELECT * FROM contacts",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            String r = rs.getString("Contact_Name");
            System.out.println(r);

        }


    }

    public static void addContact(String contactName, String email) throws SQLException {

        DBConnection.makePreparedStatement("INSERT INTO contacts (Contact_Name,Email) VALUE (?,?)",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setString(1,contactName);
        ps.setString(2,email);
        ps.executeUpdate();

    }



}
