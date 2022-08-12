package model;

/**
 * This class is the model for Contact.
 */
public class Contact {

    private int conID;
    private String contactName;
    private String email;

    /** Constructor Contact
     * @param contactID contactID
     * @param contactName contactName
     * @param email email
     */
    public Contact(int contactID, String contactName, String email){

        this.conID = conID;
        this.contactName = contactName;
        this.email = email;
    }

    /** This method gets ID
     * @return ID
     */
    public int getID(){
        return conID;
    }

    /** This method sets id
     * @param id id
     */
    public void setID(int id){
        this.conID = id;
    }

    /** This method gets contactName
     * @return contactName
     */
    public String getContactName(){
        return contactName;
    }

    /** This method sets contactName
     * @param contactName contactName
     */
    public void setContactName(String contactName){
        this.contactName = contactName;
    }

    /** This method gets email
     * @return email
     */
    public String getEmail(){
        return email;
    }

    /** This method sets email
     * @param email email
     */
    public void setEmail(String email){
        this.email = email;
    }


}
