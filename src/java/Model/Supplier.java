package Model;

/**
 * The supplier-object.
 */
public class Supplier {
    private String name;
    private String phone;
    private String street;
    private String city;
    private String country;
    private String email;
    private int supplierID;

    public Supplier(String name, String phone, String street, String city, String country, String email) {
        this.name = name;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.country = country;
        this.email = email;
    }
    public Supplier(String name, String phone, String street, String city, String country, String email, int id) {
        this.name = name;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.country = country;
        this.email = email;
        this.supplierID = id;
    }

    public String toString() {
        return String.format("%s | %s | %s | %s ", name, phone, street, email);
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
