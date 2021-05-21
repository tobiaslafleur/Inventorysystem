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
                name.setStyle("-fx-border-color: #974F4F;");
                lblName.setText(Language.getProdErrName());
            } else {
                name.setStyle("-fx-border-color: #1F701D;");
                lblName.setText("");
            }
        });

        stock.setOnKeyTyped(actionEvent -> {
            if(!ProductErrorHandling.isStockValid(stock.getText())) {
                stock.setStyle("-fx-border-color: #974F4F;");
                lblStock.setText(Language.getProdErrStock());
            } else {
                stock.setStyle("-fx-border-color: #1F701D;");
                lblStock.setText("");
            }
        });

        price.setOnKeyTyped(actionEvent -> {
            if(!ProductErrorHandling.isPriceValid(price.getText())) {
                price.setStyle("-fx-border-color: #974F4F;");
                lblPrice.setText(Language.getProdErrPrice());
            } else {
                price.setStyle("-fx-border-color: #1F701D;");
                lblPrice.setText("");
            }
        });

        cost.setOnKeyTyped(actionEvent -> {
            if(!ProductErrorHandling.isCostValid(cost.getText())) {
                cost.setStyle("-fx-border-color: #974F4F;");
                lblCost.setText(Language.getProdErrCost());
            } else {
                cost.setStyle("-fx-border-color: #1F701D;");
                lblCost.setText("");
            }
        });

        categories.setOnAction(actionEvent -> {
            categories.setStyle("-fx-border-color: #1F701D;");
        });

        suppliers.setOnAction(actionEvent -> {
            suppliers.setStyle("-fx-border-color: #1F701D;");
        });

        shelves.setOnAction(actionEvent -> {
            shelves.setStyle("-fx-border-color: #1F701D;");
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
        String name = "";

        //Märkligaste skiten ever
        try{
            name = this.name.getText();
        } catch(NullPointerException ex) {
            ex.printStackTrace();
        }

        try{
            shelf = shelves.getValue();
        } catch(NullPointerException ex) {
            ex.printStackTrace();
        }

        try{
            categoryID = String.valueOf(categories.getValue().getID());
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        try {
            supplierID = String.valueOf(suppliers.getValue().getId());
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        boolean allOk;
        try{
            allOk = ProductErrorHandling.errorHandling(name, stock.getText(), price.getText(), cost.getText(), categories.getValue().getName(), shelf, suppliers.getValue().getName());
        } catch (NullPointerException exc) {
            allOk = false;
            exc.printStackTrace();
        }

        if(allOk) {
            facilitator.addProduct(name, stock.getText(), price.getText(), categoryID, shelf, supplierID, cost.getText());
            facilitator.updateProductTable();
            facilitator.closeSecondStage(e);
        } else {

            if(!ProductErrorHandling.isNameValid(name)) {
                this.name.setStyle("-fx-border-color: #974F4F;");
            } else {
                this.name.setStyle("-fx-border-color: #1F701D;");
            }

            if(!ProductErrorHandling.isStockValid(stock.getText())) {
                stock.setStyle("-fx-border-color: #974F4F;");
            } else {
                stock.setStyle("-fx-border-color: #1F701D;");
            }

            if(!ProductErrorHandling.isPriceValid(price.getText())) {
                price.setStyle("-fx-border-color: #974F4F;");
            } else {
                price.setStyle("-fx-border-color: #1F701D;");
            }

            if(!ProductErrorHandling.isCostValid(cost.getText())) {
                cost.setStyle("-fx-border-color: #974F4F;");
            } else {
                cost.setStyle("-fx-border-color: #1F701D;");
            }

            if(!ProductErrorHandling.isCategoryValid(categoryID)) {
                categories.setStyle("-fx-border-color: #974F4F;");
            } else {
                categories.setStyle("-fx-border-color: #1F701D;");
            }

            if(!ProductErrorHandling.isShelfValid(shelf)) {
                shelves.setStyle("-fx-border-color: #974F4F;");
            } else {
                shelves.setStyle("-fx-border-color: #1F701D;");
            }

            if(!ProductErrorHandling.isSupplierValid(supplierID)) {
                suppliers.setStyle("-fx-border-color: #974F4F;");
            } else {
                suppliers.setStyle("-fx-border-color: #1F701D;");
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
