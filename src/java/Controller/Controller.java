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

    public boolean addSupplier(String supName, String supPhone, String supStreet, String supCity, String supCountry, String supEmail) {
        Supplier supplier = new Supplier(supName, supPhone, supStreet, supCity, supCountry, supEmail);
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

    public void updateSupplier(String supName, String supPhone, String supStreet, String supCity, String supCountry, String supEmail, int supID) {
        Supplier supplierUpdate = new Supplier(supName, supPhone, supStreet, supCity, supCountry, supEmail, supID);
        dbController.updateSupplier(supplierUpdate);
    }
    public void editUser(String username, String userPhone, String userLanguage, String userAddress,  String userNewpassword){
        User userUpdate = new User(dbController.getUser().getUserID(), username, userPhone, userLanguage,userAddress,userNewpassword);
        dbController.editUser(userUpdate);
    }

    public ArrayList<Product> getProductSearch(String searchText) {
        return dbController.getProductSearch(searchText);

    }

    public ArrayList<Supplier> getSupplierList() {
        return dbController.getSupplierList();
    }

    public ArrayList<Category> getCategoryList() {
        return dbController.getCategoryList();
    }
  
    public boolean getCSVFile(String filepath) {
        return dbController.getCSVProductList(filepath);

    }
    public boolean usernameExists(String username) {
        return dbController.usernameExists(username);
    }

    public ArrayList<Category> getCategorySearch(String searchText) {
        return dbController.getCategorySearch(searchText);
    }

    public ArrayList<Supplier> getSupplierSearch(String searchText) {
        return dbController.getSupplierSearch(searchText);
    }
}
