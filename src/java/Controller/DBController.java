package Controller;

import Controller.Database.DBCategory;
import Controller.Database.DBProduct;
import Controller.Database.DBSupplier;
import Controller.Database.DBUser;
import Model.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBController {
    private Controller controller;
    private DBUser dbUser;
    private DBProduct dbProduct;
    private DBCategory dbCategory;
    private DBSupplier dbSupplier;
    private Connection conn;
    private User user;
    private CSVImport csvImport;

    /**
     * Constructor to instantiate controller object as well as the database controller classes
     * @param controller is the controller to be instantiated
     */
    public DBController(Controller controller) {
        this.controller = controller;
        this.csvImport = new CSVImport(this);
        dbCategory = new DBCategory(this);
        dbUser = new DBUser(this);
        dbSupplier = new DBSupplier(this);
        dbProduct = new DBProduct(this);

    }


    /**
     * Connects to the database
     */
    public void connect() {
        try {
            String dbUrl = "jdbc:sqlserver://ecinvserver.database.windows.net:1433;database=ecinvDB;user=ecinv@ecinvserver;password=Kingarna123!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
            conn = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            System.out.println("Connection to database failed");
        }
    }

    /**
     * Closes the connection to the database
     */
    public void disconnect(){
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates an account and stores it in the database
     * @param username first String value for the creation of the account
     * @param password second String value for the creation of the account
     * @param email third String value for the creation of the account
     * @param phone fourth String value for the creation of the account
     * @param address fifth String value for the creation of the account
     * @return The created user stored in the database
     */
    public boolean createUser(String username, String password, String email, String phone, String address) {
        User user = new User(username, HashPassword.hash(username, password), email, phone, address);
        return dbUser.createUser(user);
    }

    public Connection getConnection() {
        return conn;
    }

    public void setUser(User user) {
        this.user = user;
        controller.setUser(user);
        dbProduct.createProductTable();
    }

    public User getUser() {
        return user;
    }

    public boolean checkUser(String username, String password) {
        return dbUser.checkUser(username, HashPassword.hash(username, password));
    }

    public boolean createCategory(String name) {
        return dbCategory.createCategory(name);
    }

    public boolean addSupplier(Supplier supplier) {
        return dbSupplier.addSupplier(supplier);
    }

    public boolean addProduct(String name, int stock, BigDecimal price, int categoryID, String shelfPosition, int supplierID, BigDecimal cost) {
        return dbProduct.addProduct(name, stock, price, categoryID, shelfPosition, supplierID, cost, user.getUserID());
    }

    public ArrayList<Product> getProductList() {
        return dbProduct.getProductList();
    }

    public void removeProduct(int productID) {
        dbProduct.removeProduct(productID);
    }

    public void updateProduct(int id, String name, int quantity, int categoryID, BigDecimal price, String shelf, BigDecimal cost) {
        dbProduct.updateProductSetup(id, name, quantity, categoryID, price, shelf, cost);

    }

    public void updateSupplier(Supplier supplierUpdate) {
        dbSupplier.updateSupplierSetup(supplierUpdate);
    }

    public ArrayList<Product> getSearchList(String searchText) {
        return dbProduct.getSearchList(searchText);
    }

    public ArrayList<Supplier> getSupplierList() {
       return dbSupplier.getSupplierList();
    }

    public ArrayList<Category> getCategoryList() {
        return dbCategory.getCategoryList();
    }

    public boolean getCSVProductList(String filepath) {
        boolean isValid = false;
        csvImport.readProductFromCSVFile(filepath);
        return isValid;
    }
    public boolean usernameExists(String username) {
        return dbUser.usernameExists(username);
    }
}
