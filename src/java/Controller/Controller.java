package Controller;

import View.LoginPage;
import View.MainWindow;
import javafx.application.Application;

public class Controller {
    private DBController dbController;
    private LoginPage loginPage;

    public Controller(){
        Application.launch(MainWindow.class);
        dbController = new DBController(this);
        //loginPage = new LoginPage(this);
    }

    public void login() {
        System.out.println("Login from controller");
    }
}
