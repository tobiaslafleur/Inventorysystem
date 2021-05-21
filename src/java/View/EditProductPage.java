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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class EditProductPage {
    private GUIFacilitator facilitator;
    @FXML TextField name;
    @FXML TextField quantity;
    @FXML TextField price;
//    @FXML TextField shelfPosition;
    @FXML TextField cost;
    @FXML ComboBox<Product> products;
    @FXML ComboBox<Category> categories;
    @FXML ComboBox<String> shelves;


    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();
        fillComboBoxes();

        products.setOnAction(actionEvent -> fillText(products.getValue()));

        errorHandling();
    }

    private void errorHandling() {
        name.setOnKeyTyped(actionEvent -> {
            if(name.getText().isEmpty()) {
                name.setStyle("-fx-border-color: #974F4F;");
            } else {
                name.setStyle("-fx-border-color: #1F701D;");
            }
        });

        quantity.setOnKeyTyped(actionEvent -> {
            if(!ProductErrorHandling.isStockValid(quantity.getText())) {
                quantity.setStyle("-fx-border-color: #974F4F;");
            } else {
                quantity.setStyle("-fx-border-color: #1F701D;");
            }
        });

        price.setOnKeyTyped(actionEvent -> {
            if(!ProductErrorHandling.isPriceValid(price.getText())) {
                price.setStyle("-fx-border-color: #974F4F;");
            } else {
                price.setStyle("-fx-border-color: #1F701D;");
            }
        });

        cost.setOnKeyTyped(actionEvent -> {
            if(!ProductErrorHandling.isCostValid(cost.getText())) {
                cost.setStyle("-fx-border-color: #974F4F;");
            } else {
                cost.setStyle("-fx-border-color: #1F701D;");
            }
        });
    }

    private void fillText(Product prod) {
        name.setPromptText(prod.getName());
        quantity.setPromptText(String.valueOf(prod.getStock()));
        price.setPromptText(String.valueOf(prod.getPrice()));
        cost.setPromptText(String.valueOf(prod.getCost()));
        categories.setPromptText(prod.getCategory());
        shelves.setPromptText(prod.getShelfPosition());
    }

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
    }
    public void updateProduct(ActionEvent e) {
        int id = 0;
        String name = null;
        int quantity = 0;
        int categoryID = 0;
        BigDecimal price = null;
        String shelf = null;
        BigDecimal cost = null;

        if(products.getValue() != null) {
            id = products.getValue().getProductID();
        }
        if(!this.name.getText().equals("")) {
            name = this.name.getText();
        }
        if(!this.quantity.getText().equals("")) {
            quantity = Integer.parseInt(this.quantity.getText());
        }
        if(categories.getValue() != null) {
            categoryID = categories.getValue().getID();
        }
        if(!this.price.getText().equals("")) {
            price = new BigDecimal(this.price.getText());
        }
        if(shelves.getValue() != null) {
            shelf = shelves.getValue();
        }
        if(!this.cost.getText().equals("")) {
            cost = new BigDecimal(this.cost.getText());
        }

        facilitator.updateProduct(id, name, quantity, categoryID, price, shelf,  cost);
        facilitator.updateProductTable();
        facilitator.closeSecondStage(e);
    }

    public void cancel(ActionEvent event) {
        facilitator.closeSecondStage(event);
    }
}
