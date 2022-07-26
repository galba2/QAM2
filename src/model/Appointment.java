package model;

import DBAccess.AppointmentQuery;
import DBAccess.ContactQuery;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Appointment {

    private int apptID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;
    private String contact;

    public Appointment() {
        this.apptID = 0;
        this.title = null;
        this.description = null;
        this.location = null;
        this.type = null;
        this.start = null;
        this.end = null;
        this.createDate = null;
        this.createdBy = null;
        this.lastUpdate = null;
        this.lastUpdatedBy = null;
        this.customerID = 0;
        this.userID = 0;
        this.contactID = 0;
        this.contact = null;
    }

    public Appointment(Integer id, String title, String description, String location, String type,
                       LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy, Timestamp lastUpdate,
                       String lastUpdatedBy, int customerID, int userID, int contactID) throws SQLException {

        this.apptID = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        this.contact = ContactQuery.getContactByContactID(this.contactID);

    }

    public Integer getApptID(){
        return apptID;
    }

    public void setApptID(Integer id){
        this.apptID = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public LocalDateTime getStart(){
        return start;
    }

    public void setStart(LocalDateTime start){
        this.start = start;
    }

    public LocalDateTime getEnd(){
        return end;
    }

    public void setEnd(LocalDateTime end){
        this.end = end;
    }

    public LocalDateTime getCreateDate(){
        return createDate;
    }

    public void setCreate(LocalDateTime createDate){
        this.createDate = createDate;
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

    public int getCustomerID(){
        return customerID;
    }

    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }

    public int getUserID(){
        return userID;
    }

    public void setUserID(int userID){
        this.userID = userID;
    }

    public int getContactID(){
        return contactID;
    }

    public void setContactID(int contactID){
        this.contactID = contactID;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(int contactID) throws SQLException {
        this.contact = ContactQuery.getContactByContactID(contactID);
    }
}
