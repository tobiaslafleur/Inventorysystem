package Controller;

import Model.User;
import View.GUIFacilitator;
import View.LoginPage;
import View.MainWindow;
import javafx.application.Application;

public class Controller {
    private DBController dbController;
    private GUIFacilitator facilitator;

    public Controller(){
        Application.launch(MainWindow.class);
        dbController = new DBController(this);
        facilitator = new GUIFacilitator(this);
    }
    public Controller(GUIFacilitator facilitator) {
        this.facilitator = facilitator;
    }

    public void createUser(String username, String password, String emailAddress, String phoneNr, String address) {
       User user = new User(username, password, emailAddress, phoneNr, address);
       dbController.createUser(user);


    }
}
