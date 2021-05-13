package View;

import Controller.Main;
import Model.Category;
import Model.Product;
import Model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.*;
import java.io.File;
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
    //
    @FXML private TextField searchText;
    @FXML private ComboBox<String> tableBox;
    @FXML private Hyperlink removeBtn;
    @FXML private Button editBtn;
    @FXML private Button importCSVBtn;
    //Supplier table and columns
    @FXML private TableView<Supplier> supplierTable;
    @FXML private TableColumn<Supplier, String> supNameCol;
    @FXML private TableColumn<Supplier, String> supPhoneCol;
    @FXML private TableColumn<Supplier, String> supAddressCol;
    @FXML private TableColumn<Supplier, String> supEmailCol;
    //Category table and column
    @FXML private TableView<Category> categoryTable;
    @FXML private TableColumn<Category, String> catNameCol;


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
            facilitator.changeWindow(e, "/fxml/AddSupplierPage.fxml");
        }
        else if(tableBox.getValue().equals("Category")) {
            facilitator.changeWindow(e, "/fxml/AddCategoryPage.fxml");
        } else {
            facilitator.changeWindow(e, "/fxml/AddProductPage.fxml");
        }

    }

    public void remove(ActionEvent e) {
        if(tableBox.getValue().equals("Product")) {
            facilitator.changeWindow(e, "/fxml/DeleteProductPage.fxml");
        }

    }

    public void update(ActionEvent e) {
        if(tableBox.getValue().equals("Product")) {
            facilitator.changeWindow(e, "/fxml/EditProductPage.fxml");
        }
        else if(tableBox.getValue().equals("Supplier")) {
            facilitator.changeWindow(e, "/fxml/EditSupplierPage.fxml");
        }
    }

    //TODO old methods to remove after new GUI implemented
    public void addSupplier(ActionEvent event){
        facilitator.changeWindow(event, "/fxml/.OLDaddSupplierPage.fxml");
    }

    public void updateSupplier(ActionEvent e) {
        facilitator.changeWindow(e, "/fxml/.OLDupdateSupplier.fxml");
    }

    public void addCategory(ActionEvent e) {
        facilitator.changeWindow(e, "/fxml/.OLDcategoryPage.fxml");
    }
    //


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

        supplierTable.setVisible(false);
        categoryTable.setVisible(false);

        ObservableList<String> tables = FXCollections.observableArrayList();
        tables.addAll("Product", "Category", "Supplier");
        tableBox.setValue("Product");
        tableBox.setItems(tables);
    }
    public void initSupplierTable() {
        supNameCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("name"));
        supPhoneCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("phone"));
        supAddressCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("address"));
        supEmailCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("email"));

        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
        supplierList.addAll(facilitator.getSupplierList());
        supplierTable.setItems(supplierList);
    }
    public void initCategoryTable() {
        catNameCol.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));

        ObservableList<Category> categoryList = FXCollections.observableArrayList();
        categoryList.addAll(facilitator.getCategoryList());
        categoryTable.setItems(categoryList);
    }

    public void tableSelection(ActionEvent event) {
        if(tableBox.getValue().equals("Category")) {
            removeBtn.setVisible(false);
            editBtn.setVisible(false);
            infoTable.setVisible(false);
            supplierTable.setVisible(false);
            categoryTable.setVisible(true);
            initCategoryTable();
        }
        else if(tableBox.getValue().equals("Supplier")) {
            removeBtn.setVisible(false);
            editBtn.setVisible(true);
            infoTable.setVisible(false);
            categoryTable.setVisible(false);
            supplierTable.setVisible(true);
            initSupplierTable();
        } else {
            removeBtn.setVisible(true);
            editBtn.setVisible(true);
            infoTable.setVisible(true);
            supplierTable.setVisible(false);
            categoryTable.setVisible(false);
        }
    }

    public void updateTable() {
        ArrayList<Product> productList = facilitator.getProductList();
        ObservableList<Product> list = FXCollections.observableArrayList();

        list.addAll(productList);
        infoTable.setItems(list);
    }
    public void logOut(ActionEvent event) {
        facilitator.changeWindow(event, "/fxml/LoginPage.fxml");
    }
    public void close(ActionEvent event) {
        facilitator.close(event);
    }
    public void minimize(ActionEvent event) {
        facilitator.minimize(event);
    }
    public void CSVImport(ActionEvent event) {
        Stage stage = new Stage();
        stage.setIconified(true);
        FileChooser JF = new FileChooser();
        String filepath = JF.showOpenDialog(stage).getPath();
        facilitator.CSVImport(filepath);
        facilitator.updateProductTable();
    }

}
