package Controller;

import Controller.Database.DBUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBController {
    private Controller controller;
    private DBUser dbUser;

    private Connection conn;

    public DBController(Controller controller) {
        this.controller = controller;
        dbUser = new DBUser(this);
    }

    public void connectToDatabase() {
        try {
            String dbUrl = "jdbc:sqlserver://ecinv.database.windows.net:1433;database=ecinv;user=ecinv@ecinv;password=mau123456!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

            conn = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            System.out.println("Connection to database failed");
        }
    }

    public void login(String username, String password) {

    }

}
