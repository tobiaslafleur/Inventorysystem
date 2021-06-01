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
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;

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
    private ArrayList<Category> categoryList;
    private ArrayList<Supplier> supplierList;
    @FXML private Hyperlink cancel;
    @FXML Button updateBtn;
    @FXML Label lblTitle;
    @FXML Label lblEditName;
    @FXML Label lblEditPrice;
    @FXML Label lblEditCost;
    @FXML Label lblEditStock;
    @FXML Label lblProduct;


    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();
        fillComboBoxes();

        lblTitle.setText(Language.getProdEditTitle());
        products.setPromptText(Language.getProdCmbBoxEdit());
        name.setPromptText(Language.getProdName());
        quantity.setPromptText(Language.getProdStock());
        price.setPromptText(Language.getProdPrice());
        cost.setPromptText(Language.getProdCost());
        categories.setPromptText(Language.getAppCmbCategory());
        shelves.setPromptText(Language.getProdShelf());
        suppliers.setPromptText(Language.getAppCmbSupplier());
        cancel.setText(Language.getProdCancel());
        updateBtn.setText(Language.getProdEditBtn());
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

        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
        supplierList.addAll(facilitator.getSupplierList());
        suppliers.setItems(supplierList);
    }

    public void productSelection(ActionEvent event) {
        if(products.getValue() != null) {
            lblProduct.setText("");
            products.setStyle("-fx-border-color: #1F701D;");

            name.setText(products.getValue().getName());
            quantity.setText(String.valueOf(products.getValue().getStock()));
            price.setText(String.valueOf(products.getValue().getPrice()));
            cost.setText(String.valueOf(products.getValue().getCost()));
            shelves.setValue(products.getValue().getShelfPosition());

            categoryList = facilitator.getCategoryList();
            Category category = null;
            for(Category c : categoryList) {
                if(c.getName().equals(products.getValue().getCategory())) {
                    category = c;
                    break;
                }
            }

            categories.setValue(category);

            supplierList = facilitator.getSupplierList();
            Supplier supplier = null;
            for(Supplier s : supplierList) {
                if(s.getId() == products.getValue().getSupplierID()) {
                    supplier = s;
                    break;
                }
            }
            suppliers.setValue(supplier);
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
        if(products.getValue() == null) {
            lblProduct.setText(Language.getProdErrProd());
            products.setStyle("-fx-border-color: #974F4F;");
        }
        int id = 0;
        String name = null;
        int categoryID = 0;
        String shelf = null;
        String categoryName = "";
        int supplierID = 0;

        if(products.getValue() != null) {
            id = products.getValue().getProductID();
        }

        if(!this.name.getText().equals("")) {
            name = this.name.getText();
        }

        if(categories.getValue() != null) {
            categoryName = categories.getValue().getName();
            categoryID = categories.getValue().getID();
        }

        if(shelves.getValue() != null) {
            shelf = shelves.getValue();
        }
        if(suppliers.getValue() != null) {
            supplierID = suppliers.getValue().getId();
        }

        boolean allOk;
        try{
            allOk = ProductErrorHandling.updateErrorHandling(id, name, quantity.getText(), price.getText(), cost.getText(), categoryName, shelf);
        } catch (NullPointerException exc) {
            allOk = false;
//            exc.printStackTrace();
        }

        if(allOk) {
            int stock = Integer.parseInt(quantity.getText());
            BigDecimal price = new BigDecimal(this.price.getText());
            BigDecimal cost = new BigDecimal(this.cost.getText());

            facilitator.updateProduct(id, name, stock, categoryID, price, shelf,  cost, supplierID);
            facilitator.updateProductTable();
            facilitator.closeSecondStage(e);
        }
    }

    public void cancel(ActionEvent event) {
        facilitator.closeSecondStage(event);
    }
}
