package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application {
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginPage.fxml"));
        stage.setTitle("ECINV 1.0");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}