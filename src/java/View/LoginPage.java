package View;

import Controller.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginPage {
    private static LoginPage instance;
    private GUIFacilitator facilitator;

    private Stage stage;

    @FXML private AnchorPane window;
    @FXML private AnchorPane dragAnchor;
    @FXML private TextField username = new TextField();
    @FXML private PasswordField password = new PasswordField();
    @FXML private Button loginBtn = new Button();
    @FXML private Label lblFailed;

    private double x = 0, y = 0;

    @FXML public void initialize() {
        instance = this;
        facilitator = Main.getInstance().getFacilitator();
        setInstance();
        fixFocus();
        dragAnchor();
        lblFailed.setVisible(false);
    }

    @FXML private void windowClicked() {
        fixFocus();
    }

    private void fixFocus() {
        Platform.runLater(() -> window.requestFocus());
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
        facilitator.setLoginInstance(instance);
    }

    /**
     * Checks the username and password and directs the user to the application window after the Login button has been pressed
     * Displays an error message if username and/or password don't match the information stored in the database
     * @param event
     */
    public void login(ActionEvent event) {
        if(facilitator.checkUser(username.getText(), password.getText())){
            facilitator.changeWindow(event, "/fxml/ApplicationPage.fxml");
        } else {
            //TODO: Label saying "login failed"
            //Temp:
            //JOptionPane.showMessageDialog(null, "Login failed");
            lblFailed.setVisible(true);
        }
    }

    /**
     * Opens the registrationPage window
     * @param event
     */
    public void register(ActionEvent event) {
        facilitator.changeWindow(event, "/fxml/RegistrationPage.fxml");
    }

    public void askForUserEmail (ActionEvent event) {
        facilitator.changeWindow(event, "/fxml/ForgotPassword.fxml");
    }

    public void close(ActionEvent event) {
        facilitator.close(event);
    }
    public void minimize(ActionEvent event) {
        facilitator.minimize(event);
    }
}