package Model;

import java.math.BigDecimal;

public class Product {
    private String name;
    private int stock;
    private BigDecimal price;
    private int categoryID;
    private int shelfID;
    private int supplierID;
    private BigDecimal cost;
    private int userID;

    public Product(String name, int stock, BigDecimal price, int categoryID, int shelfID, int supplierID, BigDecimal cost, int userID) {
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.categoryID = categoryID;
        this.shelfID = shelfID;
        this.supplierID = supplierID;
        this.cost = cost;
        this.userID = userID;
    }

    public String toString() {
        return String.format("name: %s | stock: %s | price: %s SEK | categoryID: %s | shelfID: %s | supplierID: %s | cost: %s SEK | userID: %s ", name, stock, price, categoryID, shelfID, supplierID, cost, userID);
    }
    //<editor-fold desc="getters and setters"
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getShelfID() {
        return shelfID;
    }

    public void setShelfID(int shelfID) {
        this.shelfID = shelfID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    //</editor-fold>
}
