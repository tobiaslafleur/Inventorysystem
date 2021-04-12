package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
        facilitator.changeWindow(event, "/fxml/applicationPage.fxml");
    }
    public void register(ActionEvent event) {
        facilitator.changeWindow(event, "/fxml/registrationPage.fxml");
    }
}