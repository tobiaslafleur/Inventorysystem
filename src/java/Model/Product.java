package Model;

import java.math.BigDecimal;

public class Product {
    private int productID;
    private String name;
    private int stock;
    private BigDecimal price;
    private String category;
    private String shelfPosition;
    private String supplier;
    private int supplierID;
    private BigDecimal cost;
    private int userID;



    public Product(int productID, String name, int stock, BigDecimal price, String category, String shelfPosition, String supplier, int supplierID, BigDecimal cost, int userID) {
        this.productID = productID;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.category = category;
        this.shelfPosition = shelfPosition;
        this.supplier = supplier;
        this.supplierID = supplierID;
        this.cost = cost;
        this.userID = userID;
    }
    public Product(int productID, String name, int stock, BigDecimal price, String category, String shelfPosition, String supplier, BigDecimal cost, int userID) {
        this.productID = productID;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.category = category;
        this.shelfPosition = shelfPosition;
        this.supplier = supplier;
        this.cost = cost;
        this.userID = userID;
    }

    public String toString() {
        return String.format("name: %s | stock: %s | price: %s SEK | category: %s | shelf position: %s | supplier: %s | cost: %s SEK | userID: %s ", name, stock, price, category, shelfPosition, supplier, cost, userID);
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShelfPosition() {
        return shelfPosition;
    }

    public void setShelfPosition(String shelfPosition) {
        this.shelfPosition = shelfPosition;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }
    //</editor-fold>
}
