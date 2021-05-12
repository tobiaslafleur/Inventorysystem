package Controller;

import Model.Category;
import Model.Product;
import Model.Supplier;
import Model.User;
import View.GUIFacilitator;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Controller {
    private DBController dbController;
    private GUIFacilitator facilitator;
    private User user;

    public Controller(){
        dbController = new DBController(this);
    }

    public boolean createUser(String username, String password, String email, String phone, String address) {
        return dbController.createUser(username, password, email, phone, address);
    }
    public boolean createCategory(String name) {
        return dbController.createCategory(name);
    }

    public void setFacilitator(GUIFacilitator facilitator) {
        this.facilitator = facilitator;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean checkUser(String username, String password) {
        return dbController.checkUser(username, password);
    }

    public boolean addSupplier(String supName, String supPhone, String supAddress, String supEmail) {
        Supplier supplier = new Supplier(supName, supPhone, supAddress, supEmail);
        return dbController.addSupplier(supplier);
    }

    public boolean addProduct(String name, String stock, String price, String categoryID, String shelfPosition, String supplierID, String cost) {
        return dbController.addProduct(name, Integer.parseInt(stock), new BigDecimal(price), Integer.parseInt(categoryID), shelfPosition, Integer.parseInt(supplierID), new BigDecimal(cost));
    }

    public ArrayList<Product> getProductList() {
        return dbController.getProductList();
    }

    public void removeProduct(int productID) {
        dbController.removeProduct(productID);
    }

    public void updateProduct(int id, String name, int quantity, int categoryID, BigDecimal price, String shelf, BigDecimal cost) {
        dbController.updateProduct(id, name, quantity, categoryID, price, shelf, cost);
    }

    public void updateSupplier(String name, String phone, String address, String email, int id) {
        Supplier supplierUpdate = new Supplier(name, phone, address, email, id);
        dbController.updateSupplier(supplierUpdate);
    }

    public ArrayList<Product> getSearchList(String searchText) {
        return dbController.getSearchList(searchText);

    }

    public ArrayList<Supplier> getSupplierList() {
        return dbController.getSupplierList();
    }

    public ArrayList<Category> getCategoryList() {
        return dbController.getCategoryList();
    }
}
