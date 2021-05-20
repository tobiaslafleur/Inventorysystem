package View;

import Controller.ErrorHandling.ProductErrorHandling;
import Controller.Main;
import Model.Category;
import Model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

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

    /**
     * Initializes the connection between this class and GUIFacilitator.
     */
    @FXML public void initialize(){
        facilitator = Main.getInstance().getFacilitator();
        facilitator.setAddProductInstance(this);
        fillComboBoxes();

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
        String categoryID = String.valueOf(categories.getValue().getID());
        String supplierID = String.valueOf(suppliers.getValue().getId());
        String shelf = shelves.getValue();

        ArrayList<String> warnings = ProductErrorHandling.errorHandling(stock.getText(), price.getText(), cost.getText());

        if(warnings == null) {
            facilitator.addProduct(name.getText(), stock.getText(), price.getText(), categoryID, shelf, supplierID, cost.getText());
            facilitator.updateProductTable();
            facilitator.closeSecondStage(e);
        } else {
            if(!ProductErrorHandling.isStockOk()) {
                stock.setStyle("-fx-border-color: #974F4F;");
            } else {
                stock.setStyle("-fx-border-color: #1F701D;");
            }

            if(!ProductErrorHandling.isPriceOk()) {
                price.setStyle("-fx-border-color: #974F4F;");
            } else {
                price.setStyle("-fx-border-color: #1F701D;");
            }

            if(!ProductErrorHandling.isCostOk()) {
                cost.setStyle("-fx-border-color: #974F4F;");
            } else {
                cost.setStyle("-fx-border-color: #1F701D;");
            }

            name.setStyle("-fx-border-color: #1F701D;");
            categories.setStyle("-fx-border-color: #1F701D;");
            suppliers.setStyle("-fx-border-color: #1F701D;");
            shelves.setStyle("-fx-border-color: #1F701D;");

            StringBuilder strBuilder = new StringBuilder();
            for(String s : warnings) {
                strBuilder.append(s).append("\n");
            }
            String str = strBuilder.toString();
            JOptionPane.showMessageDialog(null, str);
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
