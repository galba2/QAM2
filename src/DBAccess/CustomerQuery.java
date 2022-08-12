package DBAccess;

import Database.DBConnection;
import controller.PopUpFormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;


/**
 * This class connects with the database by customer type.
 */
public class CustomerQuery {

    int customerID;

    /** This method deletes a customer.
     * @param id This is the customer id to be deleted.
     * @throws SQLException Throw SQLException.
     */
    public static void deleteCustomer(int id) throws SQLException {

        //get customer info for popup
        int customerID = 0;
        String customerName = "";
        DBConnection.makePreparedStatement("SELECT Customer_ID, Customer_Name FROM customers WHERE Customer_ID = ?",DBConnection.getConnection());
        PreparedStatement psPopUp = DBConnection.getPreparedStatement();
        psPopUp.setInt(1,id);
        ResultSet rsPopUp = psPopUp.executeQuery();
        while(rsPopUp.next()){
            customerID = rsPopUp.getInt("Customer_ID");
            customerName = rsPopUp.getString("Customer_Name");
        }

        //delete customer of a certain ID
        DBConnection.makePreparedStatement("DELETE FROM customers WHERE Customer_ID = ?",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ps.setInt(1,id);
        int rows = ps.executeUpdate();
        if(rows > 0){
            System.out.println("Delete successful");
        }else{
            System.out.println("Delete failed");
        }

        //show confirmation popup
        try {
            PopUpFormController.setUpPopUp("ALERT!", "CUSTOMER DELETED\n Customer ID: " + customerID + "\n" + " Name: " + customerName);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Stage popUpStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(AppointmentQuery.class.getResource("/view/PopUpForm.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        popUpStage.setScene(new Scene(root, 400, 300));
        popUpStage.show();
        popUpStage.setAlwaysOnTop(true);
    }

    /** This method adds a customer.
     * @param cusName This is the name.
     * @param cusAddress This is the address.
     * @param cusPostal This is the postal.
     * @param cusPhone This is the phone.
     * @param cusCreaDate This is the create date.
     * @param cusCreaBy This is the create by.
     * @param cusLastUpdate This is the last update.
     * @param cusLastUpdateBy This is the last updated by.
     * @param divID This is the division id.
     * @throws SQLException Throws SQLException.
     */
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

    /** This method updates a customer.
     * @param name This is the name.
     * @param Address This is the address.
     * @param postal This is the postal.
     * @param phone This is the phone.
     * @param cusLastUpdate This is the cusLastUpdate.
     * @param cusLastUpdatedBy This is the cusLastUpdatedBy.
     * @param divID This is the divID.
     * @param updateID This is the updateID.
     * @throws SQLException Throws SQLException.
     */
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

    /** This method gets a list of all cutomers.
     * @return Returns a list of all customers.
     * @throws SQLException Throws SQLException.
     */
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

    /** This method gets a list of country id from a division id
     * @param divisionID This is division id to match.
     * @return Returns a list of country id.
     * @throws SQLException Throws SQLException.
     */
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


    /** This method gets a list of countries.
     * @return Returns a list of countries.
     * @throws SQLException Throws SQLException.
     */
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


    /** This method gets a list customer division bu country list.
     * @param countryID This the country id to match.
     * @return Return a list of customer division.
     * @throws SQLException Throws SQLException.
     */
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

    /** This method gets a list of country id by country.
     * @param country This is the country to match.
     * @return Returns a list of country ids.
     * @throws SQLException Throws SQLException.
     */
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

    /** This method gets a list of countries by division id.
     * @param divID This is the division id to match.
     * @return Returns a list of countries.
     * @throws SQLException Throws SQLException.
     */
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


    /** This method gets a list of division id by division.
     * @param div This is the division to match.
     * @return Returns a list of division id.
     * @throws SQLException Throws SQLException.
     */
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

    /** This method gets a list of division by division id.
     * @param divID This is the division id to match.
     * @return Returns a list of divisions.
     * @throws SQLException Throws SQLException.
     */
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

    /** This method gets a list of all customer ids.
     * @return Returns a list of all customer ids.
     * @throws SQLException Throws SQLException.
     */
    public static ObservableList<String> getAllCustomerIDs() throws SQLException {

        ObservableList<String> customerIDs = FXCollections.observableArrayList();

        DBConnection.makePreparedStatement("SELECT Customer_ID FROM customers ORDER BY Customer_ID",DBConnection.getConnection());
        PreparedStatement ps = DBConnection.getPreparedStatement();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            customerIDs.add(String.valueOf(rs.getInt("Customer_ID")));
        }

        return customerIDs;
    }

}
