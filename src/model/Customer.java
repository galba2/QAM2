package model;

import DBAccess.CustomerQuery;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This class is the model for Customer.
 */
public class Customer {

    private int cusID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;
    private int countryID;


    /** Constructor Customer
     * @param cusID  cusID
     * @param customerName customerName
     * @param address address
     * @param postalCode postalCode
     * @param phone phone
     * @param createDate createDate
     * @param createdBy createdBy
     * @param lastUpdate lastUpdate
     * @param lastUpdatedBy lastUpdatedBy
     * @param divisionID divisionID
     * @throws SQLException throws SQLException
     */
    public Customer(int cusID, String customerName, String address, String postalCode, String phone,
                    LocalDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int divisionID) throws SQLException {

        this.cusID = cusID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
        this.countryID = CustomerQuery.getCountryIDFromDivID(divisionID);
    }

    /** This method gets id
     * @return cusID
     */
    public int getCusID(){
        return cusID;
    }

    /** This method sets id
     * @param id
     */
    public void setCusID(int id){
        this.cusID = id;
    }

    /** This method gets customerName
     * @return customerName
     */
    public String getCustomerName(){
        return customerName;
    }

    /**This method sets customerName
     * @param customerName
     */
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    /** This method gets id
     * @return postalCode
     */
    public String getPostalCode(){
        return postalCode;
    }

    /**This method sets postalCode
     * @param postalCode
     */
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    /** This method gets phone
     * @return phone
     */
    public String getPhone(){
        return phone;
    }

    /**This method sets phone
     * @param phone
     */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /** This method gets createDate
     * @return createDate
     */
    public LocalDateTime getCreateDate(){
        return createDate;
    }

    /**This method sets createDate
     * @param createDate
     */
    public void setCreateDate(LocalDateTime createDate){
        this.createDate =createDate ;
    }

    /** This method gets address
     * @return address
     */
    public String getAddress(){
        return address;
    }

    /**This method sets address
     * @param address
     */
    public void setAddress(String address){
        this.address = address;
    }

    /** This method gets createdBy
     * @return createdBy
     */
    public String getCreatedBy(){
        return createdBy;
    }

    /**This method sets createdBy
     * @param createdBy
     */
    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }

    /** This method gets lastUpdate
     * @return lastUpdate
     */
    public Timestamp getLastUpdate(){
        return lastUpdate;
    }

    /**This method sets lastUpdate
     * @param lastUpdate
     */
    public void setLastUpdate(Timestamp lastUpdate){
        this.lastUpdate = lastUpdate;
    }

    /** This method gets lastUpdatedBy
     * @return lastUpdatedBy
     */
    public String getLastUpdatedBy(){
        return lastUpdatedBy;
    }

    /**This method sets lastUpdatedBy
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /** This method gets divisionID
     * @return divisionID
     */
    public int getDivisionID(){
        return divisionID;
    }

    /**This method sets divisionID
     * @param divisionID
     * @throws SQLException
     */
    public void setDivisionID(int divisionID) throws SQLException {
        this.divisionID = divisionID;
        this.countryID = CustomerQuery.getCountryIDFromDivID(divisionID);
    }

    /** This method gets countryID
     * @return countryID
     */
    public int getCountryID() {
        return countryID;
    }


}
