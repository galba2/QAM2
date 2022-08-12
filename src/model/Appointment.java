package model;

import DBAccess.AppointmentQuery;
import DBAccess.ContactQuery;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This class is the model for appointment.
 */
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


    /**
     * This is constructer for a null appointment.
     */
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

    /** This is constructor for appointment.
     * @param id This is id.
     * @param title This is title.
     * @param description This is description.
     * @param location This is location.
     * @param type This is type.
     * @param start This is start.
     * @param end This is end.
     * @param createDate This is createDate.
     * @param createdBy This is createdBy.
     * @param lastUpdate This is lastUpdate.
     * @param lastUpdatedBy This is lastUpdatedBy.
     * @param customerID This is customerID.
     * @param userID This is userID.
     * @param contactID This is contactID.
     * @throws SQLException Throws SQLException.
     */
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

    /** This method gets apptID.
     * @return Returns apptID.
     */
    public Integer getApptID(){
        return apptID;
    }

    /** This method sets apptID.
     * @param id This is id to set.
     */
    public void setApptID(Integer id){
        this.apptID = id;
    }

    /** This method gets Title.
     * @return Returns Title.
     */
    public String getTitle(){
        return title;
    }

    /** This method sets title.
     * @param title
     */
    public void setTitle(String title){
        this.title = title;
    }

    /** This method gets Description.
     * @return Returns Description.
     */
    public String getDescription(){
        return description;
    }

    /** This method sets description.
     * @param description
     */
    public void setDescription(String description){
        this.description = description;
    }

    /** This method gets Location.
     * @return Returns Location.
     */
    public String getLocation(){
        return location;
    }

    /** This method sets location.
     * @param location
     */
    public void setLocation(String location){
        this.location = location;
    }

    /** This method gets Type.
     * @return Returns Type.
     */
    public String getType(){
        return type;
    }

    /** This method sets type.
     * @param type
     */
    public void setType(String type){
        this.type = type;
    }

    /** This method gets Start.
     * @return Returns Start.
     */
    public LocalDateTime getStart(){
        return start;
    }

    /** This method sets start.
     * @param start
     */
    public void setStart(LocalDateTime start){
        this.start = start;
    }

    /** This method gets End.
     * @return Returns End.
     */
    public LocalDateTime getEnd(){
        return end;
    }

    /** This method sets end.
     * @param end
     */
    public void setEnd(LocalDateTime end){
        this.end = end;
    }

    /** This method gets CreateDate.
     * @return Returns CreateDate.
     */
    public LocalDateTime getCreateDate(){
        return createDate;
    }

    /** This method sets createDate.
     * @param createDate
     */
    public void setCreate(LocalDateTime createDate){
        this.createDate = createDate;
    }

    /** This method gets CreatedBy.
     * @return Returns CreatedBy.
     */
    public String getCreatedBy(){
        return createdBy;
    }

    /**This method sets createdBy.
     * @param createdBy
     */
    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }

    /** This method gets LastUpdate.
     * @return Returns LastUpdate.
     */
    public Timestamp getLastUpdate(){
        return lastUpdate;
    }

    /**This method sets lastUpdate.
     * @param lastUpdate
     */
    public void setLastUpdate(Timestamp lastUpdate){
        this.lastUpdate = lastUpdate;
    }

    /** This method gets LastUpdatedBy.
     * @return Returns LastUpdatedBy.
     */
    public String getLastUpdatedBy(){
        return lastUpdatedBy;
    }

    /** This method sets lastUpdatedBy.
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /** This method gets CustomerID.
     * @return Returns CustoxzmerID.
     */
    public int getCustomerID(){
        return customerID;
    }

    /** This method sets customerID.
     * @param customerID
     */
    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }

    /** This method gets UserID.
     * @return Returns UserID.
     */
    public int getUserID(){
        return userID;
    }

    /** This method sets apptID.
     * @param userID
     */
    public void setUserID(int userID){
        this.userID = userID;
    }

    /** This method gets ContactID.
     * @return Returns ContactID.
     */
    public int getContactID(){
        return contactID;
    }

    /** This method sets contactID.
     * @param contactID
     */
    public void setContactID(int contactID){
        this.contactID = contactID;
    }

    /** This method gets Contact.
     * @return Returns Contact.
     */
    public String getContact() {
        return contact;
    }

    /**This method sets Contact.
     * @param contactID
     * @throws SQLException
     */
    public void setContact(int contactID) throws SQLException {
        this.contact = ContactQuery.getContactByContactID(contactID);
    }
}
