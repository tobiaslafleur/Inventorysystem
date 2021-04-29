package View;

import Controller.Main;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ApplicationPage {
    private static ApplicationPage instance;
    private GUIFacilitator facilitator;
    //Product table and columns
    @FXML private TableView<Product> infoTable;
    @FXML private TableColumn<Product, String> colName;
    @FXML private TableColumn<Product, String> colStock;
    @FXML private TableColumn<Product, BigDecimal> colPrice;
    @FXML private TableColumn<Product, String> colCategory;
    @FXML private TableColumn<Product, String> colShelf;
    @FXML private TableColumn<Product, String> colSupplier;
    @FXML private TableColumn<Product, BigDecimal> colCost;

    @FXML public void initialize() {
        instance = this;
        facilitator = Main.getInstance().getFacilitator();
        setInstance();
        initColumns();
        updateTable();
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

    public void initColumns() {
        colName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        colStock.setCellValueFactory(new PropertyValueFactory<Product, String>("stock"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("price"));
        colCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        colShelf.setCellValueFactory(new PropertyValueFactory<Product, String>("shelfPosition"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<Product, String>("supplier"));
        colCost.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("cost"));
    }
    public void updateTable() {
        ArrayList<Product> productList = facilitator.getProductList();
        ObservableList<Product> list = FXCollections.observableArrayList();

        list.addAll(productList);
        infoTable.setItems(list);
    }
}
