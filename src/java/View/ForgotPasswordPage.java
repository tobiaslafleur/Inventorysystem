package View;


import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ForgotPasswordPage {

    private GUIFacilitator facilitator;

    @FXML
    public void initialize() {
        facilitator = Main.getInstance().getFacilitator();
    }

    public void cancel(ActionEvent forgotPasswordCancelled) {
        facilitator.changeWindow(forgotPasswordCancelled, "/fxml/LoginPage.fxml");
    }
    public void close(ActionEvent event) {
        facilitator.close(event);
    }
    public void minimize(ActionEvent event) {
        facilitator.minimize(event);
    }
}


