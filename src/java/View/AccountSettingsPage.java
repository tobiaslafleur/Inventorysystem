package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

public class AccountSettingsPage {

    private GUIFacilitator facilitator;

    @FXML private Button saveSettings;
    @FXML private TextField language;
    @FXML private TextField phoneNmbr;
    @FXML private TextField address;
    @FXML private TextField oldPassword;
    @FXML private TextField newPassword;
    @FXML private TextField username;


    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();
    }

    public void saveSettingsBtn(ActionEvent e) {
        facilitator.changeWindow(e, "/fxml/AccountSettingsPage.fxml");
    }
    public void checkImageBtn(ActionEvent e) {
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