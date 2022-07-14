package model;

public class Contact {

    private int conID;
    private String contactName;
    private String email;

    public Contact(int contactID, String contactName, String email){

        this.conID = conID;
        this.contactName = contactName;
        this.email = email;
    }

    public int getID(){
        return conID;
    }

    public void setID(int id){
        this.conID = id;
    }

    public String getContactName(){
        return contactName;
    }

    public void setContactName(String contactName){
        this.contactName = contactName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }


}
