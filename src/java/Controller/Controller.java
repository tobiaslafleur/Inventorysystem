package Controller;

import Model.User;
import View.GUIFacilitator;

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
        return dbController.addSupplier(supName, supPhone, supAddress, supEmail);
    }
}
