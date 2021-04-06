package View;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPage {
    @FXML
    private TextField username = new TextField();
    @FXML
    private PasswordField password = new PasswordField();

    public void login(ActionEvent event) {
        if(username.getText().equals("admin") && password.getText().equals("admin")) {
            System.out.println("Login Successful");
        }
        else{
            System.out.println("Login Unsuccessful");
        }
    }
    public void register(ActionEvent event) {
        System.out.println("Account Registration");
    }
}