package View;

import Controller.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class RegistrationPage {
    private static RegistrationPage instance;
    private GUIFacilitator facilitator;

    @FXML private TextField username = new TextField();
    @FXML private TextField email = new TextField();
    @FXML private TextField phone = new TextField();
    @FXML private TextField address = new TextField();
    @FXML private PasswordField password = new PasswordField();
    @FXML private PasswordField repeatedPW = new PasswordField();
    @FXML private Button registerBtn = new Button();

    @FXML public void initialize() {
        instance = this;
        facilitator = Main.getInstance().getFacilitator();
        setInstance();
    }

    private void setInstance() {
        facilitator.setRegistrationInstance(instance);
    }

    public void register(javafx.event.ActionEvent event) {
        //Check if all fields are entered
        if(!username.getText().isEmpty() && !password.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !address.getText().isEmpty() && !repeatedPW.getText().isEmpty()){
            //Check if password is same as repeated password
            if(password.getText().equals(repeatedPW.getText())){
                facilitator.createUser(username.getText(),password.getText(), email.getText(), phone.getText(), address.getText());
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginPage.fxml"));
                    Scene scene = new Scene(root);
                    Node button =(Node) event.getSource();
                    Stage stage = (Stage) button.getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                //Display text: Passwords must match
                //Temp:
                JOptionPane.showMessageDialog(null, "Passwords must match");
            }
        } else {
            //Display text: "Enter all required fields"
            //Temp:
            JOptionPane.showMessageDialog(null, "Enter all required fields.");
        }
    }
}
