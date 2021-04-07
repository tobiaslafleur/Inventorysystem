package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationPage {
    private static ApplicationPage instance;
    private GUIFacilitator facilitator;

    @FXML private ListView<String> infoList = new ListView<>();

    @FXML public void initialize() {
        instance = this;
        facilitator = Main.getInstance().getFacilitator();
        setInstance();
    }

    public void setInstance() {
        facilitator.setApplicationInstance(instance);
    }

    public void logOut(ActionEvent event) {
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
