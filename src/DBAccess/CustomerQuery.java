package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;

import java.sql.*;
import java.time.LocalDateTime;

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

    public static void addCustomer(String cusName, String cusAddress, String cusPostal, String cusPhone,
                                   Timestamp cusCreaDate, String cusCreaBy, Timestamp cusLastUpdate,
                                   String cusLastUpdateBy, int divID) throws SQLException {

        DBConnection.makePreparedStatement("INSERT INTO customers (Customer_Name,Address,Postal_Code," +
                                            "Phone,Create_Date,Created_By,Last_Update,Last_Updated_By,Division_ID)" +
                                                " VALUES (?,?,?,?,?,?,?,?,?)",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setString(1,cusName);
        ps.setString(2,cusAddress);
        ps.setString(3,cusPostal);
        ps.setString(4,cusPhone);
        ps.setTimestamp(5,cusCreaDate);
        ps.setString(6,cusCreaBy);
        ps.setTimestamp(7,cusLastUpdate);
        ps.setString(8,cusLastUpdateBy);
        ps.setInt(9,divID);

        int rows = ps.executeUpdate();
        if(rows > 0){
            System.out.println("Add successful");
        }else{
            System.out.println("Add failed");
        }
    }

    public static void updateCustomer(String name, String Address, String postal, String phone,
                                      Timestamp cusLastUpdate, String cusLastUpdatedBy, int divID, int updateID) throws SQLException {

        DBConnection.makePreparedStatement("UPDATE customers SET Customer_Name = ?,  Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?,Division_ID = ? WHERE Customer_ID = ?",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setString(1,name);
        ps.setString(2,Address);
        ps.setString(3,postal);
        ps.setString(4,phone);
        ps.setTimestamp(5,cusLastUpdate);
        ps.setString(6,cusLastUpdatedBy);
        ps.setInt(7,divID);
        ps.setInt(8,updateID);
        int rows = ps.executeUpdate();

        if(rows > 0){
            System.out.println("Update successful");
        }else{
            System.out.println("Update failed");
        }

    }

    public static ObservableList getAllCustomers() throws SQLException {

        ObservableList<Customer> cus = FXCollections.observableArrayList();

        DBConnection.makePreparedStatement("SELECT * FROM customers",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            Customer c = new Customer(rs.getInt("Customer_ID"),rs.getString("Customer_Name"),rs.getString("Address"),rs.getString("Postal_Code"),rs.getString("Phone"),rs.getTimestamp("Create_Date").toLocalDateTime(),rs.getString("Created_By"),rs.getTimestamp("Last_Update"),rs.getString("Last_Updated_By"),rs.getInt("Division_ID"));
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

    public static String getCounByDivID(int divID) throws SQLException {

        String coun = "";

        DBConnection.makePreparedStatement("SELECT Country FROM countries WHERE Country_ID = ?",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setInt(1,  getCountryIDFromDivID(divID));
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            coun = rs.getString("Country");
        }

        return coun;
    }


    public static int getDivIDByDiv(String div) throws SQLException {

        int divID = 1;//Default to U.S country

        DBConnection.makePreparedStatement("SELECT Division_ID FROM first_level_divisions WHERE Division = ?",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setString(1,  div);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            divID = rs.getInt("Division_ID");
        }

        return divID;
    }

    public static String getDivByDivID(int divID) throws SQLException {

        String div = "";

        DBConnection.makePreparedStatement("SELECT Division FROM first_level_divisions WHERE Division_ID = ?",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setInt(1,  divID);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            div = rs.getString("Division");
        }

        return div;
    }

    public static ObservableList<Integer> getAllCustomerIDs() throws SQLException {

        ObservableList<Integer> customerIDs = FXCollections.observableArrayList();

        DBConnection.makePreparedStatement("SELECT Customer_ID FROM customers ORDER BY Customer_ID",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            customerIDs.add(rs.getInt("Customer_ID"));
        }

        return customerIDs;
    }

}
