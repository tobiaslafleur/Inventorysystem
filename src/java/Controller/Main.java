package Controller;

import View.GUIFacilitator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Main instance;
    private Controller controller;
    private GUIFacilitator facilitator;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Controller();
        facilitator = new GUIFacilitator();
        initializeConnection();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/loginPage.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("ECINV 1.0");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    private void initializeConnection() {
        instance = this;
        controller.setFacilitator(facilitator);
        facilitator.setController(controller);
    }

    public GUIFacilitator getFacilitator() {
        return facilitator;
    }

    public static Main getInstance() {
        return instance;
    }
}