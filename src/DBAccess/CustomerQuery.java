package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerQuery {

    int customerID;

    public static void deleteCustomer(int id) throws SQLException {

        DBConnection.makePreparedStatement("DELETE FROM customers WHERE Customer_ID = ?",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setInt(1,id);
        int rows = ps.executeUpdate();
        if(rows > 0){
            System.out.println("Delete successful");
        }else{
            System.out.println("Delete failed");
        }

    }

    public static void addCustomer(String cusName,String cusAddress,int cusPostal,String cusPhone,
                                    String cusCreaDate,String cusCreaBy,String cusLastUpdate,
                                     String cusLastUpdateBy,int divID) throws SQLException {

        DBConnection.makePreparedStatement("INSERT INTO customers (Customer_Name,Address,Postal_Code," +
                                            "Phone,Create_Date,Created_By,Last_Update,Last_Updated_By,Division_ID)" +
                                                " VALUES (?,?,?,?,?,?,?,?,?)",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setString(1,cusName);
        ps.setString(2,cusAddress);
        ps.setInt(3,cusPostal);
        ps.setString(4,cusPhone);
        ps.setString(5,cusCreaDate);
        ps.setString(6,cusCreaBy);
        ps.setString(7,cusLastUpdate);
        ps.setString(8,cusLastUpdateBy);
        ps.setInt(9,divID);

        int rows = ps.executeUpdate();
        if(rows > 0){
            System.out.println("Delete successful");
        }else{
            System.out.println("Delete failed");
        }


    }


    public static ObservableList getAllCustomers() throws SQLException {

        ObservableList<Customer> cus = FXCollections.observableArrayList();

        DBConnection.makePreparedStatement("SELECT * FROM customers",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            Customer c = new Customer(rs.getInt("Customer_ID"),rs.getString("Customer_Name"),rs.getString("Address"),rs.getString("Postal_Code"),rs.getString("Phone"),rs.getDate("Create_Date"),rs.getString("Created_By"),rs.getTimestamp("Last_Update"),rs.getString("Last_Updated_By"),rs.getInt("Division_ID"));
            cus.add(c);
        }

        return cus;
    }

    public static void getCustomer(){

    }


    public static int getCountryIDFromDivID(int divisionID) throws SQLException {

        int cID = 0;

        DBConnection.makePreparedStatement("SELECT Country_ID FROM first_level_divisions WHERE Division_ID = ?",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setInt(1, divisionID);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            cID = rs.getInt("Country_ID");
        }

        return cID;
    }


    public static ObservableList<String> getCustomerCountryList() throws SQLException {

        ObservableList<String> ctry = FXCollections.observableArrayList();

        DBConnection.makePreparedStatement("SELECT Country FROM countries",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            ctry.add(rs.getString("Country"));
        }

        return ctry;
    }


    public static ObservableList<String> getCustomerDivByCounIDList(int countryID) throws SQLException {

        ObservableList<String> sts = FXCollections.observableArrayList();

        DBConnection.makePreparedStatement("SELECT Division FROM first_level_divisions WHERE Country_ID = ?",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setInt(1, countryID);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            sts.add(rs.getString("Division"));
        }

        return sts;
    }

    public static int getCounIDByCoun(String country) throws SQLException {

        int counID = 1;//Default to U.S country

        DBConnection.makePreparedStatement("SELECT Country_ID FROM countries WHERE Country = ?",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setString(1,  country);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            counID = rs.getInt("Country_ID");
        }

        return counID;
    }
}
