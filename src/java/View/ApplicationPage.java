package View;

import Controller.Main;
import Model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationPage {
    private static ApplicationPage instance;
    private GUIFacilitator facilitator;

    @FXML private TableView<String> infoTable;
    @FXML private TableColumn<Product, String> colName;
    @FXML private TableColumn<Product, String> colQuantity;
    @FXML private TableColumn<Product, String> colPrice;
    @FXML private TableColumn<Product, String> colCategory;
    @FXML private TableColumn<Product, String> colShelf;
    @FXML private TableColumn<Product, String> colSupplier;
    @FXML private TableColumn<Product, String> colCost;



    @FXML public void initialize() {
        instance = this;
        facilitator = Main.getInstance().getFacilitator();
        setInstance();
    }

    public void setInstance() {
        facilitator.setApplicationInstance(instance);
    }

    public void logOut(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginPage.fxml"));
            Scene scene = new Scene(root);
            Node button =(Node) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addSupplier(ActionEvent event){
        facilitator.changeWindow(event, "/fxml/addSupplierPage.fxml");
    }
    public void addProduct(ActionEvent e) {
        facilitator.changeWindow(e, "/fxml/addProductPage.fxml");
    }
    public void addCategory(ActionEvent e) {
        facilitator.changeWindow(e,"/fxml/categoryPage.fxml");
    }
}
