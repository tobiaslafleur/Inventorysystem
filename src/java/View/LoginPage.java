package View;

import Controller.DBController;
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
    @FXML
    private TextField username = new TextField();
    @FXML
    private PasswordField password = new PasswordField();
    @FXML
    private Button loginBtn = new Button();

    private GUIFacilitator facilitator = new GUIFacilitator(this);


    public void login(ActionEvent event) {
//        if(username.getText().equals("admin") && password.getText().equals("admin")) {
//            System.out.println("Login Successful");
//        }
//        else{
//            System.out.println("Login Unsuccessful");
//
//        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/applicationPage.fxml"));
            Scene scene = new Scene(root);
            Node button =(Node) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void register(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/registrationPage.fxml"));
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