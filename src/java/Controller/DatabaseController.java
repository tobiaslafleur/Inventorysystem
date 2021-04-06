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
            String dbUrl = "jdbc:sqlserver://ecinv.database.windows.net:1433;database=ecinv;user=ecinv@ecinv;password=mau123456!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

            conn = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            System.out.println("Connection to database failed");
        }
    }

}
