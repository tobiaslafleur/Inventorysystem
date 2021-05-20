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

    @FXML private AnchorPane TableControls2;
    @FXML private AnchorPane dragAnchor;
    @FXML private Stage stage;
//    @FXML private static AccountSettingsPage instance;



    @FXML private TextField phoneNmbr;
    @FXML private TextField address;
    @FXML private TextField oldPassword = new TextField();
    @FXML private TextField newPassword = new TextField();
    private double y = 0, x = 0;


    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();
//        instance = this;

//        setInstance();
        dragAnchor();
        fixFocus();
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
        String username = null; //Always null
        String userPhone = null;
        String userLanguage = null; //Always null
        String userAddress = null;
        String userOldpassword = null;
        String userNewpassword = null;

        if(!this.phoneNmbr.getText().equals("")) {
            userPhone = this.phoneNmbr.getText();
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
        if(userOldpassword.equals(this.newPassword.getText())) {
            this.newPassword.getText();
        }
        if(this.newPassword.getText().equals(this.oldPassword.getText())){
            userNewpassword = this.newPassword.getText();
        }

 

        facilitator.editUser(userPhone, userLanguage, userAddress, userOldpassword, userNewpassword);
        facilitator.closeSecondStage(e);
 
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
    public void cancel(ActionEvent event) {
        facilitator.closeSecondStage(event);
    }
}