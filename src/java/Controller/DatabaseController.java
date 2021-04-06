package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseController {
    private MainController mainController;

    private Connection conn;

    public DatabaseController(MainController mainController) {
        this.mainController = mainController;

        connectToDatabase();
    }

    public void connectToDatabase() {
        try {
            String dbUrl = "jdbc:sqlserver://ecinv.database.windows.net;databaseName=ecinv;";
            String user = "ecinv";
            String password = "mau123456!";

            conn = DriverManager.getConnection(dbUrl, user, password);
            System.out.println("Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
