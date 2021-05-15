package View;

import Controller.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class AccountSettingsPage {

    private GUIFacilitator facilitator;

    @FXML private AnchorPane TableControls;
    @FXML private AnchorPane dragAnchor;
    @FXML private Stage stage;
    @FXML private static AccountSettingsPage instance;


    @FXML private TextField language;
    @FXML private TextField phoneNmbr;
    @FXML private TextField address;
    @FXML private TextField oldPassword = new TextField();
    @FXML private TextField newPassword = new TextField();
    private double y = 0, x = 0;


    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();
        instance = this;

        setInstance();
        dragAnchor();
        fixFocus();
    }
    @FXML private void anchorPaneClicked() {
        fixFocus();
    }

    private void fixFocus() {
        Platform.runLater(() -> TableControls.requestFocus());
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
    public void setInstance() {
        facilitator.setAccountInstance(instance);
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
    }
    public void checkImageBtn(ActionEvent e) {
        try {
            //Preliminary "solve" until we create a new images folder specified for our users filled with profilepictures.
            Stage stage = new Stage();
            FileChooser JF = new FileChooser();
            String filepath = JF.showOpenDialog(stage).getPath();
        } catch (Exception ignore) {
            //dfg
        }
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