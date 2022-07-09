package DBAccess;

import Database.DBConnection;

import java.sql.PreparedStatement;
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

    public static void getCustomer(){

    }







}
