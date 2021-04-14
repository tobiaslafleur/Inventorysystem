package Controller;

import Model.User;
import View.GUIFacilitator;

import java.math.BigDecimal;

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
    public boolean addProduct(String name, String stock, String price, String categoryID, String shelfPosition, String supplierID, String cost, String userID) {
         return dbController.addProduct(name, Integer.parseInt(stock), new BigDecimal(price), Integer.parseInt(categoryID), shelfPosition, Integer.parseInt(supplierID), new BigDecimal(cost),Integer.parseInt(userID));
    }
}
