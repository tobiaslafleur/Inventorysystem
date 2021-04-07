package Controller;

import Model.User;
import View.GUIFacilitator;
import javafx.application.Application;

public class Controller {
    private DBController dbController;
    private GUIFacilitator facilitator;

    public Controller(){
        dbController = new DBController(this);
    }

    public void createUser(String username, String password, String email, String phone, String address) {
        dbController.createUser(username, password, email, phone, address);
    }

    public void setFacilitator(GUIFacilitator facilitator) {
        this.facilitator = facilitator;
    }
}
