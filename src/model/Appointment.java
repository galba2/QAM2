package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Appointment {

    private int apptID;
    private String title;
    private String description;
    private String location;
    private String type;
    private Date start;
    private Date end;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;


    public Appointment(){

        this.apptID = -1;
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
        this.customerID = -1;
        this.userID = -1;
        this.contactID = -1;

    }


    public Appointment(Integer id,String title,String description,String location,String type,
                       Date start,Date end,Date createDate,String createdBy,Timestamp lastUpdate,
                        String lastUpdatedBy,int customerID,int userID,int contactID){

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

    public Date getStart(){
        return start;
    }

    public void setStart(Date start){
        this.start = start;
    }

    public Date getEnd(){
        return end;
    }

    public void setEnd(Date end){
        this.end = end;
    }

    public Date getCreateDate(){
        return createDate;
    }

    public void setCreate(Date createDate){
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
}
