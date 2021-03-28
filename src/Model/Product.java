package Model;

public class Product {

    private int id;
    private String name;
    private int quantity;
    private String place;

    public Product(int id, String name, int quantity, String place) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
