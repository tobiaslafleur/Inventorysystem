package Controller;

import Controller.Database.DBUser;
import Model.User;
import View.ApplicationPage;
import View.LoginPage;
import View.RegistrationPage;

import java.nio.file.attribute.UserPrincipal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBController {
    private Controller controller;
    private DBUser dbUser;
    private Connection conn;

    /**
     * Constructor to instantiate controller object as well as the database controller classes
     * @param controller is the controller to be instantiated
     */
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

    public void createUser(String username, String password, String email, String phone, String address) {
        User user = new User(username, password, email, phone, address);
        dbUser.createUser(user);
    }
}
