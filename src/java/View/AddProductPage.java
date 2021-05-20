package View;

import Controller.ErrorHandling.ProductErrorHandling;
import Controller.Main;
import Model.Category;
import Model.Product;
import Model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Page where the user adds a product.
 */
public class AddProductPage {
    private GUIFacilitator facilitator;
    @FXML private TextField name;
    @FXML private TextField stock;
    @FXML private TextField price;
    @FXML private ComboBox<Category> categories;
    @FXML private ComboBox<String> shelves;
    @FXML private ComboBox<Supplier> suppliers;
    @FXML private TextField cost;
    @FXML private Hyperlink addCategoryBtn;
    @FXML private Hyperlink addShelfBtn;
    @FXML private Hyperlink hyperCancel;
    @FXML private Label lblTitle;
    @FXML private Button addProduct;

    @FXML private Label lblName;
    @FXML private Label lblStock;
    @FXML private Label lblPrice;
    @FXML private Label lblCost;



    /**
     * Initializes the connection between this class and GUIFacilitator.
     */
    @FXML public void initialize(){
        facilitator = Main.getInstance().getFacilitator();
        facilitator.setAddProductInstance(this);
        fillComboBoxes();

        categories.setId("cmb-cat");
        shelves.setId("cmb-she");
        suppliers.setId("cmb-sup");

        lblTitle.setText(Language.getProdTitle());
        addProduct.setText(Language.getProdAdd());
        hyperCancel.setText(Language.getProdCancel());
        name.setPromptText(Language.getProdName());
        stock.setPromptText(Language.getProdStock());
        price.setPromptText(Language.getProdPrice());
        categories.setPromptText(Language.getProdCategory());
        shelves.setPromptText(Language.getProdShelf());
        suppliers.setPromptText(Language.getProdSupplier());
        cost.setPromptText(Language.getProdCost());

        name.setOnKeyTyped(actionEvent -> {
            if(name.getText().isEmpty()) {
                name.setStyle("-fx-border-color: #EB5D5D;");
                lblName.setText(Language.getProdErrName());
            } else {
                name.setStyle("-fx-border-color: #8EFF8B;");
                lblName.setText("");
            }
        });

        stock.setOnKeyTyped(actionEvent -> {
            if(!ProductErrorHandling.isStockValid(stock.getText())) {
                stock.setStyle("-fx-border-color: #EB5D5D;");
                lblStock.setText(Language.getProdErrStock());
            } else {
                stock.setStyle("-fx-border-color: #8EFF8B;");
                lblStock.setText("");
            }
        });

        price.setOnKeyTyped(actionEvent -> {
            if(!ProductErrorHandling.isPriceValid(price.getText())) {
                price.setStyle("-fx-border-color: #EB5D5D;");
                lblPrice.setText(Language.getProdErrPrice());
            } else {
                price.setStyle("-fx-border-color: #8EFF8B;");
                lblPrice.setText("");
            }
        });

        cost.setOnKeyTyped(actionEvent -> {
            if(!ProductErrorHandling.isCostValid(cost.getText())) {
                cost.setStyle("-fx-border-color: #EB5D5D;");
                lblCost.setText(Language.getProdErrCost());
            } else {
                cost.setStyle("-fx-border-color: #8EFF8B;");
                lblCost.setText("");
            }
        });
    }

    public void fillComboBoxes() {
        new FillCombos().start();
//        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
//        supplierList.addAll(facilitator.getSupplierList());
//        suppliers.setItems(supplierList);
//
//        ObservableList<Category> categoryList = FXCollections.observableArrayList();
//        categoryList.addAll(facilitator.getCategoryList());
//        categories.setItems(categoryList);
//
//        ObservableList<String> shelfList = FXCollections.observableArrayList();
//        shelfList.addAll(facilitator.getShelfList());
//        shelves.setItems(shelfList);
    }

    /**
     * Adds a product to the database.
     * @param e     Event that triggers the method.
     */
    public void addProduct(ActionEvent e) {
        String categoryID = "";
        String supplierID = "";
        String shelf = "";

        try{
            categoryID = String.valueOf(categories.getValue().getID());
            supplierID = String.valueOf(suppliers.getValue().getId());
            shelf = shelves.getValue();
        } catch(NullPointerException ex) {
            ex.printStackTrace();
        }

        boolean allOk;
        try{
            allOk = ProductErrorHandling.errorHandling(stock.getText(), price.getText(), cost.getText(), categories.getValue().getName(), shelves.getValue(), suppliers.getValue().getName());
        } catch (NullPointerException exc) {
            allOk = false;
            exc.printStackTrace();
        }

        if(allOk) {
            facilitator.addProduct(name.getText(), stock.getText(), price.getText(), categoryID, shelf, supplierID, cost.getText());
            facilitator.updateProductTable();
            facilitator.closeSecondStage(e);
        } else {
            if(!ProductErrorHandling.isNameValid(name.getText())) {
                stock.setStyle("-fx-border-color: #EB5D5D;");
            } else {
                stock.setStyle("-fx-border-color: #8EFF8B;");
            }

            if(!ProductErrorHandling.isStockValid(stock.getText())) {
                stock.setStyle("-fx-border-color: #EB5D5D;");
            } else {
                stock.setStyle("-fx-border-color: #8EFF8B;");
            }

            if(!ProductErrorHandling.isPriceValid(price.getText())) {
                price.setStyle("-fx-border-color: #EB5D5D;");
            } else {
                price.setStyle("-fx-border-color: #8EFF8B;");
            }

            if(!ProductErrorHandling.isCostValid(cost.getText())) {
                cost.setStyle("-fx-border-color: #EB5D5D;");
            } else {
                cost.setStyle("-fx-border-color: #8EFF8B;");
            }

            if(!ProductErrorHandling.isCategoryValid(categoryID)) {
                categories.setStyle("-fx-background-color: #EB5D5D, #EB5D5D, #EB5D5D, #EB5D5D;");
            } else {
                categories.setStyle("-fx-background-color: #8EFF8B, #8EFF8B, #8EFF8B, #8EFF8B;");
            }

            if(!ProductErrorHandling.isShelfValid(shelf)) {
                shelves.setStyle("-fx-background-color: #EB5D5D, #EB5D5D, #EB5D5D, #EB5D5D;");
            } else {
                shelves.setStyle("-fx-background-color: #8EFF8B, #8EFF8B, #8EFF8B, #8EFF8B;");
            }

            if(!ProductErrorHandling.isSupplierValid(supplierID)) {
                suppliers.setStyle("-fx-background-color: #EB5D5D, #EB5D5D, #EB5D5D, #EB5D5D;");
            } else {
                suppliers.setStyle("-fx-background-color: #8EFF8B, #8EFF8B, #8EFF8B, #8EFF8B;");
            }
        }
    }

    /**
     * Cancels the page and goes back to ApplicationPage.
     * @param event  Event that triggers the method.
     */
    public void cancel(ActionEvent event) {
        facilitator.closeSecondStage(event);
    }

    public void add(ActionEvent event) {
        if(event.getSource().equals(addCategoryBtn)) {
            facilitator.openThirdStage(event, "/fxml/AddCategoryPage.fxml");
        }
        else if(event.getSource().equals(addShelfBtn)) {
            facilitator.openThirdStage(event, "/fxml/AddShelfPage.fxml");
        } else {
            facilitator.openThirdStage(event, "/fxml/AddSupplierPage.fxml");
        }
    }

    private class FillCombos  extends Thread {
        public void run() {
            ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
            supplierList.addAll(facilitator.getSupplierList());
            suppliers.setItems(supplierList);

            ObservableList<Category> categoryList = FXCollections.observableArrayList();
            categoryList.addAll(facilitator.getCategoryList());
            categories.setItems(categoryList);

            ObservableList<String> shelfList = FXCollections.observableArrayList();
            shelfList.addAll(facilitator.getShelfList());
            shelves.setItems(shelfList);
        }
    }
}
