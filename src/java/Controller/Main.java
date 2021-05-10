package Controller;

import View.GUIFacilitator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static Main instance;
    private Controller controller;
    private GUIFacilitator facilitator;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Controller();
        facilitator = new GUIFacilitator();
        initializeConnection();

        primaryStage.getIcons().add(new Image("images/WindowLogoV2.png"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("ECINV 1.0");
        Scene scene = new Scene(root, 268.0,374.0);

        scene.getStylesheets().add(getClass().getResource("/Stylesheets/Stylesheet.css").toExternalForm());
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setScene(scene);
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