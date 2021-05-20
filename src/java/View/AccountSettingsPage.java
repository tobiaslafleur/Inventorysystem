package View;

import Controller.DBController;
import Controller.ErrorHandling.EditAccountSettingHandling;
import Controller.ErrorHandling.RegistrationPageHandling;
import Controller.Main;
import Model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.Arrays;


public class AccountSettingsPage {

    private GUIFacilitator facilitator;

    @FXML private AnchorPane TableControls2;
    @FXML private AnchorPane dragAnchor;
    @FXML private Stage stage;
    @FXML private AccountSettingsPage instance;


    
    @FXML private DBController dbController;

    @FXML private ComboBox<String> cmbAreaCodes;
    @FXML private Label lblPhone;
    @FXML private Label lblAddress;
    @FXML private Label lblPw;
    @FXML private Label lblOldPw;
    @FXML private Label lblEnterAllFields;


    @FXML private TextField phoneNmbr;
    @FXML private TextField address;
    @FXML private PasswordField oldPassword = new PasswordField();
    @FXML private PasswordField newPassword = new PasswordField();
    private double y = 0, x = 0;


    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();
//        instance = this;

//        setInstance();
        dragAnchor();
        fixFocus();

        cmbAreaCodes.getItems().addAll("+46");
        cmbAreaCodes.setId("cmb-area");


        lblPhone.setText("");
        lblAddress.setText("");
        lblPw.setText("");
        lblOldPw.setText("");
        lblEnterAllFields.setVisible(false);

        phoneNmbr.setOnKeyTyped(actionEvent -> {
            System.out.println(phoneNmbr.getText());
            if(EditAccountSettingHandling.isPhoneValid(phoneNmbr.getText()) && !(cmbAreaCodes.getValue() == null)){
                phoneNmbr.setStyle("-fx-border-color: #8EFF8B;");
                lblPhone.setText("");
            } else {
                phoneNmbr.setStyle("-fx-border-color: #EB5D5D;");
                lblPhone.setText("Enter a valid number and select area code");
            }
        });

        address.setOnKeyTyped(actionEvent -> {
            System.out.println(address.getText());
            if(!address.getText().equals("")){
                address.setStyle("-fx-border-color: #8EFF8B;");
                lblAddress.setText("");
            } else {
                address.setStyle("-fx-border-color: #EB5D5D;");
                lblAddress.setText("Enter a valid address.");
            }
        });

        newPassword.setOnKeyTyped(actionEvent -> {
            System.out.println(newPassword.getText());
            if(EditAccountSettingHandling.isPasswordValid(newPassword.getText())){
                newPassword.setStyle("-fx-border-color: #8EFF8B;");
                lblPw.setText("");
            } else {
                newPassword.setStyle("-fx-border-color: #EB5D5D;");
                lblPw.setText("Password must be at least 8 characters long \n and contain at least one uppercase letter and one number");
            }

            if(EditAccountSettingHandling.isRepeatValid(newPassword.getText(), oldPassword.getText())){

                oldPassword.setStyle("-fx-border-color: #8EFF8B;");
                lblOldPw.setText("");
            } else {
                oldPassword.setStyle("-fx-border-color: #EB5D5D;");
                lblOldPw.setText("Password must match");
            }
        });


    }
    @FXML private void anchorPaneClicked() {
        fixFocus();
    }

    private void fixFocus() {
        Platform.runLater(() -> TableControls2.requestFocus());
    }

    private void dragAnchor() {
        dragAnchor.setOnMousePressed(((event) -> {
            fixFocus();
            x = event.getX();
            y = event.getY();
        }));

        dragAnchor.setOnMouseDragged(((event) -> {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        }));

        dragAnchor.setOnMouseReleased(((event) -> {
            stage.setOpacity(1f);
        }));
    }
//    public void setInstance() {
//        facilitator.setAccountInstance(instance);
//    }

    public void saveSettingsBtn(ActionEvent e) {
        String userPhone = null;
        String userAddress = null;
        String userNewPassword = null;
        String areaCode = cmbAreaCodes.getValue();

        if(RegistrationPageHandling.isPhoneValid(phoneNmbr.getText()) && !(cmbAreaCodes.getValue() == null)){
            if(phoneNmbr.getText().toCharArray()[0] == '0'){
                char[] temp = Arrays.copyOfRange(phoneNmbr.getText().toCharArray(), 1, phoneNmbr.getText().length());

                StringBuilder strBuilder = new StringBuilder();
                for(char c : temp) {
                    strBuilder.append(c);
                }
                String str = strBuilder.toString();
                userPhone = areaCode + str;

            } else {
                userPhone = areaCode + phoneNmbr.getText();
                System.out.println(userPhone);
            }

        } else {
            phoneNmbr.setStyle("-fx-border-color: #EB5D5D;");
            lblPhone.setText("Enter a valid number and select area code");
        }
        if(!address.getText().isEmpty()){
            userAddress = this.address.getText();
            address.setStyle("-fx-border-color: #8EFF8B;");
            lblAddress.setText("");
        } else {
            address.setStyle("-fx-border-color: #EB5D5D;");
            lblAddress.setText("Enter a valid address");
        }
        if(!this.newPassword.getText().equals("")) {
            userNewPassword = this.newPassword.getText();
        }
        if(!this.oldPassword.getText().equals(this.newPassword.getText())) {
            System.out.println("Cant use same password");
        }
        if(!this.newPassword.getText().equals(this.oldPassword.getText())){
            userNewPassword = this.newPassword.getText();
        }

        facilitator.editUser(userPhone, userAddress, userNewPassword);
        facilitator.closeSecondStage(e);
 
    }
    public void cancel(ActionEvent event) {
        facilitator.closeSecondStage(event);
    }
}