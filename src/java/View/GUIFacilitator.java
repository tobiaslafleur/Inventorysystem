package View;

import Controller.Controller;
import Model.Category;
import Model.Product;
import Model.Supplier;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class GUIFacilitator {
    private Controller controller;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private ApplicationPage applicationPage;
    private AddSupplierPage addSupplierPage;


    public boolean createUser(String username, String password, String email, String phone, String address) {
        return controller.createUser(username, password, email, phone, address);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setLoginInstance(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public void setRegistrationInstance(RegistrationPage registrationPage) {
        this.registrationPage = registrationPage;
    }

    public void setApplicationInstance(ApplicationPage applicationPage) {
        this.applicationPage = applicationPage;
    }

    public void changeWindow(ActionEvent event, String path){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            scene.getStylesheets().add(getClass().getResource("/Stylesheets/Stylesheet.css").toExternalForm());

            Node button =(Node) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();

            stage.setScene(scene);
            stage.show();

            stage.setScene(scene);
            stage.setX(190);
            stage.setY(110);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkUser(String username, String password) {
        return controller.checkUser(username, password);
    }

    public void setAddSupplierInstance(AddSupplierPage addSupplierPage) {
        this.addSupplierPage = addSupplierPage;
    }

    public boolean addSupplier(String supName, String supPhone, String supStreet, String supCity, String supCountry, String supEmail) {
        return controller.addSupplier(supName, supPhone, supStreet, supCity, supCountry, supEmail);
    }

    public boolean addProduct(String name, String stock, String price, String categoryID, String shelfPosition, String supplierID, String cost) {
        return controller.addProduct(name, stock, price, categoryID, shelfPosition, supplierID, cost);
    }

    public boolean createCategory(String name) {
        return controller.createCategory(name);
    }

    public ArrayList<Product> getProductList() {
        return controller.getProductList();
    }

    public void updateProductTable() {
        applicationPage.updateTable();
    }

    public void removeProduct(int productID) {
        controller.removeProduct(productID);
    }

    public void updateProduct(int id, String name, int quantity, int categoryID, BigDecimal price, String shelf, BigDecimal cost) {
        controller.updateProduct(id, name, quantity, categoryID, price, shelf, cost);
    }

    public void updateSupplier(String supName, String supPhone, String supStreet, String supCity, String supCountry, String supEmail, int id) {
        controller.updateSupplier(supName, supPhone, supStreet, supCity, supCountry, supEmail, id);
    }

    public ArrayList<Product> getProductSearch(String searchText) {
        return controller.getProductSearch(searchText);

    }
    public ArrayList<Category> getCategorySearch(String searchText) {
        return controller.getCategorySearch(searchText);
    }

    public ArrayList<Supplier> getSupplierSearch(String searchText) {
        return controller.getSupplierSearch(searchText);
    }

    public void close(ActionEvent event) {
        System.exit(0);
    }
    public void minimize(ActionEvent event) {
        Stage stage = (Stage)((Hyperlink)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public ArrayList<Supplier> getSupplierList() {
        return controller.getSupplierList();
    }

    public ArrayList<Category> getCategoryList() {
        return controller.getCategoryList();
    }


    public boolean usernameExists(String username) {
        return controller.usernameExists(username);
    }

    public boolean CSVImport(String filepath) {
            return controller.getCSVFile(filepath);
    }
}
