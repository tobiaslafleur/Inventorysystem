package Model;

public class User {
    private String username;
    private String password;
    private int userID;
    private String emailAddress;
    private String phone;
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
    public User(String username, String password, String emailAddress, String phone, String address) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.phone = phone;
        this.address = address;
    }

    public User(int userID, String username, String password, String emailAddress, String phone, String address) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.phone = phone;
        this.address = address;
    }

    public String toString(){
        return String.format("username: %s | password: %s | userID: %s | email: %s | phone: %s | address: %s |", username, password, userID, emailAddress, phone, address);
    }

    //<editor-fold desc="getters and setters"
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
