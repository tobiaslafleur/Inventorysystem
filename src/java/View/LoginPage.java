package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;


public class LoginPage {
    private static LoginPage instance;
    private GUIFacilitator facilitator;

    @FXML private TextField username = new TextField();
    @FXML private PasswordField password = new PasswordField();
    @FXML private Button loginBtn = new Button();

    @FXML public void initialize() {
        instance = this;
        facilitator = Main.getInstance().getFacilitator();
        setInstance();
    }

    public void setInstance() {
        facilitator.setLoginInstance(instance);
    }

    public void login(ActionEvent event) {
        if(facilitator.checkUser(username.getText(), password.getText())){
            facilitator.changeWindow(event, "/fxml/applicationPage.fxml");
        } else {
            //TODO: Label saying "login failed"
            //Temp:
            JOptionPane.showMessageDialog(null, "Login failed");
        }

    }
    public void register(ActionEvent event) {
        facilitator.changeWindow(event, "/fxml/registrationPage.fxml");
    }
}