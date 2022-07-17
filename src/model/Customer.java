package model;

import DBAccess.CustomerQuery;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Customer {

    private int cusID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;
    private int countryID;


    public Customer(int cusID, String customerName, String address, String postalCode, String phone,
                    Date createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int divisionID) throws SQLException {

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

    public int getCusID(){
        return cusID;
    }

    public void setCusID(int id){
        this.cusID = id;
    }

    public String getCustomerName(){
        return customerName;
    }

    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    public String getPostalCode(){
        return postalCode;
    }

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public Date getCreateDate(){
        return createDate;
    }

    public void setCreateDate(Date createDate){
        this.createDate =createDate ;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getCreatedBy(){
        return createdBy;
    }

    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }

    public Timestamp getLastUpdate(){
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate){
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy(){
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy){
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getDivisionID(){
        return divisionID;
    }

    public void setDivisionID(int divisionID) throws SQLException {
        this.divisionID = divisionID;
        this.countryID = CustomerQuery.getCountryIDFromDivID(divisionID);
    }

    public int getCountryID() {
        return countryID;
    }


}
