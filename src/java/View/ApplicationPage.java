package View;

import Controller.Main;
import Model.Category;
import Model.Product;
import Model.Supplier;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ApplicationPage {
    private static ApplicationPage instance;
    private GUIFacilitator facilitator;
    private Stage stage;
    @FXML private AnchorPane TableControls;
    @FXML private AnchorPane dragAnchor;

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
    @FXML private Hyperlink english;
    @FXML private Hyperlink swedish;

    @FXML private Button importCSVBtn;

    //Supplier table and columns
    @FXML private TableView<Supplier> supplierTable;
    @FXML private TableColumn<Supplier, String> supNameCol;
    @FXML private TableColumn<Supplier, String> supPhoneCol;
    @FXML private TableColumn<Supplier, String> supStreetCol;
    @FXML private TableColumn<Supplier, String> supCityCol;
    @FXML private TableColumn<Supplier, String> supCountryCol;
    @FXML private TableColumn<Supplier, String> supEmailCol;
    //Category table and column
    @FXML private TableView<Category> categoryTable;
    @FXML private TableColumn<Category, String> catNameCol;
    @FXML private TableColumn<Category, String> catIDCol;

    @FXML private Hyperlink logOut;
    @FXML private Button accountSettingsBtn;
    @FXML private Button btnShelf;


    private double x = 0, y = 0;

    @FXML public void initialize() {
        instance = this;
        facilitator = Main.getInstance().getFacilitator();
        setInstance();
        fixFocus();
        dragAnchor();
        initProductTable();
        initTableSelection();
        changeLangOnPage();

        searchText.setOnKeyTyped(actionEvent -> {
            search();
            if(searchText.getText().equals("")) {
                initProductTable();
            }
        });

        tableBox.setId("cmb-opt");

        if(Language.getLanguage() == 0) {
            english.setStyle("-fx-font-weight: 800;");
            swedish.setStyle("-fx-font-weight: 100;");
        } else {
            english.setStyle("-fx-font-weight: 100;");
            swedish.setStyle("-fx-font-weight: 800;");
        }

        swedish.setOnAction(actionEvent -> {
            english.setStyle("-fx-font-weight: 100;");
            swedish.setStyle("-fx-font-weight: 800;");
            changeLanguage(actionEvent);
        });

        english.setOnAction(actionEvent -> {
            english.setStyle("-fx-font-weight: 800;");
            swedish.setStyle("-fx-font-weight: 100;");
            changeLanguage(actionEvent);
        });
    }

    @FXML private void anchorPaneClicked() {
        fixFocus();
    }

    private void fixFocus() {
        Platform.runLater(() -> TableControls.requestFocus());
    }

    private void dragAnchor() {
        dragAnchor.setOnMousePressed(((event) -> {
            fixFocus();
            x = event.getX();
            y = event.getY();
        }));

        dragAnchor.setOnMouseDragged(((event) -> {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        }));
    }

    public void setInstance() {
        facilitator.setApplicationInstance(instance);
    }

    public void add(ActionEvent e) {
        if(tableBox.getValue().equals(Language.getAppCmbSupplier())) {
            facilitator.openSecondStage(e, "/fxml/AddSupplierPage.fxml");
        }
        else if(tableBox.getValue().equals(Language.getAppCmbCategory())) {
            facilitator.openSecondStage(e, "/fxml/AddCategoryPage.fxml");
        } else {
            if(facilitator.getCategoryList().isEmpty() || facilitator.getSupplierList().isEmpty()) {
                //Temp
                Alert alert = new Alert(Alert.AlertType.WARNING, "Create a category and supplier before adding a product.");
                alert.setHeaderText("Adding product not available");
                alert.setTitle("Error");
                alert.show();
            } else {

                facilitator.openSecondStage(e, "/fxml/AddProductPage.fxml");
            }
        }
    }

    public void remove(ActionEvent e) {
        if(tableBox.getValue().equals(Language.getAppCmbProd())) {
            facilitator.openSecondStage(e, "/fxml/DeleteProductPage.fxml");
        }

    }

    public void update(ActionEvent e) {
        if(tableBox.getValue().equals(Language.getAppCmbProd())) {
            if(!facilitator.getProductList().isEmpty()) {
                facilitator.openSecondStage(e, "/fxml/EditProductPage.fxml");
            } else {
                //Temp
                Alert alert = new Alert(Alert.AlertType.WARNING, "Add a product before editing a product.");
                alert.setHeaderText("Edit product not available");
                alert.setTitle("Error");
                alert.show();
            }
        }
        else if(tableBox.getValue().equals(Language.getAppCmbSupplier())) {
            facilitator.openSecondStage(e, "/fxml/EditSupplierPage.fxml");
        }
    }

    public void search() {
        if(searchText.getText() != "" && tableBox.getValue().equals(Language.getAppCmbProd())) {
           ArrayList<Product> searchList = facilitator.getProductSearch(searchText.getText());
           ObservableList<Product> list = FXCollections.observableArrayList();

           list.addAll(searchList);
           infoTable.setItems(list);
        }
        else if(searchText.getText() != "" && tableBox.getValue().equals(Language.getAppCmbCategory())) {
            ArrayList<Category> searchList = facilitator.getCategorySearch(searchText.getText());
            ObservableList<Category> list = FXCollections.observableArrayList();

            list.addAll(searchList);
            categoryTable.setItems(list);
        }
        else if(searchText.getText() != "" && tableBox.getValue().equals(Language.getAppCmbSupplier())) {
            ArrayList<Supplier> searchList = facilitator.getSupplierSearch(searchText.getText());
            ObservableList<Supplier> list = FXCollections.observableArrayList();

            list.addAll(searchList);
            supplierTable.setItems(list);
        }
    }
    public void initTableSelection() {
        ObservableList<String> tables = FXCollections.observableArrayList();
        tables.addAll(Language.getAppCmbProd(), Language.getAppCmbCategory(), Language.getAppCmbSupplier());
        tableBox.setValue(Language.getAppCmbProd());
        tableBox.setItems(tables);
    }
    public void initProductTable() {
        colID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productID"));
        colName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        colStock.setCellValueFactory(new PropertyValueFactory<Product, String>("stock"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("price"));
        colCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        colShelf.setCellValueFactory(new PropertyValueFactory<Product, String>("shelfPosition"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<Product, String>("supplier"));
        colSupplierID.setCellValueFactory(new PropertyValueFactory<Product, String>("supplierID"));
        colCost.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("cost"));

        ObservableList<Product> list = FXCollections.observableArrayList();
        list.addAll(facilitator.getProductList());
        infoTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        infoTable.setItems(list);

        supplierTable.setVisible(false);
        categoryTable.setVisible(false);
    }
    public void initSupplierTable() {
        supNameCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("name"));
        supPhoneCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("phone"));
        supStreetCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("street"));
        supCityCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("city"));
        supCountryCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("country"));
        supEmailCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("email"));

        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
        supplierList.addAll(facilitator.getSupplierList());
        supplierTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        supplierTable.setItems(supplierList);
    }
    public void initCategoryTable() {
        catNameCol.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
        catIDCol.setCellValueFactory(new PropertyValueFactory<Category, String>("ID"));

        ObservableList<Category> categoryList = FXCollections.observableArrayList();
        categoryList.addAll(facilitator.getCategoryList());
        categoryTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        categoryTable.setItems(categoryList);
    }

    public void tableSelection(ActionEvent event) {
        if(tableBox.getValue().equals(Language.getAppCmbCategory())) {
            removeBtn.setVisible(false);
            editBtn.setVisible(false);
            infoTable.setVisible(false);
            supplierTable.setVisible(false);
            categoryTable.setVisible(true);
            initCategoryTable();
        }
        else if(tableBox.getValue().equals(Language.getAppCmbSupplier())) {
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
            initProductTable();
        }
    }
    public void addShelf(ActionEvent event) {
        facilitator.openSecondStage(event, "/fxml/AddShelfPage.fxml");
    }
    public void CSVImport() {
        Stage stage = new Stage();
        stage.setIconified(true);
        try {
            FileChooser JF = new FileChooser();
            String filepath = JF.showOpenDialog(stage).getPath();
            facilitator.CSVImport(filepath);
            facilitator.updateProductTable();
        } catch (Exception ignore) {
            //Ignorerar .getPath null value när man stänger FileChooser
        }
    }
    public void accountSettings(ActionEvent event) {
        facilitator.openSecondStage(event, "/fxml/AccountSettingsPage.fxml");
    }

    public void changeLanguage(ActionEvent event) {
        if(event.getSource().equals(english)) {
            if(Language.getLanguage() != 0) {
                facilitator.setLanguage(0);
                changeLangOnPage();
            }
        } else if (event.getSource().equals(swedish)){
            if(Language.getLanguage() != 1) {
                facilitator.setLanguage(1);
                changeLangOnPage();
            }
        }
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

    private void changeLangOnPage(){
        //Prod-Columns
        colID.setText(Language.getAppColId());
        colName.setText(Language.getAppColName());
        colStock.setText(Language.getAppColQuantity());
        colPrice.setText(Language.getAppColPrice());
        colCategory.setText(Language.getAppColCategory());
        colShelf.setText(Language.getAppColShelf());
        colSupplier.setText(Language.getAppColSupplier());
        colSupplierID.setText(Language.getAppColSupplierId());
        colCost.setText(Language.getAppColCost());

        //Category-Columns
        catIDCol.setText(Language.getAppColCatId());
        catNameCol.setText(Language.getAppColCatName());

        //Supplier-Columns
        supCityCol.setText(Language.getAppColSupCity());
        supNameCol.setText(Language.getAppColSupName());
        supPhoneCol.setText(Language.getAppColSupPhone());
        supStreetCol.setText(Language.getAppColSupStreet());
        supCountryCol.setText(Language.getAppColSupCountry());
        supEmailCol.setText(Language.getAppColSupEmail());

        //Buttons
        btnShelf.setText(Language.getAppAddShelf());
        accountSettingsBtn.setText(Language.getAppSettings());
        importCSVBtn.setText(Language.getAppImport());

        //Labels
        logOut.setText(Language.getAppLogout());

        //ComboBox
        ObservableList<String> tables = FXCollections.observableArrayList();
        tables.addAll(Language.getAppCmbProd(), Language.getAppCmbCategory(), Language.getAppCmbSupplier());
        tableBox.setValue(Language.getAppCmbProd());
        tableBox.setItems(tables);

        //Search
        searchText.setPromptText(Language.getAppSearch());
    }
}