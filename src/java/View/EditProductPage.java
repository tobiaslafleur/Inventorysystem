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
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.math.BigDecimal;

public class EditProductPage {
    private GUIFacilitator facilitator;
    @FXML TextField name;
    @FXML TextField quantity;
    @FXML TextField price;
    @FXML TextField cost;
    @FXML ComboBox<Product> products;
    @FXML ComboBox<Category> categories;
    @FXML ComboBox<String> shelves;
    @FXML ComboBox<Supplier> suppliers;

    @FXML Label lblEditName;
    @FXML Label lblEditPrice;
    @FXML Label lblEditCost;
    @FXML Label lblEditStock;


    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();
        fillComboBoxes();

        errorHandling();
    }

    private void errorHandling() {
        name.setOnKeyTyped(actionEvent -> {
            if(name.getText().isEmpty()) {
                name.setStyle("-fx-border-color: #974F4F;");
                lblEditName.setText(Language.getProdErrName());
            } else {
                name.setStyle("-fx-border-color: #1F701D;");
                lblEditName.setText("");
            }
        });

        quantity.setOnKeyTyped(actionEvent -> {
            if(!ProductErrorHandling.isStockValid(quantity.getText())) {
                quantity.setStyle("-fx-border-color: #974F4F;");
                lblEditStock.setText(Language.getProdErrStock());
            } else {
                quantity.setStyle("-fx-border-color: #1F701D;");
                lblEditStock.setText("");
            }
        });

        price.setOnKeyTyped(actionEvent -> {
            if(!ProductErrorHandling.isPriceValid(price.getText())) {
                price.setStyle("-fx-border-color: #974F4F;");
                lblEditPrice.setText(Language.getProdErrPrice());
            } else {
                price.setStyle("-fx-border-color: #1F701D;");
                lblEditPrice.setText("");
            }
        });

        cost.setOnKeyTyped(actionEvent -> {
            if(!ProductErrorHandling.isCostValid(cost.getText())) {
                cost.setStyle("-fx-border-color: #974F4F;");
                lblEditCost.setText(Language.getProdErrCost());
            } else {
                cost.setStyle("-fx-border-color: #1F701D;");
                lblEditCost.setText("");
            }
        });
    }

//    private void fillText(Product prod) {
//        name.setPromptText(prod.getName());
//        quantity.setPromptText(String.valueOf(prod.getStock()));
//        price.setPromptText(String.valueOf(prod.getPrice()));
//        cost.setPromptText(String.valueOf(prod.getCost()));
//        categories.setPromptText(prod.getCategory());
//        shelves.setPromptText(prod.getShelfPosition());
//    }

    public void fillComboBoxes() {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        productList.addAll(facilitator.getProductList());
        products.setItems(productList);

        ObservableList<Category> categoryList = FXCollections.observableArrayList();
        categoryList.addAll(facilitator.getCategoryList());
        categories.setItems(categoryList);

        ObservableList<String> shelfList = FXCollections.observableArrayList();
        shelfList.addAll(facilitator.getShelfList());
        shelves.setItems(shelfList);
    }

    public void productSelection(ActionEvent event) {
        if(products.getValue() != null) {
            name.setText(products.getValue().getName());
            quantity.setText(String.valueOf(products.getValue().getStock()));
            price.setText(String.valueOf(products.getValue().getPrice()));
            cost.setText(String.valueOf(products.getValue().getCost()));
            Category category = new Category(products.getValue().getCategory(), 1);
            categories.setValue(category);
            shelves.setValue(products.getValue().getShelfPosition());
        }

        name.setStyle("-fx-border-color: black;");
        quantity.setStyle("-fx-border-color: black;");
        price.setStyle("-fx-border-color: black;");
        cost.setStyle("-fx-border-color: black;");
        categories.setStyle("-fx-border-color: black;");
        shelves.setStyle("-fx-border-color: black;");
        suppliers.setStyle("-fx-border-color: black;");

        lblEditCost.setText("");
        lblEditPrice.setText("");
        lblEditStock.setText("");
        lblEditName.setText("");
    }

    public void updateProduct(ActionEvent e) {
        int id = 0;
        String name = null;
        int categoryID = 0;
        String shelf = null;

        if(products.getValue() != null) {
            id = products.getValue().getProductID();
        }

        if(!this.name.getText().equals("")) {
            name = this.name.getText();
        }

        if(categories.getValue() != null) {
            categoryID = categories.getValue().getID();
        }

        if(shelves.getValue() != null) {
            shelf = shelves.getValue();
        }

        boolean allOk;
        try{
            allOk = ProductErrorHandling.updateErrorHandling(id, name, quantity.getText(), price.getText(), cost.getText(), categories.getValue().getName(), shelf);
        } catch (NullPointerException exc) {
            allOk = false;
            exc.printStackTrace();
        }

        if(allOk) {
            int stock = Integer.parseInt(quantity.getText());
            BigDecimal price = new BigDecimal(this.price.getText());
            BigDecimal cost = new BigDecimal(this.cost.getText());

            facilitator.updateProduct(id, name, stock, categoryID, price, shelf,  cost);
            facilitator.updateProductTable();
            facilitator.closeSecondStage(e);
        }
    }

    public void cancel(ActionEvent event) {
        facilitator.closeSecondStage(event);
    }
}
