package View;

import Controller.DBController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class RegistrationPage {
    @FXML
    private TextField username = new TextField();
    @FXML
    private PasswordField password = new PasswordField();
    @FXML
    private PasswordField repeatedPW = new PasswordField();
    @FXML
    private Button registerBtn = new Button();
    private GUIFacilitator facilitator = new GUIFacilitator(this);


    public void register(javafx.event.ActionEvent event) {
        System.out.println("Registration complete");

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginPage.fxml"));
            Scene scene = new Scene(root);
            Node button =(Node) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
