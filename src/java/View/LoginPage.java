package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.*;


public class LoginPage {
    private static LoginPage instance;
    private GUIFacilitator facilitator;

    @FXML private TextField username = new TextField();
    @FXML private PasswordField password = new PasswordField();
    @FXML private Button loginBtn = new Button();
    @FXML private Label lblFailed;

    @FXML public void initialize() {
        instance = this;
        facilitator = Main.getInstance().getFacilitator();
        setInstance();
        lblFailed.setVisible(false);
    }

    public void setInstance() {
        facilitator.setLoginInstance(instance);
    }

    /**
     * Checks the username and password and directs the user to the application window after the Login button has been pressed
     * Displays an error message if username and/or password don't match the information stored in the database
     * @param event
     */
    public void login(ActionEvent event) {
        if(facilitator.checkUser(username.getText(), password.getText())){
            facilitator.changeWindow(event, "/fxml/ApplicationPage.fxml");
        } else {
            //TODO: Label saying "login failed"
            //Temp:
            //JOptionPane.showMessageDialog(null, "Login failed");
            lblFailed.setVisible(true);
        }
    }

    /**
     * Opens the registrationPage window
     * @param event
     */
    public void register(ActionEvent event) {
        facilitator.changeWindow(event, "/fxml/RegistrationPage.fxml");
    }

    public void close(ActionEvent event) {
        facilitator.close(event);
    }
    public void minimize(ActionEvent event) {
        facilitator.minimize(event);
    }
}