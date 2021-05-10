package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    /**
     * Checks the username and password and directs the user to the application window after the Login button has been pressed
     * Displays an error message if username and/or password don't match the information stored in the database
     * @param event
     */
    public void login(ActionEvent event) {
        if(facilitator.checkUser(username.getText(), password.getText())){
            facilitator.changeWindow(event, "/fxml/MainAppPage.fxml");
        } else {
            //TODO: Label saying "login failed"
            //Temp:
            JOptionPane.showMessageDialog(null, "Login failed");
        }

    }

    /**
     * Opens the registrationPage window
     * @param event
     */
    public void register(ActionEvent event) {
        facilitator.changeWindow(event, "/fxml/registrationPage.fxml");
    }

    public void close(ActionEvent event) {
        System.exit(0);
    }
    public void minimize(ActionEvent event) {
        Stage stage = (Stage)((Hyperlink)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
}