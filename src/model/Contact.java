package model;

public class Contact {

    private int id;
    private String contactName;
    private String email;

    public Contact(int contactID, String contactName, String email){

        this.id = id;
        this.contactName = contactName;
        this.email = email;
    }

    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id = id;
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
