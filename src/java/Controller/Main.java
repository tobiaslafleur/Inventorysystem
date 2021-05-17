package Controller;

import View.GUIFacilitator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginPage.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("ECINV");
        Scene scene = new Scene(root, 300,500);

        scene.getStylesheets().add(getClass().getResource("/Stylesheets/Stylesheet.css").toExternalForm());
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setScene(scene);
        primaryStage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
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