package View;

import Controller.ErrorHandling.RegistrationPageHandling;
import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

    @FXML private Label lblFail;

    @FXML public void initialize() {
        instance = this;
        facilitator = Main.getInstance().getFacilitator();
        setInstance();

    }

    private void setInstance() {
        facilitator.setRegistrationInstance(instance);
    }

    public void register(javafx.event.ActionEvent event) {
        ArrayList<String> warnings = RegistrationPageHandling.errorHandling(username.getText(), email.getText(), phone.getText(), address.getText(), password.getText(), repeatedPW.getText());

        //If no warnings, create account
        if(warnings == null){
            facilitator.changeWindow(event, "/fxml/LoginPage.fxml");
            facilitator.createUser(username.getText(), email.getText(), phone.getText(), address.getText(), password.getText());
        } else {
            //username
            if(!RegistrationPageHandling.isUsernameOk()) {
                //TODO: Set underline to red
            } else {
                //TODO: Set underline to green
            }

            //email
            if(!RegistrationPageHandling.isEmailOk()) {
                //TODO: Set underline to red
            } else {
                //TODO: Set underline to green
            }

            //phone
            if(!RegistrationPageHandling.isPhoneOk()) {
                //TODO: Set underline to red
            } else {
                //TODO: Set underline to green
            }

            //address
            if(!RegistrationPageHandling.isAddressOk()) {
                //TODO: Set underline to red
            } else {
                //TODO: Set underline to green
            }

            //password
            if(!RegistrationPageHandling.isPasswordOk()) {
                //TODO: Set underline to red
            } else {
                //TODO: Set underline to green
            }

            //repeatPassword
            if(!RegistrationPageHandling.isRepeatPwOk()) {
                //TODO: Set underline to red
            } else {
                //TODO: Set underline to green
            }

            String strWarnings = "";
            StringBuilder strBuilder = new StringBuilder();
            for(String s : warnings) {
                strBuilder.append(s).append("\n");
            }
            strWarnings = strBuilder.toString();

            //Temp
            JOptionPane.showMessageDialog(null, strWarnings);
            //lblFail.setText(strWarnings);
        }


/*        if(!username.getText().isEmpty() && !password.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !address.getText().isEmpty() && !repeatedPW.getText().isEmpty()){
            //Check if password is same as repeated password
            if(password.getText().equals(repeatedPW.getText())){
                if(facilitator.createUser(username.getText(),password.getText(), email.getText(), phone.getText(), address.getText())) {
                    facilitator.changeWindow(event, "/fxml/LoginPage.fxml");
                } else {
                    //Todo: Update label on GUI: "Account creation failed"
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
        }*/
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
