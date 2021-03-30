package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JFXGUI extends Application {
    private Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        primaryStage.setTitle("ECINV 1.0");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}