package View;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIFacilitator {
    private Controller controller;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private ApplicationPage applicationPage;

    public void createUser(String username, String password, String email, String phone, String address) {
        controller.createUser(username, password, email, phone, address);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setLoginInstance(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public void setRegistrationInstance(RegistrationPage registrationPage) {
        this.registrationPage = registrationPage;
    }

    public void setApplicationInstance(ApplicationPage applicationPage) {
        this.applicationPage = applicationPage;
    }

    public void changeWindow(ActionEvent event, String path){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(root);
            Node button =(Node) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
