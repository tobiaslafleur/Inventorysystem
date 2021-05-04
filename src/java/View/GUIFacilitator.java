package View;

import Controller.Controller;
import Model.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
            Node button =(Node) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();

            stage.setScene(scene);
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

    public boolean addSupplier(String supName, String supPhone, String supAddress, String supEmail) {
        return controller.addSupplier(supName, supPhone, supAddress, supEmail);
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

    public void updateProduct(int id, String name, int quantity, BigDecimal price, String shelf, BigDecimal cost) {
        controller.updateProduct(id, name, quantity, price, shelf, cost);
    }

    public void updateSupplier(String name, String phone, String address, String email, int id) {
        controller.updateSupplier(name, phone, address, email, id);
    }
}
