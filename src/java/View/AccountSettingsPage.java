package View;

import Controller.ErrorHandling.EditAccountSettingHandling;
import Controller.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Arrays;


public class AccountSettingsPage {

    private GUIFacilitator facilitator;

    @FXML private AnchorPane TableControls2;
    @FXML private AnchorPane dragAnchor;
    @FXML private Stage stage;

    @FXML private Label lblAccountSettings;
    @FXML private ComboBox<String> cmbAreaCodes;
    @FXML private Label lblPhone;
    @FXML private Label lblAddress;
    @FXML private Label lblPw;
    @FXML private Label lblOldPw;
    @FXML private Label lblEnterAllFields;

    @FXML private Button saveBtn;
    @FXML private Hyperlink cancelBtn;
    @FXML private TextField phoneNmbr;
    @FXML private TextField address;
    @FXML private PasswordField oldPassword = new PasswordField();
    @FXML private PasswordField newPassword = new PasswordField();
    private double y = 0, x = 0;


    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();

        fixFocus();

        cmbAreaCodes.getItems().addAll();

        lblPhone.setText("");
        lblAddress.setText("");
        lblPw.setText("");
        lblOldPw.setText("");


        phoneNmbr.setOnKeyTyped(actionEvent -> {

            if(EditAccountSettingHandling.isPhoneValid(phoneNmbr.getText()) && !(cmbAreaCodes.getValue() == null)){
                phoneNmbr.setStyle("-fx-border-color: #1F701D;");
                lblPhone.setText("");
            } else {
                phoneNmbr.setStyle("-fx-border-color: #974F4F;");
                lblPhone.setText(Language.getErrPhonePasswordAS());
            }
        });

        address.setOnKeyTyped(actionEvent -> {
            if(!address.getText().equals("")){
                address.setStyle("-fx-border-color: #1F701D;");
                lblAddress.setText("");
            } else {
                address.setStyle("-fx-border-color: #974F4F;");
                lblAddress.setText(Language.getErrAddressAS());
            }
        });

        newPassword.setOnKeyTyped(actionEvent -> {

            if(EditAccountSettingHandling.isPasswordValid(newPassword.getText())){
                newPassword.setStyle("-fx-border-color: #1F701D;");
                lblPw.setText("");
            } else {
                newPassword.setStyle("-fx-border-color: #974F4F;");
                lblPw.setText(Language.getOldPasswordAS());
            }

            if(EditAccountSettingHandling.isNotRepeatValid(newPassword.getText(), oldPassword.getText())){
                newPassword.setStyle("-fx-border-color: #974F4F;");
                oldPassword.setStyle("-fx-border-color: #974F4F;");//974F4F RED
                lblOldPw.setText(Language.getRepeatPasswordAS());
            } else {
                newPassword.setStyle("-fx-border-color: #1F701D;");
                oldPassword.setStyle("-fx-border-color: #1F701D;");//974F4F RED
                lblOldPw.setText("");
            }
        });

        setLanguage();
    }
    @FXML private void anchorPaneClicked() {
        fixFocus();
    }
    public static boolean checkPWForEdit() {

        return true;
    }

    private void fixFocus() {
        Platform.runLater(() -> TableControls2.requestFocus());
    }

    public void saveSettingsBtn(ActionEvent e) {
        String userPhone = null;
        String userAddress = null;
        String userNewPassword = null;
        String areaCode = cmbAreaCodes.getValue();

        if(EditAccountSettingHandling.isPhoneValid(phoneNmbr.getText()) && !(cmbAreaCodes.getValue() == null)){
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

        }
        if(!address.getText().isEmpty()){
            userAddress = this.address.getText();
        }
        //Will fix
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
    public void setLanguage() {
        lblAccountSettings.setText(Language.getEditLblTitle());
        cancelBtn.setText(Language.getEditCancelAS());
        phoneNmbr.setPromptText(Language.getPhoneSettingsAS());
        address.setPromptText(Language.getEditAddressAS());
        oldPassword.setPromptText(Language.getPasswordAS());
        newPassword.setPromptText(Language.getNewPasswordAS());
        saveBtn.setText(Language.getSaveSettingsAS());
    }
}