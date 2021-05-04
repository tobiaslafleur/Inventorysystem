package Model;

public class Supplier {
    private String name;
    private String phone;
    private String address;
    private String email;
    private int supplierID;

    public Supplier(String name, String phone, String address, String email) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }
    public Supplier( String name, String phone, String address, String email, int id) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.supplierID = id;
    }

    public String toString() {
        return String.format("name: %s | phone: %s | address: %s | email: %s  | ID: %s", name, phone, address, email, supplierID);
    }
    //<editor-fold desc="getters and setters"
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return supplierID;
    }

    public void setId(int id) {
        this.supplierID= id;
    }
    //</editor-fold>
}
