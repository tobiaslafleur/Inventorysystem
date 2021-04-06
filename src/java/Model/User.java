package Model;

import java.util.logging.StreamHandler;

public class User {
    private String username;
    private String password;
    private int userID;
    private String emailAddress;
    private String phoneNr;
    private String address;



    public User(String username, String password, int userID) {
        this.username = username;
        this.password = password;
        this.userID = userID;
    }
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    public User(String username, String password, String emailAddress, String phoneNr, String address) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.phoneNr = phoneNr;
        this.address = address;
    }

    public String toString(){
        return String.format("username: %s | password: %s | userID: %s | email: %s | phone: %s | address: %s |", username, password, userID, emailAddress, phoneNr, address);
    }

    //<editor-fold desc="getters and setters"
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
//</editor-fold>

}
