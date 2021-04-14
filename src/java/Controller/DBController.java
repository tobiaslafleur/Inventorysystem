package Controller;

import Controller.Database.DBCategory;
import Controller.Database.DBSupplier;
import Controller.Database.DBUser;
import Model.Category;
import Model.Supplier;
import Model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBController {
    private Controller controller;
    private DBUser dbUser;
    private DBCategory dbCategory;
    private DBSupplier dbSupplier;
    private Connection conn;
    private User user;

    /**
     * Constructor to instantiate controller object as well as the database controller classes
     * @param controller is the controller to be instantiated
     */
    public DBController(Controller controller) {
        this.controller = controller;
        dbUser = new DBUser(this);
        dbSupplier = new DBSupplier(this);
    }

    public void connect() {
        try {
            String dbUrl = "jdbc:sqlserver://ecinv.database.windows.net:1433;database=ecinv;user=ecinv@ecinv;password=mau123456!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

            conn = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            System.out.println("Connection to database failed");
        }
    }

    public void disconnect(){
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean createUser(String username, String password, String email, String phone, String address) {
        User user = new User(username, password, email, phone, address);
        return dbUser.createUser(user);
    }
    public Connection getConnection() {
        return conn;
    }

    public void setUser(User user) {
        this.user = user;
        controller.setUser(user);
        System.out.println(user);
    }

    public boolean checkUser(String username, String password) {
        return dbUser.checkUser(username, password);
    }

    public boolean createCategory(String name) {
        Category category = new Category(name);
        return dbCategory.createCategory(category);
    }

    public boolean addSupplier(String supName, String supPhone, String supAddress, String supEmail) {
        Supplier supplier = new Supplier(supName, supPhone, supAddress, supEmail);
        return dbSupplier.addSupplier(supplier);
    }
}
