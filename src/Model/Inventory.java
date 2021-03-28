package Model;

import java.util.Vector;

public class Inventory {

    private int id;
    private Vector<Product> productList;

    public Inventory(int id, Vector<Product> productList) {
        this.id = id;
        this.productList = productList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector<Product> getProductList() {
        return productList;
    }

    public void setProductList(Vector<Product> productList) {
        this.productList = productList;
    }
}
