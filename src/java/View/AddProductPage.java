package View;

import Controller.ErrorHandling.ProductErrorHandling;
import Controller.Main;
import Model.Category;
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
    @FXML private Label lblTitle;
    @FXML private Hyperlink hyperCancel;
    @FXML private Button addProduct;

    /**
     * Initializes the connection between this class and GUIFacilitator.
     */
    @FXML public void initialize(){
        facilitator = Main.getInstance().getFacilitator();
        fillComboBoxes();

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
                //lblName.setText();
            } else {
                name.setStyle("-fx-border-color: #EB5D5D;");
            }
        });

        stock.setOnKeyTyped(actionEvent -> {
            if(!ProductErrorHandling.isStockValid(stock.getText())) {
                stock.setStyle("-fx-border-color: #EB5D5D;");
                //lblStock.setText();
            } else {
                stock.setStyle("-fx-border-color: #EB5D5D;");
            }
        });

        price.setOnKeyTyped(actionEvent -> {
            if(!ProductErrorHandling.isPriceValid(price.getText())) {
                price.setStyle("-fx-border-color: #EB5D5D;");
                //lblPrice.setText();
            } else {
                price.setStyle("-fx-border-color: #EB5D5D;");
            }
        });

        cost.setOnKeyTyped(actionEvent -> {
            if(!ProductErrorHandling.isCostValid(cost.getText())) {
                cost.setStyle("-fx-border-color: #EB5D5D;");
                //lblCost.setText();
            } else {
                cost.setStyle("-fx-border-color: #EB5D5D;");
            }
        });

    }

    public void fillComboBoxes() {
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

    /**
     * Adds a product to the database.
     * @param e     Event that triggers the method.
     */
    public void addProduct(ActionEvent e) {
        String categoryID = String.valueOf(categories.getValue().getID());
        String supplierID = String.valueOf(suppliers.getValue().getId());
        String shelf = shelves.getValue();

        if(ProductErrorHandling.errorHandling(stock.getText(), price.getText(), cost.getText(), categories.getValue().getName(), shelves.getValue(), suppliers.getValue().getName())) {
            facilitator.addProduct(name.getText(), stock.getText(), price.getText(), categoryID, shelf, supplierID, cost.getText());
            facilitator.updateProductTable();
            facilitator.closeSecondStage(e);
        } else {
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

            name.setStyle("-fx-border-color: #1F701D;");
            categories.setStyle("-fx-border-color: #1F701D;");
            suppliers.setStyle("-fx-border-color: #1F701D;");
            shelves.setStyle("-fx-border-color: #1F701D;");
//            shelfPosition.setStyle("-fx-border-color: #1F701D;");
        }
    }

    /**
     * Cancels the page and goes back to ApplicationPage.
     * @param event  Event that triggers the method.
     */
    public void cancel(ActionEvent event) {
        facilitator.closeSecondStage(event);
    }
}
