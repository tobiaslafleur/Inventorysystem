package View;

import Controller.ErrorHandling.RegistrationPageHandling;
import Controller.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

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

    @FXML private Button btnCheck;

    @FXML private ComboBox<String> cmbAreaCodes;

    @FXML private Label lblEnterAllFields;

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
        lblEnterAllFields.setVisible(false);

        cmbAreaCodes.getItems().addAll("+46");

        btnCheck.setId("check-btn");
        btnCheck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                checkUsername();
            }
        });

        username.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent actionEvent) {
                if(username.getText().isEmpty()) {
                    lblUsernameWarning.setText("Enter a username");
                    username.setStyle("-fx-border-color: #EB5D5D;");
                } else {
                    username.setStyle("-fx-border-color: #FFFF3A;");
                    lblUsernameWarning.setText("Check if username is available");
                }
            }
        });

        email.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent actionEvent) {
                if(RegistrationPageHandling.isEmailValid(email.getText())){
                    email.setStyle("-fx-border-color: #8EFF8B;");
                    lblEmailWarning.setText("");
                } else {
                    email.setStyle("-fx-border-color: #EB5D5D;");
                    lblEmailWarning.setText("Enter a valid email");
                }
            }
        });

        phone.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent actionEvent) {
                if(RegistrationPageHandling.isPhoneValid(phone.getText())){
                    phone.setStyle("-fx-border-color: #8EFF8B;");
                    lblPhoneWarning.setText("");
                } else {
                    phone.setStyle("-fx-border-color: #EB5D5D;");
                    lblPhoneWarning.setText("Enter a valid number");
                }
            }
        });

        address.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent actionEvent) {
                if(!address.getText().isEmpty()){
                    address.setStyle("-fx-border-color: #8EFF8B;");
                    lblAddressWarning.setText("");
                } else {
                    address.setStyle("-fx-border-color: #EB5D5D;");
                    lblAddressWarning.setText("Enter a valid address");
                }
            }
        });

        password.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent actionEvent) {
                if(RegistrationPageHandling.isPasswordValid(password.getText())){
                    password.setStyle("-fx-border-color: #8EFF8B;");
                    lblPwWarning.setText("");
                } else {
                    password.setStyle("-fx-border-color: #EB5D5D;");
                    lblPwWarning.setText("Password must be at least 8 characters long \n and contain at least one uppercase letter and one number");
                }

                if(RegistrationPageHandling.isRepeatValid(password.getText(), repeatedPW.getText())){
                    repeatedPW.setStyle("-fx-border-color: #8EFF8B;");
                    lblRepeatWarning.setText("");
                } else {
                    repeatedPW.setStyle("-fx-border-color: #EB5D5D;");
                    lblRepeatWarning.setText("Password must match");
                }
            }
        });

        repeatedPW.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent actionEvent) {
                if(RegistrationPageHandling.isRepeatValid(password.getText(), repeatedPW.getText())){
                    repeatedPW.setStyle("-fx-border-color: #8EFF8B;");
                    lblRepeatWarning.setText("");
                } else {
                    repeatedPW.setStyle("-fx-border-color: #EB5D5D;");
                    lblRepeatWarning.setText("Password must match");
                }
            }
        });
    }

    private void setInstance() {
        facilitator.setRegistrationInstance(instance);
    }

    private void checkUsername(){
        if(RegistrationPageHandling.usernameExists(username.getText(), facilitator)){
            username.setStyle("-fx-border-color: #8EFF8B;");
            lblUsernameWarning.setText("");
        } else {
            username.setStyle("-fx-border-color: #EB5D5D;");
            if(username.getText().isEmpty()) {
                lblUsernameWarning.setText("Enter a username");
            } else {
                lblUsernameWarning.setText("Username already in use");
            }
        }
    }

    public void register(javafx.event.ActionEvent event) {
        ArrayList<String> warnings = RegistrationPageHandling.errorHandling(username.getText(), email.getText(), phone.getText(), address.getText(), password.getText(), repeatedPW.getText(), facilitator);

        checkUsername();

        //If no warnings, create account
        if(warnings == null){
            String areacode = cmbAreaCodes.getValue();
            String number = areacode + phone.getText();
            System.out.println(number);
            facilitator.changeWindow(event, "/fxml/LoginPage.fxml");
            facilitator.createUser(username.getText(), password.getText(), email.getText(), number, address.getText());
        } else {
            lblEnterAllFields.setVisible(true);
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
