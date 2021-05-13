package View;

import Controller.ErrorHandling.RegistrationPageHandling;
import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.util.ArrayList;

public class RegistrationPage {
    private static RegistrationPage instance;
    private GUIFacilitator facilitator;

    @FXML private TextField username;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private TextField address;
    @FXML private PasswordField password;
    @FXML private PasswordField repeatedPW;

    @FXML private Label lblUsernameWarning;
    @FXML private Label lblPhoneWarning;
    @FXML private Label lblEmailWarning;
    @FXML private Label lblAddressWarning;
    @FXML private Label lblPwWarning;
    @FXML private Label lblRepeatWarning;

    @FXML public void initialize() {
        instance = this;
        facilitator = Main.getInstance().getFacilitator();
        setInstance();

        lblUsernameWarning.setText("");
        lblPhoneWarning.setText("");
        lblEmailWarning.setText("");
        lblAddressWarning.setText("");
        lblPwWarning.setText("");
        lblRepeatWarning.setText("");
    }

    private void setInstance() {
        facilitator.setRegistrationInstance(instance);
    }

    public void register(javafx.event.ActionEvent event) {
        ArrayList<String> warnings = RegistrationPageHandling.errorHandling(username.getText(), email.getText(), phone.getText(), address.getText(), password.getText(), repeatedPW.getText(), facilitator);

        //If no warnings, create account
        if(warnings == null){
            facilitator.changeWindow(event, "/fxml/LoginPage.fxml");
            facilitator.createUser(username.getText(), email.getText(), phone.getText(), address.getText(), password.getText());
        } else {
            //username
            if(!RegistrationPageHandling.isUsernameOk()) {
                //TODO: Set underline to red
                username.setStyle("-fx-border-color: #EB5D5D;");
            } else {
                //TODO: Set underline to green
                username.setStyle("-fx-border-color: #8EFF8B;");
            }

            //email
            if(!RegistrationPageHandling.isEmailOk()) {
                email.setStyle("-fx-border-color: #EB5D5D;");
            } else {
                email.setStyle("-fx-border-color: #8EFF8B;");
            }

            //phone
            if(!RegistrationPageHandling.isPhoneOk()) {
                phone.setStyle("-fx-border-color: #EB5D5D;");
            } else {
                phone.setStyle("-fx-border-color: #8EFF8B;");
            }

            //address
            if(!RegistrationPageHandling.isAddressOk()) {
                address.setStyle("-fx-border-color: #EB5D5D;");
            } else {
                address.setStyle("-fx-border-color: #8EFF8B;");
            }

            //password
            if(!RegistrationPageHandling.isPasswordOk()) {
                password.setStyle("-fx-border-color: #EB5D5D;");
            } else {
                password.setStyle("-fx-border-color: #8EFF8B;");
            }

            //repeatPassword
            if(!RegistrationPageHandling.isRepeatPwOk()) {
                repeatedPW.setStyle("-fx-border-color: #EB5D5D;");
            } else {
                repeatedPW.setStyle("-fx-border-color: #8EFF8B;");
            }

            lblUsernameWarning.setText("");
            lblEmailWarning.setText("");
            lblPhoneWarning.setText("");
            lblAddressWarning.setText("");
            lblPwWarning.setText("");
            lblRepeatWarning.setText("");

            //Check individual errors
            for(String s : warnings) {
                if(s.equals("Password must be at least 8 characters long \n and contain at least one uppercase letter and one number")) {
                    lblPwWarning.setText(s);
                }

                if(s.equals("Username already used")) {
                    lblUsernameWarning.setText(s);
                }

                if(s.equals("Enter a valid email")) {
                    lblEmailWarning.setText(s);
                }

                if(s.equals("Passwords must match")) {
                    lblRepeatWarning.setText(s);
                }

                if(s.equals("Enter a valid number")) {
                    lblPhoneWarning.setText(s);
                }

                if(s.equals("Enter a username")) {
                    lblUsernameWarning.setText(s);
                } else if (s.equals("Username already in use")) {
                    lblUsernameWarning.setText(s);
                }
            }
        }
    }
    public void cancel(ActionEvent accountRegisterCancelled) {
        facilitator.changeWindow(accountRegisterCancelled, "/fxml/LoginPage.fxml");
    }
    public void close(ActionEvent event) {
        facilitator.close(event);
    }
    public void minimize(ActionEvent event) {
        facilitator.minimize(event);
    }
}
