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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    @FXML private TableColumn<Product, Integer> colID;
    @FXML private TableColumn<Product, String> colName;
    @FXML private TableColumn<Product, String> colStock;
    @FXML private TableColumn<Product, BigDecimal> colPrice;
    @FXML private TableColumn<Product, String> colCategory;
    @FXML private TableColumn<Product, String> colShelf;
    @FXML private TableColumn<Product, String> colSupplier;
    @FXML private TableColumn<Product, String> colSupplierID;
    @FXML private TableColumn<Product, BigDecimal> colCost;
    @FXML private TextField searchText; 

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

    public void addSupplier(ActionEvent event){
        facilitator.changeWindow(event, "/fxml/addSupplierPage.fxml");
    }

    public void addProduct(ActionEvent e) {
        facilitator.changeWindow(e, "/fxml/addProductPage.fxml");
    }

    public void removeProduct(ActionEvent e) {
        facilitator.changeWindow(e, "/fxml/removeProductPage.fxml");
    }

    public void updateProduct(ActionEvent e) {
        facilitator.changeWindow(e, "/fxml/updateProduct.fxml");
    }

    public void updateSupplier(ActionEvent e) {
        facilitator.changeWindow(e, "/fxml/updateSupplier.fxml");
    }

    public void search() {
        if(searchText.getText() != "") {
           ArrayList<Product> searchList = facilitator.getSearchList(searchText.getText());
            ObservableList<Product> list = FXCollections.observableArrayList();

            list.addAll(searchList);
            infoTable.setItems(list);
        }
    }

    public void addCategory(ActionEvent e) {
        facilitator.changeWindow(e,"/fxml/categoryPage.fxml");
    }

    public void initColumns() {
        colID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productID"));
        colName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        colStock.setCellValueFactory(new PropertyValueFactory<Product, String>("stock"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("price"));
        colCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        colShelf.setCellValueFactory(new PropertyValueFactory<Product, String>("shelfPosition"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<Product, String>("supplier"));
        colSupplierID.setCellValueFactory(new PropertyValueFactory<Product, String>("supplierID"));
        colCost.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("cost"));
    }

    public void updateTable() {
        ArrayList<Product> productList = facilitator.getProductList();
        ObservableList<Product> list = FXCollections.observableArrayList();

        list.addAll(productList);
        infoTable.setItems(list);
    }

    public void logOut(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
            Scene scene = new Scene(root);
            Node button =(Node) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void close(ActionEvent event) {
        System.exit(0);
    }
    public void minimize(ActionEvent event) {
        Stage stage = (Stage)((Hyperlink)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
}
