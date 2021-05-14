package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.File;

public class AccountSettingsPage {

    private GUIFacilitator facilitator;

    @FXML private Button saveSettings;
    @FXML private TextField language;
    @FXML private TextField phoneNmbr;
    @FXML private TextField address;
    @FXML private TextField oldPassword = new TextField();
    @FXML private TextField newPassword = new TextField();
    @FXML private TextField username;


    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();
    }

    public void saveSettingsBtn(ActionEvent e) {
        String userPhone = null;
        String userLanguage = null;
        String userAddress = null;
        String userOldpassword = null;
        String userNewpassword = null;




        if(!this.phoneNmbr.getText().equals("")) {
            userPhone = this.phoneNmbr.getText();
        }
        if(!this.language.getText().equals("")) {
            userLanguage = this.language.getText();
        }
        if(!this.address.getText().equals("")) {
            userAddress = this.address.getText();
        }
        if(!this.oldPassword.getText().equals("")) {
            userOldpassword = this.oldPassword.getText();
        }
        if(!this.newPassword.getText().equals("")) {
            userNewpassword = this.newPassword.getText();
        }
        if(newPassword.getText().equals(oldPassword.getText())){
            //not finished, too tired
        }

        facilitator.editUser(userPhone, userLanguage, userAddress, userOldpassword, userNewpassword);
        facilitator.changeWindow(e, "/fxml/ApplicationPage.fxml");
        facilitator.updateProductTable();
            facilitator.changeWindow(e, "/fxml/AccountSettingsPage.fxml");
    }
    public void checkImageBtn(ActionEvent e) {
        try {
            //Preliminary "solve" until we create a new images folder specified for our users filled with profilepictures.
            Stage stage = new Stage();
            FileChooser JF = new FileChooser();
            String filepath = JF.showOpenDialog(stage).getPath();
        } catch (Exception ignore) {
        }

        facilitator.changeWindow(e,"");
    }
    public void cancel(ActionEvent cancelCategory) {
        facilitator.changeWindow(cancelCategory, "/fxml/ApplicationPage.fxml");
    }
    public void close(ActionEvent event) {
        facilitator.close(event);
    }
    public void minimize(ActionEvent event) {
        facilitator.minimize(event);
    }
}