package View;

import Controller.Main;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML private ComboBox<String> tableBox;

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



    public void add(ActionEvent e) {
        if(tableBox.getValue().equals("Supplier")) {
            facilitator.changeWindow(e, "/fxml/addSupplierPage.fxml");
        }
        else if(tableBox.getValue().equals("Category")) {
            facilitator.changeWindow(e, "/fxml/categoryPage.fxml");
        } else {
            facilitator.changeWindow(e, "/fxml/AddProduct.fxml");
        }

    }

    public void remove(ActionEvent e) {
        if(tableBox.getValue().equals("Product")) {
            facilitator.changeWindow(e, "/fxml/DeleteProductPage.fxml");
        }

    }

    public void update(ActionEvent e) {
        if(tableBox.getValue().equals("Product")) {
            facilitator.changeWindow(e, "/fxml/updateProductPage.fxml");
        }
        else if(tableBox.getValue().equals("Supplier")) {
            facilitator.changeWindow(e, "/fxml/updateSupplier.fxml");
        }

    }

    public void addSupplier(ActionEvent event){
        facilitator.changeWindow(event, "/fxml/addSupplierPage.fxml");
    }

    public void updateSupplier(ActionEvent e) {
        facilitator.changeWindow(e, "/fxml/updateSupplier.fxml");
    }

    public void addCategory(ActionEvent e) {
        facilitator.changeWindow(e,"/fxml/categoryPage.fxml");
    }

    public void search() {
        if(searchText.getText() != "") {
           ArrayList<Product> searchList = facilitator.getSearchList(searchText.getText());
            ObservableList<Product> list = FXCollections.observableArrayList();

            list.addAll(searchList);
            infoTable.setItems(list);
        }
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

        ObservableList<String> tables = FXCollections.observableArrayList();
        tables.addAll("Product", "Category", "Supplier");
        tableBox.setItems(tables);
    }

    public void updateTable() {
        ArrayList<Product> productList = facilitator.getProductList();
        ObservableList<Product> list = FXCollections.observableArrayList();

        list.addAll(productList);
        infoTable.setItems(list);
    }

    public void logOut(ActionEvent event) {
        facilitator.changeWindow(event, "/fxml/Login.fxml");
    }
    public void close(ActionEvent event) {
        facilitator.close(event);
    }
    public void minimize(ActionEvent event) {
        facilitator.minimize(event);
    }
}
